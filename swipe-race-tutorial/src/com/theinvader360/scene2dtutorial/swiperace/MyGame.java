package com.theinvader360.scene2dtutorial.swiperace;

import com.badlogic.gdx.Game;

public class MyGame extends Game {
	public final static int WIDTH = 800;
	public final static int HEIGHT = 480;
	private GameScreen gameScreen;
	private Menu menuScrean;

	@Override
	public void create() {
		Assets.load();
		//gameScreen = new GameScreen(this);
		menuScrean = new Menu(this);
		setScreen(gameScreen);
		setScreen(menuScrean);
	}

	@Override
	public void dispose() {
		Assets.dispose();
		
	}
	
	public void jugar(){
		
		setScreen(new GameScreen(this));
	}
	
	public void menu(){
		setScreen(menuScrean);
		
	}
	
}
