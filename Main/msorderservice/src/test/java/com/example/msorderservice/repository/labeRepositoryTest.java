package com.example.msorderservice.repository;

import com.example.msorderservice.entity.labE;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class labeRepositoryTest {
    /* @Autowired
    private labeRepository labeRep;

    @Test
    public void savelabe() {
        labE lab = labE.builder()
                .l_email("jpaaddtest@gmail.com")
                .l_address("jpa add")
                .l_password("jpa")
                .l_name("jpa first")
                .l_phoneN("0557656578")
                .build();
        labeRep.save(lab);
    }
    @Test
    public void printlabe(){
        List<labE> labEList = labeRep.findAll();
        System.out.println("labs: " + labEList);
    }

    @Test
    public void deletelabeById(){
        Long l_id = Long.valueOf(2);
        labeRep.deleteById(l_id);
    }
    @Test
    public void deletelabeByClass(){
        Long l_id = Long.valueOf(3);
        labE labEList = labeRep.findById(l_id).orElse(null);
        if (labEList != null) {
            labeRep.delete(labEList);
        }
    }
    @Test
    public void updatelabe(){
        Long l_id = Long.valueOf(4);
        labE user = labeRep.findById(l_id).orElse(null);
        if (user != null) {
            user.setL_name("John");
            labeRep.save(user);
        }
    }

    /*@Test
    public void findByNameMethod(){
        List<labE> labEList_name = labeRep.findByLname("John");
        System.out.println("lab: " + labEList_name);
    } */
}