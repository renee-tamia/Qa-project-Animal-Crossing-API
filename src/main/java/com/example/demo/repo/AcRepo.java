package com.example.demo.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.AcCharacters;

@Repository
public interface AcRepo extends JpaRepository<AcCharacters, Integer> {

}
