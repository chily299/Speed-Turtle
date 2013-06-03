package com.theinvader360.scene2dtutorial.swiperace;

import static com.badlogic.gdx.scenes.scene2d.actions.Actions.*;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class InfiniteScrollBg extends Actor {
	float Distancia;
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
		Distancia = 1000;
		activo = true;
		velocidad =9;
		velocidad_normal = true;
		//addAction(forever(sequence(moveTo(0, 0, Assets.velocidad_local), moveTo(width, 0))));
		iniciarCamino(velocidad, Distancia);
		
	}

	@Override
	public void draw(SpriteBatch batch, float parentAlpha) {
		super.draw(batch, parentAlpha);
		batch.draw(Assets.road, getX()-getWidth(), getY(), getWidth() * 2, getHeight());
		if(velocidad_normal){
		Assets.font.draw(batch, "["+(int)(Distancia-recorrido)+"]", Gdx.graphics.getWidth()-Gdx.graphics.getWidth()/3, Gdx.graphics.getHeight()-Gdx.graphics.getHeight()/20);
		}else{ Assets.font.draw(batch, "["+(int)(Distancia-recorrido)+"]", Gdx.graphics.getWidth()-Gdx.graphics.getWidth()/3, Gdx.graphics.getHeight()-Gdx.graphics.getHeight()/20);
		}
		
		
	}
	
	public void iniciarCamino(float _velocidad, float _distancia){
		velocidad = _velocidad;
		Distancia = _distancia;
		recorrido =0;
		activo = true;
		velocidad_normal = true;
		fin_rapido = 0;
	}
	
	
	
	public boolean finCamino(){
		if(recorrido >= Distancia){
			return true;
		}else return false;
		
	}
	
	public void cambiarVelociada(){
		if(velocidad_normal){
		fin_rapido = 50 + recorrido;
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
			if(getX() > 0){
			setX(getX()-velocidad);
			}else if(getX()<=0){
			setX(getWidth());
			}
			
			
			
			if(recorrido >= Distancia){
				pausaCamino();
			}else{
				if(recorrido < fin_rapido  ){
				recorrido+=0.2;
				setX(getX()-velocidad); //7+7 velocidad x2
				}else{
					if(!velocidad_normal){
					
					velocidad_normal = true;
					}
					recorrido+=0.1;
				}
				
				
			}
		}
	}
	
	public void pausaCamino(){
		activo = false;
	}
	
	
}
