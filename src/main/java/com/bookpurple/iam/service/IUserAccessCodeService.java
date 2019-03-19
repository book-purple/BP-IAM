package com.bookpurple.iam.service;

import com.bookpurple.iam.bo.UserAccessCodeBo;
import com.bookpurple.iam.bo.UserDeviceBo;

/*
 * Created by gauravsharma on 2019-03-10.
 */
public interface IUserAccessCodeService {

    UserAccessCodeBo createUserAccessCode(UserDeviceBo userDeviceBo, String authToken);

    UserAccessCodeBo findUserAccessCode(String userUid, String deviceId, int status);

    void invalidateUserAuthToken(UserAccessCodeBo userAccessCodeBo);
}
