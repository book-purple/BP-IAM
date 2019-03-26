package com.bookpurple.iam.repo.slave;

import com.bookpurple.iam.entity.UserDeviceEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/*
 * Created by gauravsharma on 2019-03-10.
 */
@Repository
public interface UserDeviceSlaveRepo extends JpaRepository<UserDeviceEntity, Long> {

    Optional<UserDeviceEntity> findByUserUIdAndDeviceIdAndStatus(String userUid,
                                                                 String deviceId,
                                                                 int status);

    Optional<UserDeviceEntity> findByUserUIdAndStatus(String userUid, int status);
}
