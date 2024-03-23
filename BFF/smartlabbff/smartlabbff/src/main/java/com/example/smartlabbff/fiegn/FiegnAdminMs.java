package com.example.smartlabbff.fiegn;


import com.example.smartlabbff.dto.Analyses;
import com.example.smartlabbff.dto.labE;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.validation.Valid;
import java.util.List;

@FeignClient(value = "AdminMs",url = "http://localhost:8090/admin")
public interface FiegnAdminMs {

    @PostMapping("/addtube")
    @ResponseStatus(HttpStatus.OK)
    public labE AddLab(@RequestBody labE Nlab);


    @PostMapping("/postlabs")
    @ResponseStatus(HttpStatus.OK)
    public List<labE> getAllLabs();

    @PostMapping("/postanalyses")
    @ResponseStatus(HttpStatus.OK)
    public List<Analyses> getAllAnalyses();


    //Add a new analyse
    @PostMapping("/addnewanalyse")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Analyses> AddNewAnalyse(@Valid @RequestBody Analyses AnalyseName);

}
