package com.tehreh1uneh.littlesubmarineadventure.common

import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.TextureRegion
import com.tehreh1uneh.littlesubmarineadventure.engine.Sprite2DTexture
import java.util.*

internal const val PATH_BACKGROUND_MASK = "textures/background/background_(%).png"
internal const val PATH_MENU_ATLAS = "textures/menu/atlas/atlas_menuScreen.pack"
internal const val PATH_GAME_ATLAS = "textures/game/atlas/game_screen.pack"

private val rndGen = Random()
internal fun evalRandomFloat(min: Float = 0f, max: Float = 1f) = rndGen.nextFloat() * (max - min) + min

internal fun Array<Texture>.toTextureRegion(): Array<TextureRegion> = Array(this.size) { TextureRegion(this[it]) }
internal fun getBgTextures(): Array<Texture> = Array(4) { Sprite2DTexture(PATH_BACKGROUND_MASK.replace("%", it.toString())) }
