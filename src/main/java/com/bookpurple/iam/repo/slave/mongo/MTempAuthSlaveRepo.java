package com.bookpurple.iam.repo.slave.mongo;

import com.bookpurple.iam.entity.mongo.MTempAuthEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/*
 * Created by Gaurav Sharma on 13 Apr 2019
 */
@Repository
public interface MTempAuthSlaveRepo extends MongoRepository<MTempAuthEntity, String> {

    Optional<MTempAuthEntity> findByMobileAndDeviceIdAndCountryCodeAndStatus(String mobile,
                                                                            String deviceId,
                                                                            String country_code,
                                                                            int status);
}
