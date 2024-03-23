package com.example.msorderservice.repository;


import com.example.msorderservice.entity.PackagesE;
import com.example.msorderservice.entity.labE;
import org.postgresql.shaded.com.ongres.scram.common.bouncycastle.pbkdf2.Pack;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface packageRepo extends JpaRepository<PackagesE,Long> {

    List<PackagesE> findByPowner(String powner);
    List<PackagesE> findByPdestination(String pdestination);

    PackagesE findOneBypid(Long pid);

    List<PackagesE> findByPownerAndPstatus(String powner,String pstatus);

    PackagesE findByDidAndPstatus(String did,String pstatus);

}
