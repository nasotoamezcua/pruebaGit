package org.certificatic.spring.core.practica9.test.beandefinheritance.bean;

import org.certificatic.spring.core.practica9.beandefinitioninheritance.bean.ConnectionDataBase;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class BeanDefinitionInheritanceTest {

	@Test
	public void beanDefinitionInheritanceTest1() {
		
		String ruta = "spring/practica9/bean-def-inheritance-application-context.xml";
		ApplicationContext applicationContext = new ClassPathXmlApplicationContext(ruta);

		log.info("beanDefinitionInheritanceTest1 -------------------");

		// Implementar
		
		ConnectionDataBase conProd = applicationContext.getBean("connectionProdBean",ConnectionDataBase.class);
		Assert.assertNotNull(conProd);
		conProd.showInfo();
		
		ConnectionDataBase conTest = applicationContext.getBean("connectionTestBean",ConnectionDataBase.class);
		Assert.assertNotNull(conTest);
		conTest.showInfo();
		
	}

	@Test
	public void beanDefinitionInheritanceTest2() {
		
		String ruta = "spring/practica9/bean-def-inheritance-application-context2.xml";
		ApplicationContext applicationContext = new ClassPathXmlApplicationContext(ruta);

		log.info("beanDefinitionInheritanceTest2 -------------------");

		// Implementar
		
		ConnectionDataBase conProd = applicationContext.getBean("connectionProdBean",ConnectionDataBase.class);
		Assert.assertNotNull(conProd);
		conProd.showInfo();
		
		ConnectionDataBase conTest = applicationContext.getBean("connectionTestBean",ConnectionDataBase.class);
		Assert.assertNotNull(conTest);
		conTest.showInfo();
	}

}
