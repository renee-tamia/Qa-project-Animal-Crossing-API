package com.example.demo.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class AcCharacters {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String name;
	private String species;
	private String birthday;
	private String personality;
	private String hobbies;
	private String catchphrase;
	
	public AcCharacters() {
		
	}
	
	
	public AcCharacters(Integer id, String name, String species, String birthday, String personality,
			String hobbies, String catchphrase) {
		super();
		this.id = id;
		this.name = name;
		this.species = species;
		this.birthday = birthday;
		this.personality = personality;
		this.hobbies = hobbies;
		this.catchphrase = catchphrase;
	}


	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getSpecies() {
		return species;
	}


	public void setSpecies(String species) {
		this.species = species;
	}


	public String getBirthday() {
		return birthday;
	}


	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}


	public String getPersonality() {
		return personality;
	}


	public void setPersonality(String personality) {
		this.personality = personality;
	}


	public String getHobbies() {
		return hobbies;
	}


	public void setHobbies(String hobbies) {
		this.hobbies = hobbies;
	}


	public String getCatchphrase() {
		return catchphrase;
	}


	public void setCatchphrase(String catchphrase) {
		this.catchphrase = catchphrase;
	}
	
}