package com.example.demo.service;

import java.util.List;

import org.springframework.stereotype.Service;

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

}
