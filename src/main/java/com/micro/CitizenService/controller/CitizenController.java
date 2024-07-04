package com.micro.CitizenService.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
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

	Logger LOG = LoggerFactory.getLogger(CitizenController.class);

	@Autowired
	CitizenRepo repo;
	
	@Autowired
	Environment environment;

	@RequestMapping("/test")
	public ResponseEntity<String> test() {
		return new ResponseEntity<>("Hello", HttpStatus.OK);
	}

	@PreAuthorize("hasAuthority('SCOPE_internal')")
	@RequestMapping("/id/{id}")
	public ResponseEntity<List<Citizen>> getById(@PathVariable Integer id) {
		String port = environment.getProperty("local.server.port");
		LOG.info("Citizen Instance port {}", port);
		List<Citizen> citizenList = repo.findByVaccinationCenterId(id);
		if (citizenList.isEmpty()) {
			LOG.info("No Citizen Available for Vaccination Center {}", id);
		} else {
			LOG.info("Citizenz Available for Vaccination Center {}", id);
		}
		return new ResponseEntity<>(citizenList, HttpStatus.OK);
	}

	@PostMapping("/add")
	public ResponseEntity<Citizen> addCitizen(@RequestBody Citizen newCitizen) {
		Citizen citizen = repo.save(newCitizen);
		return new ResponseEntity<>(citizen, HttpStatus.OK);
	}

}
