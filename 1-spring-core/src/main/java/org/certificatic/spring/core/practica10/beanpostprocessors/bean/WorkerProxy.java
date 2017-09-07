package org.certificatic.spring.core.practica10.beanpostprocessors.bean;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
public class WorkerProxy implements IWorker{
	
	private @Getter @Setter Worker originalWorker;
	
	@Override
	public void init() {
		System.out.println("[init] Initializing WorkerProxy.");
		
	}

	@Override
	public void showInfo() {
		System.out.println("[WorkerProxy] Aqui puedo hacer cosas antes");
		originalWorker.showInfo();
		System.out.println("[WorkerProxy] Aqui puedo hacer cosas despues");
		
	}

	@Override
	public void destroy() {
		System.out.println("[destroy] Destroying WorkerProxy.");
		
	}
	
	@Override
	public void setName(String name) {
		originalWorker.setName(name);
		
	}

	@Override
	public String getName() {
		return originalWorker.getName();
	}

	@Override
	public void setAge(int age) {
		this.originalWorker.setAge(age);
		
	}

	@Override
	public int getAge() {
		return originalWorker.getAge();
	}

}
