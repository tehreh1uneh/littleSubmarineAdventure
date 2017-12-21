package com.tehreh1uneh.littlesubmarineadventure.enemies

import com.badlogic.gdx.graphics.g2d.TextureRegion
import com.tehreh1uneh.littlesubmarineadventure.engine.sprites.Sprite

class Trap(vararg region: TextureRegion, vX: Float = 0f, vY: Float = 0f) : Sprite(*region, vX = vX, vY = vY) {

    private var updateRate: Float = 0.05f
    private var counter: Float = 0f

    override fun update(delta: Float) {
        counter += delta
        if (counter > updateRate) {
            counter = 0f
            if (++frame > regions.lastIndex) frame = 0
        }

        super.update(delta)
    }
}