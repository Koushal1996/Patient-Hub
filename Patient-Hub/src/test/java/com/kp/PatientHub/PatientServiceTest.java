package com.kp.PatientHub;

import com.kp.PatientHub.dao.PatientDao;
import com.kp.PatientHub.entity.Patient;
import com.kp.PatientHub.exception.NotFoundException;
import com.kp.PatientHub.service.impl.PatientServiceImpl;
import com.kp.PatientHub.view.PatientRequest;
import com.kp.PatientHub.view.PatientResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
public class PatientServiceTest {

    @Mock
    private PatientDao patientDao;

    @InjectMocks
    private PatientServiceImpl patientService;

    private static final long id= 1L;

    private Patient patient;

    @BeforeEach
    public  void init(){
        patient= Patient.builder().id(id).address("Delhi").email("koushal@gmail.com").build();
    }

    @Test
    public void testGetPatientById(){
        Mockito.when(patientDao.findById(id)).thenReturn(Optional.of(patient));
        PatientResponse response=patientService.findById(id);
        assertEquals(patient.getId(),response.getId());
    }

    @Test
    public void testGetPatientByIdIfNotExist(){
        Mockito.when(patientDao.findById(id)).thenReturn(Optional.ofNullable(null));
        assertThrows(
               NotFoundException.class,
                () -> patientService.findById(id));
    }

    @Test
    public void testGetAllPatients(){
        List<Patient> patientList=new ArrayList<>();
        patientList.add(patient);
        Mockito.when(patientDao.findAll()).thenReturn(patientList);
        List<PatientResponse> responseList=patientService.findAllPatients();
        assertEquals(patientList.size(),responseList.size());
    }

    @Test
    public void testSavePatient(){
        PatientRequest request=new PatientRequest();
        request.setFirstName("Rohan");
        request.setLastName("Kumar");
        request.setDateOfBirth("22-09-1994");
        request.setEmail("rohan123@gmail.com");
        request.setPhoneNumber("9823454353");
        Mockito.when(patientDao.existsByEmail(request.getEmail())).thenReturn(false);
        Mockito.when(patientDao.existsByPhoneNumber(request.getPhoneNumber())).thenReturn(false);
        PatientResponse response=patientService.savePatient(request);
        assertEquals(request.getEmail(),response.getEmail());
    }
}
