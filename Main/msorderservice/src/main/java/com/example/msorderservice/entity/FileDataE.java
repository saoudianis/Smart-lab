package com.example.msorderservice.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Type;

import javax.persistence.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "Results")
public class FileDataE {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long fid;
    private String tid;
    private String name;
    private String type;
    @Lob
    //@Type(type = "org.hibernate.type.TextType")
    @Column(name = "filedata")//, columnDefinition = "text"
    private byte[] imageData;

}
