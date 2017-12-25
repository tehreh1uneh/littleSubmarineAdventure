package com.tehreh1uneh.littlesubmarineadventure.common

import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.graphics.g2d.TextureRegion
import com.badlogic.gdx.math.Vector2
import com.tehreh1uneh.littlesubmarineadventure.engine.math.Rect
import com.tehreh1uneh.littlesubmarineadventure.engine.sprites.EndlessTouchSprite
import com.tehreh1uneh.littlesubmarineadventure.engine.sprites.SpriteBehaviour


class Background(vararg region: TextureRegion, vX: Float = 0f, vY: Float = 0f) : SpriteBehaviour {

    private val sprites: Array<EndlessTouchSprite> = Array(region.size) { EndlessTouchSprite(region[it], vX = vX, vY = vY) }
    override var destroyed = false

    init {
        for (i in 0 until sprites.lastIndex) {
            sprites[i].mainSprite.vMoveCoefficient = BACKGROUND_SPEED_BASE + BACKGROUND_SPEED_STEP * i
            sprites[i].additionalSprite.vMoveCoefficient = BACKGROUND_SPEED_BASE + BACKGROUND_SPEED_STEP * i
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

    fun stop() {
        for (sprite in sprites) {
            with(sprite) {
                mainSprite.v.setZero()
                additionalSprite.v.setZero()
            }
        }
    }

    fun initSpeed() {
        var i = 0
        sprites.forEach {
            with(it) {
                mainSprite.vX = -0.02f * i++ - 0.02f
                additionalSprite.vX = -0.02f * i++ - 0.02f
            }
        }
    }

}