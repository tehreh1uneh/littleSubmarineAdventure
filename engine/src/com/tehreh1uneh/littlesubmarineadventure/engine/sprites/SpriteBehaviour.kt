package com.tehreh1uneh.littlesubmarineadventure.engine.sprites

import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.math.Vector2
import com.tehreh1uneh.littlesubmarineadventure.engine.math.Rect

interface SpriteBehaviour {

    fun resize(worldBounds: Rect) {}
    fun update(delta: Float) {}
    fun draw(batch: SpriteBatch) {}
    fun touchDown(touch: Vector2, pointer: Int) {}
    fun touchMove(touch: Vector2, pointer: Int) {}
    fun touchUp(touch: Vector2, pointer: Int) {}

}