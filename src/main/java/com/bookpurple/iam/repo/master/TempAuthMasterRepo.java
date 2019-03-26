package com.bookpurple.iam.repo.master;

import com.bookpurple.iam.entity.TempAuthEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/*
 * Created by gauravsharma on 2019-03-10.
 */
@Repository
public interface TempAuthMasterRepo extends JpaRepository<TempAuthEntity, Long> {

    Optional<TempAuthEntity> findByMobileAndCountryCodeAndDeviceIdAndStatus(
            String mobile,
            Integer countryCode, String deviceId, Byte status);
}
