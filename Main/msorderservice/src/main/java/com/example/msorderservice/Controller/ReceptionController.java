package com.example.msorderservice.Controller;

import com.example.msorderservice.Dto.TubeAnalyseResult;
import com.example.msorderservice.entity.*;
import com.example.msorderservice.repository.*;
import com.example.msorderservice.services.StorageService;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@CrossOrigin
@Controller
@RequestMapping("/reception")
public class ReceptionController {
    @Autowired
    private  PackagesHistoryRepo historyPackRepo;
    @Autowired
    private  TubeHistoryRepo tubeHistoryRepo;
    @Autowired
    private packageRepo packRepo;

    @Autowired
    private labeRepository labRep;
    @Autowired
    private tubeRepo TRepo;

    @Autowired
    private TanalyseRepo TanalRepo;

    @Autowired
    private AnalysesRepo analysesRepo;
    @Autowired
    private FileDataRepository fileDataRepository;
    @Autowired
    private StorageService storageService;


    //Display all Packages
    @CrossOrigin
    @PostMapping("/GetPackages")
    @ResponseBody
    public List<PackagesE> getAllPackages(@Valid @RequestBody PackagesE packDestination) {

        List<PackagesE> PackagesList = packRepo.findByPdestination(packDestination.getPdestination());
        //System.out.printf(PackagesList.toString());
        //define the return list
        //List<PackagesE> Display_List = new ArrayList<>();
        //List<DisplayPackages> Display_List = null;
        //List<String> Display_List = new ArrayList<String>();


        for (int i = 0; i < PackagesList.size(); i++)
        //for (PackagesE pack : PackagesList)
        {

            // Set Person (Lab)
            PackagesE person1 = PackagesList.get(i);

            //get lab name
            Long lid = Long.valueOf(person1.getPdestination());
            labE lab_data = labRep.findOneBylid(lid);
            // Update data in the list
            person1.setPdestination(lab_data.getL_name());
            PackagesList.set(i, person1);

        }
        //System.out.printf(PackagesList.toString());
        return PackagesList;
    }

    ///Bring Analyse Name
    @CrossOrigin
    @GetMapping("/GetAnalyseName/{Tid}")
    @ResponseBody
    public TubeAnalyseResult GetAnalyseName(@PathVariable(value = "Tid") String Tid) {

        //bring analyse name
        TAnalyseE analyse = TanalRepo.findByTid(Tid);
        Analyses analyseName = analysesRepo.findOneByAnalyseid(Long.valueOf(analyse.getAnalyse()));
        //check if there old result
        FileDataE dataF = fileDataRepository.findByTid(Tid);
        if(dataF != null){
            TubeAnalyseResult data = new TubeAnalyseResult();
            data.setAnalyseid(analyseName.getAnalyseid());
            data.setAnalysename(analyseName.getAnalysename());
            data.setResult(dataF.getName());
            return data;
        }else{
            TubeAnalyseResult data = new TubeAnalyseResult();
            data.setAnalyseid(analyseName.getAnalyseid());
            data.setAnalysename(analyseName.getAnalysename());
            data.setResult("null");
            return data;
        }
        //return analyseName;
    }


///Upload Results
    @CrossOrigin
    @PostMapping("/uploadResult/{Tid}")
    @ResponseBody
    public ResponseEntity<?> uploadImage(@PathVariable(value = "Tid") String Tid,@RequestParam("image") MultipartFile file) throws IOException {
        System.out.printf("upload controller");
        String uploadImage = storageService.uploadpdf(file,Tid);
        return ResponseEntity.status(HttpStatus.OK)
                .body(uploadImage);
    }

