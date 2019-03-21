package com.bookpurple.iam.repo.master;

import com.bookpurple.iam.entity.UserEntity;
import com.bookpurple.iam.interfaces.MasterRepo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/*
 * Created by gauravsharma on 2019-03-10.
 */
@MasterRepo
public interface UserMasterRepo extends JpaRepository<UserEntity, Long> {
}
