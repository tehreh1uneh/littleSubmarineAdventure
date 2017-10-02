package com.tehreh1uneh.littlesubmarineadventure.Enemies

import com.badlogic.gdx.graphics.g2d.TextureRegion
import com.tehreh1uneh.littlesubmarineadventure.engine.sprites.Sprite

class Trap(vararg region: TextureRegion, vX: Float, vY: Float) : Sprite(*region, vX = vX, vY = vY) {

    var updateRate: Float = 0.05f
    var counter: Float = 0f

    override fun update(delta: Float) {
        counter += delta
        if (counter > updateRate) {
            counter = 0f
            if (++frame > regions.lastIndex) frame = 0
        }

        super.update(delta)
    }
}