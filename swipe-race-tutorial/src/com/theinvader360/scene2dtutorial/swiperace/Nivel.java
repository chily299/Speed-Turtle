package com.theinvader360.scene2dtutorial.swiperace;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.MathUtils;

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
	
	public float velocidad;
	
	public float tipoPoder;
	
	
	
	public Nivel() {
		// TODO Auto-generated constructor stub
	}
	
	public Texture getBackground(){
		
		return null;
	}
	
	public void setNivel(int _nivel){
		nivelActual = _nivel;
		switch (nivelActual) {
		case 1:
			distancia = 800;
			puntos = 30;  
			frecuenciaEnemigos = 30;
			frecuenciaBonus = 20;
			frecuenciaPuntos = (distancia-100)/puntos ;
			enemigo_solo = 50;
			enemigo_doble = 0;
			rapido = 40;
			defensa = 40;
			comida = 20;
			velocidad = 7;
			
			break;
		case 2:
			distancia = 900;
			puntos = 35;
			frecuenciaEnemigos = 30;
			frecuenciaBonus = 100;
			frecuenciaPuntos = (distancia-100)/puntos ;
			enemigo_solo = 50;
			enemigo_doble =10;
			rapido = 40;
			defensa = 40;
			comida = 20;
			velocidad = 7;
			break;
		case 3:
			distancia = 1000;
			puntos = 40;
			frecuenciaEnemigos = 30;
			frecuenciaBonus = 80;
			frecuenciaPuntos = (distancia-100)/puntos ;
			enemigo_solo = 50;
			enemigo_doble =12;
			rapido = 40;
			defensa = 40;
			comida = 15;
			velocidad = 7;
			break;
		case 4:
			distancia = 1050;
			puntos = 35;
			frecuenciaEnemigos = 30;
			frecuenciaBonus = 110;
			frecuenciaPuntos = (distancia-100)/puntos ;
			enemigo_solo = 50;
			enemigo_doble =15;
			rapido = 20;
			defensa = 40;
			comida = 10;
			velocidad = 7;
			break;
		case 5:
			distancia = 1100;
			puntos = 50;
			frecuenciaEnemigos = 29;
			frecuenciaBonus = 60;
			frecuenciaPuntos = (distancia-100)/puntos ;
			enemigo_solo = 50;
			enemigo_doble =40;
			rapido = 40;
			defensa = 40;
			comida = 20;
			velocidad = 8;
			break;

		default:
			distancia = 500;
			puntos = 100;
			frecuenciaEnemigos = 30;
			frecuenciaBonus = 100;
			frecuenciaPuntos = (distancia-50)/puntos;
			enemigo_solo = 50;
			enemigo_doble = 0;
			rapido = 40;
			defensa = 40;
			comida = 20;
			velocidad = 5;
			
			break;
		}
	}

	public float getDistancia(){
		return distancia;
	}
	
	public int getTipoBonus(){
		
		tipoPoder = MathUtils.random(0, rapido+defensa+comida);
		
		if(tipoPoder > rapido + defensa){ // comida
			return 1;
		}
		
		if(tipoPoder > rapido){
			return 0;
		}
		
		return 2;
	}
	
	public int getTipoEnemigo(){
		
		tipoPoder = MathUtils.random(0, enemigo_solo+enemigo_doble);
		
		if(tipoPoder > enemigo_solo){ // comida
			return 1;
		}
		
		return 0;
	}
}
