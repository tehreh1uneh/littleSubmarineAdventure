package com.tehreh1uneh.littlesubmarineadventure

import com.badlogic.gdx.graphics.g2d.TextureRegion
import com.badlogic.gdx.math.Vector2
import com.tehreh1uneh.littlesubmarineadventure.engine.math.Rect
import com.tehreh1uneh.littlesubmarineadventure.engine.sprites.Sprite

private const val SIZE = 0.1f
private const val V_FALL_MIN = 0.3f
private const val V_FALL_MAX = 0.6f
private const val V_ASCENT_MIN = 0.05f
private const val V_ASCENT_MAX = 0.2f
private const val COMMON_RATIO = 1.3f
private const val SPEEDUP_INTERVAL = 0.1f
private const val WIDTH_PARAM = "width"
private const val HEIGHT_PARAM = "height"
private const val LEFT_PARAM = "left"
private const val TOP_PARAM = "top"

class Submarine(region: TextureRegion) : Sprite(region) {

    private var pointer: Int? = null
    private var pressed: Boolean = false
    private var interval: Float = 0f
    private val worldBounds = Rect()
    private val borders = List(21) { Rect() }
    private val ratio = List(21, { HashMap<String, Double>() })

    init {
        fillBorderParams()
    }

    override fun resize(worldBounds: Rect) {
        this.worldBounds.set(worldBounds)
        setWidthProportion(SIZE)
        centerPos.x = worldBounds.left + width
        calculateBorders()
    }

    private fun fillBorderParams() {

        var rectParams = ratio[0]
        rectParams[WIDTH_PARAM] = 0.079734219269103
        rectParams[HEIGHT_PARAM] = 0.293269230769231
        rectParams[LEFT_PARAM] = 0.00996677740863787
        rectParams[TOP_PARAM] = 0.5

        rectParams = ratio[1]
        rectParams[WIDTH_PARAM] = 0.12624584717608
        rectParams[HEIGHT_PARAM] = 0.163461538461538
        rectParams[LEFT_PARAM] = .0
        rectParams[TOP_PARAM] = 0.423076923076923

        rectParams = ratio[2]
        rectParams[WIDTH_PARAM] = 0.0299003322259136
        rectParams[HEIGHT_PARAM] = 0.25
        rectParams[LEFT_PARAM] = 0.12624584717608
        rectParams[TOP_PARAM] = 0.466346153846154

        rectParams = ratio[3]
        rectParams[WIDTH_PARAM] = 0.026578073089701
        rectParams[HEIGHT_PARAM] = 0.341346153846154
        rectParams[LEFT_PARAM] = 0.156146179401993
        rectParams[TOP_PARAM] = 0.514423076923077

        rectParams = ratio[4]
        rectParams[WIDTH_PARAM] = 0.0299003322259136
        rectParams[HEIGHT_PARAM] = 0.413461538461538
        rectParams[LEFT_PARAM] = 0.182724252491694
        rectParams[TOP_PARAM] = 0.557692307692308

        rectParams = ratio[5]
        rectParams[WIDTH_PARAM] = 0.0398671096345515
        rectParams[HEIGHT_PARAM] = 0.504807692307692
        rectParams[LEFT_PARAM] = 0.212624584717608
        rectParams[TOP_PARAM] = 0.596153846153846

        rectParams = ratio[6]
        rectParams[WIDTH_PARAM] = 0.0498338870431894
        rectParams[HEIGHT_PARAM] = 0.581730769230769
        rectParams[LEFT_PARAM] = 0.252491694352159
        rectParams[TOP_PARAM] = 0.629807692307692

        rectParams = ratio[7]
        rectParams[WIDTH_PARAM] = 0.053156146179402
        rectParams[HEIGHT_PARAM] = 0.639423076923077
        rectParams[LEFT_PARAM] = 0.302325581395349
        rectParams[TOP_PARAM] = 0.658653846153846

        rectParams = ratio[8]
        rectParams[WIDTH_PARAM] = 0.425249169435216
        rectParams[HEIGHT_PARAM] = 0.6875
        rectParams[LEFT_PARAM] = 0.355481727574751
        rectParams[TOP_PARAM] = 0.6875

        rectParams = ratio[9]
        rectParams[WIDTH_PARAM] = 0.0564784053156146
        rectParams[HEIGHT_PARAM] = 0.663461538461538
        rectParams[LEFT_PARAM] = 0.780730897009967
        rectParams[TOP_PARAM] = 0.668269230769231

        rectParams = ratio[10]
        rectParams[WIDTH_PARAM] = 0.0332225913621262
        rectParams[HEIGHT_PARAM] = 0.625
        rectParams[LEFT_PARAM] = 0.837209302325581
        rectParams[TOP_PARAM] = 0.644230769230769

        rectParams = ratio[11]
        rectParams[WIDTH_PARAM] = 0.0299003322259136
        rectParams[HEIGHT_PARAM] = 0.576923076923077
        rectParams[LEFT_PARAM] = 0.870431893687708
        rectParams[TOP_PARAM] = 0.620192307692308

        rectParams = ratio[12]
        rectParams[WIDTH_PARAM] = 0.0232558139534884
        rectParams[HEIGHT_PARAM] = 0.519230769230769
        rectParams[LEFT_PARAM] = 0.900332225913621
        rectParams[TOP_PARAM] = 0.591346153846154

        rectParams = ratio[13]
        rectParams[WIDTH_PARAM] = 0.0166112956810631
        rectParams[HEIGHT_PARAM] = 0.461538461538462
        rectParams[LEFT_PARAM] = 0.92358803986711
        rectParams[TOP_PARAM] = 0.567307692307692

        rectParams = ratio[14]
        rectParams[WIDTH_PARAM] = 0.0132890365448505
        rectParams[HEIGHT_PARAM] = 0.408653846153846
        rectParams[LEFT_PARAM] = 0.940199335548173
        rectParams[TOP_PARAM] = 0.538461538461538

        rectParams = ratio[15]
        rectParams[WIDTH_PARAM] = 0.0199335548172757
        rectParams[HEIGHT_PARAM] = 0.346153846153846
        rectParams[LEFT_PARAM] = 0.953488372093023
        rectParams[TOP_PARAM] = 0.519230769230769

        rectParams = ratio[16]
        rectParams[WIDTH_PARAM] = 0.026578073089701
        rectParams[HEIGHT_PARAM] = 0.259615384615385
        rectParams[LEFT_PARAM] = 0.973421926910299
        rectParams[TOP_PARAM] = 0.480769230769231

        rectParams = ratio[17]
        rectParams[WIDTH_PARAM] = 0.368770764119601
        rectParams[HEIGHT_PARAM] = 0.100961538461538
        rectParams[LEFT_PARAM] = 0.358803986710963
        rectParams[TOP_PARAM] = 0.788461538461538

        rectParams = ratio[18]
        rectParams[WIDTH_PARAM] = 0.176079734219269
        rectParams[HEIGHT_PARAM] = 0.0528846153846154
        rectParams[LEFT_PARAM] = 0.415282392026578
        rectParams[TOP_PARAM] = 0.841346153846154

        rectParams = ratio[19]
        rectParams[WIDTH_PARAM] = 0.0863787375415282
        rectParams[HEIGHT_PARAM] = 0.0576923076923077
        rectParams[LEFT_PARAM] = 0.448504983388704
        rectParams[TOP_PARAM] = 0.899038461538462

        rectParams = ratio[20]
        rectParams[WIDTH_PARAM] = 0.0897009966777409
        rectParams[HEIGHT_PARAM] = 0.211538461538462
        rectParams[LEFT_PARAM] = 0.604651162790698
        rectParams[TOP_PARAM] = 1.0
    }

