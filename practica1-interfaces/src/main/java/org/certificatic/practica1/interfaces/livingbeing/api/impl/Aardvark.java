package org.certificatic.practica1.interfaces.livingbeing.api.impl;

import org.certificatic.practica1.interfaces.livingbeing.api.IBugEater;

public class Aardvark extends Animal implements IBugEater {
	
	public Aardvark(String subType) {
		// TODO Auto-generated constructor stub
		this.setSubType(subType);
	}
	
	// Implementar

	@Override
	public void born() {
		// TODO Auto-generated method stub
		System.out.println("born like a " + this.getType() + " (" + this.getSubType() + ")");
		
	}

	@Override
	public void grow() {
		// TODO Auto-generated method stub
		System.out.println("grow like a " + this.getType() + " (" + this.getSubType() + ")");
		
	}

	@Override
	public void bread() {
		// TODO Auto-generated method stub
		System.out.println("bread like a " + this.getType() + " (" + this.getSubType() + ")");
		
	}

	@Override
	public void die() {
		// TODO Auto-generated method stub
		System.out.println("die like a " + this.getType() + " (" + this.getSubType() + ")");
	}
	
	public void eatBug(){
		System.out.println("eatBug like a " + this.getType() + " (" + this.getSubType() + ")");
	}	
	
}
