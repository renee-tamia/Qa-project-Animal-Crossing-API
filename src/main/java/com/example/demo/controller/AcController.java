package com.example.demo.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
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
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<AcCharacters> deleteCharacter(@PathVariable Integer id) {

		boolean deleted = this.service.deleteCharacter(id);
		if (deleted) {
			return new ResponseEntity<>(HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);

		}
	}
	
	@GetMapping("/search/name/{name}")
	public ResponseEntity<List<AcCharacters>> getByName(@PathVariable String name) {
		ResponseEntity<List<AcCharacters>> response = new ResponseEntity<List<AcCharacters>>(this.service.getByName(name), HttpStatus.OK);
		return response;
	}
	
	@GetMapping("/search/species/{species}")
	public ResponseEntity<List<AcCharacters>> getBySpecies(@PathVariable String species) {
		ResponseEntity<List<AcCharacters>> response = new ResponseEntity<List<AcCharacters>>(this.service.getBySpecies(species), HttpStatus.OK);
		return response;
	}

	@GetMapping("/search/birthday/{birthday}")
	public ResponseEntity<List<AcCharacters>> getByBirthday(@PathVariable String birthday) {
		ResponseEntity<List<AcCharacters>> response = new ResponseEntity<List<AcCharacters>>(this.service.getByBirthday(birthday), HttpStatus.OK);
		return response;
	}
	
	@GetMapping("/search/personality/{personality}")
	public ResponseEntity<List<AcCharacters>> getByPersonality(@PathVariable String personality) {
		ResponseEntity<List<AcCharacters>> response = new ResponseEntity<List<AcCharacters>>(this.service.getByPersonality(personality), HttpStatus.OK);
		return response;
	}
	
	@GetMapping("/search/hobbies/{hobbies}")
	public ResponseEntity<List<AcCharacters>> getByHobbies(@PathVariable String hobbies) {
		ResponseEntity<List<AcCharacters>> response = new ResponseEntity<List<AcCharacters>>(this.service.getByHobbies(hobbies), HttpStatus.OK);
		return response;
	}
	
	@GetMapping("/search/catchphrase/{catchphrase}")
	public ResponseEntity<List<AcCharacters>> getByCatchphrase(@PathVariable String catchphrase) {
		ResponseEntity<List<AcCharacters>> response = new ResponseEntity<List<AcCharacters>>(this.service.getByCatchphrase(catchphrase), HttpStatus.OK);
		return response;
	}
	
	
}
