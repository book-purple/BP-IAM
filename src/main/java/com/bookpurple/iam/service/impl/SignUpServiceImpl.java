package com.bookpurple.iam.service.impl;

import com.bookpurple.iam.Processor.ISignUpProcessor;
import com.bookpurple.iam.bo.*;
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

    @Autowired
    private ISignUpProcessor signUpProcessor;

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

    @Override
    public SignUpResponseBo doUserSignUp(AuthRequestBo authRequestBo, SignUpRequestBo signUpRequestBo) {
        SignUpResponseBo signUpResponseBo = null;
        if (null == authRequestBo.getOtp()) {
            return null;
        }
        String otp = authRequestBo.getOtp();
        String mobile = authRequestBo.getMobile();
        String deviceId = authRequestBo.getDeviceId();
        String countryCode = authRequestBo.getCountryCode();

        TempAuthBo tempAuthBo = tempAuthService.findTempAuth(authRequestBo, Constants.AuthConstants.AUTH_TOKEN_ACTIVE);
        if (null == tempAuthBo) {
            // sign up API got hit with /opt API
            return null;
        }
        if (otp.equalsIgnoreCase(tempAuthBo.getOtp())) {
            // otp matched... start sign-up service
            signUpResponseBo = signUpProcessor.initiateSignUpProcessor(authRequestBo, signUpRequestBo);
        }
        return null;
    }


}
