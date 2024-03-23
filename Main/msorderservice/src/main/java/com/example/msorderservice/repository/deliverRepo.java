package com.example.msorderservice.repository;


import com.example.msorderservice.entity.DeliverE;
//import com.example.msorderservice.entity.PackagesE;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface deliverRepo extends JpaRepository<DeliverE,Long> {

    DeliverE findOneByDid(Long did);
    List<DeliverE> findByLabid(String labid);
}
