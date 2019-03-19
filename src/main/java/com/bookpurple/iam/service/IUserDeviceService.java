package com.bookpurple.iam.service;

import com.bookpurple.iam.bo.SignUpRequestBo;
import com.bookpurple.iam.bo.UserBo;
import com.bookpurple.iam.bo.UserDeviceBo;

/*
 * Created by gauravsharma on 2019-03-10.
 */
public interface IUserDeviceService {

    UserDeviceBo findUserDevice(String userUid, String deviceId);

    UserDeviceBo createUserDevice(UserBo userBo, SignUpRequestBo signUpRequestBo);

    void updateUserDevice(UserDeviceBo userDeviceBo);
}
