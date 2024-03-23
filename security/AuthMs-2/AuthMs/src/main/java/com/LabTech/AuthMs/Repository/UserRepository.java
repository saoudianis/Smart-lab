package com.LabTech.AuthMs.Repository;

import com.LabTech.AuthMs.Models.User;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<User, String> {
}
