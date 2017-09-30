package com.tehreh1uneh.littlesubmarineadventure.common

import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.graphics.g2d.TextureRegion
import com.badlogic.gdx.math.Vector2
import com.tehreh1uneh.littlesubmarineadventure.engine.Math.Axis
import com.tehreh1uneh.littlesubmarineadventure.engine.Math.Rect
import com.tehreh1uneh.littlesubmarineadventure.engine.sprites.EndlessTouchSprite
import com.tehreh1uneh.littlesubmarineadventure.engine.sprites.SpriteBehaviour

private const val V_BASE = 0.02f
private const val V_STEP = 0.02f

class Background(vararg region: TextureRegion, vX: Float = 0f, vY: Float = 0f, moveDirection: Axis = Axis.NONE) : SpriteBehaviour {

    private val regions: Array<out TextureRegion> = region
    private val sprites: Array<EndlessTouchSprite> = Array(regions.size) { EndlessTouchSprite(regions[it], vX = vX - V_STEP * it, vY = vY - V_STEP * it, moveDirection = moveDirection) }

    init {
        for (i in 0 until sprites.lastIndex) {
            sprites[i].setVMoveCoefficient(V_BASE + V_STEP * i)
        }
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