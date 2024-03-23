package com.example.msorderservice.Dto;


import lombok.Data;
import org.springframework.stereotype.Component;

import javax.persistence.Column;

@Data
public class AddTube {
    private Long tid;

    private String pid;

    private String patient;

    private String page;

    private String dlid;

    private String analyse;
}
