package com.mana.sample.api.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.mana.sample.api.model.Person;
import com.mana.sample.api.repo.PersonRepository;

@Service
public class PersonService {

	@Autowired
	private PersonRepository personRepository;

	public Optional<Person> findById(Long id) {
		return personRepository.findById(id);
	}

	public Page<Person> findAll(Pageable pageable) {
		return personRepository.findAll(pageable);
	}
	
	public Person save(Person person) {
		if (person.getAddresses() != null) {
			person.getAddresses().forEach(a -> a.setPerson(person));
		}
		return personRepository.save(person);
	}

	public Person delete(Person person) {
		personRepository.delete(person);

		return person;
	}

}
