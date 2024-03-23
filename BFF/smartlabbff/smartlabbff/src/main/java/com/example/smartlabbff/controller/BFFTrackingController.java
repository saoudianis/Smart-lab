package com.example.smartlabbff.controller;

import com.example.smartlabbff.dto.PackagesE;
import com.example.smartlabbff.dto.RoadHistoryE;
import com.example.smartlabbff.fiegn.FiegnTrackingMs;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/bff/track")
public class BFFTrackingController {


    @Autowired
    private FiegnTrackingMs fiegnTrackingMs;

    ///Display all Packages Tracking
    @CrossOrigin
    @PostMapping("/GetRoadOfPackages")
    @ResponseBody
    public List<RoadHistoryE> getAllDeliveredPackages(@Valid @RequestBody RoadHistoryE roadH) {
        List<RoadHistoryE> GetAllDeliveredPackages = fiegnTrackingMs.getAllDeliveredPackages(roadH);
        return GetAllDeliveredPackages;
    }



    //Update Status
    @CrossOrigin
    @PutMapping("/UpdateStatusReception/{pid}")
    @ResponseBody
    public ResponseEntity<PackagesE> updateStatus(@PathVariable(value = "pid") Long pid, @Valid @RequestBody PackagesE PackStatus) {

        ResponseEntity<PackagesE> UpdateStatus = fiegnTrackingMs.updateStatus(pid, PackStatus);
        return UpdateStatus;
    }


}
