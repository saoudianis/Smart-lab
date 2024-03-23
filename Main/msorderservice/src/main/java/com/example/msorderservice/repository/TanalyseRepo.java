package com.example.msorderservice.repository;


import com.example.msorderservice.entity.Analyses;
import com.example.msorderservice.entity.TAnalyseE;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface TanalyseRepo extends JpaRepository<TAnalyseE,Long> {

    TAnalyseE findByTid(String tid);
}
