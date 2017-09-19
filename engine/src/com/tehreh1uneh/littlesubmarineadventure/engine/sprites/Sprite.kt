package com.tehreh1uneh.littlesubmarineadventure.engine.sprites

import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.graphics.g2d.TextureRegion

open class Sprite(vararg region: TextureRegion) : Rect() {

    private var regions: Array<TextureRegion> = region as Array<TextureRegion>

    protected var frame = 0
    var scaleX = 1f
    var scaleY = 1f
    var rotation = 0f
    var destroyed = false

    fun draw(batch: SpriteBatch) {
        batch.draw(regions[frame], left, bottom,  halfWidth, halfHeight, width, height, scaleX, scaleY, rotation)
    }

    fun setWidthProportion(width: Float) {
        val ratio: Float = (regions[frame].regionWidth / regions[frame].regionHeight.toFloat())
        setSize(width, width / ratio)
    }

    fun setHeightProportion(height: Float) {
        val ratio: Float = (regions[frame].regionHeight / regions[frame].regionWidth.toFloat())
        setSize(height / ratio, height)
    }

    fun setScale(scale: Float = 1f){
        scaleX = scale
        scaleY = scale
    }
}
