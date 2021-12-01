package com.example.demo.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
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
	
	@GetMapping("/{id}")
	public ResponseEntity<AcCharacters> get(@PathVariable Integer id) {
		ResponseEntity<AcCharacters> response = new ResponseEntity<AcCharacters>(this.service.getById(id), HttpStatus.OK);
		return response;
	}

	@PostMapping("/create")
	public ResponseEntity<AcCharacters> createCharacter(@RequestBody AcCharacters character) {
		ResponseEntity<AcCharacters> response = new ResponseEntity<AcCharacters>(
				this.service.createCharacter(character), HttpStatus.CREATED);
		return response;
	}
	
	@PutMapping("/update/{id}")
	public ResponseEntity<AcCharacters> updateCharacter(@RequestBody AcCharacters character, @PathVariable Integer id) {
		ResponseEntity<AcCharacters> response = new ResponseEntity<AcCharacters>(
				this.service.updateCharacter(character, id), HttpStatus.ACCEPTED);
		return response;
	}
	
}
