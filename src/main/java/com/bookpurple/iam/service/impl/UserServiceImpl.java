package com.bookpurple.iam.service.impl;

import com.bookpurple.iam.bo.AuthRequestBo;
import com.bookpurple.iam.bo.UserBo;
import com.bookpurple.iam.constant.Constants;
import com.bookpurple.iam.converter.IRequestMapper;
import com.bookpurple.iam.entity.UserEntity;
import com.bookpurple.iam.repo.master.UserMasterRepo;
import com.bookpurple.iam.repo.slave.UserSlaveRepo;
import com.bookpurple.iam.service.IUserService;
import com.bookpurple.iam.util.CommonUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

/*
 * Created by gauravsharma on 2019-03-10.
 */
@Service
public class UserServiceImpl implements IUserService {

    @Autowired
    private UserMasterRepo userMasterRepo;

    @Autowired
    private UserSlaveRepo userSlaveRepo;

    @Autowired
    private IRequestMapper requestMapper;

    @Override
    @Transactional(rollbackOn = Exception.class)
    public UserBo createUser(AuthRequestBo authRequestBo) {
        UserBo userBo = UserBo.builder()
                .userUId(generateUserUid())
                .mobile(authRequestBo.getMobile())
                .deviceId(authRequestBo.getDeviceId())
                .countryCode(authRequestBo.getCountryCode())
                .createdAt(CommonUtils.getDate())
                .modifiedAt(CommonUtils.getDate())
                .status(Constants.AuthConstants.TEMP_AUTH_ACTIVE)
                .build();
        return saveUser(userBo);
    }

    private UserBo saveUser(UserBo userBo) {
        return requestMapper.userEntityToBo(
                userMasterRepo.save(requestMapper.userBoToEntity(userBo)));
    }

    @Override
    public long updateUser(UserBo userBo) {
        UserEntity userEntity = userMasterRepo.save(requestMapper.userBoToEntity(userBo));
        return userEntity.getId();
    }

    @Override
    public UserBo findUser(AuthRequestBo authRequestBo, int status) {
        return requestMapper.userEntityToBo(userSlaveRepo.findByMobileAndDeviceIdAndCountryCodeAndStatus(authRequestBo.getMobile(),
                authRequestBo.getDeviceId(),
                authRequestBo.getCountryCode(),
                status).orElse(null)
        );
    }

    @Override
    public UserBo findUser(String userUid) {
        return requestMapper.userEntityToBo(
                userSlaveRepo.findByUserUId(userUid).orElse(null)
        );
    }

    private String generateUserUid() {
        return RandomStringUtils.randomAlphanumeric(Constants.AuthConstants.USER_ID_LENGTH);
    }
}
