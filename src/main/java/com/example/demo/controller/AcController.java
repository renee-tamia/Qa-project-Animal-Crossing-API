package com.example.demo.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.AcCharacters;
import com.example.demo.service.AcService;


@RestController
@RequestMapping(path = "/animalcrossingcharacters")
public class AcController {

	private AcService service;

	public AcController(AcService service) {
		this.service = service;
	}

	@GetMapping
	public ResponseEntity<List<AcCharacters>> get() {
		return new ResponseEntity<List<AcCharacters>>(this.service.getAllCharacters(), HttpStatus.OK);
	}
	
}
