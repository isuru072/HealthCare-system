package com.service;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.java.Patient;
import com.repository.PatientRepository;


@Service("PatientService")
public class PatientService {

	private PatientRepository patientRepository;
	
	@Autowired
	public PatientService(PatientRepository patientRepository) {
		this.patientRepository = patientRepository;
	}
	
	public Patient findByEmail(String email) {
		return patientRepository.findByEmail(email);
	}
	
	public Patient findByConfirmationToken(String confirmationToken) {
		return patientRepository.findByConfirmationToken(confirmationToken);
	}
	
	public void savePatient(Patient patient) {
		patientRepository.save(patient);
	}
	
	public List<Patient> findAll(){
		return patientRepository.findAll();
	}
	
}
