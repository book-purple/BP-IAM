package com.bookpurple.iam.repo.master;

import com.bookpurple.iam.entity.TempAuthEntity;
import com.bookpurple.iam.interfaces.MasterRepo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/*
 * Created by gauravsharma on 2019-03-10.
 */
@MasterRepo
public interface TempAuthMasterRepo extends JpaRepository<TempAuthEntity, Long> {

    Optional<TempAuthEntity> findByMobileAndCountryCodeAndPartnerIdAndDeviceIdAndAuthStatus(
            String mobile,
            Integer countryCode, int partnerId, String deviceId, Byte status);
}
