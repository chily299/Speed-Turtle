package com.theinvader360.scene2dtutorial.swiperace;

import static com.badlogic.gdx.scenes.scene2d.actions.Actions.*;

<<<<<<< HEAD
import java.util.Iterator;

=======
>>>>>>> 3d0933654e3bfd918b5b56bc8df4a6eca9ff55c2
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.utils.Array;

public class PlayerCar extends Actor {
	private TrafficGame trafficGame;
	private Rectangle bounds = new Rectangle();
	private int lane;
	private int puntos;
	private Array<Bonus> bonusArray;
	
	
	public PlayerCar(TrafficGame trafficGame) {
		this.trafficGame = trafficGame;
		setWidth(160);
		setHeight(85);
		lane = 1;
		setPosition(100, trafficGame.lane1 - getHeight()/2);
		setColor(Color.WHITE);
		bonusArray = new Array<Bonus>();
	}
	
	@Override
	public void act(float delta){
		super.act(delta);
		updateBounds();
	}

	@Override
	public void draw(SpriteBatch batch, float parentAlpha) {
		batch.setColor(getColor().r, getColor().g, getColor().b, getColor().a);		
		batch.draw(Assets.car, getX(), getY(), getWidth()/2, getHeight()/2, getWidth(), getHeight(), 1, 1, getRotation());
		
		batch.draw(Assets.bonus_boton, 0, 0 ,Assets.escala,Assets.escala);
		batch.draw(Assets.bonus_boton, Assets.bonus_boton.getRegionWidth(), 0,Assets.escala,Assets.escala);
		batch.draw(Assets.bonus_boton, Assets.bonus_boton.getRegionWidth() * 2, 0,Assets.escala,Assets.escala);
<<<<<<< HEAD
	
		
        

        //informacion
        Assets.font.draw(batch, "Puntos: ["+puntos+"]", Gdx.graphics.getWidth()-Gdx.graphics.getWidth()/10 , Gdx.graphics.getHeight()-Gdx.graphics.getHeight()/20);
        
	
=======
>>>>>>> 3d0933654e3bfd918b5b56bc8df4a6eca9ff55c2
	}
	
	private void updateBounds() {
		bounds.set(getX(), getY(), getWidth(), getHeight());
		Iterator<Bonus> iterB = bonusArray.iterator();
		while (iterB.hasNext()) {
			Bonus BonusActual = iterB.next();
			if(BonusActual.esActivo){
				if (BonusActual.isVisible()) {
					BonusActual.setX(getX());
					BonusActual.setY(getY());
				}else{
					iterB.remove();
					trafficGame.removeActor(BonusActual);
				}
				
			}
			
			
			
		}
	}

	public void tryMoveUp() {
		if ((getActions().size == 0) && (lane != 2)) moveToLane(lane+1);
	}

	public void tryMoveDown() {
		if ((getActions().size == 0) && (lane != 0)) moveToLane(lane-1);
	}
	
	private void moveToLane(int lane) {
		this.lane = lane;
		
		switch (lane) {
			case 0:
				addAction(moveTo(getX(), trafficGame.lane0 - getHeight()/2, 0.5f));
				break;
			case 1:
				addAction(moveTo(getX(), trafficGame.lane1 - getHeight()/2, 0.5f));
				break;
			case 2:
				addAction(moveTo(getX(), trafficGame.lane2 - getHeight()/2, 0.5f));
				break;
		}
	}

<<<<<<< HEAD
	public void button1(float x, float y){
		if(bonusArray.size > 0 ){
			Iterator<Bonus> iterB = bonusArray.iterator();
			while (iterB.hasNext()) {
				Bonus BonusActual = iterB.next();
				if (BonusActual.getBounds().x + BonusActual.getWidth() <= 0) {
					iterB.remove();
					removeActor(BonusActual);
				}
				if (BonusActual.getBounds().overlaps(new Rectangle(x,y,1,1))) {					
					BonusActual.ActivarBonus();
					BonusActual.BonusActivo();
					}
				
			}
=======
	public void button1(){
		if(bonusArray.size > 0){
			
>>>>>>> 3d0933654e3bfd918b5b56bc8df4a6eca9ff55c2
		}
		
	}
	
<<<<<<< HEAD
	
	public void agregarPoder(Bonus poder){
		bonusArray.add(poder);
		
=======
	public void agregarPoder(Bonus poder){
		bonusArray.add(poder);
>>>>>>> 3d0933654e3bfd918b5b56bc8df4a6eca9ff55c2
	}
	
	public int numeroPoderes(){
		return bonusArray.size;
	}
	
	public void sumarPuntos(int extra_puntos){
<<<<<<< HEAD
		puntos += extra_puntos;
=======
		puntos = extra_puntos;
>>>>>>> 3d0933654e3bfd918b5b56bc8df4a6eca9ff55c2
	}
	
	public Rectangle getBounds() {
		return bounds;
	}
}
