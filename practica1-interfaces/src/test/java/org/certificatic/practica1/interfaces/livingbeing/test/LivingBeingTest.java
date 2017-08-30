package org.certificatic.practica1.interfaces.livingbeing.test;

import org.certificatic.practica1.interfaces.livingbeing.api.IBugEater;
import org.certificatic.practica1.interfaces.livingbeing.api.ILivingBeing;
import org.certificatic.practica1.interfaces.livingbeing.api.impl.Aardvark;
import org.certificatic.practica1.interfaces.livingbeing.api.impl.Animal;
import org.certificatic.practica1.interfaces.livingbeing.api.impl.Plant;
import org.certificatic.practica1.interfaces.livingbeing.api.impl.VenusFlyTrap;
import org.junit.Assert;
import org.junit.Test;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class LivingBeingTest {

	@Test
	public void livingBeingTest() {
		log.info("livingBeingTest -------------------");

		// Implementar
		
		ILivingBeing iLivingBeing1 = new VenusFlyTrap("venus fly trap");
		ILivingBeing iLivingBeing2 = new Aardvark("Aardvark");
		
		log.info("VenusFlyTrap: {}", iLivingBeing1);
		
		iLivingBeing1.born();
		iLivingBeing1.grow();
		iLivingBeing1.breed();
		iLivingBeing1.die();
		
		((IBugEater)iLivingBeing1).eatBug();
		
		Assert.assertTrue(iLivingBeing1 instanceof VenusFlyTrap);
		Assert.assertTrue(iLivingBeing1 instanceof Plant);
		Assert.assertTrue(iLivingBeing1 instanceof IBugEater);
		Assert.assertFalse(iLivingBeing1 instanceof Animal);
		
		log.info("Aardvark: {}", iLivingBeing2);
		
		iLivingBeing2.born();
		iLivingBeing2.grow();
		iLivingBeing2.breed();
		iLivingBeing2.die();
		
		((IBugEater)iLivingBeing1).eatBug();
		
		Assert.assertTrue(iLivingBeing2 instanceof Aardvark);
		Assert.assertTrue(iLivingBeing2 instanceof Animal);
		Assert.assertTrue(iLivingBeing2 instanceof IBugEater);
		Assert.assertEquals(iLivingBeing2.getClass().getSimpleName(), Aardvark.class.getSimpleName());
		
	}

}
