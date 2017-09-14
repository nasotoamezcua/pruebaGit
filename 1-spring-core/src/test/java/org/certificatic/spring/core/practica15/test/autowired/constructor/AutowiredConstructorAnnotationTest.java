package org.certificatic.spring.core.practica15.test.autowired.constructor;

import org.certificatic.spring.core.practica15.autowired.constructor.bean.Engineer;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class AutowiredConstructorAnnotationTest {

	@Test
	public void autowiredConstructorAnnotationTest() {

		log.info("autowiredConstructorAnnotationTest -------------------");

		String ctxFile = "spring/practica15/annotations-autowired-constructor-application-context.xml";

		// Implementar
		ApplicationContext context = new ClassPathXmlApplicationContext(ctxFile);
		
		Engineer engineer = context.getBean(Engineer.class);
		
		Assert.assertNotNull(engineer);
		
		Assert.assertNotNull(engineer.getName());
		
		Assert.assertNotNull(engineer.getAge());
		
		Assert.assertNotNull(engineer.getNotebook());
		
		Assert.assertNotNull(engineer.getPen());
		
		Assert.assertNull(engineer.getDni());
		
		log.info("engineer: {}", engineer);
		
		((AbstractApplicationContext)context).close();
	}

}
