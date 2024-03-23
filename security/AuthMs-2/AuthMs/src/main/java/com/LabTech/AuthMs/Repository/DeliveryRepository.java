package com.LabTech.AuthMs.Repository;

import com.LabTech.AuthMs.Models.Delivery;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface DeliveryRepository extends MongoRepository<Delivery, String> {
}
