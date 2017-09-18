package com.tehreh1uneh.littlesubmarineadventure;

import com.badlogic.gdx.Game;
import com.tehreh1uneh.littlesubmarineadventure.screens.menu_screen.MenuScreen;

public class SubmarineGame extends Game{

	@Override
	public void create () {
		setScreen(new MenuScreen(this));
	}
}
