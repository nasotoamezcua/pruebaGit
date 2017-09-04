package org.certificatic.spring.core.practica2.test.bean;

import org.certificatic.spring.core.practica2.bean.HolaMundo;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.core.io.ClassPathResource;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class HolaMundoSpringTest1 {

	@Test
	public void holaMundoNoSpringTest() {
		log.info("holaMundoNoSpringTest -------------------------");

		String ruta = "spring/practica2/beans.xml";

		// Implementar IoC con BeanFactory
		BeanFactory beanFactory = new XmlBeanFactory(new ClassPathResource(ruta) );
		
		HolaMundo holaMundoConst = beanFactory.getBean("holaMundo1", HolaMundo.class);
		HolaMundo holaMundoProp = beanFactory.getBean("holaMundo2", HolaMundo.class);
		
		Assert.assertNotNull(holaMundoConst);
		Assert.assertNotNull(holaMundoProp);
		
		log.info("holaMundoConst: {}" , holaMundoConst);
		log.info("holaMundoProp: {}" , holaMundoProp);
		
		

	}
}
