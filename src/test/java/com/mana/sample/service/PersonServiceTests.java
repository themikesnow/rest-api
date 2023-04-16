package com.mana.sample.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.assertj.core.util.Arrays;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.junit4.SpringRunner;

import com.mana.sample.api.TestConfig;
import com.mana.sample.api.model.Address;
import com.mana.sample.api.model.Person;
import com.mana.sample.api.repo.PersonRepository;
import com.mana.sample.api.service.PersonService;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = { PersonService.class })
public class PersonServiceTests {

	@Autowired
	private PersonService service;

	@MockBean
	private PersonRepository repository;

	private Person person;
	
	private List<Address> addresses; 

	@BeforeEach
	public void init() {
		person = Person.builder().firstName("Test").build();
		
		addresses = new ArrayList<>();
		addresses.add(Address.builder().line1("Address 1").build());
	}

	@Test
	public void testFindById() {
		when(repository.findById(1L)).thenReturn(Optional.of(person));
		assertEquals(person, service.findById(1L).get());
	}
	
	@Test
	public void testFindAll() {
		List<Person> persons = new ArrayList<>();
		Page<Person> page = new PageImpl<Person>(persons);
		when(repository.findAll(any(Pageable.class))).thenReturn(page);
		assertEquals(page, service.findAll(PageRequest.of(0, 10)));
	}
	
	@Test
	public void testSave() {
		
		when(repository.save(any(Person.class))).thenReturn(person);
		assertEquals(person, service.save(person));
		
		person.setAddresses(addresses);
		assertEquals(person, service.save(person));
		
		assertEquals(person, service.save(person).getAddresses().get(0).getPerson());
		
	}
	
	@Test
	public void testDelete() {
		doNothing().when(repository).delete(any(Person.class));
		service.delete(person);
		verify(repository, times(1)).delete(person);
		
	}

//	public Page<Person> findAll(Pageable pageable) {
//		return personRepository.findAll(pageable);
//	}
//	
//	public Person save(Person person) {
//		if (person.getAddresses() != null) {
//			person.getAddresses().forEach(a -> a.setPerson(person));
//		}
//		return personRepository.save(person);
//	}
//
//	public Person delete(Person person) {
//		personRepository.delete(person);
//
//		return person;
//	}

}
