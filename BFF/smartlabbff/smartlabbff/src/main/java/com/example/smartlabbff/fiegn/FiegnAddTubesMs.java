package com.example.smartlabbff.fiegn;

import com.example.smartlabbff.dto.*;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@FeignClient(value = "AddTubesMs",url = "http://localhost:8090/addTube")
public interface FiegnAddTubesMs {

    //list of available analyses
    @GetMapping("/analyses")
    @ResponseStatus(HttpStatus.OK)
    public List<Analyses> getAllAnalyses();

    //list of available Packages
    @GetMapping("/setpackages")
    @ResponseStatus(HttpStatus.OK)
    public List<PackagesE> getAllPackages();

    //list of available labs
    @PostMapping("/setlabs")
    @ResponseStatus(HttpStatus.OK)
    public ArrayList<labE> alabs(@Valid @RequestBody Analyses analyseid);

    //Add New Tube in existing Package
    @PostMapping("/addtube")
    @ResponseStatus(HttpStatus.OK)
    public TubesE SetTube(@Valid @RequestBody AddTube Tube);

    //Add a new Package & Tube
    @PostMapping("/addNewPackage")
    @ResponseStatus(HttpStatus.OK)
    public TubesE addNewPackage(@Valid @RequestBody AddNewPackage Tube);


}
