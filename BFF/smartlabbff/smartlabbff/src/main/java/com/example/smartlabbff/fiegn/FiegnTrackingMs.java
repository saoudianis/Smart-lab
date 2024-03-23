package com.example.smartlabbff.fiegn;
import com.example.smartlabbff.dto.PackagesE;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import com.example.smartlabbff.dto.RoadHistoryE;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@FeignClient(value = "Tracking",url = "http://localhost:8090/track")
public interface FiegnTrackingMs {

    ///Display all Packages Tracking
    @PostMapping("/GetRoadOfPackages")
    @ResponseStatus(HttpStatus.OK)
    public List<RoadHistoryE> getAllDeliveredPackages(@Valid @RequestBody RoadHistoryE roadH);

    //Update Status
    @PutMapping("/UpdateStatusReception/{pid}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<PackagesE> updateStatus(@PathVariable(value = "pid") Long pid, @Valid @RequestBody PackagesE PackStatus);

}
