package com.kp.PatientHub.view;

import com.kp.PatientHub.entity.Patient;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;

@Getter
@Setter
public class PatientRequest {

    private String firstName;
    private String lastName;
    private String dateOfBirth;
    private String gender;
    private String address;
    private String phoneNumber;
    private String email;
    private String emergencyPhoneNumber;
    private String medicalHistory;

    public Patient toEntity(){
        Patient patient=new Patient();
        patient.setFirstName(firstName);
        patient.setLastName(lastName);
        patient.setDateOfBirth(LocalDate.parse(dateOfBirth, DateTimeFormatter.ofPattern("dd-MM-yyyy")));
        patient.setGender(gender);
        patient.setAddress(address);
        patient.setPhoneNumber(phoneNumber);
        patient.setEmail(email);
        patient.setEmergencyPhoneNumber(emergencyPhoneNumber);
        patient.setMedicalHistory(medicalHistory);
        return patient;
    }

    public Patient toEntity(Patient patient){
        if(patient!=null) {
            patient.setFirstName(firstName);
            patient.setLastName(lastName);
            patient.setDateOfBirth(LocalDate.parse(dateOfBirth, DateTimeFormatter.ofPattern("dd-MM-yyyy")));
            patient.setGender(gender);
            patient.setAddress(address);
            patient.setPhoneNumber(phoneNumber);
            patient.setEmail(email);
            patient.setEmergencyPhoneNumber(emergencyPhoneNumber);
            patient.setMedicalHistory(medicalHistory);
            return patient;
        }
        return null;
    }


}
