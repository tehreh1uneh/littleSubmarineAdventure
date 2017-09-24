package com.tehreh1uneh.littlesubmarineadventure.engine.sprites

import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.graphics.g2d.TextureRegion
import com.badlogic.gdx.math.Vector2

open class Sprite(vararg region: TextureRegion) : Rect() {

    private var regions: Array<out TextureRegion> = region

    protected var frame = 0
    var scaleX = 1f
    var scaleY = 1f
    var rotation = 0f

    open fun resize(worldBounds: Rect) {}
    open fun update(delta: Float) {}

    fun draw(batch: SpriteBatch) {
        batch.draw(regions[frame], left, bottom, halfWidth, halfHeight, width, height, scaleX, scaleY, rotation)
    }

    fun setWidthProportion(width: Float = 1f) {
        val ratio: Float = (regions[frame].regionWidth / regions[frame].regionHeight.toFloat())
        setSize(width, width / ratio)
    }

    fun setHeightProportion(height: Float = 1f) {
        val ratio: Float = (regions[frame].regionHeight / regions[frame].regionWidth.toFloat())
        setSize(height / ratio, height)
    }

    fun setScale(scale: Float = 1f) {
        scaleX = scale
        scaleY = scale
    }

    open fun touchDown(touch: Vector2, pointer: Int): Boolean {
        return false
    }

    open fun touchMove(touch: Vector2, pointer: Int): Boolean {
        return false
    }

    open fun touchUp(touch: Vector2, pointer: Int): Boolean {
        return false
    }
}

