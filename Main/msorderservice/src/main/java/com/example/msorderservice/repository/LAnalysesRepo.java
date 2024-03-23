package com.example.msorderservice.repository;


import com.example.msorderservice.entity.LAnalyseE;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface LAnalysesRepo extends JpaRepository<LAnalyseE,Long> {

    //LAnalyseE FindByanalyse(String analyse);
}
