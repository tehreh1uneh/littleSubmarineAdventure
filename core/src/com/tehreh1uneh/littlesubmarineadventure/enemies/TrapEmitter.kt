package com.tehreh1uneh.littlesubmarineadventure.enemies

import com.tehreh1uneh.littlesubmarineadventure.common.evalRandomFloat
import com.tehreh1uneh.littlesubmarineadventure.engine.math.Rect

class TrapEmitter(val trapPool: TrapPool, var worldBounds: Rect) {

    private var trapGeneratorTimer = 0f
    var trapGeneratorInterval = TRAP_GENERATOR_INTERVAL_BASIC
    var trapInterval = TRAP_INTERVAL_BASIC

    fun generateTrap(delta: Float) {
        trapGeneratorTimer += delta
        if (trapGeneratorTimer < trapGeneratorInterval) return

        // TODO think how to fill all height of screen

        trapGeneratorTimer = 0f
//        val trap = trapPool.get()
//        trap.setHeightProportion(worldBounds.height * TRAP_HEIGHT_BASIC)
//        trap.centerPos.x = worldBounds.right + trap.width / 2
//        trap.bottom = evalRandomFloat(worldBounds.bottom + worldBounds.height * trapInterval, worldBounds.top)
//
//        val secondTrap = trapPool.get()
//        secondTrap.setHeightProportion(worldBounds.height * TRAP_HEIGHT_BASIC)
//        secondTrap.centerPos.x = worldBounds.right + trap.width / 2
//        secondTrap.top = trap.bottom - worldBounds.height * trapInterval

        val trap = trapPool.get()
        trap.setHeightProportion(worldBounds.height * TRAP_HEIGHT_BASIC)
        trap.centerPos.x = worldBounds.right + trap.halfWidth
        trap.bottom = evalRandomFloat(worldBounds.bottom, worldBounds.top - trap.height)


    }
}



