package com.bookpurple.iam.repo.slave;

import com.bookpurple.iam.entity.UserDeviceTokenEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/*
 * Created by Gaurav Sharma on 21 Mar 2019
 */
@Repository
public interface UserDeviceTokenSlaveRepo extends JpaRepository<UserDeviceTokenEntity, Long>{

    Optional<UserDeviceTokenEntity> findUserDeviceTokenByUserUIdAndDeviceId(String userUid,
                                                                            String userDeviceId);
}
