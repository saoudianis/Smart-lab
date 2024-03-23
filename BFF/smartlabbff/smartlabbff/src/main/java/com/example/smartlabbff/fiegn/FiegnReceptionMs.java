package com.example.smartlabbff.fiegn;

import com.example.smartlabbff.dto.PackagesE;
import com.example.smartlabbff.dto.TubeAnalyseResult;
import com.example.smartlabbff.dto.TubesE;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.IOException;
import java.util.List;

@FeignClient(value = "receptionMs",url = "http://localhost:8090/reception")
public interface FiegnReceptionMs {

    //Display all Packages
    @PostMapping("/GetPackages")
    @ResponseStatus(HttpStatus.OK)
    List<PackagesE> getAllPackages(@Valid @RequestBody PackagesE packDestination);

    ///Bring Analyse Name
    @GetMapping("/GetAnalyseName/{Tid}")
    @ResponseStatus(HttpStatus.OK)
    TubeAnalyseResult GetAnalyseName(@PathVariable(value = "Tid") String Tid);

    ///Upload Results
    @PostMapping("/uploadResult/{Tid}")
    @ResponseStatus(HttpStatus.OK)
    ResponseEntity<?> uploadImage(@PathVariable(value = "Tid") String Tid, @RequestParam("image") MultipartFile file) throws IOException;

    ///DownLoad Results
    @GetMapping("/download/{fileName}")
    @ResponseStatus(HttpStatus.OK)
    ResponseEntity<byte[]> downloadImage(@PathVariable String fileName);

    //Delete Tube Button
    @DeleteMapping("/SubmitResult/{Tid}")
    @ResponseStatus(HttpStatus.OK)
    ResponseEntity<TubesE> SubmitResult(@PathVariable(value = "Tid") Long Tid);


}
