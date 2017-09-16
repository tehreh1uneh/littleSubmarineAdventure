package com.tehreh1uneh.littlesubmarineadventure.engine.math

import com.badlogic.gdx.math.Matrix3
import com.badlogic.gdx.math.Matrix4
import java.util.*

private val rndGen = Random()
fun evalRandomFloat(min: Float = 0f, max: Float = 1f) = rndGen.nextFloat() * (max - min) + min

fun Matrix3.transformationMatrix(srcRect: Rect, dstRect: Rect) {
    this.idt()
            .translate(dstRect.pos.x, dstRect.pos.y)
            .scale(dstRect.getWidth() / srcRect.getWidth(), dstRect.getHeight() / srcRect.getHeight())
            .translate(-srcRect.pos.x, -srcRect.pos.y)
}

fun Matrix4.transformationMatrix(srcRect: Rect, dstRect: Rect) {
    this.idt()
            .translate(dstRect.pos.x, dstRect.pos.y, 0f)
            .scale(dstRect.getWidth() / srcRect.getWidth(), dstRect.getHeight() / srcRect.getHeight(), 1f)
            .translate(-srcRect.pos.x, -srcRect.pos.y, 0f)
}