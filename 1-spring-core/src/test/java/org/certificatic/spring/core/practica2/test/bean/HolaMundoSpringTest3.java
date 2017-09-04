package org.certificatic.spring.core.practica2.test.bean;

import org.certificatic.spring.core.practica2.bean.HolaMundo;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;




import lombok.extern.slf4j.Slf4j;

@Slf4j
public class HolaMundoSpringTest3 {

	private static ApplicationContext factory;

	// Instanciar ApplicationContext Antes de ejecutar la clase de test
	String ruta = "spring/practica2/beans.xml";
	ApplicationContext context = new ClassPathXmlApplicationContext(ruta);
	

	@Test
	public void holaMundoSpringTest2() {
		log.info("holaMundoSpringTest2 -------------------------");

		// Implementar IoC con ApplicationContext
		
		HolaMundo holaMundoConst = context.getBean("holaMundo1", HolaMundo.class );
		HolaMundo holaMundoProp =  (HolaMundo)context.getBean("holaMundo2");
		
		Assert.assertNotNull(holaMundoConst);
		Assert.assertNotNull(holaMundoProp);
		
		log.info("holaMundoConst: {}", holaMundoConst);
		log.info("holaMundoProp: {}", holaMundoProp);

	}
}
