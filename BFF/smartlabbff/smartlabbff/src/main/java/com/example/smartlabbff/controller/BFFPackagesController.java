package com.example.smartlabbff.controller;

import com.example.smartlabbff.dto.PackagesE;
import com.example.smartlabbff.dto.TubesE;
import com.example.smartlabbff.fiegn.fiegnpackagesms;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/bff/Packages")
public class BFFPackagesController {

    @Autowired
    private com.example.smartlabbff.fiegn.fiegnpackagesms fiegnpackagesms;

    ///Display all Packages
    @CrossOrigin
    @PostMapping("/GetPackages")
    public List<PackagesE> getAllPackages(@Valid @RequestBody PackagesE powner) {

        System.out.printf(powner.getPowner());

        List<PackagesE> PackagesList = fiegnpackagesms.getAllPackages(powner);
        return PackagesList;
    }

    ///Display all labs
    @CrossOrigin
    @PostMapping ("/GetPTubesList")
    @ResponseBody
    public List<TubesE> getPackageTubes(@Valid @RequestBody TubesE pid) {
        List<TubesE> TubesList = fiegnpackagesms.getPackageTubes(pid);
        return  TubesList;
    }

    //Delete Tube Button
    @CrossOrigin
    @DeleteMapping("/DeleteTube/{Tid}")
    @ResponseBody
    public ResponseEntity<TubesE> deleteNote(@PathVariable(value = "Tid") Long Tid) {
        ResponseEntity<TubesE> DeleteTube = fiegnpackagesms.deleteNote(Tid);
        return DeleteTube;
    }

    //Delete Package Button
    @CrossOrigin
    @DeleteMapping("/DeletePack/{Packageid}")
    @ResponseBody
    public ResponseEntity<PackagesE> deletePack(@PathVariable(value = "Packageid") String Pid) {
        ResponseEntity<PackagesE> DeletePack = fiegnpackagesms.deletePack(Pid);
        return DeletePack;

    }

    //Update Status
    @CrossOrigin
    @PutMapping("/UpdateStatus/{pid}")
    @ResponseBody
    public ResponseEntity<PackagesE> updateUser(@PathVariable(value = "pid") Long pid, @Valid @RequestBody PackagesE PackStatus) {
        ResponseEntity<PackagesE> updatestatus = fiegnpackagesms.updateUser(pid,PackStatus);
        return updatestatus;

    }


}
