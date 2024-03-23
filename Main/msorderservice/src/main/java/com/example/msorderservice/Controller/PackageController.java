package com.example.msorderservice.Controller;

import com.example.msorderservice.entity.PackagesE;
import com.example.msorderservice.entity.RoadHistoryE;
import com.example.msorderservice.entity.TubesE;
import com.example.msorderservice.entity.labE;
import com.example.msorderservice.repository.RoadHistoryRepo;
import com.example.msorderservice.repository.labeRepository;
import com.example.msorderservice.repository.packageRepo;
import com.example.msorderservice.repository.tubeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@CrossOrigin
@Controller
@RequestMapping("/packages")
public class PackageController {

    @Autowired
    private packageRepo packRepo;

    @Autowired
    private labeRepository labRep;
    @Autowired
    private tubeRepo TRepo;
    @Autowired
    private RoadHistoryRepo roadHistoryRepo;


    ///Display all Packages
    @CrossOrigin
    @PostMapping("/GetPackages")
    @ResponseBody
    public List<PackagesE> getAllPackages(@Valid @RequestBody PackagesE powner) {

        List<PackagesE> PackagesList = packRepo.findByPowner(powner.getPowner());
        //System.out.printf(PackagesList.toString());
        //define the return list
        //List<PackagesE> Display_List = new ArrayList<>();
        //List<DisplayPackages> Display_List = null;
        //List<String> Display_List = new ArrayList<String>();


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
    @ResponseBody
    public List<TubesE> getPackageTubes(@Valid @RequestBody TubesE pid) {

        List<TubesE> PackageTubes = TRepo.findByPid(pid.getPid());
        return PackageTubes;
    }

    //Delete Tube Button
    @CrossOrigin
    @DeleteMapping("/DeleteTube/{Tid}")
    @ResponseBody
    public ResponseEntity<TubesE> deleteNote(@PathVariable(value = "Tid") Long Tid) {
        //Check if tube exist
        TubesE tube = TRepo.findOneBytid(Tid);
        if(tube == null) {
            return ResponseEntity.notFound().build();
        }
        //get tube package
        String TubePackageID = tube.getPid();
        //delete Tube
        TRepo.delete(tube);
        //check if package not empty
        TubesE pack = TRepo.findOneBypid(TubePackageID);
        if(pack == null){
            packRepo.deleteById(Long.valueOf(TubePackageID));
        }
        return ResponseEntity.ok().build();
    }



    //Delete Package Button
    @CrossOrigin
    @DeleteMapping("/DeletePack/{Packageid}")
    @ResponseBody
    public ResponseEntity<PackagesE> deletePack(@PathVariable(value = "Packageid") String Pid) {
        System.out.printf("delete package ");
        //Delete all Tubes inside The Package
        Long tube = TRepo.removeBypid(Pid);
        if(tube == null) {
            return ResponseEntity.notFound().build();
        }
        //Delete The Package
        packRepo.deleteById(Long.valueOf(Pid));

        return ResponseEntity.ok().build();
    }
//Update Status
@CrossOrigin
@PutMapping("/UpdateStatus/{pid}")
@ResponseBody
public ResponseEntity<PackagesE> updateUser(@PathVariable(value = "pid") Long pid, @Valid @RequestBody PackagesE PackStatus) {

    System.out.println("put is worked"+ PackStatus.getDid());

    /* //Add road history

        //Get current date and time
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        String nowDate = dtf.format(now);
      //fill Data
    RoadHistoryE roadstatus = RoadHistoryE.builder()
            .pid(String.valueOf(pid))
            .did(PackStatus.getDid())
            .dater(nowDate)
            .build();
    //save Status Data
    RoadHistoryE status = roadHistoryRepo.save(roadstatus); */
    //change status
    PackagesE user = packRepo.findOneBypid(pid);
    if(user == null) {
        return ResponseEntity.notFound().build();
    }
    user.setPstatus(PackStatus.getPstatus());

    PackagesE updatedUser = packRepo.save(user);
    return ResponseEntity.ok(updatedUser);
}




}



















