package com.tehreh1uneh.littlesubmarineadventure.engine.sprites

import com.badlogic.gdx.graphics.g2d.TextureRegion
import com.badlogic.gdx.math.Vector2
import com.tehreh1uneh.littlesubmarineadventure.engine.math.Axis

open class TouchSprite(vararg region: TextureRegion,
                       private val reactionAxis: Axis = Axis.X,
                       vX: Float = 0f,
                       vY: Float = 0f,
                       var vMoveCoefficient: Float = 0.1f) : Sprite(*region, vX = vX, vY = vY) {

    private val touchPosition = Vector2()
    private var pointer: Int? = null

    override fun touchDown(touch: Vector2, pointer: Int) {
        if (this.pointer != null) return
        touchPosition.set(touch)
        this.pointer = pointer
    }

    override fun touchMove(touch: Vector2, pointer: Int) {
        when (reactionAxis) {
            Axis.X -> onTouchMove(touch, pointer) { centerPos.x += touchPosition.sub(it).x * vMoveCoefficient }
            Axis.Y -> onTouchMove(touch, pointer) { centerPos.y += touchPosition.sub(it).y * vMoveCoefficient }
            Axis.BOTH -> onTouchMove(touch, pointer) { centerPos.add(touchPosition.sub(it).scl(vMoveCoefficient)) }
            Axis.NONE -> {
            }
        }
    }

    override fun touchUp(touch: Vector2, pointer: Int) {
        if (this.pointer != pointer) return
        this.pointer = null
    }

    private inline fun onTouchMove(touch: Vector2, pointer: Int, action: (touch: Vector2) -> Unit) {
        if (this.pointer != pointer) return
        action(touch)
        touchPosition.set(touch)
    }
}
