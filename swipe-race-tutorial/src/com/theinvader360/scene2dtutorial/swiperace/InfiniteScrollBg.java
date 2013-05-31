package com.theinvader360.scene2dtutorial.swiperace;

import static com.badlogic.gdx.scenes.scene2d.actions.Actions.*;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class InfiniteScrollBg extends Actor {
	float maximo;
	float suma;
	public float extra;
	
	public InfiniteScrollBg(float width, float height) {
		setWidth(width);
		setHeight(height);
		setPosition(width, 0);
		suma =0;
		maximo = 1000;
		addAction(forever(sequence(moveTo(0, 0, Assets.velocidad_local), moveTo(width, 0))));

		
	}

	@Override
	public void draw(SpriteBatch batch, float parentAlpha) {
		super.draw(batch, parentAlpha);
		batch.draw(Assets.road, getX()-getWidth(), getY(), getWidth() * 2, getHeight());
		Assets.font.draw(batch, "Distancia: ["+suma+"]", Gdx.graphics.getWidth()-Gdx.graphics.getWidth()/3, Gdx.graphics.getHeight()-Gdx.graphics.getHeight()/20);
	    
		
		
	}
	
	public boolean finCamino(){
		if(suma >= maximo){
			return true;
		}else return false;
		
	}
	
	public void cambiarVelociada(float extra){
		
		
	}
	
	@Override
	public void act(float delta) {
		// TODO Auto-generated method stub
		super.act(delta);
		if(suma >= maximo){
			pausaCamino();
		}else{
			suma+=0.1;
		}
	}
	
	public void pausaCamino(){
		clearActions();
	}
	
	
}
