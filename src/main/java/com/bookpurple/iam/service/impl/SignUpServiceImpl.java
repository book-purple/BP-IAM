package com.bookpurple.iam.service.impl;

import com.bookpurple.iam.service.ISignupService;
import org.springframework.stereotype.Service;

/*
 * Created by gauravsharma on 2019-02-24.
 */
@Service
public class SignUpServiceImpl implements ISignupService {

    @Override
    public String generateOtp() {
        return "1234";
    }
}
