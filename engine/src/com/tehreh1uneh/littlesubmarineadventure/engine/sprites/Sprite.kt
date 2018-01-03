package com.tehreh1uneh.littlesubmarineadventure.engine.sprites

import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.graphics.g2d.TextureRegion
import com.badlogic.gdx.math.Vector2
import com.tehreh1uneh.littlesubmarineadventure.engine.math.Rect

open class Sprite(vararg region: TextureRegion, vX: Float = 0f, vY: Float = 0f) : Rect(), SpriteBehaviour, Resizable {

    protected var regions: Array<out TextureRegion> = region
    protected var frame = 0
    private var scaleX = 1f
    private var scaleY = 1f
    private var rotation = 0f
    private var originX = halfWidth
    private var originY = halfHeight
    override var destroyed = false

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
        batch.draw(regions[frame], left, bottom, originX, originY, width, height, scaleX, scaleY, rotation)
    }

    override fun setWidthProportion(width: Float) {
        val ratio: Float = (regions[frame].regionWidth / regions[frame].regionHeight.toFloat())
        setSize(width, height = width / ratio)
    }

    override fun setHeightProportion(height: Float) {
        val ratio: Float = (regions[frame].regionHeight / regions[frame].regionWidth.toFloat())
        setSize(height / ratio, height)
    }

    override fun setScale(scale: Float) {
        scaleX = scale
        scaleY = scale
    }

    fun stop() {
        v.setZero()
    }
}

