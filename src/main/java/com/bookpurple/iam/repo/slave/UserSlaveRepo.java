package com.bookpurple.iam.repo.slave;

import com.bookpurple.iam.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/*
 * Created by gauravsharma on 2019-03-10.
 */
@Repository
public interface UserSlaveRepo extends JpaRepository<UserEntity, Long> {

    Optional<UserEntity> findByUserUId(String userUid);

    Optional<UserEntity> findByMobileAndDeviceIdAndCountryCodeAndStatus(String mobile,
                                                                        String deviceId,
                                                                        String countryCode,
                                                                        int status);
}
