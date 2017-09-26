package com.tehreh1uneh.littlesubmarineadventure.common

import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.graphics.g2d.TextureRegion
import com.badlogic.gdx.math.Vector2
import com.tehreh1uneh.littlesubmarineadventure.engine.sprites.Axis
import com.tehreh1uneh.littlesubmarineadventure.engine.sprites.OnTouchMovingSprite
import com.tehreh1uneh.littlesubmarineadventure.engine.sprites.Rect

private const val BOUNDS_OFFSET_PERCENT = 0.1f
private const val CORRECTION_BORDER_OFFSET = 0.001f

class Background(region: TextureRegion, moveDirection: Axis = Axis.NONE) {

    private val mainSprite = OnTouchMovingSprite(region, moveDirection = moveDirection)
    private val additionalSprite = OnTouchMovingSprite(region, moveDirection = moveDirection)
    private val worldBounds: Rect = Rect()

    fun resize(worldBounds: Rect) {
        this.worldBounds.set(worldBounds)

        mainSprite.setWidthProportion(worldBounds.width + worldBounds.width * BOUNDS_OFFSET_PERCENT * 2)
        additionalSprite.setWidthProportion(mainSprite.width)

        mainSprite.bottom = worldBounds.bottom
        additionalSprite.bottom = mainSprite.bottom
        mainSprite.left = worldBounds.left - worldBounds.width * BOUNDS_OFFSET_PERCENT
        additionalSprite.left = mainSprite.right
    }

    fun update(delta: Float) {
        mainSprite.update(delta)
        additionalSprite.update(delta)

        if (mainSprite intersect worldBounds) {
            joinSpriteBorders(mainSprite, additionalSprite)
        } else {
            joinSpriteBorders(additionalSprite, mainSprite)
        }
    }

    private fun joinSpriteBorders(sprite: OnTouchMovingSprite, secondSprite: OnTouchMovingSprite) {
        if (sprite.right < worldBounds.right) {
            secondSprite.left = sprite.right - worldBounds.width * CORRECTION_BORDER_OFFSET
        } else if (sprite.left > worldBounds.left) {
            secondSprite.right = sprite.left + worldBounds.width * CORRECTION_BORDER_OFFSET
        }
    }

    fun draw(batch: SpriteBatch) {
        mainSprite.draw(batch)
        additionalSprite.draw(batch)
    }

    fun touchDown(touch: Vector2, pointer: Int) {
        mainSprite.touchDown(touch, pointer)
        additionalSprite.touchDown(touch, pointer)
    }

    fun touchMove(touch: Vector2, pointer: Int) {
        mainSprite.touchMove(touch, pointer)
        additionalSprite.touchMove(touch, pointer)
    }

    fun touchUp(touch: Vector2, pointer: Int) {
        mainSprite.touchUp(touch, pointer)
        additionalSprite.touchUp(touch, pointer)
    }
}