package com.example.smartlabbff.fiegn;

import com.example.smartlabbff.dto.labE;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@FeignClient(value = "LoginMs",url = "http://localhost:8090/login")
public interface FiegnLoginMs {

    //Get lab info by email
    @PostMapping("/getlab")
    @ResponseStatus(HttpStatus.OK)
    public labE LabData(@Valid @RequestBody String Lemail);

}
