package com.tehreh1uneh.littlesubmarineadventure.engine.math

import com.badlogic.gdx.math.Vector2

open class Rect(x: Float = 0f, y: Float = 0f, protected var halfWidth: Float = 0f, protected var halfHeight: Float = 0f) {

    val pos = Vector2()

    init {
        pos.set(x, y)
    }

    constructor(other: Rect) : this(other.pos.x, other.pos.y, other.halfWidth, other.halfHeight)

    //region Getters

    fun getLeft() = pos.x - halfWidth
    fun getTop() = pos.y + halfHeight
    fun getRight() = pos.x + halfWidth
    fun getBottom() = pos.y - halfHeight
    fun getWidth() = halfWidth * 2
    fun getHeight() = halfHeight * 2
    //endregion

    //region Setters

    fun set(other: Rect) {
        pos.set(other.pos.x, other.pos.y)
        halfWidth = other.halfWidth
        halfHeight = other.halfHeight
    }

    fun setLeft(left: Float) {
        pos.x = left + halfWidth
    }

    fun setTop(top: Float) {
        pos.y = top - halfHeight
    }

    fun setRight(right: Float) {
        pos.x = right - halfWidth
    }

    fun setBottom(bottom: Float) {
        pos.y = bottom + halfHeight
    }

    fun setSize(width: Float = halfWidth * 2f, height: Float = halfHeight * 2f) {
        halfWidth = width / 2f
        halfHeight = height / 2f
    }
    //endregion

    fun isInside(touch: Vector2) = touch.x in getLeft()..getRight() && touch.y in getBottom()..getTop()
    fun isOutside(touch: Vector2) = touch.x !in getLeft()..getRight() || touch.y !in getBottom()..getTop()

}