package com.example.smartlabbff.controller;


import com.example.smartlabbff.dto.DeliverE;
import com.example.smartlabbff.dto.PackagesE;
import com.example.smartlabbff.fiegn.fiegnDeliverMs;
import com.example.smartlabbff.fiegn.fiegnpackagesms;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController

public class testcontroller {

    //@Autowired
    //RestTemplate restTemplate;

    @Autowired
    private fiegnDeliverMs fiegnms;
    @Autowired
    private fiegnpackagesms fiegnpackagesms;

    @CrossOrigin
    @PostMapping("/getdeliver")
    public DeliverE posttesting(){
        Long did = Long.valueOf(2);
        DeliverE deliverE = fiegnms.GetDeliverData(did);
        //DeliverE deliver = restTemplate.getForObject("http://localhost:8090/Deliver/GetDeliverData/2", DeliverE.class);
        return deliverE;
    }

    ///Display all Packages
    @CrossOrigin
    @PostMapping("/GetPackages")
    public List<PackagesE> getAllPackages(@Valid @RequestBody PackagesE powner) {

        System.out.printf(powner.getPowner());

        List<PackagesE> PackagesList = fiegnpackagesms.getAllPackages(powner);
        return PackagesList;
    }

    /*
    @CrossOrigin
    @PostMapping("/setlabs")
    public PackagesE postdatatesting(){

        System.out.printf("post is working!!!!!!!!!!");
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        //MultiValueMap<String, String> map= new LinkedMultiValueMap<String, String>();
        //map.add("powner", "1");
        PackagesE request = new PackagesE();
        request.setPowner("1");

        //HttpEntity<PackagesE, String> request = new HttpEntity<PackagesE,String>(map, headers);

        PackagesE response =restTemplate.postForObject("http://localhost:8090/packages/GetPackages"
                                                                , request
                                                                ,PackagesE.class  );
        return response;
    }

*/

}
