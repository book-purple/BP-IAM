package com.bookpurple.iam.service.impl;

import com.bookpurple.iam.Processor.ISignUpProcessor;
import com.bookpurple.iam.bo.*;
import com.bookpurple.iam.constant.Constants;
import com.bookpurple.iam.enums.ProfilesEnum;
import com.bookpurple.iam.model.AbstractErrorModel;
import com.bookpurple.iam.repo.master.TempAuthMasterRepo;
import com.bookpurple.iam.repo.slave.TempAuthSlaveRepo;
import com.bookpurple.iam.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
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

    @Autowired
    private IUserDeviceTokenService deviceTokenService;

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

        TempAuthBo tempAuthBo = tempAuthService.findTempAuth(authRequestBo, Constants.AuthConstants.AUTH_TOKEN_ACTIVE);
        if (null == tempAuthBo) {
            // sign up API got hit with /opt API
            return null;
        }
        if (otp.equalsIgnoreCase(tempAuthBo.getOtp())) {
            // otp matched... start sign-up service
            signUpResponseBo = signUpProcessor.initiateSignUpProcessor(authRequestBo, signUpRequestBo);
        } else {
            signUpResponseBo = SignUpResponseBo.builder()
                    .abstractErrorModel(new AbstractErrorModel() {

                        @Override
                        public void setError(String error) {
                            super.setError(Constants.Errors.INCORRECT_OTP_ERROR);
                        }

                        @Override
                        public void setErrorMessage(String errorMessage) {
                            super.setErrorMessage(Constants.Errors.INCORRECT_OTP_ERROR_MSG);
                        }
                    })
                    .build();
        }
        return signUpResponseBo;
    }

    @Override
    public void handleDeviceTokenRegistration(DeviceTokenRequestBo deviceTokenRequestBo) {
        UserBo userBo = userService.findUser(deviceTokenRequestBo.getUseruUid());
        if (Optional.ofNullable(userBo).isPresent()) {
            UserDeviceTokenBo deviceTokenBo = deviceTokenService.findUserDeviceToken(deviceTokenRequestBo.getUseruUid(),
                    deviceTokenRequestBo.getDeviceToken());
            if (Optional.ofNullable(deviceTokenBo).isPresent()) {
                deviceTokenBo.setDeviceToken(deviceTokenRequestBo.getDeviceToken());
                deviceTokenService.updateUserDeviceToken(deviceTokenBo);
            } else {
                deviceTokenService.createUserDeviceToken(userBo, deviceTokenRequestBo.getDeviceToken());
            }
        } else {
            // user does not exist... save device token in separate DB for product purposes
        }
    }
}
