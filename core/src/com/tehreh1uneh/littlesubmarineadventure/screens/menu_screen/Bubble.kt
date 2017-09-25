package com.tehreh1uneh.littlesubmarineadventure.screens.menu_screen

import com.badlogic.gdx.graphics.g2d.TextureRegion
import com.tehreh1uneh.littlesubmarineadventure.common.evalRandomFloat
import com.tehreh1uneh.littlesubmarineadventure.engine.sprites.Axis
import com.tehreh1uneh.littlesubmarineadventure.engine.sprites.OnTouchMovingSprite
import com.tehreh1uneh.littlesubmarineadventure.engine.sprites.Rect

private const val BUBBLE_SIZE_MIN = 0.02f
private const val BUBBLE_SIZE_MAX = 0.05f
private const val SIZE_TO_SPEED_RATIO_DEFAULT = 1f
private const val V_ZERO = 0f


class Bubble(
        region: TextureRegion,
        size: Float = evalRandomFloat(BUBBLE_SIZE_MIN, BUBBLE_SIZE_MAX),
        vX: Float = V_ZERO,
        vY: Float = V_ZERO,
        axis: Axis,
        moveDirection: Axis,
        sizeToSpeedRatio: Float = SIZE_TO_SPEED_RATIO_DEFAULT)
    : OnTouchMovingSprite(
        region,
        axis = axis,
        moveDirection = moveDirection,
        vX = vX,
        vY = vY) {

    init {
        setWidthProportion(size)
        v.scl(sizeToSpeedRatio)
    }

    constructor(region: TextureRegion,
                size: Float = evalRandomFloat(BUBBLE_SIZE_MIN, BUBBLE_SIZE_MAX),
                vBoth: Float = V_ZERO,
                axis: Axis,
                moveDirection: Axis,
                sizeToSpeedRatio: Float = SIZE_TO_SPEED_RATIO_DEFAULT) : this(region, size, vBoth, vBoth, axis, moveDirection, sizeToSpeedRatio)

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