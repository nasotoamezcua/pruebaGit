package org.certificatic.spring.core.practica2.test.bean;

import org.certificatic.spring.core.practica2.bean.HolaMundo;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class HolaMundoSpringTest2 {


	@Test
	public void holaMundoSpringTest2() {
		log.info("holaMundoSpringTest2 -------------------------");
		
		String ruta = "spring/practica2/beans.xml";
		
		// Implementar IoC con ApplicationContext
		ApplicationContext context = new ClassPathXmlApplicationContext(ruta);
		
		HolaMundo holaMundoConst = (HolaMundo)context.getBean("holaMundo1");
		HolaMundo holaMundoProp = context.getBean("holaMundo2", HolaMundo.class);
		
		Assert.assertNotNull(holaMundoConst);
		Assert.assertNotNull(holaMundoProp);
		
		log.info("holaMundoConst: {}" , holaMundoConst);
		log.info("holaMundoProp: {}" , holaMundoProp);

	}
}
