package com.bookpurple.iam.service;

import com.bookpurple.iam.bo.AuthRequestBo;
import com.bookpurple.iam.bo.TempAuthBo;
import com.bookpurple.iam.constant.Constants;
import com.bookpurple.iam.converter.TempAuthRequestMapper;
import com.bookpurple.iam.entity.TempAuthEntity;
import com.bookpurple.iam.repo.master.TempAuthMasterRepo;
import com.bookpurple.iam.repo.repo.TempAuthSlaveRepo;
import com.bookpurple.iam.service.impl.ITempAuthService;
import com.bookpurple.iam.util.CommonUtils;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.Instant;
import java.util.Date;

/*
 * Created by gauravsharma on 2019-03-10.
 */
public class TempAuthServiceImpl implements ITempAuthService {

    @Autowired
    private TempAuthMasterRepo tempAuthMasterRepo;

    @Autowired
    private TempAuthSlaveRepo tempAuthSlaveRepo;

    @Autowired
    private TempAuthRequestMapper tempAuthRequestMapper;

    @Override
    public TempAuthBo createTempAuth(AuthRequestBo authRequestBo) {
        TempAuthBo tempAuthBo = TempAuthBo.builder()
                .mobile(authRequestBo.getMobile())
                .deviceId(authRequestBo.getDeviceId())
                .countryCode(authRequestBo.getCountryCode())
                .otp(authRequestBo.getOtp())
                .expiredAt(CommonUtils.getDateWithAddedMinutes(Constants.AuthConstants.OTP_EXPIRY_OFFSET))
                .createdAt(CommonUtils.getDate())
                .modifiedAt(CommonUtils.getDate())
                .status(Constants.AuthConstants.TEMP_AUTH_ACTIVE)
                .build();
        saveTempAuth(tempAuthBo);
        return tempAuthBo;
    }

    private void saveTempAuth(TempAuthBo tempAuthBo) {
        tempAuthMasterRepo.save(tempAuthRequestMapper.BoToEntity(tempAuthBo));
    }

    @Override
    public TempAuthBo findTempAuth(AuthRequestBo authRequestBo, int status) {
        TempAuthEntity tempAuthEntity = tempAuthSlaveRepo
                .findByMobileAndDeviceIdAndCountryCodeAndStatus(authRequestBo.getMobile(),
                        authRequestBo.getDeviceId(),
                        authRequestBo.getCountryCode(),
                        status)
                .orElse(null);
        return tempAuthRequestMapper.entityToBo(tempAuthEntity);
    }

    @Override
    public void updateTempAuth(TempAuthBo tempAuthBo) {
        tempAuthMasterRepo.save(tempAuthRequestMapper.BoToEntity(tempAuthBo));
    }

    @Override
    public void deleteTempAuth(long id) {
        tempAuthMasterRepo.deleteById(id);
    }
}
