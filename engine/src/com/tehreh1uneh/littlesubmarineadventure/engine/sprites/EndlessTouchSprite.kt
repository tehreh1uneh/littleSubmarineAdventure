package com.tehreh1uneh.littlesubmarineadventure.engine.sprites

import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.graphics.g2d.TextureRegion
import com.badlogic.gdx.math.Vector2
import com.tehreh1uneh.littlesubmarineadventure.engine.math.Axis
import com.tehreh1uneh.littlesubmarineadventure.engine.math.Rect
import com.tehreh1uneh.littlesubmarineadventure.engine.utils.CORRECTION_BORDER_OFFSET

class EndlessTouchSprite(region: TextureRegion, vX: Float, vY: Float, reactionAxis: Axis = Axis.X) : SpriteBehaviour {

    val mainSprite = TouchSprite(region, vX = vX, vY = vY, reactionAxis = reactionAxis)
    val additionalSprite = TouchSprite(region, vX = vX, vY = vY, reactionAxis = reactionAxis)
    private val worldBounds: Rect = Rect()
    override var destroyed = false

    override fun resize(worldBounds: Rect) {
        this.worldBounds.set(worldBounds)

        mainSprite.setWidthProportion(worldBounds.width)
        additionalSprite.setWidthProportion(mainSprite.width)

        mainSprite.bottom = worldBounds.bottom
        additionalSprite.bottom = mainSprite.bottom
        mainSprite.left = worldBounds.left
        additionalSprite.left = mainSprite.right
    }

    override fun update(delta: Float) {
        mainSprite.update(delta)
        additionalSprite.update(delta)

        if (mainSprite intersect worldBounds) {
            joinSpriteBorders(mainSprite, additionalSprite)
        } else {
            joinSpriteBorders(additionalSprite, mainSprite)
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
        mainSprite.draw(batch)
        additionalSprite.draw(batch)
    }

    override fun touchDown(touch: Vector2, pointer: Int) {
        mainSprite.touchDown(touch, pointer)
        additionalSprite.touchDown(touch, pointer)
    }

    override fun touchMove(touch: Vector2, pointer: Int) {
        mainSprite.touchMove(touch, pointer)
        additionalSprite.touchMove(touch, pointer)
    }

    override fun touchUp(touch: Vector2, pointer: Int) {
        mainSprite.touchUp(touch, pointer)
        additionalSprite.touchUp(touch, pointer)
    }
}