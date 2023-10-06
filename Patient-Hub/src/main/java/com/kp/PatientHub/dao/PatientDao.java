package com.kp.PatientHub.dao;

import com.kp.PatientHub.entity.Patient;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PatientDao extends JpaRepository<Patient,Long> {

    public boolean existsById(Long id);

    public boolean existsByPhoneNumber(String phoneNumber);

    public boolean existsByPhoneNumberAndIdNot(String phoneNumber,Long id);

    public boolean existsByEmail(String email);

    public boolean existsByEmailAndIdNot(String email,Long id);

}
