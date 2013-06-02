package com.theinvader360.scene2dtutorial.swiperace;

import java.util.Iterator;

import com.badlogic.gdx.Gdx;
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
	private int linea;
	private Nivel nivel;
	private float ultimoEnemigo;
	private float ultimoBonus;
	private float ultimoPunto;
	private int contPuntos;
	public boolean gano;
	
	public TrafficGame(int _nivel) {
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
		nivel = new Nivel();
		nuevoJuego(_nivel);
	}
	
	public void GanoNivel(){
		gano = true;
		
	}
	
	public void PerdioNivel(){
		backgroundRoad.pausaCamino();
		
	}
	
	@Override
	public void act(float delta) {
		super.act(delta);
		backgroundRoad.act(delta);
		
		
		if(backgroundRoad.finCamino()){ //
			GanoNivel();
		}
		
		
		if(!backgroundRoad.finCamino()){
		//pantalla de ganar
			
		if(playerCar.estaVivo()){
		
		if (TimeUtils.nanoTime() - lastCarTime > 800000000f) spawnCar();
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
                
                if (enemyCar.getX() > playerCar.getX()) {
                    if (enemyCar.getY() > playerCar.getY()){ enemyCar.crash(true, true);}
                    else{ enemyCar.crash(true, false);}
                    playerCar.perderVida();
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
		
		}else {
			PerdioNivel();
		}
		}else{
			GanoNivel();
		}
	}

	private void spawnCar() {
		
		int lineasUsadas =0;
		float yPos = 0;
		if(backgroundRoad.recorrido - ultimoEnemigo > nivel.frecuenciaEnemigos){
			EnemyCar enemyCar;
			switch (nivel.getTipoEnemigo()) {
			case 1:
				linea = MathUtils.random(0, 2);
				
				if (linea == 0) yPos = lane0;
				if (linea == 1) yPos = lane1;
				if (linea == 2) yPos = lane2;
				
				enemyCar = new EnemyCar(getWidth(), yPos);
				enemyCars.add(enemyCar);
				addActor(enemyCar);
				lineasUsadas = linea;
				while(linea == lineasUsadas){
					linea = MathUtils.random(0, 2);
				}
				
				if (linea == 0) yPos = lane0;
				if (linea == 1) yPos = lane1;
				if (linea == 2) yPos = lane2;
				
				enemyCar = new EnemyCar(getWidth(), yPos);
				enemyCars.add(enemyCar);
				addActor(enemyCar);
				break;

			default:
				linea = MathUtils.random(0, 2);
				
				if (linea == 0) yPos = lane0;
				if (linea == 1) yPos = lane1;
				if (linea == 2) yPos = lane2;
				
				enemyCar = new EnemyCar(getWidth(), yPos);
				enemyCars.add(enemyCar);
				addActor(enemyCar);
				lineasUsadas = linea;
				break;
			}
			
			
			ultimoEnemigo = backgroundRoad.recorrido;
		}
		
		
		if(backgroundRoad.recorrido - ultimoBonus > nivel.frecuenciaBonus){
			linea = MathUtils.random(0, 2);
			while(linea == lineasUsadas){
				linea = MathUtils.random(0, 2);
			}
			
			if (linea == 0) yPos = lane0;
			if (linea == 1) yPos = lane1;
			if (linea == 2) yPos = lane2;
			
			Bonus enemyCar = new Bonus(getWidth(), yPos,nivel.getTipoBonus());
			bonusArray.add(enemyCar);
			addActor(enemyCar);
			ultimoBonus = backgroundRoad.recorrido;
		}
		
		if(backgroundRoad.recorrido - ultimoPunto > nivel.frecuenciaPuntos && contPuntos < nivel.puntos){
			linea = MathUtils.random(0, 2);
			
			if (linea == 0) yPos = lane0;
			if (linea == 1) yPos = lane1;
			if (linea == 2) yPos = lane2;
			
			Puntos enemyCar = new Puntos(getWidth(), yPos);
			pointsArray.add(enemyCar);
			addActor(enemyCar);
			ultimoPunto = backgroundRoad.recorrido;
			contPuntos++;
		}
		
		lastCarTime = TimeUtils.nanoTime();
		
		
	}
	
	public void Rapido(){
		backgroundRoad.cambiarVelociada();
	}
	
	public void nuevoJuego(int _nivel){
		nivel.setNivel(_nivel);
		backgroundRoad.Distancia = nivel.distancia;
		ultimoBonus = ultimoPunto = ultimoEnemigo =0;
		gano = false;
		
	}
	
	@Override
	public void draw(SpriteBatch batch, float parentAlpha) {
		batch.setColor(Color.WHITE);
		super.draw(batch, parentAlpha);
		Assets.font.draw(batch, "Puntos generados: " +contPuntos, Gdx.graphics.getWidth()/5 , Gdx.graphics.getHeight()-Gdx.graphics.getHeight()/20);
		
		if(gano){
			Assets.font.draw(batch, "Nivel completo - Puntos de nivel: " +playerCar.getPuntos()+"/"+contPuntos, Gdx.graphics.getWidth()/3 , Gdx.graphics.getHeight()-Gdx.graphics.getHeight()/2);
			Assets.font.draw(batch, "Acumulado: " +9999999, Gdx.graphics.getWidth()/3 , Gdx.graphics.getHeight()-Gdx.graphics.getHeight()/3);
		}
	}
}
