package com.bookpurple.iam.service.impl;

import com.bookpurple.iam.Processor.ISignUpProcessor;
import com.bookpurple.iam.bo.*;
import com.bookpurple.iam.constant.Constants;
import com.bookpurple.iam.enums.ProfilesEnum;
import com.bookpurple.iam.model.AbstractErrorModel;
import com.bookpurple.iam.repo.master.TempAuthMasterRepo;
import com.bookpurple.iam.repo.slave.TempAuthSlaveRepo;
import com.bookpurple.iam.service.*;
import org.apache.log4j.Logger;
import org.apache.tomcat.util.bcel.Const;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import java.util.Optional;

/*
 * Created by gauravsharma on 2019-02-24.
 */
@Service
public class SignUpServiceImpl implements ISignupService {

    private static Logger logger = Logger.getLogger(SignUpServiceImpl.class);

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
        logger.info("inside generate OTP class");
        invalidateUserAuthToken(authRequestBo);
        deleteTempAuth(authRequestBo);
        String otp = getOtp();
        authRequestBo.setOtp(otp);
        // call UCF service Proxy
        logger.info("Sending OTP to UCF Service Proxy...");
        tempAuthService.createTempAuth(authRequestBo);
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
                    logger.info("user access code does not exist for user: " + userBo.getId());
                }
            } else {
                logger.info("user device does not exist for user: " + userBo.getId());
            }
        } else {
            logger.info("user does not exist for mobile no. : " + authRequestBo.getMobile());
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
            logger.info("SignUp API got hit without /otp API... Maybe a fraud call");
            return null;
        }
        if (tempAuthBo.getCounter() < Constants.AuthConstants.OTP_RESEND_LIMIT &&
                otp.equalsIgnoreCase(tempAuthBo.getOtp())) {
            // otp matched... start sign-up service
            signUpResponseBo = signUpProcessor.initiateSignUpProcessor(authRequestBo, signUpRequestBo);
        } else if (tempAuthBo.getCounter() < 3) {
            logger.info("OTP mismatch, incrementing OTP counter...");
            tempAuthBo.setCounter(tempAuthBo.getCounter() + 1);
            tempAuthService.updateTempAuth(tempAuthBo);
            return buildSignUpErrorResponse(Constants.Errors.INCORRECT_OTP_ERROR_MSG);
        } else {
            logger.info("OTP mismatch, blocking user for retrying...");
            return buildSignUpErrorResponse(Constants.Errors.INCORRECT_OTP_RETRY_REACHED_ERROR_MSG);

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
            logger.info("User does not exist... saving device token for promotion purposes");
            // todo: add device token registration service here
        }
    }

    private SignUpResponseBo buildSignUpErrorResponse(String message) {
        return SignUpResponseBo.builder()
                .abstractErrorModel(new AbstractErrorModel() {

                    @Override
                    public void setError(String error) {
                        super.setError(Constants.Errors.INCORRECT_OTP_ERROR);
                    }

                    @Override
                    public void setErrorMessage(String errorMessage) {
                        super.setErrorMessage(message);
                    }
                })
                .build();
    }
}
