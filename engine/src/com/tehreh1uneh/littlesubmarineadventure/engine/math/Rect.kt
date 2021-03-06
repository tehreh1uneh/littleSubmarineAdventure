package com.tehreh1uneh.littlesubmarineadventure.engine.math

import com.badlogic.gdx.math.Vector2

open class Rect(x: Float = 0f, y: Float = 0f, var halfWidth: Float = 0f, var halfHeight: Float = 0f) {

    val centerPos = Vector2()

    init {
        centerPos.set(x, y)
    }

    //region PropertiesWithoutBackingFields

    var width: Float
        get() = halfWidth * 2f
        set(value) {
            halfWidth = value / 2f
        }

    var height: Float
        get() = halfHeight * 2f
        set(value) {
            halfHeight = value / 2f
        }

    var left: Float
        get() = centerPos.x - halfWidth
        set(value) {
            centerPos.x = value + halfWidth
        }

    var top: Float
        get() = centerPos.y + halfHeight
        set(value) {
            centerPos.y = value - halfHeight
        }

    var right: Float
        get() = centerPos.x + halfWidth
        set(value) {
            centerPos.x = value - halfWidth
        }

    var bottom: Float
        get() = centerPos.y - halfHeight
        set(value) {
            centerPos.y = value + halfHeight
        }
    //endregion

    //region Setters

    fun set(other: Rect) {
        centerPos.set(other.centerPos.x, other.centerPos.y)
        halfWidth = other.halfWidth
        halfHeight = other.halfHeight
    }

    fun setSize(width: Float, height: Float) {
        this.width = width
        this.height = height
    }
    //endregion

    operator fun contains(touch: Vector2) = touch.x in left..right && touch.y in bottom..top
    open infix fun intersect(other: Rect) = left < other.right && right > other.left && bottom < other.top && top > other.bottom
    infix fun toTheLeftOf(other: Rect) = !intersect(other) && right < other.left
}