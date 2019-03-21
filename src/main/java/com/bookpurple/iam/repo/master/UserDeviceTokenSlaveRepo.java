package com.bookpurple.iam.repo.master;

import com.bookpurple.iam.entity.UserDeviceTokenEntity;
import com.bookpurple.iam.interfaces.SlaveRepo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/*
 * Created by Gaurav Sharma on 21 Mar 2019
 */
@SlaveRepo
public interface UserDeviceTokenSlaveRepo extends JpaRepository<UserDeviceTokenEntity, Long>{

    Optional<UserDeviceTokenEntity> findUserDeviceTokenByUserUidAndUserDeviceId(String userUid,
                                                                               String userDeviceId);
}
