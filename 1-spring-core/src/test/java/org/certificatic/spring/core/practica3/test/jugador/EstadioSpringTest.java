package org.certificatic.spring.core.practica3.test.jugador;


import org.certificatic.spring.core.practica3.jugador.JugadorFutbol;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class EstadioSpringTest {

	private static ApplicationContext applicationContext;

	@BeforeClass
	public static void beforeClass() {
		// Instanciar ApplicationContext
		String ruta = "spring/practica3/jugador-application-context.xml";
		applicationContext = new ClassPathXmlApplicationContext(ruta);
	}

	@Test
	public void estadioSpringTest1() {

		log.info("estadioSpringTest1 -------------------");

		// Implementar
		
		JugadorFutbol jugadorFutbol = (JugadorFutbol) applicationContext.getBean("jugadorBean");
		
		Assert.assertNotNull(jugadorFutbol);
		Assert.assertNotNull(jugadorFutbol.getTorneo());
		Assert.assertNotNull(jugadorFutbol.getTorneo().getEvento());
		
		log.info("{}" , jugadorFutbol);
		log.info("{}" , jugadorFutbol.getTorneo());
		log.info("{}" , jugadorFutbol.getTorneo().getEvento());
	}

	@Test
	public void estadioSpringTest2() {
		log.info("estadioSpringTest2 -------------------");

		//Implementar
	}

}
