package com.example.smartlabbff.controller;

import com.example.smartlabbff.dto.*;
import com.example.smartlabbff.fiegn.FiegnAddTubesMs;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/bff/addTube")
public class BFFAddTubesController {

    @Autowired
    private FiegnAddTubesMs fiegnAddTubesMs;

    //list of available analyses
    @CrossOrigin
    @GetMapping("/analyses")
    @ResponseBody
    public List<Analyses> getAllAnalyses() {
        List<Analyses> GetAllAnalyses = fiegnAddTubesMs.getAllAnalyses();
        return GetAllAnalyses;
    }

    //list of available Packages
    @CrossOrigin
    @GetMapping("/setpackages")
    @ResponseBody
    public List<PackagesE> getAllPackages() {
        List<PackagesE> GetAllPackages = fiegnAddTubesMs.getAllPackages();
        return GetAllPackages;
    }

    //list of available labs
    @CrossOrigin
    @PostMapping("/setlabs")
    @ResponseBody
    public ArrayList<labE> alabs(@Valid @RequestBody Analyses analyseid) {
        ArrayList<labE> Alabs = fiegnAddTubesMs.alabs(analyseid);
        return Alabs;
    }

    //Add New Tube in existing Package
    @CrossOrigin
    @PostMapping("/addtube")
    @ResponseBody
    public TubesE SetTube(@Valid @RequestBody AddTube Tube) {
        TubesE setTube = fiegnAddTubesMs.SetTube(Tube);
        return setTube;
    }

    //Add a new Package & Tube
    @CrossOrigin
    @PostMapping("/addNewPackage")
    @ResponseBody
    public TubesE addNewPackage(@Valid @RequestBody AddNewPackage Tube) {
        TubesE AddNewPackage = fiegnAddTubesMs.addNewPackage(Tube);
        return AddNewPackage;
    }


}
