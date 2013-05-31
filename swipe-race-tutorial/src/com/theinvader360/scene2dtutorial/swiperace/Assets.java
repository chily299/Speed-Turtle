package com.theinvader360.scene2dtutorial.swiperace;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Pixmap.Format;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;

public class Assets {
	public static TextureAtlas atlas;
	public static TextureRegion car;
	public static TextureRegion road;
	public static TextureRegion bonus_boton;
	public static TextureRegion poder;
	public static TextureRegion puntos;
	public static TextureRegion rapido_icono;
	public static TextureRegion comida_icono;
	public static TextureRegion defensa_icono;
	public static TextureRegion rapido;
	public static TextureRegion comida;
	public static TextureRegion defensa;
	public static final float escala = Gdx.graphics.getHeight()/5;
	

	public static float velocidad_global = 1f;
	public static float velocidad_local = velocidad_global;
	public static BitmapFont font = new BitmapFont();
	public static Skin skin;

	

	public static void load() {
		atlas = new TextureAtlas(Gdx.files.internal("empaquetado.atlas"));
		car = atlas.findRegion("0005");
		road = atlas.findRegion("road");
		bonus_boton = atlas.findRegion("espacio_poder");
		poder = atlas.findRegion("poder");
		rapido_icono = atlas.findRegion("rapido_icono");
		comida_icono = atlas.findRegion("comida_icono");
		defensa_icono = atlas.findRegion("defensa_icono");
		rapido = atlas.findRegion("rapido");
		comida = atlas.findRegion("cura");
		defensa = atlas.findRegion("defensa");
		puntos = atlas.findRegion("puntos");
		
		skin = new Skin();
		
		//botones
		// Generate a 1x1 white texture and store it in the skin named "white".
				Pixmap pixmap = new Pixmap(1, 1, Format.RGBA8888);
				pixmap.setColor(Color.WHITE);
				pixmap.fill();
				skin.add("white", new Texture(pixmap));

				// Store the default libgdx font under the name "default".
				skin.add("default", new BitmapFont());

				// Configure a TextButtonStyle and name it "default". Skin resources are stored by type, so this doesn't overwrite the font.
						TextButtonStyle textButtonStyle = new TextButtonStyle();
						textButtonStyle.up = skin.newDrawable("white", Color.DARK_GRAY);
						textButtonStyle.down = skin.newDrawable("white", Color.DARK_GRAY);
						textButtonStyle.checked = skin.newDrawable("white", Color.BLUE);
						textButtonStyle.over = skin.newDrawable("white", Color.GREEN);
						textButtonStyle.font = skin.getFont("default");
						skin.add("default", textButtonStyle);

						// Create a table that fills the screen. Everything else will go inside this table.
		
	}

	public static void dispose() {
		atlas.dispose();
	}
}
