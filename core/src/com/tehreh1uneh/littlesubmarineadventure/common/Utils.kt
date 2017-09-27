package com.tehreh1uneh.littlesubmarineadventure.common

import java.util.*

internal const val PATH_BACKGROUND_MASK = "textures/background/background_(%).png"
internal const val PATH_MENU_ATLAS = "textures/menu/atlas/atlas_menuScreen.pack"
internal const val PATH_MAIN_MUSIC = "sounds/DST-WaterLily.mp3"
internal const val PATH_GAME_ATLAS = "textures/game/atlas/game_screen.pack"

private val rndGen = Random()
internal fun evalRandomFloat(min: Float = 0f, max: Float = 1f) = rndGen.nextFloat() * (max - min) + min
