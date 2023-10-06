package com.kp.PatientHub.service;

import com.kp.PatientHub.entity.Patient;
import com.kp.PatientHub.view.PatientRequest;
import com.kp.PatientHub.view.PatientResponse;
import com.kp.PatientHub.view.SuccessResponse;

import java.util.List;

public interface PatientService {

    public PatientResponse savePatient(PatientRequest patientRequest);

    public PatientResponse updatePatient(Long id,PatientRequest patientRequest);

    public PatientResponse findById(Long id);

    public SuccessResponse deletePatientById(Long id);

    public List<PatientResponse> findAllPatients();

}
