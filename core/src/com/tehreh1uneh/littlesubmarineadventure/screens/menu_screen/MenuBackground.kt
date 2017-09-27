package com.tehreh1uneh.littlesubmarineadventure.screens.menu_screen

import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.graphics.g2d.TextureRegion
import com.badlogic.gdx.math.Vector2
import com.tehreh1uneh.littlesubmarineadventure.engine.sprites.Axis
import com.tehreh1uneh.littlesubmarineadventure.engine.sprites.NeverendingSprite
import com.tehreh1uneh.littlesubmarineadventure.engine.sprites.Rect

private const val V_BASE = 0.02f
private const val V_STEP = 0.02f

class MenuBackground(vararg region: TextureRegion, moveDirection: Axis = Axis.NONE) {

    private val regions: Array<out TextureRegion> = region
    private val sprites: Array<NeverendingSprite> = Array(regions.size) { NeverendingSprite(regions[it], moveDirection) }

    init {
        for (i in 0 until sprites.lastIndex) {
            sprites[i].vMoveCoefficient = V_BASE + V_STEP * i
        }
    }

    fun resize(worldBounds: Rect) {
        sprites.forEach { it.resize(worldBounds) }
    }

    fun update(delta: Float) {
        sprites.forEach { it.update(delta) }
    }

    fun draw(batch: SpriteBatch) {
        sprites.forEach { it.draw(batch) }
    }

    fun touchDown(touch: Vector2, pointer: Int) {
        sprites.forEach { it.touchDown(touch, pointer) }
    }

    fun touchMove(touch: Vector2, pointer: Int) {
        sprites.forEach { it.touchMove(touch, pointer) }
    }

    fun touchUp(touch: Vector2, pointer: Int) {
        sprites.forEach { it.touchUp(touch, pointer) }
    }
}