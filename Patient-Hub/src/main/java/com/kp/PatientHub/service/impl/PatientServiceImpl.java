package com.kp.PatientHub.service.impl;

import com.kp.PatientHub.dao.PatientDao;
import com.kp.PatientHub.entity.Patient;
import com.kp.PatientHub.exception.NotFoundException;
import com.kp.PatientHub.exception.ValidationException;
import com.kp.PatientHub.service.PatientService;
import com.kp.PatientHub.view.PatientRequest;
import com.kp.PatientHub.view.PatientResponse;
import com.kp.PatientHub.view.SuccessResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@Service("patientServiceImpl")
@Transactional
public class PatientServiceImpl implements PatientService {

    @Autowired
    private PatientDao patientDao;

    private void validatePhoneNumber(String phoneNo,Long id) {
        String regex="^[789]\\d{9}$";
        Pattern pattern=Pattern.compile(regex);
        Matcher matcher=pattern.matcher(phoneNo);
        if(!matcher.matches()){
            throw new ValidationException("Phone Number is not correct");
        }
        if(id==null) {
            if (patientDao.existsByPhoneNumber(phoneNo)) {
                throw new ValidationException("Phone Number already exists");
            }
        }else{
            if (patientDao.existsByPhoneNumberAndIdNot(phoneNo,id)) {
                throw new ValidationException("Phone Number already exists");
            }
        }
    }

    private void validateEmail(String email,Long id){
        String regex="^(.+)@(.+)$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher=pattern.matcher(email);
        if(!matcher.matches()){
            throw new ValidationException("Incorrect Email");
        }
        if(id==null) {
            if (patientDao.existsByEmail(email)) {
                throw new ValidationException("Email already exists");
            }
        }else{
            if (patientDao.existsByEmailAndIdNot(email,id)) {
                throw new ValidationException("Email already exists");
            }
        }
    }

    private void validateDateOfBirthFormat(String dateOfBirth){
        DateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        sdf.setLenient(false);
        try {
            sdf.parse(dateOfBirth);
        } catch (ParseException e) {
            throw new ValidationException("Date of birth format incorrect");
        }
    }

    private void validateRequest(PatientRequest request,Long id){
        validatePhoneNumber(request.getPhoneNumber(),id);
        validateEmail(request.getEmail(),id);
        validateDateOfBirthFormat(request.getDateOfBirth());
    }



    @Override
    @CachePut(cacheNames = "patients" , key = "#patient.id")
    public PatientResponse savePatient(PatientRequest patientRequest) {
        validateRequest(patientRequest,null);
        Patient patient=patientRequest.toEntity();
        patientDao.save(patient);
        return PatientResponse.get(patient);
    }

    @Override
    @CachePut(cacheNames = "patients" , key = "#id")
    public PatientResponse updatePatient(Long id, PatientRequest patientRequest) {
        Patient patient=patientDao.findById(id).orElse(null);
        if(patient==null){
            throw new NotFoundException("Patient not exist");
        }
        validateRequest(patientRequest,id);
        patient=patientRequest.toEntity(patient);
        patientDao.save(patient);
        return PatientResponse.get(patient);
    }

    @Override
    @Cacheable(cacheNames = "patients" , key = "#id")
    public PatientResponse findById(Long id) {
        Optional<Patient> patient=patientDao.findById(id);
        if(!patient.isPresent()){
            throw new NotFoundException("Patient not exist");
        }
        return PatientResponse.get(patient.get());
    }

    @Override
    @CacheEvict(cacheNames = "patients" , key = "#id")
    public SuccessResponse deletePatientById(Long id) {
        if(!patientDao.existsById(id)){
            throw new NotFoundException("Patient not exist");
        }
        patientDao.deleteById(id);
        return new SuccessResponse(String.format("Patient (%s) successfully deleted",id),200);
    }

    @Override
    @Cacheable(cacheNames = "patients")
    public List<PatientResponse> findAllPatients() {
        List<Patient> patients=patientDao.findAll();
        return patients.stream().map(PatientResponse::get).collect(Collectors.toList());
    }
}
