package com.tehreh1uneh.littlesubmarineadventure.screens.gamescreen

import com.badlogic.gdx.Game
import com.badlogic.gdx.graphics.g2d.TextureAtlas
import com.badlogic.gdx.math.Vector2
import com.tehreh1uneh.littlesubmarineadventure.Submarine
import com.tehreh1uneh.littlesubmarineadventure.common.Background
import com.tehreh1uneh.littlesubmarineadventure.common.PATH_GAME_ATLAS
import com.tehreh1uneh.littlesubmarineadventure.common.getBgTextures
import com.tehreh1uneh.littlesubmarineadventure.common.toTextureRegion
import com.tehreh1uneh.littlesubmarineadventure.enemies.TRAP_HEIGHT_BASIC
import com.tehreh1uneh.littlesubmarineadventure.enemies.TrapEmitter
import com.tehreh1uneh.littlesubmarineadventure.enemies.TrapPool
import com.tehreh1uneh.littlesubmarineadventure.engine.Audio
import com.tehreh1uneh.littlesubmarineadventure.engine.Base2DScreen
import com.tehreh1uneh.littlesubmarineadventure.engine.math.Rect

class GameScreen(game: Game) : Base2DScreen(game) {

    private val backgroundTextures = getBgTextures()
    private val background = Background(*backgroundTextures.toTextureRegion())

    init {
        var i = 0
        background.spriteActions {
            it.mainSprite.vX = -0.02f * i++ - 0.02f
            it.additionalSprite.vX = -0.02f * i++ - 0.02f
        }
    }

    private val atlas = TextureAtlas(PATH_GAME_ATLAS)
    private val submarine = Submarine(atlas.findRegion("submarine"))
    private val trapPool = TrapPool(atlas)
    private val trapEmitter = TrapEmitter(trapPool, worldBounds, submarine.height)

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

        trapEmitter.resize(worldBounds, submarine.height)
    }

    override fun render(delta: Float) {
        batch.begin()

        trapEmitter.generateTrap(delta)

        update(delta)
        draw()
        checkCollisions()
        deleteAllDestroyed()

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
        background.update(delta)
        submarine.update(delta)
        trapPool.update(delta)
    }

    private fun draw() {
        background.draw(batch)
        submarine.draw(batch)
        trapPool.draw(batch)
    }


    private fun checkCollisions() {
        val traps = trapPool.active
        traps.forEach {
            if (it toTheLeftOf worldBounds) {
                it.destroyed = true
            }
        }
    }

    private fun deleteAllDestroyed() {
        trapPool.freeAllDestroyed()
    }

    override fun touchDown(touch: Vector2, pointer: Int) {
        submarine.touchDown(touch, pointer)
    }

    override fun touchUp(touch: Vector2, pointer: Int) {
        submarine.touchUp(touch, pointer)
    }
}