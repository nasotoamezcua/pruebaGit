package org.certificatic.spring.core.practica2.test.bean;

import org.certificatic.spring.core.practica2.bean.HolaMundo;
import org.junit.Assert;
import org.junit.Test;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class HolaMundoNoSpringTest {

	@Test
	public void holaMundoNoSpringTest() {
		log.info("holaMundoNoSpringTest -------------------------");

		// Crear instancia de HolaMundo
		HolaMundo holaMundoConst = new HolaMundo("Hola Mundo: Inyeccion por constructor");
		HolaMundo holaMundoProp = new HolaMundo();
		holaMundoProp.setMensaje("Hola Mundo: Inyeccion por propiedad");
		
		Assert.assertNotNull(holaMundoConst);
		Assert.assertNotNull(holaMundoProp);
		
		log.info("holaMundoConst: {}" , holaMundoConst);
		log.info("holaMundoProp: {}" , holaMundoProp);

	}
}
