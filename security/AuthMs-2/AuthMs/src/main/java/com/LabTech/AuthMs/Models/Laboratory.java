package com.LabTech.AuthMs.Models;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Builder
@Document(value = "Laboratory")
public class Laboratory {

    @Id
    private String id;
    @DBRef
    private User laboratoryId;
}
