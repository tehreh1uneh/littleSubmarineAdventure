package com.tehreh1uneh.littlesubmarineadventure.screens.gamescreen

import com.badlogic.gdx.Game
import com.badlogic.gdx.graphics.g2d.TextureAtlas
import com.badlogic.gdx.math.Vector2
import com.tehreh1uneh.littlesubmarineadventure.Submarine
import com.tehreh1uneh.littlesubmarineadventure.common.*
import com.tehreh1uneh.littlesubmarineadventure.enemies.TRAP_HEIGHT_BASIC
import com.tehreh1uneh.littlesubmarineadventure.enemies.TRAP_SPEED_BASIC
import com.tehreh1uneh.littlesubmarineadventure.enemies.TrapEmitter
import com.tehreh1uneh.littlesubmarineadventure.enemies.TrapPool
import com.tehreh1uneh.littlesubmarineadventure.engine.Audio
import com.tehreh1uneh.littlesubmarineadventure.engine.Base2DScreen
import com.tehreh1uneh.littlesubmarineadventure.engine.math.Rect
import com.tehreh1uneh.littlesubmarineadventure.engine.sprites.OnTouchScalingButton
import com.tehreh1uneh.littlesubmarineadventure.engine.ui.TouchListener

class GameScreen(game: Game) : Base2DScreen(game), TouchListener {

    private val backgroundTextures = getBgTextures()
    private val background = Background(*backgroundTextures.toTextureRegion())
    private val restartButtonName = "button_restart_game"
    private var state = ObjectState.ACTIVE

    init {
        background.initSpeed()
    }

    private val atlas = TextureAtlas(PATH_GAME_ATLAS)
    private val submarine = Submarine(atlas.findRegion("submarine"))
    private val trapPool = TrapPool(atlas)
    private val trapEmitter = TrapEmitter(trapPool, worldBounds, submarine.height)
    private val buttonRestart = OnTouchScalingButton(atlas.findRegion(restartButtonName), touchListener = this, scale = BUTTON_SCALE)

    override fun show() {
        super.show()
        Audio.play()
    }

    override fun resize(worldBounds: Rect) {

        this.worldBounds = worldBounds

        background.resize(worldBounds)
        trapPool.resize(worldBounds)
        trapPool.setHeightProportion(worldBounds.height * TRAP_HEIGHT_BASIC)
        submarine.resize(worldBounds)

        buttonRestart.setWidthProportion(BUTTON_WIDTH)
        buttonRestart.resize(worldBounds)

        trapEmitter.resize(worldBounds, submarine.height)
    }

    override fun render(delta: Float) {
        batch.begin()

        update(delta)
        draw()

        if (state == ObjectState.ACTIVE) {
            checkCollisions()
            deleteAllDestroyed()
        }

        batch.end()
    }

    override fun hide() {
        dispose()
    }

    override fun dispose() {
        backgroundTextures.forEach { it.dispose() }
        atlas.dispose()
        Audio.dispose()

        super.dispose()
    }

    private fun update(delta: Float) {

        if (state == ObjectState.ACTIVE) {
            trapEmitter.generateTrap(delta)
        } else if (state == ObjectState.STOP) {
            buttonRestart.update(delta)
        } else {
            RuntimeException("Unknown object state")
        }

        background.update(delta)
        submarine.update(delta)
        trapPool.update(delta)
    }

    private fun draw() {
        background.draw(batch)
        submarine.draw(batch)
        trapPool.draw(batch)

        if (state == ObjectState.STOP) {
            buttonRestart.draw(batch)
        }
    }

    private fun checkCollisions() {
        val traps = trapPool.active
        traps.forEach {
            if (it toTheLeftOf worldBounds) {
                it.destroyed = true
            } else if (it intersect submarine) {
                stopGame()
                return@forEach
            }
        }
    }

    private fun stopGame() {
        state = ObjectState.STOP
        submarine.v.setZero()
        background.stop()

        val traps = trapPool.active
        traps.forEach {
            it.v.setZero()
        }
    }

    private fun deleteAllDestroyed() {
        trapPool.freeAllDestroyed()
    }

    override fun touchDown(touch: Vector2, pointer: Int) {

        if (state == ObjectState.ACTIVE) {
            submarine.touchDown(touch, pointer)
        } else {
            buttonRestart.touchDown(touch, pointer)
        }
    }

    override fun touchUp(touch: Vector2, pointer: Int) {
        if (state == ObjectState.ACTIVE) {
            submarine.touchUp(touch, pointer)
        } else {
            buttonRestart.touchUp(touch, pointer)
        }
    }

    override fun actionPerformed(src: Any) {
        if (src == buttonRestart) {
            restartGame()
        }
    }

    private fun restartGame() {
        state = ObjectState.ACTIVE
        background.initSpeed()

        val traps = trapPool.active
        traps.forEach {
            it.vX = TRAP_SPEED_BASIC
        }
        trapPool.freeAll()
    }
}