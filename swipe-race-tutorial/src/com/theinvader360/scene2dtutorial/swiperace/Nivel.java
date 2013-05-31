package com.theinvader360.scene2dtutorial.swiperace;

public class Nivel {
	int nivelActual;
	/*Total de distancia del nivel*/
	public float distancia; 
	/*Cada cuantos metros se generan enemigos*/
	public float frecuenciaEnemigos;
	/*Cada cuantos metros se generan bonuss*/
	public float frecuenciaBonus;
	/*Cada cuantos metros se generan puntos*/
	public float frecuenciaPuntos;
	/*genera enemigo en 1 linea unica*/
	public float enemigo_solo;
	/*genera enemigo en 2 lineas*/
	public float enemigo_doble;
	/*maximo de puntos del nivel no varia*/
	public float puntos;
	/*total de rapido del nivel no varia*/
	public float rapido;
	/*total de defensa del nivel no varia*/
	public float defensa;
	/*total de comida del nivel no varia*/
	public float comida;
	
	public Nivel() {
		// TODO Auto-generated constructor stub
	}
	
	public void setNivel(int _nivel){
		nivelActual = _nivel;
		switch (nivelActual) {
		case 1:
			break;

		default:
			break;
		}
	}

	public float getDistancia(){
		return distancia;
	}
}
