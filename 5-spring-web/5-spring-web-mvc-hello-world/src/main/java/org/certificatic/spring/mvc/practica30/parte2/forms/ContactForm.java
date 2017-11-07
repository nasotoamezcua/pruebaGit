package org.certificatic.spring.mvc.practica30.parte2.forms;

import java.util.List;

import javax.validation.constraints.Size;

import lombok.Data;

@Data
public class ContactForm {
	
	@Size(min = 6, max =20, message = "name: Debe contener minimo 6 caracteres")
	private String name;
	private String email;
	private String gender;
	@Size(min = 5, max =10, message = "password: Debe contener entre 5 y 10 caracteres")
	private String password;
	private String confirmPassword;
	private List<String> courses;
	private String tutor;
	private String hiddenMessage;
}
