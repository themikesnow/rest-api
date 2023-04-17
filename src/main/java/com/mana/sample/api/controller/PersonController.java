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

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping(value = "/api/person")
@OpenAPIDefinition
@Tag(name = "person", description = "Person API")
public class PersonController {

	@Autowired
	private PersonService personService;

	@Operation(summary = "Get a person by its id", operationId = "GetPerson")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Found the book", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = Person.class)) }),
			@ApiResponse(responseCode = "400", description = "Invalid id supplied", content = @Content),
			@ApiResponse(responseCode = "404", description = "Person not found", content = @Content) })
	@GetMapping("/{id}")
	public Person get(@Parameter(description = "id of person to be searched") @PathVariable Long id) {
		return personService.findById(id).orElseThrow(InvalidParameterException::new);
	}

	@Operation(summary = "Get persons", operationId = "GetPersons")
	@GetMapping
	public Page<Person> getPersons(Pageable page) {
		return personService.findAll(page);
	}

	@Operation(summary = "Create person", operationId = "CreatePerson")
	@PostMapping
	public Person create(@RequestBody Person person) {
		return personService.save(person);
	}

	@Operation(summary = "Update person", operationId = "UpdatePerson")
	@PutMapping
	public Person update(@RequestBody Person person) {
		return personService.save(person);
	}

	@Operation(summary = "Delete person", operationId = "DeletePerson")
	@DeleteMapping
	public Person delete(@RequestBody Person person) {
		return personService.delete(person);
	}

}
