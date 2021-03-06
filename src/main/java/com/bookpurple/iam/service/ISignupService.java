package com.bookpurple.iam.service;

import com.bookpurple.iam.bo.AuthRequestBo;
import com.bookpurple.iam.bo.DeviceTokenRequestBo;
import com.bookpurple.iam.bo.SignUpRequestBo;
import com.bookpurple.iam.bo.SignUpResponseBo;

/*
 * Created by gauravsharma on 2019-02-24.
 */
public interface ISignupService {

    void generateOtp(AuthRequestBo authRequestBo);

    SignUpResponseBo doUserSignUp(AuthRequestBo authRequestBo, SignUpRequestBo signUpRequestBo);

    void handleDeviceTokenRegistration(DeviceTokenRequestBo deviceTokenRequestBo);
}
