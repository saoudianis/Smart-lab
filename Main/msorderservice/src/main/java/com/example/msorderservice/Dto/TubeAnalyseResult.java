package com.example.msorderservice.Dto;

import lombok.Data;

import javax.persistence.Column;

@Data
public class TubeAnalyseResult {
    private Long analyseid;

    private String analysename;
    private String result;
}
