package com.tehreh1uneh.littlesubmarineadventure.common

import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.graphics.g2d.TextureRegion
import com.badlogic.gdx.math.Vector2
import com.tehreh1uneh.littlesubmarineadventure.engine.math.Rect
import com.tehreh1uneh.littlesubmarineadventure.engine.sprites.EndlessTouchSprite
import com.tehreh1uneh.littlesubmarineadventure.engine.sprites.SpriteBehaviour

private const val V_BASE = 0.02f
private const val V_STEP = 0.02f

class Background(vararg region: TextureRegion, vX: Float = 0f, vY: Float = 0f) : SpriteBehaviour {

    val sprites: Array<EndlessTouchSprite> = Array(region.size) { EndlessTouchSprite(region[it], vX = vX, vY = vY) }

    init {
        for (i in 0 until sprites.lastIndex) {
            sprites[i].mainSprite.vMoveCoefficient = V_BASE + V_STEP * i
            sprites[i].additionalSprite.vMoveCoefficient = V_BASE + V_STEP * i
        }
    }

    inline fun spriteActions(action: (sprite: EndlessTouchSprite) -> Unit) {
        sprites.forEach { action(it) }
    }

    override fun resize(worldBounds: Rect) {
        sprites.forEach { it.resize(worldBounds) }
    }

    override fun update(delta: Float) {
        sprites.forEach { it.update(delta) }
    }

    override fun draw(batch: SpriteBatch) {
        sprites.forEach { it.draw(batch) }
    }

    override fun touchDown(touch: Vector2, pointer: Int) {
        sprites.forEach { it.touchDown(touch, pointer) }
    }

    override fun touchMove(touch: Vector2, pointer: Int) {
        sprites.forEach { it.touchMove(touch, pointer) }
    }

    override fun touchUp(touch: Vector2, pointer: Int) {
        sprites.forEach { it.touchUp(touch, pointer) }
    }
}