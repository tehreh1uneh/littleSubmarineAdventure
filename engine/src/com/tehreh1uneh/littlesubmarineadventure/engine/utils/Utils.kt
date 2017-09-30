package com.tehreh1uneh.littlesubmarineadventure.engine.utils

import com.badlogic.gdx.graphics.g2d.TextureRegion
import com.badlogic.gdx.math.Matrix3
import com.badlogic.gdx.math.Matrix4
import com.badlogic.gdx.math.Vector2
import com.tehreh1uneh.littlesubmarineadventure.engine.Math.Rect

internal fun Matrix3.toTransformationMatrix(srcRect: Rect, dstRect: Rect) {
    idt()
            .translate(dstRect.centerPos.x, dstRect.centerPos.y)
            .scale(dstRect.width / srcRect.width, dstRect.height / srcRect.height)
            .translate(-srcRect.centerPos.x, -srcRect.centerPos.y)
}

internal fun Matrix4.toTransformationMatrix(srcRect: Rect, dstRect: Rect) {
    idt()
            .translate(dstRect.centerPos.x, dstRect.centerPos.y, 0f)
            .scale(dstRect.width / srcRect.width, dstRect.height / srcRect.height, 1f)
            .translate(-srcRect.centerPos.x, -srcRect.centerPos.y, 0f)
}

operator internal fun Vector2.timesAssign(matrix: Matrix3) {
    mul(matrix)
}

fun TextureRegion.split(rows: Int = 1, columns: Int = 2, frames: Int = 2): Array<TextureRegion> {

    if (frames > rows * columns) throw IllegalArgumentException("Amount of frames more than product of rows and columns")
    val elementWidth = regionWidth / columns
    val elementHeight = regionHeight / rows

    return Array(frames, { TextureRegion(this, elementWidth * (it % columns), elementHeight * (it / columns), elementWidth, elementHeight) })
}
