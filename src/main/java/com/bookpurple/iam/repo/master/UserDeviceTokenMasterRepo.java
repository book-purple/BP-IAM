package com.bookpurple.iam.repo.master;

import com.bookpurple.iam.entity.UserDeviceTokenEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/*
 * Created by Gaurav Sharma on 21 Mar 2019
 */
@Repository
public interface UserDeviceTokenMasterRepo extends JpaRepository<UserDeviceTokenEntity, Long> {
}
