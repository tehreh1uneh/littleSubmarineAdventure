package com.tehreh1uneh.littlesubmarineadventure.engine.sprites

import com.badlogic.gdx.graphics.g2d.TextureRegion
import com.badlogic.gdx.math.Vector2
import com.tehreh1uneh.littlesubmarineadventure.engine.ui.TouchListener


class OnTouchScalingButton(vararg region: TextureRegion, private val touchListener: TouchListener, private val scale: Float) : Sprite(*region) {

    private var pressed = false
    private var pointer: Int = 0

    fun touchDown(touch: Vector2, pointer: Int): Boolean =
            if (this intersect touch && !pressed) {
                pressed = true
                this.pointer = pointer
                setScale(scale)
                true
            } else {
                false
            }

    fun touchUp(touch: Vector2, pointer: Int): Boolean {

        if (pointer != this.pointer) return false
        pressed = false
        setScale()

        if (this intersect touch) {
            touchListener.actionPerformed(this)
            return true
        }
        return false
    }
}
