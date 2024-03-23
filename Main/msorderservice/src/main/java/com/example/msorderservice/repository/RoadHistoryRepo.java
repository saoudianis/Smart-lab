package com.example.msorderservice.repository;

import com.example.msorderservice.entity.RoadHistoryE;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Repository
public interface RoadHistoryRepo extends JpaRepository<RoadHistoryE,Long> {
@Transactional
List<RoadHistoryE> findByOwnerid(String ownerid);

RoadHistoryE findOneByPid(String pid);
}
