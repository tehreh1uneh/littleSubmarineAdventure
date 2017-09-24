package com.tehreh1uneh.littlesubmarineadventure.common

import java.util.*

internal const val PATH_BG = "textures/blue_background.png"
internal const val PATH_SEABED = "textures/seabed.png"
internal const val PATH_MENU_ATLAS = "textures/menu/atlas/atlas_menuScreen.pack"

private val rndGen = Random()
fun evalRandomFloat(min: Float = 0f, max: Float = 1f) = rndGen.nextFloat() * (max - min) + min
