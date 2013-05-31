package com.theinvader360.scene2dtutorial.swiperace;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.input.GestureDetector;
import com.badlogic.gdx.input.GestureDetector.GestureListener;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Stage;

public class GameScreen implements Screen, GestureListener {
	private Stage stage;
	private TrafficGame trafficGame;
	private MyGame game;
	EnemyCar salir;
	int blen = 0; 
	
	public GameScreen(MyGame _game) {
		stage = new Stage();
		trafficGame = new TrafficGame();
		stage.addActor(trafficGame);
		game = _game;
		
		//salir
		
		salir = new EnemyCar(Gdx.graphics.getWidth()-Assets.escala, 0);
		salir.clearActions();
		stage.addActor(salir);
	}
	
	public void resize(int width, int height) {
		stage.setViewport(MyGame.WIDTH, MyGame.HEIGHT, true);
		stage.getCamera().translate(-stage.getGutterWidth(), -stage.getGutterHeight(), 0);
	}

	@Override
	public void render(float delta) {
		//Gdx.gl.glClearColor(0, 0, 0, 1);
		
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
		
		stage.act(delta);
		stage.draw();
		
		//Gdx.gl.glEnable(GL10.GL_CULL_FACE);
		 // Enable face culling- be careful with spriteBatch, might cull sprites as well!
		 //Gdx.gl.glEnable(GL10.GL_CULL_FACE);
		 
		// What faces to remove with the face culling.
		 //Gdx.gl.glCullFace(GL10.GL_BACK);
		
	}

	@Override
	public void show() {
		Gdx.input.setInputProcessor(new GestureDetector(this));
		
	}

	@Override 
    public void hide() {
    	Gdx.input.setInputProcessor(null);
    }
	
	@Override
	public boolean fling(float velocityX, float velocityY, int button) {
		if (velocityY < -100) trafficGame.playerCar.tryMoveUp();
		if (velocityY > 100) trafficGame.playerCar.tryMoveDown();
		
		return false;
	}

	@Override public boolean touchDown(float x, float y, int pointer, int button) {
		
		if(y > Gdx.graphics.getHeight()-Assets.escala){
		
		
		trafficGame.playerCar.button1(x,Gdx.graphics.getHeight()- y);
		if(x > Gdx.graphics.getWidth()/2 +Gdx.graphics.getWidth()/4){
			if(salir.getBounds().overlaps(new Rectangle(x, y, 1, 1)));
				game.menu();
			}
		}
		

		return false;
	}
	
	@Override public void resume() {}
	@Override public void pause() {}
	@Override public void dispose() {}	
	@Override public boolean tap(float x, float y, int count, int button) {return false;}
	
	@Override public boolean longPress(float x, float y) {return false;}
	@Override public boolean pan(float x, float y, float deltaX, float deltaY) {return false;}
	@Override public boolean zoom(float initialDistance, float distance) {return false;}
	@Override public boolean pinch(Vector2 initialPointer1, Vector2 initialPointer2, Vector2 pointer1, Vector2 pointer2) {return false;}

}
