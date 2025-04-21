package com.sid.journal.repository;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import com.sid.journal.entity.User;

public interface UserRepository extends MongoRepository<User,ObjectId> {
    User findByUserName(String userName);

}
