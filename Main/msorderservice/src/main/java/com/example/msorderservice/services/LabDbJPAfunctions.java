package com.example.msorderservice.services;


import com.example.msorderservice.entity.TubesE;
import com.example.msorderservice.entity.labE;

import com.example.msorderservice.repository.labeRepository;
import com.example.msorderservice.repository.tubeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


import java.util.List;
import java.util.Optional;

@Component
public class LabDbJPAfunctions {
    @Autowired
    private labeRepository labeRep;
    @Autowired
    private tubeRepo tubesE;

    public void savelabe() {
        labE lab = labE.builder()
                .email("jpaaddtest@gmail.com")
                .l_address("jpa add")
                .password("jpa")
                .l_name("jpa first")
                .l_phoneN("0557656578")
                .build();
        labeRep.save(lab);
    }

    public void printlabe(){
        List<labE> labEList = labeRep.findAll();
        System.out.println("labs: " + labEList);
    }


    public void deletelabeById(){
        Long l_id = Long.valueOf(2);
        labeRep.deleteById(l_id);
    }

    public void deletelabeByClass(){
        Long l_id = Long.valueOf(3);
        labE labEList = labeRep.findById(l_id).orElse(null);
        if (labEList != null) {
            labeRep.delete(labEList);
        }
    }

    public void updatelabe(){
        Long l_id = Long.valueOf(4);
        labE user = labeRep.findById(l_id).orElse(null);
        if (user != null) {
            user.setL_name("John");
            labeRep.save(user);
        }
    }

    public Boolean loginlab(labE user){
        //check if there is an account existing
        labE e_lab = labeRep.findByEmail(user.getEmail());

        if (e_lab != null) {
            //represent a value that may or may not be present
            Optional<labE> lab = labeRep.findOneByemailAndPassword(user.getEmail(), user.getPassword());
            //check if present or not
            if (lab.isPresent()) {
                return true;
            //if lab password is wrong
            }else{
                return false;
            }
        //if lab email was wrong or doesn't exist
        }else{
            return false;
        }

    }

    public String savetube(TubesE Tube){
        TubesE tube = TubesE.builder()
                .pid(Tube.getPid())
                .patient(Tube.getPatient())
                .page(Tube.getPage())
                .dlid(Tube.getDlid())
                .build();
        tubesE.save(tube);

        return "SaveTube";

    }




}
