package com.tehreh1uneh.littlesubmarineadventure.engine.pool

import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.tehreh1uneh.littlesubmarineadventure.engine.math.Rect
import com.tehreh1uneh.littlesubmarineadventure.engine.sprites.Resizable
import com.tehreh1uneh.littlesubmarineadventure.engine.sprites.SpriteBehaviour

abstract class SpritesPool<T> : SpriteBehaviour, Resizable  where T : SpriteBehaviour, T : Resizable {

    protected val active: ArrayList<T> = arrayListOf()
    protected val inactive: ArrayList<T> = arrayListOf()

    protected abstract fun newElement(): T

    fun get(): T {
        val element = if (inactive.isEmpty()) newElement() else inactive.removeAt(inactive.lastIndex)
        active += element
        return element
    }

    fun free(element: T) {
        if (!active.remove(element)) throw RuntimeException("Element does not exists in active elements")
        inactive += element
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

    override fun setWidthProportion(width: Float) {
        active.forEach { it.setWidthProportion(width) }
    }

    override fun setHeightProportion(height: Float) {
        active.forEach { it.setHeightProportion(height) }
    }

    override fun setScale(scale: Float) {
        active.forEach { it.setScale(scale) }
    }

    fun forEach(action: (T) -> Unit) {
        active.forEach(action)
    }
}