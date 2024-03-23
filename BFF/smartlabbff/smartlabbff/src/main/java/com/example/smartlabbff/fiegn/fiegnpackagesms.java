package com.example.smartlabbff.fiegn;


import com.example.smartlabbff.dto.PackagesE;
import com.example.smartlabbff.dto.TubesE;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@FeignClient(value = "packagesms",url = "http://localhost:8090/packages/")
public interface fiegnpackagesms {

    ///Display all Packages
    @PostMapping("/GetPackages")
    @ResponseStatus(HttpStatus.OK)
    List<PackagesE> getAllPackages(@Valid @RequestBody PackagesE powner);

    ///Display all labs
    @PostMapping ("/GetPTubesList")
    @ResponseStatus(HttpStatus.OK)
    public List<TubesE> getPackageTubes(@Valid @RequestBody TubesE pid);

    //Delete Tube Button
    @DeleteMapping("/DeleteTube/{Tid}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<TubesE> deleteNote(@PathVariable(value = "Tid") Long Tid);

    //Delete Package Button
    @DeleteMapping("/DeletePack/{Packageid}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<PackagesE> deletePack(@PathVariable(value = "Packageid") String Pid);

    //Update Status
    @PutMapping("/UpdateStatus/{pid}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<PackagesE> updateUser(@PathVariable(value = "pid") Long pid, @Valid @RequestBody PackagesE PackStatus);

}


