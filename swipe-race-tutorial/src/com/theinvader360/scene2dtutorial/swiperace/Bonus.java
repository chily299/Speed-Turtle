package com.theinvader360.scene2dtutorial.swiperace;

import static com.badlogic.gdx.scenes.scene2d.actions.Actions.*;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;

public class Bonus extends EnemyCar{
	private int tipo;
	private int index;
	
	public boolean esActivo = false;

	public Bonus(float x, float y) {
		super(x, y);
		// TODO Auto-generated constructor stub
		tipo = MathUtils.random(0, 2);
		if(tipo == 0){
				setColor(Color.GREEN);
			}else if(tipo == 1){
				setColor(Color.RED);
				} else if(tipo == 2){
					setColor(Color.BLUE);
				}

		esActivo = false;
		index = -1;

	}
	
	public void draw(SpriteBatch batch, float parentAlpha) {
		batch.setColor(getColor().r, getColor().g, getColor().b, getColor().a);		
		if(tipo == 0){

			if(esActivo){
				batch.draw(Assets.bonus_boton, getX(), getY(), getWidth()/2, getHeight()/2, Assets.escala, Assets.escala, 1, 1, getRotation());			
			}else{
				batch.draw(Assets.bonus_boton, getX(), getY(), getWidth()/2, getHeight()/2, Assets.escala, Assets.escala, 1, 1, getRotation());			
				}
			}else if(tipo == 1){
				if(esActivo){
					batch.draw(Assets.bonus_boton, getX(), getY(), getWidth()/2, getHeight()/2, Assets.escala, Assets.escala, 1, 1, getRotation());			
				}else{
					batch.draw(Assets.bonus_boton, getX(), getY(), getWidth()/2, getHeight()/2, Assets.escala, Assets.escala, 1, 1, getRotation());			
					} 
			}else if(tipo == 2){
				if(esActivo){
					batch.draw(Assets.bonus_boton, getX(), getY(), getWidth()/2, getHeight()/2, Assets.escala, Assets.escala, 1, 1, getRotation());			
				}else{
					batch.draw(Assets.bonus_boton, getX(), getY(), getWidth()/2, getHeight()/2, Assets.escala, Assets.escala, 1, 1, getRotation());			
					}

		batch.draw(Assets.bonus_boton, getX(), getY(), getWidth()/2, getHeight()/2, Assets.escala, Assets.escala, 1, 1, getRotation());
		}else if(tipo == 1){
			batch.draw(Assets.bonus_boton, getX(), getY(), getWidth()/2, getHeight()/2, Assets.escala, Assets.escala, 1, 1, getRotation());
			} else if(tipo == 2){
				batch.draw(Assets.bonus_boton, getX(), getY(), getWidth()/2, getHeight()/2, Assets.escala, Assets.escala, 1, 1, getRotation());

			}
		//Assets.font.draw(batch,""+index, getX(), getY());
	}
	
	public void crash(int posicion) {
		clearActions();
		index = posicion;
		//addAction(fadeOut(1f));
		if (posicion == 0) addAction(sequence(parallel(rotateBy(-720, 1f), moveTo(0, 0,1f))));
		if (posicion == 1) addAction(sequence(parallel(rotateBy(720, 1f), moveTo(Assets.bonus_boton.getRegionWidth() , 0,1f) )));
		if (posicion == 2) addAction(sequence(parallel(rotateBy(720, 1f), moveTo(Assets.bonus_boton.getRegionWidth() *2, 0,1f))));
		if (posicion > 2) {addAction(sequence(parallel(rotateBy(-720, 1f), moveTo(Gdx.graphics.getWidth(), Gdx.graphics.getHeight(),1f),fadeOut(0.8f)), removeActor()));
		}
		}

	
	
	public int getindex(){
		return index;
	}
	
	public void BonusActivo(){
		clearActions();
		setColor(Color.PINK);
		if(tipo == 0){
			}else if(tipo == 1){
				} else if(tipo == 2){
				}
		addAction(sequence(parallel(rotateBy(-720, 5f)),visible(false)));
	}
	
	public void ActivarBonus(){
		esActivo = true;
	}
	
	
	
	public void BonusPasivo(){
		if(tipo == 0){
			}else if(tipo == 1){
				} else if(tipo == 2){
				}
		
	}

}
