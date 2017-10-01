package com.tehreh1uneh.littlesubmarineadventure

import com.badlogic.gdx.graphics.g2d.TextureRegion
import com.badlogic.gdx.math.Vector2
import com.tehreh1uneh.littlesubmarineadventure.engine.math.Rect
import com.tehreh1uneh.littlesubmarineadventure.engine.sprites.Sprite

private const val SIZE = 0.1f

private const val V_FALL_MIN = 0.3f
private const val V_FALL_MAX = 0.6f

private const val V_ASCENT_MIN = 0.05f
private const val V_ASCENT_MAX = 0.2f

private const val COMMON_RATIO = 1.3f

private const val SPEEDUP_INTERVAL = 0.1f

class Submarine(region: TextureRegion) : Sprite(region) {

    private var pointer: Int? = null
    private var pressed: Boolean = false
    private var interval: Float = 0f
    private val worldBounds = Rect()

    override fun resize(worldBounds: Rect) {
        this.worldBounds.set(worldBounds)
        setWidthProportion(SIZE)
        centerPos.set(worldBounds.left + width, worldBounds.centerPos.y)
    }

    override fun update(delta: Float) {
        interval += delta
        if (interval > SPEEDUP_INTERVAL) {
            interval = 0f

            if (pressed && vY < V_ASCENT_MAX || vX < V_FALL_MAX) {
                vY *= COMMON_RATIO
            }
        }
        if (bottom < worldBounds.bottom) {
            bottom = worldBounds.bottom
            if (!pressed) v.setZero()
        } else if (top > worldBounds.top) {
            top = worldBounds.top
            if (pressed) v.setZero()
        }
        super.update(delta)
    }

    override fun touchDown(touch: Vector2, pointer: Int) {
        if (this.pointer != null) return
        this.pointer = pointer
        vY = V_ASCENT_MIN
        pressed = true
    }

    override fun touchUp(touch: Vector2, pointer: Int) {
        if (pointer != this.pointer) return
        this.pointer = null
        vY = -V_FALL_MIN
        pressed = false
    }
}