package com.micro.CitizenService.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.micro.CitizenService.entity.Citizen;
import com.micro.CitizenService.repository.CitizenRepo;

@RestController
@RequestMapping("/citizen")
public class CitizenController {
	
	@Autowired
	CitizenRepo repo;
	
	@RequestMapping("/test")
	public ResponseEntity<String> test() {
		return new ResponseEntity<>("Hello",HttpStatus.OK);
	}
	
	@RequestMapping("/id/{id}")
	public ResponseEntity<List<Citizen>> getById(@PathVariable Integer id) {
		List<Citizen> citizenList=repo.findByVaccinationCenterId(id);
		return new ResponseEntity<>(citizenList,HttpStatus.OK);
	}
	
	@PostMapping("/add")
	public ResponseEntity<Citizen> addCitizen(@RequestBody Citizen newCitizen) {
		Citizen citizen=repo.save(newCitizen);
		return new ResponseEntity<>(citizen,HttpStatus.OK);
	}

}
