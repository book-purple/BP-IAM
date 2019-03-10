package com.bookpurple.iam.repo.slave;

import com.bookpurple.iam.entity.TempAuthEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/*
 * Created by gauravsharma on 2019-03-10.
 */
@Repository
public interface TempAuthSlaveRepo extends JpaRepository<TempAuthEntity, Long> {

    Optional<TempAuthEntity> findByMobileAndDeviceIdAndCountryCodeAndStatus(String mobile,
                                                                            String deviceId,
                                                                            String country_code,
                                                                            int status);
}