    private fun calculateBorders() {
        val submarineWidth = width
        val submarineHeight = height
        val submarineLeft = left
        val submarineBottom = bottom

        for ((index, border) in borders.withIndex()) {
            val params = ratio[index]

            border.width = submarineWidth * params[WIDTH_PARAM]!!.toFloat()
            border.height = submarineHeight * params[HEIGHT_PARAM]!!.toFloat()
            border.left = submarineLeft + submarineWidth * params[LEFT_PARAM]!!.toFloat()
            border.top = submarineBottom + submarineHeight * params[TOP_PARAM]!!.toFloat()
        }
    }

    override fun update(delta: Float) {
        interval += delta
        if (interval > SPEEDUP_INTERVAL) {
            interval = 0f

            if (pressed && vY < V_ASCENT_MAX || vX < V_FALL_MAX) {
                vY *= COMMON_RATIO
            }
        }
        if (bottom < worldBounds.bottom) {
            bottom = worldBounds.bottom
            if (!pressed) v.setZero()
        } else if (top > worldBounds.top) {
            top = worldBounds.top
            if (pressed) v.setZero()
        }
        super.update(delta)

        updateBorderPositions(delta)
    }

    private fun updateBorderPositions(delta: Float) {
        for (border in borders) {
            border.centerPos.mulAdd(v, delta)
        }
    }

    override fun touchDown(touch: Vector2, pointer: Int) {
        if (this.pointer != null) return
        this.pointer = pointer
        vY = V_ASCENT_MIN
        pressed = true
    }

    override fun touchUp(touch: Vector2, pointer: Int) {
        if (pointer != this.pointer) return
        this.pointer = null
        vY = -V_FALL_MIN
        pressed = false
    }

    override fun intersect(other: Rect): Boolean {
        if (super.intersect(other)) {
            return borders.any { it intersect other }
        }
        return false
    }

    fun setStartPosition() {
        centerPos.y = worldBounds.centerPos.y
        calculateBorders()
    }
}