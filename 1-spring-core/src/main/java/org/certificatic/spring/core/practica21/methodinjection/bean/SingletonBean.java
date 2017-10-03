package org.certificatic.spring.core.practica21.methodinjection.bean;

import org.certificatic.spring.core.practica21.methodinjection.bean.api.IProcessor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
//Utilizando ApplicationContextAware
public class SingletonBean implements ApplicationContextAware {
	
	private @Setter ApplicationContext applicationContext;

	@Value("#{1 + 2}")
	private @Setter Integer iterations;

	// Inyecta un prototype IProcessor en este bean singleton (����como????)
	//private IProcessor processor;

	public String process(String data) {
		log.info("SingletonBean id [{}]: sending to process data ...",
				super.hashCode());

		return getProcessor().processData(data, iterations);
	}
	
	//Se inyecta el bean IProcessor
	public IProcessor getProcessor() {
		return this.applicationContext.getBean(IProcessor.class);
	}

}
// Define la estructura para inyectar m�todos en �ste bean