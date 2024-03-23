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
@Table(name = "packages")
public class PackagesE {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "p_id")
        private Long pid;
        @Column(name = "p_owner")
        private String powner;
        @Column(name = "p_destination")
        private String pdestination;
        @Column(name = "d_id") //deliver
        private String did;
        @Column(name = "p_status")
        private String pstatus;

    }


