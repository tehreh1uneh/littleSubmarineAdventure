package com.tehreh1uneh.littlesubmarineadventure.screens.menuscreen

import com.badlogic.gdx.graphics.g2d.TextureRegion
import com.tehreh1uneh.littlesubmarineadventure.common.evalRandomFloat
import com.tehreh1uneh.littlesubmarineadventure.engine.math.Axis
import com.tehreh1uneh.littlesubmarineadventure.engine.math.Rect
import com.tehreh1uneh.littlesubmarineadventure.engine.sprites.TouchSprite

class Bubble(
        region: TextureRegion,
        size: Float = evalRandomFloat(0.02f, 0.05f),
        vX: Float = 0f,
        vY: Float = 0f,
        reactionAxis: Axis,
        sizeToSpeedRatio: Float = 1f)
    : TouchSprite(
        region,
        reactionAxis = reactionAxis,
        vX = vX,
        vY = vY) {

    init {
        setWidthProportion(size)
        v.scl(sizeToSpeedRatio)
    }

    private lateinit var worldBounds: Rect

    override fun resize(worldBounds: Rect) {
        this.worldBounds = worldBounds
        centerPos.set(evalRandomFloat(worldBounds.left, worldBounds.right),
                evalRandomFloat(worldBounds.bottom, worldBounds.top))
    }

    override fun update(delta: Float) {
        super.update(delta)
        mirror()
    }

    private fun mirror() {
        when {
            right < worldBounds.left -> left = worldBounds.right
            left > worldBounds.right -> right = worldBounds.left
            top < worldBounds.bottom -> bottom = worldBounds.top
            bottom > worldBounds.top -> {
                top = worldBounds.bottom
                centerPos.x = evalRandomFloat(worldBounds.left, worldBounds.right)
            }
        }
    }
}