    ///DownLoad Results
    @CrossOrigin
    @GetMapping("/download/{fileName}")
    @ResponseBody
    public ResponseEntity<byte[]> downloadImage(@PathVariable String fileName){
        FileDataE imageData= storageService.downloadpdf(fileName);

        if (imageData != null) {

            HttpHeaders headers = new HttpHeaders();
            headers.set("Content-type", "application/pdf");
            headers.set("Content-Disposition", "inline; filename=" + imageData.getName());

            return new ResponseEntity<byte[]>(imageData.getImageData(), headers, HttpStatus.OK);
        }else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }


    }




    //Delete Tube Button
    @CrossOrigin
    @DeleteMapping("/SubmitResult/{Tid}")
    @ResponseBody
    public ResponseEntity<TubesE> SubmitResult(@PathVariable(value = "Tid") Long Tid) {

        System.out.printf("submit result");
        //Submit Data Into History
        //get tube info's
        TubesE tube = TRepo.findOneBytid(Tid);
            //check if tube exist
        if(tube != null){
            //check if package exist In Packages history
            PackagesHistoryE ExistPack = historyPackRepo.findOneBypid(tube.getPid()) ;
            if(ExistPack == null){
                //Create New Package IN Package History
                PackagesE CurrentPack = packRepo.findOneBypid(Long.valueOf(tube.getPid()));
                //build New History package
               // String nowDate = String.valueOf(java.time.LocalDate.now());
                DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
                LocalDateTime now = LocalDateTime.now();
                String nowDate = dtf.format(now);
                System.out.printf(nowDate);
                PackagesHistoryE newHPack = PackagesHistoryE.builder()
                        .pid(String.valueOf(CurrentPack.getPid()))
                        .pdistination(CurrentPack.getPdestination())
                        .did(CurrentPack.getDid())
                        .status(CurrentPack.getPstatus())
                        .date(nowDate)
                        .build();

                PackagesHistoryE saveHP = historyPackRepo.save(newHPack);
                //Then Add New History Tube
                if(saveHP != null){
                    //Build History Tube
                    TubeHistoryE newHTube = TubeHistoryE.builder()
                            .tid(String.valueOf(tube.getTid()))
                            .pname(tube.getPatient())
                            .page(tube.getPage())
                            .distinationlid(tube.getDlid())
                            .packageid(tube.getPid())
                            .date(nowDate)
                            .build();
                    TubeHistoryE saveTH = tubeHistoryRepo.save(newHTube);
                    //get tube package
                    String TubePackageID = tube.getPid();
                    //delete Tube
                    TRepo.delete(tube);
                    //check if package not empty
                    TubesE pack = TRepo.findOneBypid(TubePackageID);
                    if(pack == null){
                        packRepo.deleteById(Long.valueOf(TubePackageID));
                    }

                }
            }else{
                //if Package already EXISTING
                //Add only the tube
                //Build History Tube
                DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
                LocalDateTime now = LocalDateTime.now();
                String nowDate = dtf.format(now);

                TubeHistoryE newHTube = TubeHistoryE.builder()
                        .tid(String.valueOf(tube.getTid()))
                        .pname(tube.getPatient())
                        .page(tube.getPage())
                        .distinationlid(tube.getDlid())
                        .packageid(tube.getPid())
                        .date(nowDate)
                        .build();
                TubeHistoryE saveTH = tubeHistoryRepo.save(newHTube);
                //get tube package
                String TubePackageID = tube.getPid();
                //delete Tube
                TRepo.delete(tube);
                //check if package not empty
                TubesE pack = TRepo.findOneBypid(TubePackageID);
                if(pack == null){
                    packRepo.deleteById(Long.valueOf(TubePackageID));
                }
            }
            return ResponseEntity.ok().build();
        }else{
            return ResponseEntity.notFound().build();
        }

    }




//--------------------------------------------------------------------------------------------------//
    /*

    ///Upload Results
    @CrossOrigin
    @PostMapping("/uploadResult/{Tid}")
    @ResponseBody
    public ResponseEntity<?> uploadImage(@PathVariable(value = "Tid") String Tid,@RequestParam("image") MultipartFile file) throws IOException {

        String uploadImage = storageService.uploadImage(file,Tid);
        return ResponseEntity.status(HttpStatus.OK)
                .body(uploadImage);
    }





    ///DownLoad Results
    @CrossOrigin
    @GetMapping("/download/{fileName}")
    @ResponseBody
    public ResponseEntity<?> downloadImage(@PathVariable String fileName){
        byte[] imageData=
                storageService.downloadImage(fileName);
        return ResponseEntity.status(HttpStatus.OK)
                .contentType(MediaType.valueOf("image/png"))
                .body(imageData);

    }*/


}
