package com.bookpurple.iam.service.impl;

import com.bookpurple.iam.bo.UserDeviceBo;
import com.bookpurple.iam.converter.IRequestMapper;
import com.bookpurple.iam.entity.UserDeviceEntity;
import com.bookpurple.iam.repo.master.UserDeviceMasterRepo;
import com.bookpurple.iam.repo.slave.UserDeviceSlaveRepo;
import com.bookpurple.iam.service.IUserDeviceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/*
 * Created by gauravsharma on 2019-03-10.
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
}
