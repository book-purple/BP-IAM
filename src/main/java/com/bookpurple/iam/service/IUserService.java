package com.bookpurple.iam.service;

import com.bookpurple.iam.bo.AuthRequestBo;
import com.bookpurple.iam.bo.UserBo;

/*
 * Created by gauravsharma on 2019-03-10.
 */
public interface IUserService {

    UserBo createUser(AuthRequestBo authRequestBo);

    long updateUser(UserBo userBo);

    UserBo findUser(AuthRequestBo authRequestBo, int status);

    UserBo findUser(String userUid);

}
