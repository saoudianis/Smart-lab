package com.example.smartlabbff.controller;

import com.example.smartlabbff.dto.PackagesE;
import com.example.smartlabbff.dto.PackagesHistoryE;
import com.example.smartlabbff.dto.TubeHistoryE;
import com.example.smartlabbff.dto.TubesE;
import com.example.smartlabbff.fiegn.FiegnHistoryMs;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/bff/History")
@CrossOrigin
public class BFFHistoryController {

    @Autowired
    private FiegnHistoryMs fiegnHistoryMs;

    ///Display all Packages
    @CrossOrigin
    @PostMapping("/GetPackages")
    @ResponseBody
    public List<PackagesHistoryE> getAllPackages(@Valid @RequestBody PackagesE powner) {
        List<PackagesHistoryE> GetAllPacks = fiegnHistoryMs.getAllPackages(powner);
        return  GetAllPacks;
    }

    ///Display all Tubes List
    @CrossOrigin
    @PostMapping ("/GetPTubesList")
    @ResponseBody
    public List<TubeHistoryE> getPackageTubes(@Valid @RequestBody TubesE pid) {
        List<TubeHistoryE> GetPackTubes = fiegnHistoryMs.getPackageTubes(pid);
        return GetPackTubes;

    }

}
