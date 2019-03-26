package com.bookpurple.iam.service.impl;

import com.bookpurple.iam.bo.UserAccessCodeBo;
import com.bookpurple.iam.bo.UserDeviceBo;
import com.bookpurple.iam.constant.Constants;
import com.bookpurple.iam.converter.IRequestMapper;
import com.bookpurple.iam.entity.UserAccessCodeEntity;
import com.bookpurple.iam.repo.master.UserAccessCodeMasterRepo;
import com.bookpurple.iam.repo.slave.UserAccessCodeSlaveRepo;
import com.bookpurple.iam.service.IUserAccessCodeService;
import com.bookpurple.iam.util.CommonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/*
 * Created by gauravsharma on 2019-03-10.
 */
@Service
public class UserAccessCodeServiceImpl implements IUserAccessCodeService {

    @Autowired
    private UserAccessCodeMasterRepo userAccessCodeMasterRepo;

    @Autowired
    private UserAccessCodeSlaveRepo userAccessCodeSlaveRepo;

    @Autowired
    private IRequestMapper requestMapper;

    @Autowired
    private UserAccessCodeMasterRepo masterRepo;

    @Autowired
    private UserAccessCodeSlaveRepo slaveRepo;

    @Override
    public UserAccessCodeBo createUserAccessCode(UserDeviceBo userDeviceBo, String authToken) {
        UserAccessCodeBo userAccessCodeBo = UserAccessCodeBo.builder()
                .userId(userDeviceBo.getUserId())
                .userUId(userDeviceBo.getUserUId())
                .authToken(authToken)
                .status(Constants.AuthConstants.AUTH_TOKEN_ACTIVE)
                .createdAt(CommonUtils.getDate())
                .modifiedAt(CommonUtils.getDate())
                .expiredAt(CommonUtils.getDateWithAddedMinutes(Constants.AuthConstants.AUTH_TOKEN_ACTIVE_TIME))
                .build();
        UserAccessCodeEntity userAccessCodeEntity = requestMapper.userAccessCodeBoToEntity(userAccessCodeBo);
        return requestMapper.userAccessCodeEntityToBo(masterRepo.save(userAccessCodeEntity));
    }

    @Override
    public UserAccessCodeBo findUserAccessCode(String userUid, String deviceId, int status) {
        UserAccessCodeEntity userAccessCodeEntity = userAccessCodeSlaveRepo
                .findByUserUIdAndDeviceIdAndStatus(userUid, deviceId, status)
                .orElse(null);
        return requestMapper.userAccessCodeEntityToBo(userAccessCodeEntity);
    }

    @Override
    public void invalidateUserAuthToken(UserAccessCodeBo userAccessCodeBo) {
        userAccessCodeBo.setStatus(Constants.AuthConstants.AUTH_TOKEN_INACTIVE);
        userAccessCodeMasterRepo.save(requestMapper.userAccessCodeBoToEntity(userAccessCodeBo));
    }
}
