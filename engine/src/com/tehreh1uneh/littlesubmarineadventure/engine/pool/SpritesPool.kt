package com.tehreh1uneh.littlesubmarineadventure.engine.pool

import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.tehreh1uneh.littlesubmarineadventure.engine.sprites.SpriteBehaviour

abstract class SpritesPool<T : SpriteBehaviour> {

    val active: ArrayList<T> = arrayListOf()
    val inactive: ArrayList<T> = arrayListOf()

    protected abstract fun newElement(): T

    fun get(): T {
        val element = if (inactive.isEmpty()) newElement() else inactive.removeAt(inactive.lastIndex)
        active += element
        return element
    }

    fun free(element: T) {
        if (!active.remove(element)) throw RuntimeException("Element is not exists in active elements")
        inactive += element
    }

    fun freeAll() {
        inactive.addAll(active)
        active.clear()
    }

    fun update(delta: Float) {
        active.forEach { it.update(delta) }
    }

    fun draw(batch: SpriteBatch) {
        active.forEach { it.draw(batch) }
    }
}