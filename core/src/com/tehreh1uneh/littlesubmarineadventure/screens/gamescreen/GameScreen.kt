package com.tehreh1uneh.littlesubmarineadventure.screens.gamescreen

import com.badlogic.gdx.Game
import com.badlogic.gdx.graphics.g2d.TextureAtlas
import com.badlogic.gdx.graphics.g2d.TextureRegion
import com.badlogic.gdx.math.Vector2
import com.tehreh1uneh.littlesubmarineadventure.Submarine
import com.tehreh1uneh.littlesubmarineadventure.common.*
import com.tehreh1uneh.littlesubmarineadventure.enemies.TRAP_HEIGHT_BASIC
import com.tehreh1uneh.littlesubmarineadventure.enemies.TRAP_SPEED_BASIC
import com.tehreh1uneh.littlesubmarineadventure.enemies.TrapEmitter
import com.tehreh1uneh.littlesubmarineadventure.enemies.TrapPool
import com.tehreh1uneh.littlesubmarineadventure.engine.Audio
import com.tehreh1uneh.littlesubmarineadventure.engine.Base2DScreen
import com.tehreh1uneh.littlesubmarineadventure.engine.Font
import com.tehreh1uneh.littlesubmarineadventure.engine.Sprite2DTexture
import com.tehreh1uneh.littlesubmarineadventure.engine.math.Rect
import com.tehreh1uneh.littlesubmarineadventure.engine.sprites.OnTouchScalingButton
import com.tehreh1uneh.littlesubmarineadventure.engine.sprites.Sprite
import com.tehreh1uneh.littlesubmarineadventure.engine.ui.TouchListener

class GameScreen(game: Game) : Base2DScreen(game), TouchListener {

    private val backgroundTextures = getBgTextures()
    private val background = Background(*backgroundTextures.toTextureRegion())
    private var state = ObjectState.ACTIVE

    init {
        background.initSpeed()
    }

    private val atlas = TextureAtlas(PATH_GAME_ATLAS)
    private val submarine = Submarine(atlas.findRegion("submarine"))
    private val backgroundGreyTexture = Sprite2DTexture(PATH_BACKGROUND_GREY, false)
    private val backgroundGrey = Sprite(TextureRegion(backgroundGreyTexture))
    private val trapPool = TrapPool(atlas)
    private val trapEmitter = TrapEmitter(trapPool, worldBounds, submarine.height)
    private val buttonRestart = OnTouchScalingButton(atlas.findRegion("button_restart_game"), touchListener = this, scale = BUTTON_SCALE)
    private val font = Font(PATH_FONT_DESCRIPTION, PATH_FONT_IMAGE)
    private val stringBuilderScore = StringBuilder()
    private var score = 0

    override fun show() {
        super.show()
        Audio.play()
    }

    override fun resize(worldBounds: Rect) {

        this.worldBounds = worldBounds

        backgroundGrey.resize(worldBounds)
        backgroundGrey.setWidthProportion(worldBounds.width)
        background.resize(worldBounds)
        trapPool.resize(worldBounds)
        trapPool.setHeightProportion(worldBounds.height * TRAP_HEIGHT_BASIC)
        submarine.resize(worldBounds)

        buttonRestart.setWidthProportion(BUTTON_WIDTH)
        buttonRestart.resize(worldBounds)

        trapEmitter.resize(worldBounds, submarine.height)
        font.heightScale = worldBounds.height * FONT_HEIGHT_SCALE
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
        backgroundGreyTexture.dispose()
        atlas.dispose()
        font.dispose()
        Audio.dispose()

        super.dispose()
    }

    private fun update(delta: Float) {

        when (state) {
            ObjectState.ACTIVE -> trapEmitter.generateTrap(delta)
            ObjectState.STOP -> {
                buttonRestart.update(delta)
                backgroundGrey.update(delta)
            }
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
            backgroundGrey.draw(batch)
            buttonRestart.draw(batch)
        }

        drawScore()
    }

    private fun drawScore(x: Float = worldBounds.left, y: Float = worldBounds.top) {
        stringBuilderScore.clear()
        font.draw(batch, stringBuilderScore.append(SCORE_DESCRIPTION).append(score), x, y)
    }

    private fun checkCollisions() {
        val traps = trapPool.active
        var scoreIncreased = false

        traps.forEach {
            if (it toTheLeftOf submarine) {
                if (!scoreIncreased && !it.counted) {
                    score++
                    scoreIncreased = true
                }
                if (!it.counted && !it.destroyed) {
                    it.counted = true
                }
            }

            if (it toTheLeftOf worldBounds) {
                it.destroyed = true
                it.counted = false
            } else if (submarine intersect it) {
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
        when (state) {
            ObjectState.ACTIVE -> submarine.touchDown(touch, pointer)
            ObjectState.STOP -> buttonRestart.touchDown(touch, pointer)
        }
    }

    override fun touchUp(touch: Vector2, pointer: Int) {
        when (state) {
            ObjectState.ACTIVE -> submarine.touchUp(touch, pointer)
            ObjectState.STOP -> buttonRestart.touchUp(touch, pointer)
        }
    }

    override fun actionPerformed(src: Any) {
        if (src == buttonRestart) {
            restartGame()
        }
    }

    private fun restartGame() {
        state = ObjectState.ACTIVE
        score = 0
        background.initSpeed()
        submarine.centerPos.y = worldBounds.centerPos.y
        submarine.resize(worldBounds)

        val traps = trapPool.active
        traps.forEach {
            it.vX = TRAP_SPEED_BASIC
        }
        trapPool.freeAll()
    }
}