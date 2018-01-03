package com.tehreh1uneh.littlesubmarineadventure.enemies

import com.badlogic.gdx.graphics.g2d.TextureAtlas
import com.tehreh1uneh.littlesubmarineadventure.engine.pool.SpritesPool


class TrapPool(private val atlas: TextureAtlas) : SpritesPool<Trap>() {

    override fun newElement(): Trap {
        return Trap(atlas.findRegion(TRAP_FRAME_FIRST_NAME), atlas.findRegion(TRAP_FRAME_SECOND_NAME), vX = TRAP_SPEED_BASIC)
    }

    fun stop() {
        active.forEach { it.stop() }
    }

    fun setStartState() {
        active.forEach {
            it.vX = TRAP_SPEED_BASIC
        }
        freeAll()
    }
}