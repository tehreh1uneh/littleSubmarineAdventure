package com.tehreh1uneh.littlesubmarineadventure.engine.sprites

import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.graphics.g2d.TextureRegion
import com.badlogic.gdx.math.Vector2

private const val CORRECTION_BORDER_OFFSET = 0.001f

class NeverendingSprite(region: TextureRegion, moveDirection: Axis = Axis.NONE) : OnTouchMovingSprite(region, moveDirection = moveDirection) {

    private val additionalSprite = OnTouchMovingSprite(region)
    private val worldBounds: Rect = Rect()

    override fun resize(worldBounds: Rect) {
        this.worldBounds.set(worldBounds)

        setWidthProportion(worldBounds.width)
        additionalSprite.setWidthProportion(width)

        bottom = worldBounds.bottom
        additionalSprite.bottom = bottom
        left = worldBounds.left
        additionalSprite.left = right
    }

    override fun update(delta: Float) {
        super.update(delta)
        additionalSprite.update(delta)

        if (this intersect worldBounds) {
            joinSpriteBorders(this, additionalSprite)
        } else {
            joinSpriteBorders(additionalSprite, this)
        }
    }

    private fun joinSpriteBorders(sprite: Sprite, secondSprite: Sprite) {
        if (sprite.right < worldBounds.right) {
            secondSprite.left = sprite.right - worldBounds.width * CORRECTION_BORDER_OFFSET
        } else if (sprite.left > worldBounds.left) {
            secondSprite.right = sprite.left + worldBounds.width * CORRECTION_BORDER_OFFSET
        }
    }

    override fun draw(batch: SpriteBatch) {
        super.draw(batch)
        additionalSprite.draw(batch)
    }

    override fun touchDown(touch: Vector2, pointer: Int): Boolean {
        return super.touchDown(touch, pointer) && additionalSprite.touchDown(touch, pointer)
    }

    override fun touchMove(touch: Vector2, pointer: Int): Boolean {
        return super.touchMove(touch, pointer) && additionalSprite.touchMove(touch, pointer)
    }

    override fun touchUp(touch: Vector2, pointer: Int): Boolean {
        return super.touchUp(touch, pointer) && additionalSprite.touchUp(touch, pointer)

    }
}