package com.tehreh1uneh.littlesubmarineadventure.engine.sprites

interface Resizable {
    fun setWidthProportion(width: Float = 1f)
    fun setHeightProportion(height: Float = 1f)
    fun setScale(scale: Float = 1f)
}