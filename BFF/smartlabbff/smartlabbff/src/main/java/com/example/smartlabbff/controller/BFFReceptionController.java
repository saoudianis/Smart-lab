package com.example.smartlabbff.controller;

import com.example.smartlabbff.dto.PackagesE;
import com.example.smartlabbff.dto.TubeAnalyseResult;
import com.example.smartlabbff.dto.TubesE;
import com.example.smartlabbff.fiegn.FiegnReceptionMs;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.IOException;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/bff/reception")
public class BFFReceptionController {

    @Autowired
    private FiegnReceptionMs fiegnReceptionMs;

    //Display all Packages
    @CrossOrigin
    @PostMapping("/GetPackages")
    @ResponseBody
    public List<PackagesE> getAllPackages(@Valid @RequestBody PackagesE packDestination) {
        List<PackagesE> GetPackeges = fiegnReceptionMs.getAllPackages(packDestination);
        return GetPackeges;
    }

    ///Bring Analyse Name
    @CrossOrigin
    @GetMapping("/GetAnalyseName/{Tid}")
    @ResponseBody
    public TubeAnalyseResult GetAnalyseName(@PathVariable(value = "Tid") String Tid) {
        TubeAnalyseResult getAnalyseName = fiegnReceptionMs.GetAnalyseName(Tid);
        return getAnalyseName;
    }

    ///Upload Results
    @CrossOrigin
    @PostMapping("/uploadResult/{Tid}")
    @ResponseBody
    public ResponseEntity<?> uploadImage(@PathVariable(value = "Tid") String Tid, @RequestParam("image") MultipartFile file) throws IOException {
        ResponseEntity<?> UploadImage = fiegnReceptionMs.uploadImage(Tid, file);
        return UploadImage;
    }

    ///DownLoad Results
    @CrossOrigin
    @GetMapping("/download/{fileName}")
    @ResponseBody
    public ResponseEntity<byte[]> downloadImage(@PathVariable String fileName){
        ResponseEntity<byte[]> DownloadImage = fiegnReceptionMs.downloadImage(fileName);
        return DownloadImage;
    }

    //Delete Tube Button
    @CrossOrigin
    @DeleteMapping("/SubmitResult/{Tid}")
    @ResponseBody
    public ResponseEntity<TubesE> SubmitResult(@PathVariable(value = "Tid") Long Tid) {
        ResponseEntity<TubesE> submitResult = fiegnReceptionMs.SubmitResult(Tid);
        return submitResult;
    }


}
