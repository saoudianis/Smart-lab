package com.example.smartlabbff.fiegn;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.smartlabbff.dto.DeliverE;

import javax.validation.Valid;
import java.util.List;

@FeignClient(value = "deliverlabms",url = "http://localhost:8090/Deliver/")
public interface fiegnDeliverMs {
    @GetMapping("GetDeliverData/{DeliverID}")
    @ResponseStatus(HttpStatus.OK)
    DeliverE GetDeliverData(@PathVariable(value = "DeliverID") Long Did);

    //Add new Deliver Account
    @PostMapping("/AddNewDeliverAccount")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<DeliverE> AddNewDeliverAcc(@Valid @RequestBody DeliverE DeliverData);

    ///Display all labs
    @PostMapping("/GetDelivers")
    @ResponseStatus(HttpStatus.OK)
    public List<DeliverE> getAllDelivers(@Valid @RequestBody DeliverE Downer);

    //Update Deliver
    @PutMapping("/UpdateDeliverAccount/{did}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<DeliverE> updateDeliver(@PathVariable(value = "did") Long pid, @Valid @RequestBody DeliverE DeliverInfo);


}
