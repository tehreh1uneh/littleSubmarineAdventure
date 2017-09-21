package com.tehreh1uneh.littlesubmarineadventure.engine.utils

import com.badlogic.gdx.graphics.g2d.TextureRegion
import com.badlogic.gdx.math.Matrix3
import com.badlogic.gdx.math.Matrix4
import com.badlogic.gdx.math.Vector2
import com.tehreh1uneh.littlesubmarineadventure.engine.sprites.Rect
import java.util.*

private val rndGen = Random()
internal fun evalRandomFloat(min: Float = 0f, max: Float = 1f) = rndGen.nextFloat() * (max - min) + min

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

operator fun Vector2.times(matrix: Matrix3){
    mul(matrix)
}

internal fun TextureRegion.split(rows: Int = 1, columns: Int = 1, frames: Int = 2): Array<TextureRegion> {

    val elementWidth = regionWidth / columns
    val elementHeight = regionHeight / rows

//    val regions: Array<TextureRegion> = emptyArray()
//    var frame = 0
//
//    outerLoop@for (r in 0 until rows) {
//        for (c in 0 until columns) {
//            regions[frame++] = TextureRegion(this, elementWidth * c, elementHeight * r, elementWidth, elementHeight)
//            if (frame == frames) break@outerLoop
//        }
//    }

    return Array(frames, { i -> TextureRegion(this, elementWidth * (i % columns), elementHeight * (i / columns), elementWidth, elementHeight) })
}

