package com.example.msorderservice.Controller;


import com.example.msorderservice.DbFunctions;
//import com.example.msorderservice.models.lab;
import com.example.msorderservice.repository.labeRepository;
import com.example.msorderservice.repository.packageRepo;
import com.example.msorderservice.services.LabDbJPAfunctions;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.*;
import com.example.msorderservice.entity.labE;
import org.springframework.web.client.RestTemplate;

import javax.validation.Valid;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

//@SessionAttributes("labs")
@CrossOrigin(origins = "*", allowedHeaders = "*")
@Controller
public class RestApiController {

    @Autowired
    private labeRepository labeRep;
    @Autowired
    private packageRepo packageRepo;

    @Autowired
    private LabDbJPAfunctions lab_functions;

/*
    @CrossOrigin
    @PostMapping("/users")
    public labE createUser(@Valid @RequestBody labE user) {

        System.out.println(user);
        return labeRep.save(user);
    }
*/


    @CrossOrigin
    @PostMapping("/usersz")
    @ResponseBody
    public Map<String, String> cUser(@Valid @RequestParam String l_email, @Valid @RequestParam String l_password) {

        System.out.println("email: "+ l_email +"pass: "+ l_password);

        HashMap<String, String> mapm = new HashMap<>();
        mapm.put("statu", "success");

        return mapm;

        }

        ///Display all labs
    @CrossOrigin
    @GetMapping("/users")
    @ResponseBody
    public List<labE> getAllUsers() {
        System.out.println("get working");
        List<labE> labEList = labeRep.findAll();
        return labEList;
    }


    @CrossOrigin
    @PostMapping("/logintocken")
    @ResponseBody
    public Map<String, Object> login(@Valid @RequestBody labE user) {
        System.out.println("data:"+ user.getL_name());
        String keycloakServerUrl = "http://localhost:8080/realms/labtech/protocol/openid-connect/token";
        String clientId = "labtech-sprin-boot";
        String clientSecret = "hEcErnSZDvdWqF6d1d4A3zWXKSLLqexM";

        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);


        MultiValueMap<String, String> requestBody = new LinkedMultiValueMap<>();
        requestBody.add("grant_type", "password");
        requestBody.add("client_id", clientId);
        requestBody.add("username", user.getL_name());
        requestBody.add("password", user.getPassword());
        requestBody.add("client_secret", clientSecret);

        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(requestBody, headers);

        return restTemplate.exchange(keycloakServerUrl, HttpMethod.POST, request, Map.class).getBody();
    }
/*
    @Autowired
    private userservice userservice;
    @CrossOrigin
    @PostMapping("/createtocken")
    @ResponseBody
    public String register(@RequestBody labE user) {

            System.out.printf("entered");
            userservice.createKeycloakUser(user);

            return "success";


    } */



    //login a user
    @CrossOrigin
    @PostMapping("/lablogin")
    @ResponseBody
    public Map<String, Object> loginlab(ModelMap model, @Valid @RequestBody labE user) {

        System.out.println("data :"+ user.getEmail());
        //call cheking function
        Boolean check = lab_functions.loginlab(user);
        //chek if info's was right
        if(check){
            //get lab values
            labE labInfos ;
            labInfos = labeRep.findByEmail(user.getEmail());
            //set values map list
            Map<String, Object> map1 = new HashMap<>();
            map1.put("l_email",labInfos.getEmail());
            map1.put("l_id",labInfos.getLid());
            map1.put("l_name",labInfos.getL_name());
            map1.put("status","true");
            //model.addAttribute("labs",map1);

            return map1;

        }//if there is a wrong info's
        else{

            Map<String, Object> map2 = new HashMap<>();
            map2.put("status","false");
            return map2;
        }

    }


    //check if he is a logged user
    /*
    @CrossOrigin
    @GetMapping("/checklablogin")
    @ResponseBody
    public Map<String, Object> checkloginlab(ModelMap model) {
        //@SessionAttribute("labs") Map name
        System.out.printf("check entered:" + model.getAttribute("labs"));
        //System.out.printf("entered");
        Map<String, Object> map2 = new HashMap<>();
        map2.put("lab",model);

        return map2;
    }   */


}
