package com.example.demo.repo;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.AcCharacters;

@Repository
public interface AcRepo extends JpaRepository<AcCharacters, Integer> {

	Optional<List<AcCharacters>> findByName(String name);
	
	Optional<List<AcCharacters>> findBySpecies(String species);
	
	Optional<List<AcCharacters>> findByBirthday(String birthday);
	
	Optional<List<AcCharacters>> findByPersonality(String personality);
	
	Optional<List<AcCharacters>> findByHobbies(String hobbies);
	
	Optional<List<AcCharacters>> findByCatchphrase(String catchphrase);
	
}
