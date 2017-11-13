package org.certificatic.spring.mvc.practica31.controller.rest;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.annotation.PostConstruct;

import org.certificatic.spring.mvc.practica31.controller.advice.RestResponseError;
import org.certificatic.spring.validation.practica30.parte1.domain.Person;
import org.certificatic.spring.validation.practica30.parte1.domain.Persons;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.sun.org.apache.xerces.internal.util.Status;

// Anotar RestController
@RestController
// Anotar request mapping "/rest/persons"
@RequestMapping("/rest/persons")
public class PersonsRestController {

	private List<Person> persons = Collections.synchronizedList(new ArrayList<Person>());

	@PostConstruct
	private void init() {
		for (int i = 0; i < 3; i++) {
			Person p = new Person();
			p.setId(i + 1);
			p.setName("Ivan " + (i + 1));
			p.setAge(28 + i + 1);
			persons.add(p);
		}
	}

	// Anotar request mapping "/", "", con metodo GET y produciendo json y xml
	@RequestMapping(value = {"/" , ""}, method = RequestMethod.GET, 
			produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
	// Cuando se utiliza @RestController ya no es necesario colocar la etiqueta @ResponseBody
	public Persons getAllPersons() {
		return new Persons(persons);
	}

	// Anotar request mapping "/{id}", con metodo GET y produciendo json y xml
	@RequestMapping(value = "/{id}", method = RequestMethod.GET, 
			produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE} )
	// Anotar response status ok
	@ResponseStatus(code = HttpStatus.OK)
	// Cuando se utiliza @RestController ya no es necesario colocar la etiqueta @ResponseBody
	public Person getPerson(@PathVariable Integer id) {
		return persons.get(id - 1);
	}

	// Anotar request mapping "/", "", con metodo POST y produciendo json y xml
	@RequestMapping(value = {"/",""}, method = RequestMethod.POST, 
			produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
	// Anotar response status NO CONTENT
	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	// En metodos post la etiqueta @RequestBody es obligatoria
	public void createPerson(@RequestBody Person person) {
		persons.add(person);
	}

	// Anotar request mapping "/getException", con metodo GET y produciendo json
	// y xml. Analizar implementación
	@RequestMapping(value = "/getException", method = RequestMethod.GET, 
			produces = {} )
	public ResponseEntity<?> getException() {
		//try {
			throw new IllegalArgumentException("Argumentos Inválidos");
			//return new ResponseEntity<Person>(Person.builder().name("Nestor Soto").age(33).build(),HttpStatus.OK);
		/*
		} catch (IllegalArgumentException ex) {
			String errorMessage = "Exception: " + ex.getMessage();

			RestResponseError rre = new RestResponseError(HttpStatus.BAD_REQUEST, errorMessage,
					ex.getClass().getSimpleName());

			return new ResponseEntity<RestResponseError>(rre, HttpStatus.BAD_REQUEST);
		}
		*/
		
	}

}
