package org.certificatic.practica1.interfaces.operations.api.impl;

import org.certificatic.practica1.interfaces.operations.api.ICalculator;

public class Calculator<K> implements ICalculator<K> {
	
	
	private double accumulator;

	@Override
	public K set(double number) {
		// TODO Auto-generated method stub
		this.accumulator = number;
		K k = (K)this;
		return k;
	}

	@Override
	public double resul() {
		// TODO Auto-generated method stub
		return 0;
	}
	// Implementar
}
