package com.example.msorderservice.entity;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "Delivers")
public class DeliverE {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "d_id")
    private Long did;
    @Column(name = "firstname")
    private String fname;
    @Column(name = "lastname")
    private String lname;
    @Column(name = "d_phonen")
    private String phone;
    @Column(name = "d_email")
    private String email;
    @Column(name = "d_password")
    private String password;
    @Column(name = "lab_id")
    private String labid;

}
