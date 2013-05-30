package com.theinvader360.scene2dtutorial.swiperace;

import java.util.Iterator;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.TimeUtils;

public class TrafficGame extends Table {
	private InfiniteScrollBg backgroundRoad;
	private Array<EnemyCar> enemyCars;
	private Array<Bonus> bonusArray;
	private Array<Puntos> pointsArray;
	private long lastCarTime = 0;
	public  float lane2 = 0;
	public  float lane1 = 0;
	public  float lane0 = 0;
	public PlayerCar playerCar;
	private int tipo, linea;
	
	public TrafficGame() {
		setBounds(0,0, 800, 480);
		setClip(true);
		lane2 = getHeight()/2 + getHeight()/4;
		lane1 = getHeight()/2;
		lane0 = getHeight()/2 - getHeight()/4;
		backgroundRoad = new InfiniteScrollBg(getWidth(),getHeight());
		addActor(backgroundRoad);
		playerCar = new PlayerCar(this);
		addActor(playerCar);
		enemyCars = new Array<EnemyCar>();		
		bonusArray = new Array<Bonus>();	
		pointsArray = new Array<Puntos>();	
		
	}
	
	@Override
	public void act(float delta) {
		super.act(delta);
		
		if (TimeUtils.nanoTime() - lastCarTime > 3000000000f) spawnCar();
		//enemigos
		Iterator<EnemyCar> iter = enemyCars.iterator();
		while (iter.hasNext()) {
			EnemyCar enemyCar = iter.next();
			if (enemyCar.getBounds().x + enemyCar.getWidth() <= 0) {
				iter.remove();
				removeActor(enemyCar);
			}
			if (enemyCar.getBounds().overlaps(playerCar.getBounds())) {
                iter.remove();
                playerCar.perderVida();
                if (enemyCar.getX() > playerCar.getX()) {
                    if (enemyCar.getY() > playerCar.getY()) enemyCar.crash(true, true);
                    else enemyCar.crash(true, false);
                }
                else {
                    if (enemyCar.getY() > playerCar.getY()) enemyCar.crash(false, true);
                    else enemyCar.crash(false, false);
                }
			}
		}
		
		
		//puntos
		Iterator<Puntos> iterP = pointsArray.iterator();
		while (iterP.hasNext()) {
			Puntos enemyCar = iterP.next();
			if (enemyCar.getBounds().x + enemyCar.getWidth() <= 0) {
				iterP.remove();
				removeActor(enemyCar);
			}
			if (enemyCar.getBounds().overlaps(playerCar.getBounds())) {
                iterP.remove();

                

                if (enemyCar.getX() > playerCar.getX()) {
                	playerCar.sumarPuntos(enemyCar.Puntos);
                    if (enemyCar.getY() > playerCar.getY()) enemyCar.crash(true, true);
                    else enemyCar.crash(true, false);
                }
                else {
                	playerCar.sumarPuntos(1);
                    if (enemyCar.getY() > playerCar.getY()) enemyCar.crash(false, true);
                    else enemyCar.crash(false, false);
                }
			}
		}
		//bonus
		Iterator<Bonus> iterB = bonusArray.iterator();
		while (iterB.hasNext()) {
			Bonus enemyCar = iterB.next();
			if (enemyCar.getBounds().x + enemyCar.getWidth() <= 0) {
				iterB.remove();
				removeActor(enemyCar);
			}
			if (enemyCar.getBounds().overlaps(playerCar.getBounds())) {
				iterB.remove();
				if(playerCar.numeroPoderes()>2){
					playerCar.sumarPuntos(+1);
				}
				enemyCar.crash(playerCar.numeroPoderes());
                   playerCar.agregarPoder(enemyCar);
                   
				}
			
		}
	}

	private void spawnCar() {
		linea = MathUtils.random(0, 2);
		float yPos = 0;
		if (linea == 0) yPos = lane0;
		if (linea == 1) yPos = lane1;
		if (linea == 2) yPos = lane2;
		tipo = MathUtils.random(0, 2);
		
		if (tipo == 0){
			EnemyCar enemyCar = new EnemyCar(getWidth(), yPos);
			enemyCars.add(enemyCar);
			addActor(enemyCar);
		}else if (tipo == 1){
			Bonus enemyCar = new Bonus(getWidth(), yPos);
			bonusArray.add(enemyCar);
			addActor(enemyCar);
			
		}else if (tipo == 2){
			Puntos enemyCar = new Puntos(getWidth(), yPos);
			pointsArray.add(enemyCar);
			addActor(enemyCar);
			
		}
		
		
		
		
		lastCarTime = TimeUtils.nanoTime();
	}
	
	@Override
	public void draw(SpriteBatch batch, float parentAlpha) {
		batch.setColor(Color.WHITE);
		super.draw(batch, parentAlpha);
	}
}
