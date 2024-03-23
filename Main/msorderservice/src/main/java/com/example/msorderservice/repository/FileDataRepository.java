package com.example.msorderservice.repository;

import com.example.msorderservice.entity.FileDataE;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
public interface FileDataRepository extends JpaRepository<FileDataE,Long> {
    @Transactional
    FileDataE findByTid(String tid);

    @Transactional
    FileDataE findByName(String name);





}
