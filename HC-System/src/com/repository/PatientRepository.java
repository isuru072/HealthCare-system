package com.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.java.Patient;

@Repository("patientRepository")
public interface PatientRepository extends CrudRepository<Patient,Long> {

	Patient findByEmail(String email);
	
	Patient findByConfirmationToken(String confirmationToken);
	
	List<Patient> findAll();
	
}
