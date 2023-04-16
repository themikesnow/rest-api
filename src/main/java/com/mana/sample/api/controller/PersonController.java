package com.mana.sample.api.controller;

import java.security.InvalidParameterException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mana.sample.api.model.Person;
import com.mana.sample.api.service.PersonService;

@RestController
@RequestMapping(value = "/api/person")

public class PersonController {

	@Autowired
	private PersonService personService;

	@GetMapping("/{id}")
	public Person get(@PathVariable Long id) {
		return personService.findById(id).orElseThrow(InvalidParameterException::new);
	}

	@GetMapping
	public Page<Person> getPersons(Pageable page) {
		return personService.findAll(page);
	}

	@PostMapping
	public Person create(@RequestBody Person person) {
		return personService.save(person);
	}

	@PutMapping
	public Person update(@RequestBody Person person) {
		return personService.save(person);
	}

	@DeleteMapping
	public Person delete(@RequestBody Person person) {
		return personService.delete(person);
	}

}
