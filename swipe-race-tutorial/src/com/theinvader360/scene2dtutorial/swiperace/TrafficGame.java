package com.theinvader360.scene2dtutorial.swiperace;

import static com.badlogic.gdx.scenes.scene2d.actions.Actions.fadeOut;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.moveTo;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.parallel;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.removeActor;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.rotateBy;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.sequence;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.visible;

import java.util.Iterator;

import javax.sound.midi.Sequence;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.scenes.scene2d.Event;
import com.badlogic.gdx.scenes.scene2d.EventListener;
import com.badlogic.gdx.scenes.scene2d.actions.MoveToAction;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
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
	public Label Infonivel;
	public Label labelPuntosNivel;
	public Table table;
	
	public TrafficGame(int _nivel) {
		setBounds(0,0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
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
		
		
		
		//tabla final
		table = new Table();
		table.setLayoutEnabled(false);
		
				TextButton siguiente = new TextButton("Siguiente", Assets.skin);
				siguiente.setBounds(Gdx.graphics.getWidth()/2, Assets.escala*1,Assets.escala,Assets.escala);
				table.add(siguiente);
				Infonivel =new Label("Total Puntos Nivel: "+playerCar.getPuntos(),  Assets.skin);
				Infonivel.setBounds(Gdx.graphics.getWidth()/3, Assets.escala*2,Assets.escala,Assets.escala);
				table.add(Infonivel);
				labelPuntosNivel =new Label("Nivel 1-"+nivel.nivelActual+" Completo",  Assets.skin);
				labelPuntosNivel.setBounds(Gdx.graphics.getWidth()/3, Assets.escala*3,Assets.escala,Assets.escala);
				table.add(labelPuntosNivel);
				addActor(table);
				
				siguiente.addListener(new EventListener() {
					
					@Override
					public boolean handle(Event event) {
						// TODO Auto-generated method stub
						//System.out.println(""+event.toString());
						
						if(event.toString() == "touchDown"){
							nuevoJuego(++nivel.nivelActual);
						}
						
						return false;
					}
				});
				
				
				nuevoJuego(_nivel);
		
	}
	
	public void mostrarTotalNivel(){
		//table.addAction(visible(true));
		
		Infonivel.setText("Nivel "+nivel.nivelActual+" Completo");
		labelPuntosNivel.setText("Total Puntos Nivel: "+playerCar.getPuntos());
		table.setVisible(true);
	}
	
	public void ocultarTotalNivel(){

		//table.addAction(sequence( fadeOut(0.8f),visible(false)));
		table.setVisible(false);
	}
	
	public void GanoNivel(){
		gano = true;
		mostrarTotalNivel();
		limpiarPantalla();
	}
	
	public void limpiarPantalla() {
		Iterator<EnemyCar> iter = enemyCars.iterator();
		while (iter.hasNext()) {
			EnemyCar enemyCar = iter.next();
			enemyCar.remove();
		}
		
		
		//puntos
		Iterator<Puntos> iterP = pointsArray.iterator();
		while (iterP.hasNext()) {
			Puntos enemyCar = iterP.next();
			enemyCar.remove();
		}
		//bonus
		Iterator<Bonus> iterB = bonusArray.iterator();
		while (iterB.hasNext()) {
			Bonus enemyCar = iterB.next();
			enemyCar.remove();
			
		}
	}
	
	public void PerdioNivel(){
		backgroundRoad.pausaCamino();
		limpiarPantalla();
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
		//backgroundRoad.Distancia = nivel.distancia;
		ultimoBonus = ultimoPunto = ultimoEnemigo = contPuntos =0;
		//playerCar = new PlayerCar(this);
		bonusArray.clear();
		enemyCars.clear();
		pointsArray.clear();
		backgroundRoad.iniciarCamino(nivel.velocidad, nivel.distancia);
		playerCar.nuevo();
		Infonivel.setText("Nivel 1-"+nivel.nivelActual+" Completo");
		gano = false;
		ocultarTotalNivel();
		
	}
	
	@Override
	public void draw(SpriteBatch batch, float parentAlpha) {
		batch.setColor(Color.WHITE);
		super.draw(batch, parentAlpha);
		Assets.font.draw(batch, "Nivel: " +nivel.nivelActual, Gdx.graphics.getWidth()/3 , Gdx.graphics.getHeight()-Gdx.graphics.getHeight()/20);
	}
}
