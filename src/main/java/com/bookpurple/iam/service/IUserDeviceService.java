package com.bookpurple.iam.service;

import com.bookpurple.iam.bo.UserDeviceBo;

/*
 * Created by gauravsharma on 2019-03-10.
 */
public interface IUserDeviceService {

    UserDeviceBo findUserDevice(String userUid, String deviceId);
}
