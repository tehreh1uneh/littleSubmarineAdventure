package com.tehreh1uneh.littlesubmarineadventure

import com.badlogic.gdx.graphics.g2d.TextureRegion
import com.badlogic.gdx.math.Vector2
import com.tehreh1uneh.littlesubmarineadventure.engine.sprites.Rect
import com.tehreh1uneh.littlesubmarineadventure.engine.sprites.Sprite

private const val SIZE = 0.1f

private const val V_FALL_MIN = 0.07f
private const val V_FALL_MAX = 0.15f

private const val V_ASCENT_MIN = 0.06f
private const val V_ASCENT_MAX = 0.15f

private const val SPEEDUP_INTERVAL = 0.1f
private const val SPEEDUP_STEP = 0.03f

class Submarine(region: TextureRegion) : Sprite(region) {

    private var pointer: Int? = null
    private var pressed: Boolean = false
    private val v = Vector2()
    private var interval: Float = 0f


    override fun resize(worldBounds: Rect) {
        setWidthProportion(SIZE)
        centerPos.set(worldBounds.left + width, worldBounds.centerPos.y)
    }

    override fun update(delta: Float) {
        interval += delta
        if (interval > SPEEDUP_INTERVAL) {
            interval = 0f

            if (pressed && v.y < V_ASCENT_MAX) {
                v.y += SPEEDUP_STEP
            } else if (v.y < V_FALL_MAX) {
                v.y -= SPEEDUP_STEP
            }
        }
        centerPos.mulAdd(v, delta)
    }

    override fun touchDown(touch: Vector2, pointer: Int): Boolean {
        if (this.pointer != null) return false
        this.pointer = pointer
        v.y = V_ASCENT_MIN
        pressed = true
        return true
    }

    override fun touchMove(touch: Vector2, pointer: Int): Boolean {
        return false
    }

    override fun touchUp(touch: Vector2, pointer: Int): Boolean {
        if (pointer != this.pointer) return false
        this.pointer = null
        v.y = -V_FALL_MIN
        pressed = false
        return true
    }
}