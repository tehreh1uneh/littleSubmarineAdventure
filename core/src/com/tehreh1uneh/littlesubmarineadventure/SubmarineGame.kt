package com.tehreh1uneh.littlesubmarineadventure

import com.badlogic.gdx.Game
import com.tehreh1uneh.littlesubmarineadventure.screens.menu_screen.MenuScreen

class SubmarineGame : Game() {
    override fun create() {
        setScreen(MenuScreen(game = this))
    }
}