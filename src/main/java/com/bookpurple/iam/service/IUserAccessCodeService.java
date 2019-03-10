package com.bookpurple.iam.service;

import com.bookpurple.iam.bo.UserAccessCodeBo;

/*
 * Created by gauravsharma on 2019-03-10.
 */
public interface IUserAccessCodeService {

    UserAccessCodeBo findUserAccessCode(String userUid, String deviceId, int status);

    void invalidateUserAuthToken(UserAccessCodeBo userAccessCodeBo);
}
