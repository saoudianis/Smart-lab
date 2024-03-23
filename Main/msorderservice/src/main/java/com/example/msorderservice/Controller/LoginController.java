package com.example.msorderservice.Controller;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import com.example.msorderservice.DbFunctions;
import com.example.msorderservice.entity.labE;
import com.example.msorderservice.repository.labeRepository;
import org.apache.catalina.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@RestController
@CrossOrigin

//start and define session variables
@SessionAttributes({"username", "l_phonen", "l_id"})
@RequestMapping("/login")

public class LoginController {

    @Autowired
    private labeRepository labeRepo;

    DbFunctions db=new DbFunctions();
    Connection conn =db.connect_to_db("tutdb","postgres","mysecretpassword");

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String loginPage(){
        return "login";
    }

/*
    @RequestMapping(value = "/welcome", method = RequestMethod.GET)
    public String welcomePageloademail(@RequestParam String email,ModelMap model){
        //set parameter with GET method
        model.addAttribute("email",email);

        return "welcome";
    } */
    //if there is a post method from login page
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String loginwelcomePage(ModelMap model, @RequestParam String email, @RequestParam String password, Session request) throws SQLException {
        //map var to store the function retuns
        Map<String, String> ex = new HashMap<>();
        //call the function by var's from the user
         ex = db.lab_email_pass_login(conn,"labs",email,password);
         //check if there is a success login
        if (ex.get("exist")=="exist") {


            //send the variables to the page welcome with sessions
            model.put("username",ex.get("l_name"));
            model.put("l_id",ex.get("l_id"));
            model.put("l_phonen",ex.get("l_phonen"));
            //send email var with the url
            return "redirect:/welcome?email="+email;
        }
        //if "notexist" in last if statement
            //send an error msg to login page
        model.put("errorMsg","There is a wrong info please verifie ..!");
        return "login";
    }

//Get lab info by email
    @CrossOrigin
    @PostMapping("/getlab")
    @ResponseBody
    public labE LabData(@Valid @RequestBody String Lemail){
        labE responselab = labeRepo.findByEmail(Lemail);
        return responselab;
    }



}
