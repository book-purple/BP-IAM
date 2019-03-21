package com.bookpurple.iam.service.impl;

import com.bookpurple.iam.bo.UserBo;
import com.bookpurple.iam.bo.UserDeviceTokenBo;
import com.bookpurple.iam.constant.Constants;
import com.bookpurple.iam.converter.IRequestMapper;
import com.bookpurple.iam.entity.UserDeviceTokenEntity;
import com.bookpurple.iam.repo.master.UserDeviceTokenMasterRepo;
import com.bookpurple.iam.repo.master.UserDeviceTokenSlaveRepo;
import com.bookpurple.iam.service.IUserDeviceTokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/*
 * Created by Gaurav Sharma on 21 Mar 2019
 */
@Service
public class UserDeviceTokenServiceImpl implements IUserDeviceTokenService {

    @Autowired
    private UserDeviceTokenMasterRepo masterRepo;

    @Autowired
    private UserDeviceTokenSlaveRepo slaveRepo;

    @Autowired
    private IRequestMapper requestMapper;

    @Override
    public UserDeviceTokenBo createUserDeviceToken(UserBo userBo, String deviceToken) {
        UserDeviceTokenBo userDeviceTokenBo = UserDeviceTokenBo.builder()
                .userId(userBo.getId())
                .userUId(userBo.getUserUId())
                .deviceId(userBo.getDeviceId())
                .deviceToken(deviceToken)
                .installStatus(Constants.AuthConstants.STATUS_ACTIVE)
                .build();
        UserDeviceTokenEntity userDeviceTokenEntity = requestMapper.userDeviceTokenBoToEntity(userDeviceTokenBo);
        return requestMapper
                .userDeviceTokenEntityToBo(
                        masterRepo.save(userDeviceTokenEntity));
    }

    @Override
    public void updateUserDeviceToken(UserDeviceTokenBo userDeviceTokenBo) {
        if (null == userDeviceTokenBo.getId()) {
            return;
        }
        masterRepo.save(requestMapper.userDeviceTokenBoToEntity(userDeviceTokenBo));
    }

    @Override
    public UserDeviceTokenBo findUserDeviceToken(String userUid, String deviceId) {
        UserDeviceTokenEntity userDeviceTokenEntity = slaveRepo.findUserDeviceTokenByUserUidAndUserDeviceId(userUid,
                deviceId).orElse(null);
        return requestMapper.userDeviceTokenEntityToBo(userDeviceTokenEntity);
    }
}
