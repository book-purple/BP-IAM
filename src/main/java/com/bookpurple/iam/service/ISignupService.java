package com.bookpurple.iam.service;

import com.bookpurple.iam.bo.AuthRequestBo;

/*
 * Created by gauravsharma on 2019-02-24.
 */
public interface ISignupService {

    public String generateOtp(AuthRequestBo authRequestBo);
}
