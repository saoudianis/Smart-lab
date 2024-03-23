package com.example.msorderservice.Controller;


import com.example.msorderservice.entity.PackagesE;
import com.example.msorderservice.entity.RoadHistoryE;
import com.example.msorderservice.repository.RoadHistoryRepo;
import com.example.msorderservice.repository.packageRepo;
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
@RequestMapping("/track")
public class TrackingController {

    @Autowired
    private RoadHistoryRepo roadHistoryRepo;
    @Autowired
    private packageRepo packRepo;




    ///Display all Packages Tracking
    @CrossOrigin
    @PostMapping("/GetRoadOfPackages")
    @ResponseBody
    public List<RoadHistoryE> getAllDeliveredPackages(@Valid @RequestBody RoadHistoryE roadH) {

        List<RoadHistoryE> PackagesList = roadHistoryRepo.findByOwnerid(roadH.getOwnerid());
        System.out.printf(PackagesList.toString());
        return PackagesList;
    }




    //Update Status
    @CrossOrigin
    @PutMapping("/UpdateStatusReception/{pid}")
    @ResponseBody
    public ResponseEntity<PackagesE> updateStatus(@PathVariable(value = "pid") Long pid, @Valid @RequestBody PackagesE PackStatus) {

        System.out.println("put is worked"+ PackStatus.getDid());

        //Add road history
        //get current row
        RoadHistoryE road = roadHistoryRepo.findOneByPid(String.valueOf(pid));

        //Get current date and time
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        String nowDate = dtf.format(now);
        //fill Data
        road.setDater(nowDate);
        //save Status Data
        RoadHistoryE updateRstatus = roadHistoryRepo.save(road);
        if(updateRstatus == null) {
            return ResponseEntity.notFound().build();
        }
       // RoadHistoryE status = roadHistoryRepo.save(roadstatus);
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
