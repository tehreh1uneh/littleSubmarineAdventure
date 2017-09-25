package com.tehreh1uneh.littlesubmarineadventure.common

import com.badlogic.gdx.graphics.g2d.TextureRegion
import com.tehreh1uneh.littlesubmarineadventure.engine.sprites.Axis
import com.tehreh1uneh.littlesubmarineadventure.engine.sprites.OnTouchMovingSprite
import com.tehreh1uneh.littlesubmarineadventure.engine.sprites.Rect

private const val BOUNDS_OFFSET_PERCENT = 0.1f

class Background(region: TextureRegion) : OnTouchMovingSprite(region, moveDirection = Axis.NONE) {

    override fun resize(worldBounds: Rect) {
        setWidthProportion(worldBounds.width + worldBounds.width * BOUNDS_OFFSET_PERCENT * 2)
        bottom = worldBounds.bottom
        left = worldBounds.left - worldBounds.width * BOUNDS_OFFSET_PERCENT
    }
}