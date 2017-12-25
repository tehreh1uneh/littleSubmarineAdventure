package com.tehreh1uneh.littlesubmarineadventure.enemies

import com.tehreh1uneh.littlesubmarineadventure.common.evalRandomFloat
import com.tehreh1uneh.littlesubmarineadventure.engine.math.Rect

class TrapEmitter(private val trapPool: TrapPool, private var worldBounds: Rect, private var submarineHeight: Float) {

    private var trapGeneratorTimer = 0f
    private var trapGeneratorInterval = TRAP_GENERATOR_INTERVAL_BASIC

    fun generateTrap(delta: Float) {
        trapGeneratorTimer += delta
        if (trapGeneratorTimer < trapGeneratorInterval) return

        trapGeneratorTimer = 0f

        val trapHeight = worldBounds.height * TRAP_HEIGHT_BASIC
        val windowHeight = submarineHeight * 2
        val startPosition = evalRandomFloat(worldBounds.bottom, worldBounds.top)
        var currentPosition = startPosition

        while (true) {
            val freeSpace = worldBounds.top - currentPosition
            if (freeSpace < windowHeight) break

            val trap = trapPool.get()
            trap.setHeightProportion(trapHeight)
            trap.left = worldBounds.right + trap.width

            trap.bottom = currentPosition + windowHeight
            currentPosition = trap.top
        }

        if (startPosition != worldBounds.bottom) {
            val trap = trapPool.get()
            trap.setHeightProportion(trapHeight)
            trap.left = worldBounds.right + trap.width

            trap.top = startPosition
        }

        currentPosition = startPosition - trapHeight

        while (true) {
            val freeSpace = currentPosition - worldBounds.bottom
            if (freeSpace < windowHeight) break

            val trap = trapPool.get()
            trap.setHeightProportion(trapHeight)
            trap.left = worldBounds.right + trap.width

            trap.top = currentPosition - windowHeight
            currentPosition = trap.bottom
        }
    }

    fun resize(worldBounds: Rect, submarineHeight: Float) {
        this.worldBounds = worldBounds
        this.submarineHeight = submarineHeight
    }
}



