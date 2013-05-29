package com.theinvader360.scene2dtutorial.swiperace;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Assets {
	public static TextureAtlas atlas;
	public static TextureRegion car;
	public static TextureRegion road;

	public static void load() {
		atlas = new TextureAtlas(Gdx.files.internal("images.atlas"));
		car = atlas.findRegion("car");
		road = atlas.findRegion("road");
		//road.setRegion(0, 0, 55, 120);
		
	}

	public static void dispose() {
		atlas.dispose();
	}
}
