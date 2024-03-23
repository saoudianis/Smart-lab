package com.example.msorderservice.Controller;


import com.example.msorderservice.entity.*;
import com.example.msorderservice.repository.*;
import com.example.msorderservice.services.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@CrossOrigin
@Controller
@RequestMapping("/History")
public class HistoryController {

    @Autowired
    private PackagesHistoryRepo historyPackRepo;
    @Autowired
    private TubeHistoryRepo tubeHistoryRepo;
    @Autowired
    private packageRepo packRepo;

    @Autowired
    private labeRepository labRep;
    @Autowired
    private tubeRepo TRepo;

    @Autowired
    private TanalyseRepo TanalRepo;

    @Autowired
    private AnalysesRepo analysesRepo;
    @Autowired
    private FileDataRepository fileDataRepository;
    @Autowired
    private StorageService storageService;




    ///Display all Packages
    @CrossOrigin
    @PostMapping("/GetPackages")
    @ResponseBody
    public List<PackagesHistoryE> getAllPackages(@Valid @RequestBody PackagesE powner) {

        List<PackagesHistoryE> PackagesList = historyPackRepo.findByPowner(powner.getPowner());
        //System.out.printf(PackagesList.toString());
        //define the return list
        //List<PackagesE> Display_List = new ArrayList<>();
        //List<DisplayPackages> Display_List = null;
        //List<String> Display_List = new ArrayList<String>();


        for (int i = 0; i < PackagesList.size(); i++)
        //for (PackagesE pack : PackagesList)
        {

            // Set Person (Lab)
            PackagesHistoryE person1 = PackagesList.get(i);

            //get lab name
            Long lid = Long.valueOf(person1.getPdistination());
            labE lab_data = labRep.findOneBylid(lid);
            // Update data in the list
            person1.setPdistination(lab_data.getL_name());
            PackagesList.set(i, person1);

        }
        //System.out.printf(PackagesList.toString());
        return PackagesList;
    }



    ///Display all Tubes List
    @CrossOrigin
    @PostMapping ("/GetPTubesList")
    @ResponseBody
    public List<TubeHistoryE> getPackageTubes(@Valid @RequestBody TubesE pid) {

        List<TubeHistoryE> PackageTubes = tubeHistoryRepo.findByPackageid(pid.getPid());
        return PackageTubes;
    }

}
