package com.example.demo.controllers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
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

}
