package com.bookpurple.iam.service.impl;

import com.bookpurple.iam.bo.AuthRequestBo;
import com.bookpurple.iam.repo.master.TempAuthMasterRepo;
import com.bookpurple.iam.repo.repo.TempAuthSlaveRepo;
import com.bookpurple.iam.service.ISignupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/*
 * Created by gauravsharma on 2019-02-24.
 */
@Service
public class SignUpServiceImpl implements ISignupService {

    @Autowired
    private TempAuthMasterRepo tempAuthMasterRepo;

    @Autowired
    private TempAuthSlaveRepo tempAuthSlaveRepo;

    @Override
    public String generateOtp(AuthRequestBo authRequestBo) {
        // todo handling for block user

        return null;
    }
}
