package com.example.msorderservice.Controller;


import com.example.msorderservice.DbFunctions;
import com.example.msorderservice.models.lab;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Connection;
import java.util.ArrayList;

@Controller
public class WelcomeController {

    /*
    @RequestMapping(value = "/welcome", method = RequestMethod.GET)
    public String welcomepagewithemail(@RequestParam String email,ModelMap model) {

        //set parameter with GET method
        model.addAttribute("email",email);
        return "welcome";
    }

     */


    @RequestMapping(value = "/welcome", method = RequestMethod.GET)
    public String welcomepage(@RequestParam String email,ModelMap model){

        //make list of items
        ArrayList<lab> LabElements = new ArrayList<lab>();
        //DB connection
        DbFunctions db=new DbFunctions();
        Connection conn =db.connect_to_db("tutdb","postgres","mysecretpassword");
        //add new item
        LabElements = db.read_labs(conn);
        /*
        LabElements.add(new lab("1","anis","250","pass",
                "anis@gmail.com","0556877698"));
        LabElements.add(new lab("2","saoudi","250","pass",
                "saoudi@gmail.com","05559248068"));
                */
        System.out.println(LabElements);
        // send variables
        model.addAttribute("labs",LabElements);
        model.addAttribute("email",email);

        return "welcome";
    }





    }
