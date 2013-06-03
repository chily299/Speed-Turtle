package com.theinvader360.scene2dtutorial.swiperace;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.input.GestureDetector.GestureListener;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Event;
import com.badlogic.gdx.scenes.scene2d.EventListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
public class Menu implements Screen{
	
	
	// Generate a 1x1 white texture and store it in the skin named "white".
	Table table;
	
	MyGame game;
	private Stage stage;
	
	public Menu(MyGame _migame) {
		game = _migame;
		stage = new Stage();
		//Gdx.input.setInputProcessor(stage);
		
		table = new Table();
		table.setLayoutEnabled(false);
		
				TextButton button = new TextButton("Jugar", Assets.skin);
				button.setName("Jugar");
				button.setBounds(Assets.escala, Assets.escala*3,Assets.escala,Assets.escala);
				
				
				TextButton butto2n = new TextButton("Puntuaciones", Assets.skin);
				butto2n.setBounds(Assets.escala, Assets.escala*2,Assets.escala,Assets.escala);
				table.add(butto2n);
				
				TextButton salir = new TextButton("salir", Assets.skin);
				salir.setBounds(Assets.escala, Assets.escala*1,Assets.escala,Assets.escala);
				table.add(salir);
				
				//table.add(new Image(skin.newDrawable("white", Color.RED))).size(64);
				stage.addActor(table);

				
					
			  
				
				table.add(button);
				
				button.addListener(new EventListener() {
					
					@Override
					public boolean handle(Event event) {
						// TODO Auto-generated method stub
						//System.out.println(""+event.toString());
						
						if(event.toString() == "touchDown"){
							game.jugar();
						}
						
						return false;
					}
				});
				
				salir.addListener(new EventListener() {
					
					@Override
					public boolean handle(Event event) {
						// TODO Auto-generated method stub
						//System.out.println(""+event.toString());
						
						if(event.toString() == "touchDown"){
							Gdx.app.exit();
						}
						
						return false;
					}
				});
		
	}

	@Override
	public void render(float delta) {
		// TODO Auto-generated method stub
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
		stage.act(delta);
		stage.draw();
	}


	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub
		stage.setViewport(MyGame.WIDTH, MyGame.HEIGHT, true);
		stage.getCamera().translate(-stage.getGutterWidth(), -stage.getGutterHeight(), 0);
	}


	@Override
	public void show() {
		// TODO Auto-generated method stub
		//Gdx.input.setInputProcessor(new GestureDetector(this));
		Gdx.input.setInputProcessor(stage);
	}


	@Override
	public void hide() {
		// TODO Auto-generated method stub
    	Gdx.input.setInputProcessor(null);
	}


	@Override
	public void pause() {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void resume() {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		
	}

}
