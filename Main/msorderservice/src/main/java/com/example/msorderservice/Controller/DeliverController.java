package com.example.msorderservice.Controller;
import com.example.msorderservice.entity.Analyses;
import com.example.msorderservice.entity.DeliverE;
import com.example.msorderservice.entity.PackagesE;
import com.example.msorderservice.entity.labE;
import com.example.msorderservice.repository.deliverRepo;
import com.example.msorderservice.repository.packageRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@CrossOrigin
@Controller
@RequestMapping("/Deliver")
public class DeliverController {

    @Autowired
    private packageRepo packRepo;
    @Autowired
    private deliverRepo DelRepo;

    //Add new Deliver Account
    @CrossOrigin
    @PostMapping("/AddNewDeliverAccount")
    @ResponseBody
    public ResponseEntity<DeliverE> AddNewDeliverAcc(@Valid @RequestBody DeliverE DeliverData) {
        System.out.println("Deliver working");
        DeliverE saveDeliverData = DeliverE.builder()
                .fname(DeliverData.getFname())
                .lname(DeliverData.getLname())
                .phone(DeliverData.getPhone())
                .email(DeliverData.getEmail())
                .password(DeliverData.getPassword())
                .labid(DeliverData.getLabid())
                .build();
        DeliverE saveDeliver = DelRepo.save(saveDeliverData);
        if(saveDeliver == null) {
            return ResponseEntity.notFound().build();
        }
        //DeliverE deliver = analyses.findAll();
        return ResponseEntity.ok(saveDeliver);
    }



    ///Display all labs
    @CrossOrigin
    @PostMapping("/GetDelivers")
    @ResponseBody
    public List<DeliverE> getAllDelivers(@Valid @RequestBody DeliverE Downer) {

        List<DeliverE> DeliversList = DelRepo.findByLabid(Downer.getLabid());

        return DeliversList;
    }

    ///Display all labs///////////////////////////////////////////////////////////
    @CrossOrigin
    @GetMapping("/GetDeliverData/{DeliverID}")
    @ResponseBody
    public DeliverE GetDeliverData(@PathVariable(value = "DeliverID") Long Did) {


        DeliverE Deliver = DelRepo.findOneByDid(Did);
        return Deliver;
    }

    //Update Deliver
    @CrossOrigin
    @PutMapping("/UpdateDeliverAccount/{did}")
    @ResponseBody
    public ResponseEntity<DeliverE> updateDeliver(@PathVariable(value = "did") Long pid, @Valid @RequestBody DeliverE DeliverInfo) {

        System.out.printf("put is worked");
        DeliverE user = DelRepo.findOneByDid(pid);
        if(user == null) {
            return ResponseEntity.notFound().build();
        }
        user.setFname(DeliverInfo.getFname());
        user.setLname(DeliverInfo.getLname());
        user.setEmail(DeliverInfo.getEmail());
        user.setPhone(DeliverInfo.getPhone());
        user.setPassword(DeliverInfo.getPassword());


        DeliverE updatedUser = DelRepo.save(user);
        return ResponseEntity.ok(updatedUser);
    }



    /*
    //Update Status When deliver take the package
    @CrossOrigin
    @PutMapping("/UpdateStatus/{pid}")
    @ResponseBody
    public ResponseEntity<PackagesE> updateUser(@PathVariable(value = "pid") Long pid, @Valid @RequestBody PackagesE PackStatus) {

        System.out.println("put is worked"+ PackStatus.getDid());

     //Add road history

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
    RoadHistoryE status = roadHistoryRepo.save(roadstatus);
        //change status
        PackagesE user = packRepo.findOneBypid(pid);
        if(user == null) {
            return ResponseEntity.notFound().build();
        }
        user.setPstatus(PackStatus.getPstatus());

        PackagesE updatedUser = packRepo.save(user);
        return ResponseEntity.ok(updatedUser);
    }  */
}
