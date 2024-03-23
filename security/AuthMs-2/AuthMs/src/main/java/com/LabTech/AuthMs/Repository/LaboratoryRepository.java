package com.LabTech.AuthMs.Repository;

import com.LabTech.AuthMs.Models.Laboratory;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface LaboratoryRepository extends MongoRepository<Laboratory, String> {
}
