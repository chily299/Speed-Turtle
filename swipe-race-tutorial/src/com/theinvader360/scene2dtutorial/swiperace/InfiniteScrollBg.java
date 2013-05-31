package com.theinvader360.scene2dtutorial.swiperace;

import static com.badlogic.gdx.scenes.scene2d.actions.Actions.*;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class InfiniteScrollBg extends Actor {
	float maximo;
	float recorrido;
	public float fin_rapido;
	boolean activo;
	float velocidad;
	boolean velocidad_normal;
	
	public InfiniteScrollBg(float width, float height) {
		setWidth(width);
		setHeight(height);
		setPosition(width, 0);
		recorrido =0;
		maximo = 1000;
		activo = true;
		velocidad = 2;
		velocidad_normal = true;
		//addAction(forever(sequence(moveTo(0, 0, Assets.velocidad_local), moveTo(width, 0))));
		iniciarCamino(velocidad, maximo);
		
	}

	@Override
	public void draw(SpriteBatch batch, float parentAlpha) {
		super.draw(batch, parentAlpha);
		batch.draw(Assets.road, getX()-getWidth(), getY(), getWidth() * 2, getHeight());
		if(velocidad_normal){
		Assets.font.draw(batch, "Distancia: ["+recorrido+"]", Gdx.graphics.getWidth()-Gdx.graphics.getWidth()/3, Gdx.graphics.getHeight()-Gdx.graphics.getHeight()/20);
		}else{ Assets.font.draw(batch, "2x Distancia: ["+recorrido+"]", Gdx.graphics.getWidth()-Gdx.graphics.getWidth()/3, Gdx.graphics.getHeight()-Gdx.graphics.getHeight()/20);
		}
		
		
	}
	
	public void iniciarCamino(float _velocidad, float _distancia){
		velocidad = _velocidad;
		maximo = _distancia;
		addAction(forever(sequence(moveTo(0, 0, velocidad ), moveTo(getWidth(), 0))));
	}
	
	
	
	public boolean finCamino(){
		if(recorrido >= maximo){
			return true;
		}else return false;
		
	}
	
	public void cambiarVelociada(){
		if(velocidad_normal){
		fin_rapido = 50 + recorrido;
		clearActions();
		addAction(forever(sequence(moveTo(0, 0, velocidad -1f ), moveTo(getWidth(), 0))));
		velocidad_normal = false;
		}else{
			fin_rapido = 50 + recorrido;
			velocidad_normal = false;
		}
	}
	
	@Override
	public void act(float delta) {
		// TODO Auto-generated method stub
		super.act(delta);
		if(activo){
			if(recorrido >= maximo){
				pausaCamino();
			}else{
				if(recorrido < fin_rapido  ){
				recorrido+=0.2;
				}else{
					if(!velocidad_normal){
					clearActions();
					addAction(forever(sequence(moveTo(0, 0, velocidad ), moveTo(getWidth(), 0))));
					velocidad_normal = true;
					}
					recorrido+=0.1;
				}
				
				
			}
		}
	}
	
	public void pausaCamino(){
		activo = false;
		clearActions();
	}
	
	
}
