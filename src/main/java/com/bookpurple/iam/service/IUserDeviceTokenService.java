package com.bookpurple.iam.service;

import com.bookpurple.iam.bo.UserBo;
import com.bookpurple.iam.bo.UserDeviceTokenBo;

/*
 * Created by Gaurav Sharma on 21 Mar 2019
 */
public interface IUserDeviceTokenService {

    UserDeviceTokenBo createUserDeviceToken(UserBo userBo, String deviceToken);

    void updateUserDeviceToken(UserDeviceTokenBo userDeviceTokenBo);

    UserDeviceTokenBo findUserDeviceToken(String userUid, String deviceId);
}
