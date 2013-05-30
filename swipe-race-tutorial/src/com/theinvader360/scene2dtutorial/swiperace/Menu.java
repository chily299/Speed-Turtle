package com.theinvader360.scene2dtutorial.swiperace;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.utils.ScreenUtils;

public class Menu extends Table implements Screen{
	
	public Menu() {
		// TODO Auto-generated constructor stub
		// label "welcome"
        Label welcomeLabel = new Label( "Welcome to Tyrian for Android!", getSkin() );
        //welcomeLabel.x = ( ( width - welcomeLabel.width ) / 2 );
        //welcomeLabel.y = ( currentY + 100 );
        addActor( welcomeLabel );
 
        // button "start game"
      /*  TextButton startGameButton = new TextButton( "Start game", getSkin() );
        startGameButton.x = buttonX;
        startGameButton.y = currentY;
        startGameButton.width = BUTTON_WIDTH;
        startGameButton.height = BUTTON_HEIGHT;
        addActor( startGameButton );*/
	}

}
