package com.example.msorderservice.repository;
import com.example.msorderservice.entity.labE;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
public interface labeRepository extends JpaRepository<labE,Long> {
    labE findByEmail(String email);
    //@Transactional
    labE findOneBylid(Long lid);
    Optional<labE> findOneByemailAndPassword(String email, String password);

    //List<labE> findByl_email(String l_email);
}
