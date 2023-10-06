package com.kp.PatientHub.controller;

import com.kp.PatientHub.service.PatientService;
import com.kp.PatientHub.view.PatientRequest;
import com.kp.PatientHub.view.PatientResponse;
import com.kp.PatientHub.view.SuccessResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class PatientController {

    @Autowired
    private PatientService patientService;

    @PostMapping(value = "/patient")
    public ResponseEntity<PatientResponse> savePatient(@RequestBody PatientRequest patientRequest){
        PatientResponse patientResponse=patientService.savePatient(patientRequest);
        return ResponseEntity.ok(patientResponse);
    }

    @PutMapping(value = "/patient/{id}")
    public ResponseEntity<PatientResponse> updatePatient(@PathVariable Long id,@RequestBody PatientRequest patientRequest){
        PatientResponse patientResponse=patientService.updatePatient(id,patientRequest);
        return ResponseEntity.ok(patientResponse);
    }

    @GetMapping(value = "/patient/{id}")
    public ResponseEntity<PatientResponse> findPatientById(@PathVariable Long id){
        PatientResponse patientResponse=patientService.findById(id);
        return ResponseEntity.ok(patientResponse);
    }

    @GetMapping(value = "/patients")
    public List<PatientResponse> findAllPatients(){
        return patientService.findAllPatients();
    }

    @DeleteMapping(value = "/patient/{id}")
    public SuccessResponse deletePatient(@PathVariable Long id){
       return patientService.deletePatientById(id);
    }



}
