package com.example.smartlabbff.controller;


import com.example.smartlabbff.dto.Analyses;
import com.example.smartlabbff.dto.labE;
import com.example.smartlabbff.fiegn.FiegnAdminMs;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/bff/admin")
@CrossOrigin
public class BFFAdminController {


    @Autowired
    private FiegnAdminMs fiegnAdminMs;

//get all sign labs
    @PostMapping("/postlabs")
    public List<labE> getAllLabs() {

        List<labE> AllLabs = fiegnAdminMs.getAllLabs();

        return AllLabs;
    }

//get all analyses types
    @PostMapping("/postanalyses")
    public List<Analyses> getAllAnalyses() {

        List<Analyses> AllAnalyses = fiegnAdminMs.getAllAnalyses();

        return AllAnalyses;
    }



    //Add a new analyse
    @PostMapping("/addnewanalyse")
    public Analyses AddNewAnalyse(@RequestBody Analyses AnalyseName){

        Analyses saveanalyse = fiegnAdminMs.AddNewAnalyse(AnalyseName).getBody();

        return saveanalyse;

    }


}
