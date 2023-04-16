		package com.mana.sample.api.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

//import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mana.sample.api.model.Person;
import com.mana.sample.api.service.PersonService;


@RunWith(SpringRunner.class)
@WebMvcTest(controllers = { PersonController.class })
@AutoConfigureJsonTesters
public class PersonControllerTests {

	@Autowired
	private MockMvc mvc;

	@MockBean
	private PersonService service;

	private Person person;
	
	ObjectMapper mapper = new ObjectMapper();

	@Autowired
	private JacksonTester<Person> jsonSuperHero;
	

	@BeforeEach
	public void init() {
		person = Person.builder().firstName("Test").build();
	}

	@Test
	public void getTest() throws Exception {
		when(service.findById(anyLong())).thenReturn(Optional.of(person));

		MockHttpServletResponse response = mvc.perform(get("/api/person/1").accept(MediaType.APPLICATION_JSON))
				.andReturn().getResponse();

		assertEquals(response.getStatus(), HttpStatus.OK.value());
		assertEquals(response.getContentAsString(),
				jsonSuperHero.write(Person.builder().firstName("Test").build()).getJson());
	}
	
	@Test
	public void getPersonsTest() throws Exception {
		List<Person> persons = new ArrayList<>();
		Page<Person> page = new PageImpl<Person>(persons);
		when(service.findAll(any())).thenReturn(page);

		MockHttpServletResponse response = mvc.perform(get("/api/person").accept(MediaType.APPLICATION_JSON))
				.andReturn().getResponse();

		assertEquals(response.getStatus(), HttpStatus.OK.value());
	}

	@Test
	public void createTest() throws Exception {
		when(service.save(any(Person.class))).thenReturn(person);

		MockHttpServletResponse response = mvc.perform(post("/api/person").accept(MediaType.APPLICATION_JSON).contentType("application/json").content(mapper.writeValueAsString(person)).
				characterEncoding("utf-8"))
				.andReturn().getResponse();

		assertEquals(response.getStatus(), HttpStatus.OK.value());
		assertEquals(response.getContentAsString(),
				jsonSuperHero.write(Person.builder().firstName("Test").build()).getJson());
	}
	
	@Test
	public void updateTest() throws Exception {
		when(service.save(any(Person.class))).thenReturn(person);

		MockHttpServletResponse response = mvc.perform(put("/api/person").accept(MediaType.APPLICATION_JSON).contentType("application/json").content(mapper.writeValueAsString(person)).
				characterEncoding("utf-8"))
				.andReturn().getResponse();

		assertEquals(response.getStatus(), HttpStatus.OK.value());
		assertEquals(response.getContentAsString(),
				jsonSuperHero.write(Person.builder().firstName("Test").build()).getJson());
	}
	
	@Test
	public void deleteTest() throws Exception {
		when(service.delete(any(Person.class))).thenReturn(person);

		MockHttpServletResponse response = mvc.perform(delete("/api/person").accept(MediaType.APPLICATION_JSON).contentType("application/json").content(mapper.writeValueAsString(person)).
				characterEncoding("utf-8"))
				.andReturn().getResponse();

		assertEquals(response.getStatus(), HttpStatus.OK.value());
		assertEquals(response.getContentAsString(),
				jsonSuperHero.write(Person.builder().firstName("Test").build()).getJson());
	}
}