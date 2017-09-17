package com.tehreh1uneh.littlesubmarineadventure.engine.math

import com.badlogic.gdx.math.Matrix3
import com.badlogic.gdx.math.Matrix4
import java.util.*

private val rndGen = Random()
internal fun evalRandomFloat(min: Float = 0f, max: Float = 1f) = rndGen.nextFloat() * (max - min) + min

internal fun Matrix3.transformationMatrix(srcRect: Rect, dstRect: Rect) {
    idt()
            .translate(dstRect.centerPos.x, dstRect.centerPos.y)
            .scale(dstRect.width / srcRect.width, dstRect.height / srcRect.height)
            .translate(-srcRect.centerPos.x, -srcRect.centerPos.y)
}

internal fun Matrix4.transformationMatrix(srcRect: Rect, dstRect: Rect) {
    idt()
            .translate(dstRect.centerPos.x, dstRect.centerPos.y, 0f)
            .scale(dstRect.width / srcRect.width, dstRect.height / srcRect.height, 1f)
            .translate(-srcRect.centerPos.x, -srcRect.centerPos.y, 0f)
}

