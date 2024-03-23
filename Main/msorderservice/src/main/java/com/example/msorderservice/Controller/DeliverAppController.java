package com.example.msorderservice.Controller;

import com.example.msorderservice.entity.DeliverE;
import com.example.msorderservice.entity.PackagesE;
import com.example.msorderservice.entity.TubesE;
import com.example.msorderservice.entity.labE;
import com.example.msorderservice.repository.labeRepository;
import com.example.msorderservice.repository.packageRepo;
import com.example.msorderservice.repository.tubeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("DeliverApp")
public class DeliverAppController {

    @Autowired
    packageRepo packRepo;

    @Autowired
    labeRepository labRep;

    @Autowired
    tubeRepo TRepo;

    ///Display all Packages
    @CrossOrigin
    @PostMapping("/GetPackages")
    public List<PackagesE> getAllPackagesForDApp(@Valid @RequestBody DeliverE DeliverInfo) {

        List<PackagesE> PackagesList = packRepo.findByPownerAndPstatus(DeliverInfo.getLabid(),"1");
        //System.out.printf(PackagesList.toString());
        //define the return list
        //List<PackagesE> Display_List = new ArrayList<>();
        //List<DisplayPackages> Display_List = null;
        //List<String> Display_List = new ArrayList<String>();

        //Set lab name in list
        for (int i = 0; i < PackagesList.size(); i++)
        //for (PackagesE pack : PackagesList)
        {

            // Set Person (Lab)
            PackagesE person1 = PackagesList.get(i);

            //get lab name
            Long lid = Long.valueOf(person1.getPdestination());
            labE lab_data = labRep.findOneBylid(lid);
            // Update data in the list
            person1.setPdestination(lab_data.getL_name());
            PackagesList.set(i, person1);

        }
        //System.out.printf(PackagesList.toString());
        return PackagesList;
    }

    ///Display all labs
    @CrossOrigin
    @PostMapping ("/GetPTubesList")
    public List<TubesE> getPackageTubesDApp(@Valid @RequestBody TubesE pid) {

        List<TubesE> PackageTubes = TRepo.findByPid(pid.getPid());
        return PackageTubes;
    }

    ///Take the package by the Deliver
    @CrossOrigin
    @PostMapping ("/takeP/{pid}")
    public ResponseEntity<PackagesE> TakeThePackage(@PathVariable(value = "pid") Long pid,@Valid @RequestBody DeliverE mrdeliver) {
        //select the package
        PackagesE user = packRepo.findOneBypid(pid);

        if(user == null) {
            return ResponseEntity.notFound().build();
        }
        user.setPstatus("2");
        String deliv = String.valueOf(mrdeliver.getDid());
        user.setDid(deliv);
        PackagesE updatedUser = packRepo.save(user);
        return ResponseEntity.ok(updatedUser);
    }

    ///check if there is a packages staus transport & he's mine
    @CrossOrigin
    @PostMapping ("/Transportcheck")
    public ResponseEntity<PackagesE> TransportCheck(@Valid @RequestBody DeliverE mrdeliver) {
        //selct a package with status transport
        PackagesE PackageT = packRepo.findByDidAndPstatus(mrdeliver.getLabid(),"2");
        if(PackageT == null) {
            return ResponseEntity.notFound().build();}
        return ResponseEntity.ok(PackageT);
    }


    ///Bring the destination address
    @CrossOrigin
    @PostMapping ("/BringDest")
    public ResponseEntity<labE> BringDest(@Valid @RequestBody PackagesE mrpack) {

        //selct a package with status transport
        Long lid = Long.valueOf(mrpack.getPdestination());
        //Long lid = Long.valueOf("1");
        labE LabAddress = labRep.findOneBylid(lid);

        if(LabAddress == null) {
            System.out.printf("its worked");
            return ResponseEntity.notFound().build();}

        LabAddress.setPassword("");
       LabAddress.setEmail("");
        return ResponseEntity.ok(LabAddress);


    }



}
