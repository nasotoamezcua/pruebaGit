package org.certificatic.spring.core.practica8.test.factorymethod;

import org.certificatic.spring.core.practica8.factorymethod.bean.Student;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class FactoryMethodTest {

	private static ApplicationContext applicationContext;

	@Before
	public void beforeClass() {
		// Instanciar ApplicationContext
		String ruta = "spring/practica8/factory-method-application-context.xml";
		applicationContext = new ClassPathXmlApplicationContext(ruta);
	}

	@Test
	public void factoryMethodTest() {

		log.info("factoryMethodTest -------------------");

		// Implementar
		Student student1 = applicationContext.getBean("studen1Bean", Student.class);
		
		Assert.assertNotNull(student1);
		
		log.info("student1: {}", student1);
		
		((AbstractApplicationContext) applicationContext).close();
	}

	@Test
	public void factoryMethodTest2() {

		log.info("factoryMethodTest2 -------------------");

		// Implementar
		Student student1 = applicationContext.getBean("student2Bean", Student.class);
		
		Assert.assertNotNull(student1);
		
		log.info("student1: {}", student1);
		
		((AbstractApplicationContext) applicationContext).close();
	}

}
