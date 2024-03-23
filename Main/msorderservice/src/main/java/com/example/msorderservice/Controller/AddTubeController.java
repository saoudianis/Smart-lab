package com.example.msorderservice.Controller;



import com.example.msorderservice.DbFunctions;
import com.example.msorderservice.Dto.AddNewPackage;
import com.example.msorderservice.Dto.AddTube;
import com.example.msorderservice.entity.*;
import com.example.msorderservice.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@CrossOrigin
@Controller
//@RequestMapping("/addTube")
public class AddTubeController {

    @Autowired
    private labeRepository labeRep;
    @Autowired
    private AnalysesRepo analyses;
    @Autowired
    private LAnalysesRepo LabAnalyse;
    @Autowired
    private DbFunctions dbFun;
    @Autowired
    private tubeRepo tubesE;
    @Autowired
    private TanalyseRepo tAnalyseE;
    @Autowired
    private packageRepo packageRepo;




    //list of available analyses
    @CrossOrigin
    @GetMapping("/analyses")
    @ResponseBody
    public List<Analyses> getAllAnalyses() {
        System.out.println("get working");
        List<Analyses> AnalysesList = analyses.findAll();
        return AnalysesList;
    }
    //list of available Packages
    @CrossOrigin
    @GetMapping("/setpackages")
    @ResponseBody
    public List<PackagesE> getAllPackages() {
        System.out.println("get working");
        List<PackagesE> PackagesList = packageRepo.findAll();
        return PackagesList;
    }



    //list of available labs
    @CrossOrigin
    @PostMapping("/setlabs")
    @ResponseBody
    public ArrayList<labE> alabs(@Valid @RequestBody Analyses analyseid) {
        //DB connect
        DbFunctions db=new DbFunctions();
        Connection conn =db.connect_to_db("tutdb","postgres","mysecretpassword");

        System.out.printf("enterednewpost");
        //get labs id's
        ArrayList<String> labsid = new ArrayList<String>();
        String analyse = String.valueOf(analyseid.getAnalyseid());
        labsid = dbFun.read_labs_by_analyse_id(conn,analyse);
        System.out.printf(String.valueOf(labsid));
        //get labs names
        //HashMap<String, String> labnames = new HashMap<>();


        ArrayList<labE> LabElements = new ArrayList<labE>();
        LabElements = dbFun.read_labs_name_by_id(conn,labsid);
        System.out.printf("our List : "+String.valueOf(LabElements));
        //convert
        for (int i = 0; i < LabElements.size(); i++) {
            System.out.println("ID :" + LabElements.get(i).getLid());

        }




        return LabElements;

    }



    //Add New Tube in existing Package
    @CrossOrigin
    @PostMapping("/addtube")
    @ResponseBody
    public TubesE SetTube(@Valid @RequestBody AddTube Tube) {
        System.out.println("get working");
        TubesE tube = TubesE.builder()
                .pid(Tube.getPid())
                .patient(Tube.getPatient())
                .page(Tube.getPage())
                .dlid(Tube.getDlid())
                .build();
        TubesE SaveTube = tubesE.save(tube);
        TAnalyseE analyse = TAnalyseE.builder()
                .tid(String.valueOf(SaveTube.getTid()))
                .analyse(Tube.getAnalyse())
                .build();
        TAnalyseE SaveAnalyse = tAnalyseE.save(analyse);
        return SaveTube;
    }
    //Add a new Package & Tube
    @CrossOrigin
    @PostMapping("/addNewPackage")
    @ResponseBody
    public TubesE addNewPackage(@Valid @RequestBody AddNewPackage Tube) {
        System.out.println("get working");
        //Create New Package
        PackagesE pack = PackagesE.builder()
                .powner(Tube.getPowner())
                .pdestination(Tube.getDlid())
                .did("")
                .pstatus("0")
                .build();
        PackagesE savePackage = packageRepo.save(pack);
        //Create New Tube
        TubesE tube = TubesE.builder()
                .pid(String.valueOf(savePackage.getPid()))
                .patient(Tube.getPatient())
                .page(Tube.getPage())
                .dlid(Tube.getDlid())
                .build();
        TubesE SaveTube = tubesE.save(tube);
        //Add Tube Analyse Type
        TAnalyseE analyse = TAnalyseE.builder()
                .tid(String.valueOf(SaveTube.getTid()))
                .analyse(Tube.getAnalyse())
                .build();

        TAnalyseE SaveAnalyse = tAnalyseE.save(analyse);
        return SaveTube;
    }

}


