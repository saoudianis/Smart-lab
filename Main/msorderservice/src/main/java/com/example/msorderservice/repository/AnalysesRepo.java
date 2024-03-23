package com.example.msorderservice.repository;


import com.example.msorderservice.entity.Analyses;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface AnalysesRepo extends JpaRepository<Analyses,Long> {
Analyses findOneByAnalyseid(Long analyseid);
}
