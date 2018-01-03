package com.tehreh1uneh.littlesubmarineadventure.common

import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.TextureRegion
import com.tehreh1uneh.littlesubmarineadventure.engine.Sprite2DTexture
import java.util.*

internal const val BACKGROUND_SPEED_BASE = 0.02f
internal const val BACKGROUND_SPEED_STEP = 0.02f
internal const val PATH_BACKGROUND_GREY = "textures/background/background_gray.png"
internal const val PATH_BACKGROUND_MASK = "textures/background/background_(%).png"
internal const val PATH_MENU_ATLAS = "textures/menu/atlas/atlas_menuScreen.pack"
internal const val PATH_GAME_ATLAS = "textures/game/atlas/game_screen.pack"
internal const val PATH_FONT_DESCRIPTION = "fonts/font_monaco.fnt"
internal const val PATH_FONT_IMAGE = "fonts/font_monaco.png"
internal const val FONT_HEIGHT_SCALE = 0.05f
internal const val SCORE_DESCRIPTION = "Score: "



internal const val BUTTON_WIDTH = 0.1f
internal const val BUTTON_SCALE = 0.9f

const val PATH_MAIN_MUSIC = "sounds/DST-WaterLily.mp3"

private val rndGen = Random()
internal fun evalRandomFloat(min: Float = 0f, max: Float = 1f) = rndGen.nextFloat() * (max - min) + min

internal fun Array<Texture>.toTextureRegion(): Array<TextureRegion> = Array(this.size) { TextureRegion(this[it]) }
internal fun getBgTextures(): Array<Texture> = Array(4) { Sprite2DTexture(PATH_BACKGROUND_MASK.replace("%", it.toString())) }

internal fun StringBuilder.clear() {
    setLength(0)
}