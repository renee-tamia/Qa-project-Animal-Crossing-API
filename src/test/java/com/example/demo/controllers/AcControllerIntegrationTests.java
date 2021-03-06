package com.example.demo.controllers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.MediaType;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.Sql.ExecutionPhase;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.ResultMatcher;

import com.example.demo.model.AcCharacters;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@Sql(scripts = {"classpath:test-schema.sql", "classpath:test-data.sql"}, executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
public class AcControllerIntegrationTests {
	
	@Autowired
	private MockMvc mockmvc;
	
	@Autowired
	private ObjectMapper mapper;
	
	
	@Test
	void getAllCharactersTest() throws Exception {
		String listOfCharactersAsJson = this.mapper.writeValueAsString(List.of(
				new AcCharacters(1, "Beau", "deer", "5th April", "lazy", "nature", "saltlick"),
				new AcCharacters(2, "Agnes", "pig", "21st April", "sisterly", "play", "snuffle"),
				new AcCharacters(3, "Tipper", "cow", "25th August", "snooty", "fashion", "pushy"),
				new AcCharacters(4, "Tammy", "cub", "23rd June", "sisterly", "play", "ya heard"),
				new AcCharacters(5, "Shep", "dog", "24th November", "smug", "education", "baa baa baa"),
				new AcCharacters(6, "Roscoe", "horse", "16th June", "cranky", "music", "nay"),
				new AcCharacters(7, "Phoebe", "ostrich", "27th November", "sisterly", "fitness", "sparky")
				));
				
		
		RequestBuilder requestb = get("/animalcrossingcharacters");
		
		ResultMatcher status = status().isOk();
		ResultMatcher content = content().json(listOfCharactersAsJson);
		
		this.mockmvc.perform(requestb).andExpect(status).andExpect(content);
	}

	@Test
	void getByIdTest() throws Exception {
		String foundCharacterAsJson = this.mapper.writeValueAsString(new AcCharacters(
				1, "Beau", "deer", "5th April", "lazy", "nature", "saltlick"));  // put an existing character pls, one from the test-data.sql
		
		RequestBuilder request = get("/animalcrossingcharacters/1");
		
		ResultMatcher status = status().isOk();
		ResultMatcher content = content().json(foundCharacterAsJson);
		
		this.mockmvc.perform(request).andExpect(status).andExpect(content);
		
	}
	
	@Test
	void createCharacterTest() throws Exception {
		String testCharacterAsJson = this.mapper.writeValueAsString(new AcCharacters
				(null, "Chadder", "mouse", "15th December", "smug", "fitness", "fromage")); 
		String testCharacterAsJsonResponse = this.mapper.writeValueAsString(new AcCharacters
				(8, "Chadder", "mouse", "15th December", "smug", "fitness", "fromage"));
		
		RequestBuilder request = post("/animalcrossingcharacters/create").contentType(MediaType.APPLICATION_JSON).content(testCharacterAsJson);
		
		ResultMatcher status = status().isCreated();
		ResultMatcher content = content().json(testCharacterAsJsonResponse);
		
		this.mockmvc.perform(request).andExpect(status).andExpect(content);
		
		
	}
	
	@Test
	void updateCharacterTest() throws Exception {
		String updateCharacterAsJsonUpdated = this.mapper.writeValueAsString(new AcCharacters
				(2, "Agnes", "rabbit", "21st April", "play", "sisterly", "snuffle"));
		
		RequestBuilder request = put("/animalcrossingcharacters/update/2").contentType(MediaType.APPLICATION_JSON).content(updateCharacterAsJsonUpdated);
		
		ResultMatcher status = status().isAccepted();
		ResultMatcher content = content().json(updateCharacterAsJsonUpdated);
		
		this.mockmvc.perform(request).andExpect(status).andExpect(content);
	
	
	}
	
	@Test
	void deleteCharacterTest() throws Exception {
		this.mockmvc.perform(delete("/animalcrossingcharacters/delete/1")).andExpect(status().isOk());
	}
	
	
	@Test
	void getByNameTest() throws Exception {
		String foundCharacterByName = this.mapper.writeValueAsString(List.of (new AcCharacters
				(2, "Agnes", "pig", "21st April", "sisterly", "play", "snuffle")));
		
	RequestBuilder requestb = get("/animalcrossingcharacters/search/name/Agnes");
	
	ResultMatcher status = status().isOk();
	ResultMatcher content = content().json(foundCharacterByName);
	
	this.mockmvc.perform(requestb).andExpect(status).andExpect(content);
	
	}
	
	@Test
	void getBySpeciesTest() throws Exception {
		String foundCharacterBySpecies = this.mapper.writeValueAsString(List.of(new AcCharacters
				(1, "Beau", "deer", "5th April", "lazy", "nature", "saltlick")));
				
		RequestBuilder requestb = get("/animalcrossingcharacters/search/species/deer");
		
		ResultMatcher status = status().isOk();
		ResultMatcher content = content().json(foundCharacterBySpecies);
		
		this.mockmvc.perform(requestb).andExpect(status).andExpect(content);
		
	}
	
	@Test
	void getByBirthdayTest() throws Exception {
		String foundCharacterByBirthday = this.mapper.writeValueAsString(List.of(new AcCharacters
				(7, "Phoebe", "ostrich", "27th November", "sisterly", "fitness", "sparky")));
		
		RequestBuilder request = get("/animalcrossingcharacters/search/birthday/27th November");
		
		ResultMatcher status = status().isOk();
		ResultMatcher content = content().json(foundCharacterByBirthday);
		
		this.mockmvc.perform(request).andExpect(status).andExpect(content);
		
	}
	
	@Test
	void getByPersonalityTest() throws Exception {
		String foundCharacterByPersonality = this.mapper.writeValueAsString(List.of(
				new AcCharacters(2, "Agnes", "pig", "21st April", "sisterly", "play", "snuffle"),  // must write new acCharaters in front of each
				new AcCharacters(4, "Tammy", "cub", "23rd June", "sisterly", "play", "ya heard"),
				new AcCharacters(7, "Phoebe", "ostrich", "27th November", "sisterly", "fitness", "sparky")
				));
				
			RequestBuilder request = get("/animalcrossingcharacters/search/personality/sisterly");
			
			ResultMatcher status = status().isOk();
			ResultMatcher content = content().json(foundCharacterByPersonality);
			
			this.mockmvc.perform(request).andExpect(status).andExpect(content);
				
	}
	
	@Test
	void getByHobbiesTest() throws Exception {
		String foundCharacterByHobbies = this.mapper.writeValueAsString(List.of(
				new AcCharacters(6, "Roscoe", "horse", "16th June", "cranky", "music", "nay")));
		
		RequestBuilder request = get("/animalcrossingcharacters/search/hobbies/music");
		
		ResultMatcher status = status().isOk();
		ResultMatcher content = content().json(foundCharacterByHobbies);
		
		this.mockmvc.perform(request).andExpect(status).andExpect(content);

	}
	
	@Test
	void getByCatchphraseTest() throws Exception {
		String foundCharacterByCatchphrase = this.mapper.writeValueAsString(List.of(
				new AcCharacters(5, "Shep", "dog", "24th November", "smug", "education", "baa baa baa")));
		
		RequestBuilder request = get("/animalcrossingcharacters/search/catchphrase/baa baa baa");
		
		ResultMatcher status = status().isOk();
		ResultMatcher content = content().json(foundCharacterByCatchphrase);
		
		this.mockmvc.perform(request).andExpect(status).andExpect(content);
		
	}

	
}
