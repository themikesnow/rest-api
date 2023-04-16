package com.mana.sample.api.repo;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import com.mana.sample.api.model.Person;

@DataJpaTest
public class PersonRepositoryTests {

	@Autowired
	private TestEntityManager entityManager;

	@Autowired
	private PersonRepository repository;

	@Test
	public void testFindByFirstName() {
		Person person = Person.builder().firstName("Michael").lastName("Snow").build();
		entityManager.persist(person);

		List<Person> persons = repository.findByFirstName(person.getFirstName());
		assertThat(persons).extracting(Person::getFirstName).containsOnly(person.getFirstName());
	}

	@Test
	public void testFindAll() {
		Person person = Person.builder().firstName("Michael").lastName("Snow").build();
		
		entityManager.persist(person);
		
		Page<Person> persons = repository.findAll(PageRequest.of(0, 10));
		assertEquals(persons.getContent().size(), 1);
	}
}
