package com.bookpurple.iam.service.impl;

import com.bookpurple.iam.bo.SignUpRequestBo;
import com.bookpurple.iam.bo.UserBo;
import com.bookpurple.iam.bo.UserDeviceBo;
import com.bookpurple.iam.converter.IRequestMapper;
import com.bookpurple.iam.entity.UserDeviceEntity;
import com.bookpurple.iam.repo.master.UserDeviceMasterRepo;
import com.bookpurple.iam.repo.slave.UserDeviceSlaveRepo;
import com.bookpurple.iam.service.IUserDeviceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/*
 * Created by Gaurav Sharma on 2019-03-10.
 */
@Service
public class UserDeviceServiceImpl implements IUserDeviceService {

    @Autowired
    private UserDeviceMasterRepo userDeviceMasterRepo;

    @Autowired
    private UserDeviceSlaveRepo userDeviceSlaveRepo;

    @Autowired
    private IRequestMapper requestMapper;

    @Override
    public UserDeviceBo findUserDevice(String userUid, String deviceId) {
        UserDeviceEntity userDeviceEntity = userDeviceSlaveRepo
                .findByUserUidAndDeviceIdAndStatus(userUid, deviceId, 1)
                .orElse(null);
        return requestMapper.userDeviceEntityToBo(userDeviceEntity);
    }

    @Override
    public UserDeviceBo createUserDevice(UserBo userBo, SignUpRequestBo signUpRequestBo) {
        UserDeviceBo userDeviceBo = UserDeviceBo.builder()
                .deviceId(userBo.getDeviceId())
                .userId(userBo.getId())
                .userUId(userBo.getUserUId())
                .deviceType(signUpRequestBo.getDeviceType())
                .appVersion(signUpRequestBo.getAppVersion())
                .appVersionCode(signUpRequestBo.getAppVersionCode())
                .build();

        UserDeviceEntity userDeviceEntity = requestMapper.userDeviceBoToEntity(userDeviceBo);
        return requestMapper.userDeviceEntityToBo(userDeviceMasterRepo.save(userDeviceEntity));
    }

    @Override
    public void updateUserDevice(UserDeviceBo userDeviceBo) {
        if (null == userDeviceBo.getId()) {
            // Id must be there
            return;
        }
        userDeviceMasterRepo.save(requestMapper.userDeviceBoToEntity(userDeviceBo));
    }
}
