package com.example.Assignment;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@SpringBootApplication
@RestController
@RequestMapping("/Persons")
public class AssignmentApplication {
	@Autowired
	private PersonRepository personRepository;

	@GetMapping
	public List<Person> getUsers() {
		return personRepository.findAll();
	}
	public static void main(String[] args) {
		SpringApplication.run(AssignmentApplication.class, args);
	}

}
