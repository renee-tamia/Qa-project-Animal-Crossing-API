package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.demo.exceptions.CharacterNotFoundException;
import com.example.demo.model.AcCharacters;
import com.example.demo.repo.AcRepo;

@Service
public class AcService {
	
	private AcRepo repo;
	
	public AcService(AcRepo repo) {
		this.repo = repo;
	}
	
	public List<AcCharacters> getAllCharacters() {
		return this.repo.findAll();
	}
	
	public AcCharacters getById(Integer id) {
		AcCharacters foundCharacter = this.repo.findById(id).orElseThrow(CharacterNotFoundException::new);
		return foundCharacter;
	}

	public AcCharacters createCharacter(AcCharacters character) {
		return this.repo.save(character);
	}
	
	public AcCharacters updateCharacter(AcCharacters character, Integer id) {
		Optional<AcCharacters> characterToFind = this.repo.findById(id);
		AcCharacters characterToUpdate = characterToFind.get();
		
		characterToUpdate.setName(character.getName());
		characterToUpdate.setSpecies(character.getSpecies());
		characterToUpdate.setBirthday(character.getBirthday());
		characterToUpdate.setPersonality(character.getPersonality());
		characterToUpdate.setHobbies(character.getHobbies());
		characterToUpdate.setCatchphrase(character.getCatchphrase());
		
		return this.repo.save(characterToUpdate);
	}
	
}
