package com.bookpurple.iam.repo.slave;

import com.bookpurple.iam.entity.UserAccessCodeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/*
 * Created by gauravsharma on 2019-03-10.
 */
@Repository
public interface UserAccessCodeSlaveRepo extends JpaRepository<UserAccessCodeEntity, Long> {

    Optional<UserAccessCodeEntity> findByUserUIdAndDeviceIdAndStatus(String userUid,
                                                                     String deviceId,
                                                                     int status);

    Optional<UserAccessCodeEntity> findByAuthTokenAndStatus(String authToken, int status);

}
