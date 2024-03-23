package com.LabTech.AuthMs.Models;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Builder
@Document(value = "Delivery")
public class Delivery {

    @Id
    private String id;
    @DBRef
    private User deliveryId;
}
