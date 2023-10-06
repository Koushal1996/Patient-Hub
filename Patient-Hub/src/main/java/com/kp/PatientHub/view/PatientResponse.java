package com.kp.PatientHub.view;

import com.kp.PatientHub.entity.Patient;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class PatientResponse {
    private Long id;
    private String firstName;
    private String lastName;
    private LocalDate dateOfBirth;
    private String gender;
    private String address;
    private String phoneNumber;
    private String email;
    private String emergencyPhoneNumber;
    private String medicalHistory;

    public PatientResponse() {
    }

    public PatientResponse(Long id, String firstName, String lastName, LocalDate dateOfBirth, String gender, String address, String phoneNumber, String email, String emergencyPhoneNumber, String medicalHistory) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
        this.gender = gender;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.emergencyPhoneNumber = emergencyPhoneNumber;
        this.medicalHistory = medicalHistory;
    }

    public static PatientResponse get(Patient patient) {
        if (patient != null) {
            return new PatientResponse(patient.getId(), patient.getFirstName(), patient.getLastName(), patient.getDateOfBirth(), patient.getGender(), patient.getAddress(), patient.getPhoneNumber(), patient.getEmail(), patient.getEmergencyPhoneNumber(), patient.getMedicalHistory());
        } else {
            return null;
        }
    }
}
