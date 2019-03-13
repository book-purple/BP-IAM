package com.bookpurple.iam.service.impl;

import com.bookpurple.iam.bo.AuthRequestBo;
import com.bookpurple.iam.bo.UserAccessCodeBo;
import com.bookpurple.iam.bo.UserBo;
import com.bookpurple.iam.bo.UserDeviceBo;
import com.bookpurple.iam.constant.Constants;
import com.bookpurple.iam.enums.ProfilesEnum;
import com.bookpurple.iam.repo.master.TempAuthMasterRepo;
import com.bookpurple.iam.repo.slave.TempAuthSlaveRepo;
import com.bookpurple.iam.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import java.util.Optional;

/*
 * Created by gauravsharma on 2019-02-24.
 */
@Service
public class SignUpServiceImpl implements ISignupService {

    @Autowired
    private Environment environment;

    @Autowired
    private TempAuthMasterRepo tempAuthMasterRepo;

    @Autowired
    private TempAuthSlaveRepo tempAuthSlaveRepo;

    @Autowired
    private IUserService userService;

    @Autowired
    private ITempAuthService tempAuthService;

    @Autowired
    private IUserDeviceService userDeviceService;

    @Autowired
    private IUserAccessCodeService userAccessCodeService;

    @Override
    public void generateOtp(AuthRequestBo authRequestBo) {
        // todo handling for block user
        invalidateUserAuthToken(authRequestBo);
        deleteTempAuth(authRequestBo);
        String otp = getOtp();
        // send this otp to UCF layer
    }

    private void invalidateUserAuthToken(AuthRequestBo authRequestBo) {
        UserBo userBo = userService.findUser(authRequestBo,
                Constants.AuthConstants.STATUS_ACTIVE);
        if (null != userBo) {
            UserDeviceBo userDeviceBo = userDeviceService
                    .findUserDevice(userBo.getUserUId(),
                            userBo.getDeviceId());
            if (null != userDeviceBo) {
                UserAccessCodeBo userAccessCodeBo = userAccessCodeService
                        .findUserAccessCode(userDeviceBo.getUserUId(),
                                userDeviceBo.getDeviceId(),
                                Constants.AuthConstants.AUTH_TOKEN_ACTIVE);
                if (null != userAccessCodeBo) {
                    userAccessCodeService.invalidateUserAuthToken(userAccessCodeBo);
                } else {
                    // todo add log here
                }
            } else {
                // todo add log here
            }
        } else {
            // todo: add log here
        }
    }

    private void deleteTempAuth(AuthRequestBo authRequestBo) {
        Optional.ofNullable(tempAuthService.findTempAuth(authRequestBo, Constants.AuthConstants.TEMP_AUTH_ACTIVE))
                .ifPresent(tempAuthBo -> tempAuthService.deleteTempAuth(tempAuthBo.getId()));
    }

    private String getOtp() {
        return String
                .valueOf(ProfilesEnum.getProfilesByEnv(environment.getActiveProfiles()[0])
                        .getGeneratedOtp());
    }
}
