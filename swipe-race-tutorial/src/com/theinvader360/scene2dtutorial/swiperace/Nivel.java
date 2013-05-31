package com.theinvader360.scene2dtutorial.swiperace;

public class Nivel {
	int nivelActual;
	float distancia;
	
	
	
	public Nivel() {
		// TODO Auto-generated constructor stub
	}
	
	public void setNivel(int _nivel){
		nivelActual = _nivel;
		switch (nivelActual) {
		case 1:
			distancia = 50;
			break;

		default:
			break;
		}
	}

	public float getDistancia(){
		return distancia;
	}
}
