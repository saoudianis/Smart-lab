package com.example.msorderservice.Controller;


import com.example.msorderservice.Dto.AddTube;
import com.example.msorderservice.entity.Analyses;
import com.example.msorderservice.entity.TubesE;
import com.example.msorderservice.entity.labE;
import com.example.msorderservice.repository.AnalysesRepo;
import com.example.msorderservice.repository.labeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private labeRepository labrepo;

    @Autowired
    private AnalysesRepo analysesRepo;



    //add new lab into db
    @PostMapping("/addtube")
    public labE AddLab(@Valid @RequestBody labE Nlab) {

        System.out.printf(String.valueOf(Nlab));
        labE NewLab = labE.builder()
                        .l_name(Nlab.getL_name())
                        .email(Nlab.getEmail())
                        .l_address(Nlab.getL_address())
                        .l_phoneN(Nlab.getL_phoneN())
                        .password(Nlab.getPassword())
                        .build();
        labE saveLab = labrepo.save(NewLab);
        return  saveLab;
    }


//display all labs
    @PostMapping("/postlabs")
    public List<labE> getAllLabs() {

        List<labE> labs = labrepo.findAll();

        return labs;
    }


    //display all analyses
    @PostMapping("/postanalyses")
    public List<Analyses> getAllAnalyses() {

        List<Analyses> analyses = analysesRepo.findAll();

        return analyses;
    }


    //Add a new analyse
    @PostMapping("/addnewanalyse")
    public ResponseEntity<Analyses> AddNewAnalyse(@Valid @RequestBody Analyses AnalyseName) {

        Analyses newanalyse = Analyses.builder().analysename(AnalyseName.getAnalysename()).build();

        Analyses saveAnalyse = analysesRepo.save(newanalyse);

        if(saveAnalyse == null) {
            return ResponseEntity.notFound().build();
        }
        //DeliverE deliver = analyses.findAll();
        return ResponseEntity.ok(saveAnalyse);

    }


}
