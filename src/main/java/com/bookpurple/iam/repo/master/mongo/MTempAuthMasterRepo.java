package com.bookpurple.iam.repo.master.mongo;

import com.bookpurple.iam.entity.mongo.MTempAuthEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/*
 * Created by Gaurav Sharma on 13 Apr 2019
 */
@Repository
public interface MTempAuthMasterRepo extends MongoRepository<MTempAuthEntity, String> {

}
