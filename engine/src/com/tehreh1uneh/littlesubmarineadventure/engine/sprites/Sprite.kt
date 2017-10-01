package com.tehreh1uneh.littlesubmarineadventure.engine.sprites

import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.graphics.g2d.TextureRegion
import com.badlogic.gdx.math.Vector2
import com.tehreh1uneh.littlesubmarineadventure.engine.math.Rect

open class Sprite(vararg region: TextureRegion, vX: Float = 0f, vY: Float = 0f) : Rect(), SpriteBehaviour {

    private var regions: Array<out TextureRegion> = region
    protected var frame = 0
    var scaleX = 1f
    var scaleY = 1f
    var rotation = 0f

    val v = Vector2()
    var vX: Float
        set(value) {
            v.x = value
        }
        get() = v.x
    var vY: Float
        set(value) {
            v.y = value
        }
        get() = v.y

    init {
        this.vX = vX
        this.vY = vY
    }

    override fun update(delta: Float) {
        centerPos.mulAdd(v, delta)
    }

    override fun draw(batch: SpriteBatch) {
        batch.draw(regions[frame], left, bottom, halfWidth, halfHeight, width, height, scaleX, scaleY, rotation)
    }

    fun setWidthProportion(width: Float = 1f) {
        val ratio: Float = (regions[frame].regionWidth / regions[frame].regionHeight.toFloat())
        setSize(width, height = width / ratio)
    }

    fun setHeightProportion(height: Float = 1f) {
        val ratio: Float = (regions[frame].regionHeight / regions[frame].regionWidth.toFloat())
        setSize(height / ratio, height)
    }

    fun setScale(scale: Float = 1f) {
        scaleX = scale
        scaleY = scale
    }
}

