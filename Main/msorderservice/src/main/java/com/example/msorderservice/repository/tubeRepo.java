package com.example.msorderservice.repository;


import com.example.msorderservice.entity.TubesE;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface tubeRepo extends JpaRepository<TubesE,Long> {
List<TubesE> findByPid(String pid);
TubesE findOneBytid(Long tid);
TubesE findOneBypid(String pid);

//List <TubesE> deleteBypid(String pid);
@Transactional
Long removeBypid(String pid);
}
