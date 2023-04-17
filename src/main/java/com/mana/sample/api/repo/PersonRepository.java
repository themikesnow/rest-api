package com.mana.sample.api.repo;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.mana.sample.api.model.Person;

public interface PersonRepository extends JpaRepository<Person, Long> {
	Page<Person> findAll(Pageable pageable);

	List<Person> findByFirstName(String firstName);

}
