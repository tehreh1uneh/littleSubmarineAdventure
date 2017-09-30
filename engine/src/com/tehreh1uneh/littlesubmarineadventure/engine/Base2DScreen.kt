package com.tehreh1uneh.littlesubmarineadventure.engine

import com.badlogic.gdx.Game
import com.badlogic.gdx.Gdx
import com.badlogic.gdx.InputProcessor
import com.badlogic.gdx.Screen
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.math.Matrix3
import com.badlogic.gdx.math.Matrix4
import com.badlogic.gdx.math.Vector2
import com.tehreh1uneh.littlesubmarineadventure.engine.Math.Rect
import com.tehreh1uneh.littlesubmarineadventure.engine.utils.timesAssign
import com.tehreh1uneh.littlesubmarineadventure.engine.utils.toTransformationMatrix

const val WORLD_WIDTH = 1f
private val glBounds = Rect(halfWidth = 1f, halfHeight = 1f)

open class Base2DScreen(protected val game: Game) : Screen, InputProcessor {

    private val screenBounds = Rect()
    private val worldBounds = Rect()
    private val matrixScreenToWorld = Matrix3()
    private val matrixWorldToGl = Matrix4()
    protected var batch = SpriteBatch()

    //region ScreenInterfaceMethods

    override fun show() {
        println("screen showed")
        Gdx.input.inputProcessor = this
    }

    protected open fun resize(worldBounds: Rect) {}

    override final fun resize(width: Int, height: Int) {
        println("screen resized(w:$width, h:$height)")

        screenBounds.setSize(width.toFloat(), height.toFloat())
        screenBounds.left = 0f
        screenBounds.bottom = 0f

        worldBounds.width = WORLD_WIDTH
        worldBounds.height = WORLD_WIDTH * (height.toFloat() / width.toFloat())

        matrixWorldToGl.toTransformationMatrix(worldBounds, glBounds)
        batch.projectionMatrix = matrixWorldToGl
        matrixScreenToWorld.toTransformationMatrix(screenBounds, worldBounds)
        resize(worldBounds)
    }

    override fun render(delta: Float) {
        //fun update(delta: Float)
        //fun checkCollisions()
        //fun deleteDestroyed()
        //fun draw()
    }

    override fun pause() {
        println("screen paused")
    }

    override fun resume() {
        println("screen resumed")
    }

    override fun hide() {
        println("screen hid")
        dispose()
    }

    override fun dispose() {
        println("screen disposed")
        batch.dispose()
    }
    //endregion

    protected open fun touchDown(touch: Vector2, pointer: Int) {}
    protected open fun touchMove(touch: Vector2, pointer: Int) {}
    protected open fun touchUp(touch: Vector2, pointer: Int) {}

    //region InputProcessorMethods
    private val touch = Vector2()

    override final fun touchDown(screenX: Int, screenY: Int, pointer: Int, button: Int): Boolean {
        touch.set(screenX.toFloat(), screenBounds.height - screenY.toFloat()) *= matrixScreenToWorld
        touchDown(touch, pointer)
        return false
    }

    override final fun touchUp(screenX: Int, screenY: Int, pointer: Int, button: Int): Boolean {
        touch.set(screenX.toFloat(), screenBounds.height - screenY.toFloat()) *= matrixScreenToWorld
        touchUp(touch, pointer)
        return false
    }

    override final fun touchDragged(screenX: Int, screenY: Int, pointer: Int): Boolean {
        touch.set(screenX.toFloat(), screenBounds.height - screenY.toFloat()) *= matrixScreenToWorld
        touchMove(touch, pointer)
        return false
    }

    override fun keyDown(keycode: Int): Boolean {
        return false
    }

    override fun mouseMoved(screenX: Int, screenY: Int): Boolean {
        return false
    }

    override fun keyTyped(character: Char): Boolean {
        return false
    }

    override fun scrolled(amount: Int): Boolean {
        return false
    }

    override fun keyUp(keycode: Int): Boolean {
        return false
    }

    //endregion
}

