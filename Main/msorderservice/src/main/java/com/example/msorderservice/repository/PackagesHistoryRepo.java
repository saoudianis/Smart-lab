package com.example.msorderservice.repository;

import com.example.msorderservice.entity.PackagesE;
import com.example.msorderservice.entity.PackagesHistoryE;
//import com.example.msorderservice.entity.TAnalyseE;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PackagesHistoryRepo extends JpaRepository<PackagesHistoryE,Long> {

    PackagesHistoryE findOneBypid(String pid);

    List<PackagesHistoryE> findByPowner(String powner);
}
