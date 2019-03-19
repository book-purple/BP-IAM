package com.bookpurple.iam.Processor;

import com.bookpurple.iam.bo.AuthRequestBo;
import com.bookpurple.iam.bo.SignUpRequestBo;
import com.bookpurple.iam.bo.SignUpResponseBo;

public interface ISignUpProcessor {

    SignUpResponseBo initiateSignUpProcessor(AuthRequestBo authRequestBo,
                                             SignUpRequestBo signUpRequestBo);
}
