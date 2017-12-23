package com.tehreh1uneh.littlesubmarineadventure.engine.pool

import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.tehreh1uneh.littlesubmarineadventure.engine.math.Rect
import com.tehreh1uneh.littlesubmarineadventure.engine.sprites.Resizable
import com.tehreh1uneh.littlesubmarineadventure.engine.sprites.SpriteBehaviour

abstract class SpritesPool<T> : SpriteBehaviour, Resizable where T : SpriteBehaviour, T : Resizable {

    val active: MutableList<T> = mutableListOf()
    private val inactive: MutableList<T> = mutableListOf()
    override var destroyed = false

    protected abstract fun newElement(): T

    fun get(): T {
        val element = if (inactive.isEmpty()) newElement() else inactive.removeAt(inactive.lastIndex)
        active += element
        println("active traps:${active.size} inactive traps: ${inactive.size}")
        return element
    }

    fun free(element: T) {
        if (!active.remove(element)) throw RuntimeException("Element does not exists in active elements")
        inactive += element
        println("active traps:${active.size} inactive traps: ${inactive.size}")
    }

    fun freeAll() {
        inactive.addAll(active)
        active.clear()
    }

    override fun update(delta: Float) {
        active.forEach { it.update(delta) }
    }

    override fun draw(batch: SpriteBatch) {
        active.forEach { it.draw(batch) }
    }

    override fun resize(worldBounds: Rect) {
        active.forEach { it.resize(worldBounds) }
    }

    fun freeAllDestroyed() {
        var i = 0
        while (i < active.size) {
            val sprite = active[i]
            if (sprite.destroyed) {
                free(sprite)
                i--
                sprite.destroyed = false
            }
            i++
        }
    }

    override fun setWidthProportion(width: Float) {
        active.forEach { it.setWidthProportion(width) }
    }

    override fun setHeightProportion(height: Float) {
        active.forEach { it.setHeightProportion(height) }
    }

    override fun setScale(scale: Float) {
        active.forEach { it.setScale(scale) }
    }
}