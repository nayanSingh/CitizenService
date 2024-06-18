package com.micro.CitizenService.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.micro.CitizenService.entity.Citizen;

public interface CitizenRepo extends JpaRepository<Citizen, Integer>{
	
	List<Citizen> findByVaccinationCenterId(Integer id);

}
