package com.bookpurple.iam.Processor.impl;

import com.bookpurple.iam.Processor.ISignUpProcessor;
import com.bookpurple.iam.interfaces.Processor;
import com.bookpurple.iam.bo.*;
import com.bookpurple.iam.constant.Constants;
import com.bookpurple.iam.service.ITempAuthService;
import com.bookpurple.iam.service.IUserAccessCodeService;
import com.bookpurple.iam.service.IUserDeviceService;
import com.bookpurple.iam.service.IUserService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

@Processor
public class SignUpProcessorImpl implements ISignUpProcessor {

    private static Logger logger = Logger.getLogger(SignUpProcessorImpl.class);
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
        logger.info("Initiating SignUp Processor...");
        UserBo userBo = userService.findUser(authRequestBo, Constants.AuthConstants.STATUS_ACTIVE);
        if (null == userBo) {
            // user does not exist... start sign up process
            return doSignUpProcess(authRequestBo, signUpRequestBo);
        }
        // user found... start login process
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
        return initUserAuthProcess(userBo, userDeviceBo);
    }

    // --- Login Process ---

    /**
     * Login Process
     *
     * @param userBo {@link UserBo}
     * @return Sign-up Response {@link SignUpResponseBo}
     */
    private SignUpResponseBo doLoginProcess(UserBo userBo) {
        SignUpResponseBo signUpResponseBo = null;
        UserDeviceBo userDeviceBo = userDeviceService.findUserDevice(userBo.getUserUId(),
                userBo.getDeviceId());
        UserAccessCodeBo userAccessCodeBo = userAccessCodeService.findUserAccessCode(userDeviceBo.getUserUId(),
                userDeviceBo.getDeviceId(), Constants.AuthConstants.AUTH_TOKEN_ACTIVE);
        if (Optional.ofNullable(userAccessCodeBo).isPresent()) {
            userAccessCodeService.invalidateUserAuthToken(userAccessCodeBo);
        }
        return initUserAuthProcess(userBo, userDeviceBo);
    }

    /**
     * Create User Access code and generate Auth Token
     *
     * @param userBo       {@link UserBo}
     * @param userDeviceBo {@link UserDeviceBo}
     * @return sign-up Response {@link SignUpResponseBo}
     */
    private SignUpResponseBo initUserAuthProcess(UserBo userBo, UserDeviceBo userDeviceBo) {
        UserAccessCodeBo userAccessCodeBo = userAccessCodeService.createUserAccessCode(userDeviceBo,
                generateAuthToken(userBo));
        return SignUpResponseBo.builder()
                .authToken(userAccessCodeBo.getAuthToken())
                .userUid(userBo.getUserUId())
                .build();
    }

    /**
     * Function to generate Auth Token
     *
     * @param userBo {@link UserBo}
     * @return
     */
    private String generateAuthToken(UserBo userBo) {
        String authToken = "dummyAuthTokenForNow";
        // use JWT Token machanism for generating the payload
        return authToken;
    }
}
