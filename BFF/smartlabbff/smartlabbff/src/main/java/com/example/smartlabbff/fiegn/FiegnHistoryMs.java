package com.example.smartlabbff.fiegn;


import com.example.smartlabbff.dto.PackagesE;
import com.example.smartlabbff.dto.PackagesHistoryE;
import com.example.smartlabbff.dto.TubeHistoryE;
import com.example.smartlabbff.dto.TubesE;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@FeignClient(value = "HistoryMs",url = "http://localhost:8090/History")
public interface FiegnHistoryMs {

    ///Display all Packages
    @PostMapping("/GetPackages")
    @ResponseStatus(HttpStatus.OK)
    public List<PackagesHistoryE> getAllPackages(@Valid @RequestBody PackagesE powner);

    ///Display all Tubes List
    @PostMapping ("/GetPTubesList")
    @ResponseStatus(HttpStatus.OK)
    public List<TubeHistoryE> getPackageTubes(@Valid @RequestBody TubesE pid);

}
