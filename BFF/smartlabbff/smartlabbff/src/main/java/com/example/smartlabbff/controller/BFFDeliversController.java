package com.example.smartlabbff.controller;

import com.example.smartlabbff.dto.DeliverE;
import com.example.smartlabbff.fiegn.fiegnDeliverMs;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/bff/Delivers")
public class BFFDeliversController {

    @Autowired
    private fiegnDeliverMs fiegnDeliver;

    @CrossOrigin
    @PostMapping("/getdeliver")
    public DeliverE posttesting(){
        Long did = Long.valueOf(2);
        DeliverE deliverE = fiegnDeliver.GetDeliverData(did);
        //DeliverE deliver = restTemplate.getForObject("http://localhost:8090/Deliver/GetDeliverData/2", DeliverE.class);
        return deliverE;
    }

    //Add new Deliver Account
    @CrossOrigin
    @PostMapping("/AddNewDeliverAccount")
    @ResponseBody
    public ResponseEntity<DeliverE> AddNewDeliverAcc(@Valid @RequestBody DeliverE DeliverData) {
        ResponseEntity<DeliverE> AddDeliverAccount = fiegnDeliver.AddNewDeliverAcc(DeliverData);
        return AddDeliverAccount;
    }

    ///Display all labs
    @CrossOrigin
    @PostMapping("/GetDelivers")
    @ResponseBody
    public List<DeliverE> getAllDelivers(@Valid @RequestBody DeliverE Downer) {
        List<DeliverE> GetAllDeliver = fiegnDeliver.getAllDelivers(Downer);
        return GetAllDeliver;
    }

    //Update Deliver
    @CrossOrigin
    @PutMapping("/UpdateDeliverAccount/{did}")
    @ResponseBody
    public ResponseEntity<DeliverE> updateDeliver(@PathVariable(value = "did") Long pid, @Valid @RequestBody DeliverE DeliverInfo) {
        ResponseEntity<DeliverE> UpdateDeliver = fiegnDeliver.updateDeliver(pid, DeliverInfo);
        return UpdateDeliver;
    }
}
