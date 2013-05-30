package com.theinvader360.scene2dtutorial.swiperace;

import static com.badlogic.gdx.scenes.scene2d.actions.Actions.*;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class InfiniteScrollBg extends Actor {
	public InfiniteScrollBg(float width, float height) {
		setWidth(width);
		setHeight(height);
		setPosition(width, 0);
<<<<<<< HEAD
		addAction(forever(sequence(moveTo(0, 0, Assets.velocidad_local), moveTo(width, 0))));
=======
		addAction(forever(sequence(moveTo(0, 0, 1f), moveTo(width, 0))));
>>>>>>> 3d0933654e3bfd918b5b56bc8df4a6eca9ff55c2
		
	}

	@Override
	public void draw(SpriteBatch batch, float parentAlpha) {
		super.draw(batch, parentAlpha);
		batch.draw(Assets.road, getX()-getWidth(), getY(), getWidth() * 2, getHeight());
	}
}
