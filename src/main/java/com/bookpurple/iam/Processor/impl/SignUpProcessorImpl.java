package com.bookpurple.iam.Processor.impl;

import com.bookpurple.iam.Processor.Processor;
import com.bookpurple.iam.Processor.ISignUpProcessor;
import com.bookpurple.iam.bo.*;
import com.bookpurple.iam.constant.Constants;
import com.bookpurple.iam.service.ITempAuthService;
import com.bookpurple.iam.service.IUserAccessCodeService;
import com.bookpurple.iam.service.IUserDeviceService;
import com.bookpurple.iam.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

@Processor
public class SignUpProcessorImpl implements ISignUpProcessor {

    @Autowired
    private IUserService userService;

    @Autowired
    private ITempAuthService tempAuthService;

    @Autowired
    private IUserDeviceService userDeviceService;

    @Autowired
    private IUserAccessCodeService userAccessCodeService;

    @Override
    public SignUpResponseBo initiateSignUpProcessor(AuthRequestBo authRequestBo, SignUpRequestBo signUpRequestBo) {
        // check for user
        UserBo userBo = userService.findUser(authRequestBo, Constants.AuthConstants.STATUS_ACTIVE);
        if (null == userBo) {
            // user does not exist... start sign up process
            return doSignUpProcess(authRequestBo, signUpRequestBo);
        }
        return doLoginProcess(userBo);
    }

    // --- Sign up Process ---
    private SignUpResponseBo doSignUpProcess(AuthRequestBo authRequestBo, SignUpRequestBo signUpRequestBo) {
        // create a new user
        UserBo userBo = userService.createUser(authRequestBo);
        // check for user devices ... should not be null
        UserDeviceBo userDeviceBo = userDeviceService.findUserDevice(userBo.getUserUId(),
                userBo.getDeviceId());
        if (null != userDeviceBo) {
            // log here: This should never happen
            return null;
        }
        userDeviceBo = userDeviceService.createUserDevice(userBo, signUpRequestBo);

        UserAccessCodeBo userAccessCodeBo = userAccessCodeService.findUserAccessCode(userBo.getUserUId(),
                userDeviceBo.getDeviceId(), Constants.AuthConstants.AUTH_TOKEN_ACTIVE);
        if (null != userAccessCodeBo) {
            // this should never happen
            return null;
        }
        String authToken = generateAuthToken(authRequestBo);
        return SignUpResponseBo.builder()
                .authToken(authToken)
                .build();
    }

    // --- Login Process ---
    private SignUpResponseBo doLoginProcess(UserBo userBo) {
        SignUpResponseBo signUpResponseBo = null;
        UserDeviceBo userDeviceBo = userDeviceService.findUserDevice(userBo.getUserUId(),
                userBo.getDeviceId());
        if (Optional.ofNullable(userDeviceBo).isPresent()) {
            UserAccessCodeBo userAccessCodeBo = userAccessCodeService.findUserAccessCode(userDeviceBo.getUserUId(),
                    userDeviceBo.getDeviceId(), Constants.AuthConstants.AUTH_TOKEN_ACTIVE);
            if (Optional.ofNullable(userAccessCodeBo).isPresent()) {
                userAccessCodeService.invalidateUserAuthToken(userAccessCodeBo);
            }
        } else {
            userDeviceBo = userDeviceService.createUserDevice(userBo)
        }
        return signUpResponseBo;
    }

    private String generateAuthToken(AuthRequestBo authRequestBo) {
        String authToken = null;
        // use JWT Token machanism for generating the payload
        return authToken;
    }
}
