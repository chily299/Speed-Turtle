package com.theinvader360.scene2dtutorial.swiperace;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Assets {
	public static TextureAtlas atlas;
	public static TextureRegion car;
	public static TextureRegion road;
	public static TextureRegion bonus_boton;
	public static TextureRegion poder;
	public static final float escala = Gdx.graphics.getHeight()/5;
	

	public static float velocidad_global = 1f;
	public static float velocidad_local = velocidad_global;
	public static BitmapFont font = new BitmapFont();

	

	public static void load() {
		atlas = new TextureAtlas(Gdx.files.internal("empaquetado.atlas"));
		car = atlas.findRegion("0001");
		road = atlas.findRegion("road");
		bonus_boton = atlas.findRegion("espacio_poder");
		poder = atlas.findRegion("poder");
	}

	public static void dispose() {
		atlas.dispose();
	}
}
