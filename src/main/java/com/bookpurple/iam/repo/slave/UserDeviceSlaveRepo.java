package com.bookpurple.iam.repo.slave;

import com.bookpurple.iam.entity.UserDeviceEntity;
import com.bookpurple.iam.interfaces.SlaveRepo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/*
 * Created by gauravsharma on 2019-03-10.
 */
@SlaveRepo
public interface UserDeviceSlaveRepo extends JpaRepository<UserDeviceEntity, Long> {

    Optional<UserDeviceEntity> findByUserUidAndDeviceIdAndStatus(String userUid,
                                                                 String deviceId,
                                                                 int status);

    Optional<UserDeviceEntity> findByUserUidAndStatus(String userUid, int status);
}
