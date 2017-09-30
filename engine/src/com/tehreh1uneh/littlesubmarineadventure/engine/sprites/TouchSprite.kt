package com.tehreh1uneh.littlesubmarineadventure.engine.sprites

import com.badlogic.gdx.graphics.g2d.TextureRegion
import com.badlogic.gdx.math.Vector2
import com.tehreh1uneh.littlesubmarineadventure.engine.Math.Axis

private const val V_ZERO = 0f
private const val V_MOVE_COEFFICIENT_DEFAULT = 0.1f

open class TouchSprite(vararg region: TextureRegion,
                       private val axis: Axis = Axis.X,
                       moveDirection: Axis = Axis.Y,
                       vX: Float = V_ZERO,
                       vY: Float = V_ZERO,
                       var vMoveCoefficient: Float = V_MOVE_COEFFICIENT_DEFAULT) : Sprite(*region) {

    val v = Vector2()

    init {
        when (moveDirection) {
            Axis.X -> v.set(vX, V_ZERO)
            Axis.Y -> v.set(V_ZERO, vY)
            Axis.BOTH -> v.set(vX, vY)
            Axis.NONE -> v.set(V_ZERO, V_ZERO)
        }
    }

    private val touchPosition = Vector2()
    private var pointer: Int? = null

    override fun update(delta: Float) {
        centerPos.mulAdd(v, delta)
    }

    override fun touchDown(touch: Vector2, pointer: Int) {
        if (this.pointer != null) return
        touchPosition.set(touch)
        this.pointer = pointer
    }

    override fun touchMove(touch: Vector2, pointer: Int) {
        when (axis) {
            Axis.X -> onTouchMove(touch, pointer) { centerPos.x += touchPosition.sub(it).x * vMoveCoefficient }
            Axis.Y -> onTouchMove(touch, pointer) { centerPos.y += touchPosition.sub(it).y * vMoveCoefficient }
            Axis.BOTH -> onTouchMove(touch, pointer) { centerPos.add(touchPosition.sub(it).scl(vMoveCoefficient)) }
            Axis.NONE -> onTouchMove(touch, pointer) {}
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

