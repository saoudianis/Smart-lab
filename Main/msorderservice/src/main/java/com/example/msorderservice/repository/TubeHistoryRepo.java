package com.example.msorderservice.repository;

//import com.example.msorderservice.entity.TAnalyseE;
import com.example.msorderservice.entity.TubeHistoryE;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TubeHistoryRepo extends JpaRepository<TubeHistoryE,Long> {

    List<TubeHistoryE> findByPackageid(String packageid);
}
