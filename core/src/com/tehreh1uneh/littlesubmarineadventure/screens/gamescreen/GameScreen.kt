package com.tehreh1uneh.littlesubmarineadventure.screens.gamescreen

import com.badlogic.gdx.Game
import com.badlogic.gdx.graphics.g2d.TextureAtlas
import com.badlogic.gdx.math.Vector2
import com.tehreh1uneh.littlesubmarineadventure.Submarine
import com.tehreh1uneh.littlesubmarineadventure.common.*
import com.tehreh1uneh.littlesubmarineadventure.enemies.TRAP_HEIGHT_BASIC
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

    override fun show() {
        super.show()
        Audio.play()

        for (i in 1..10) {
            trapPool.get()
        }
    }

    override fun resize(worldBounds: Rect) {
        background.resize(worldBounds)

        trapPool.worldBounds = worldBounds
        trapPool.resize(worldBounds)
        trapPool.setHeightProportion(worldBounds.height * TRAP_HEIGHT_BASIC)
        trapPool.forEach { it.centerPos.set(evalRandomFloat(-worldBounds.halfWidth, worldBounds.halfWidth), evalRandomFloat(-worldBounds.halfHeight, worldBounds.halfHeight)) }

        submarine.resize(worldBounds)
    }

    override fun render(delta: Float) {
        batch.begin()

        background.update(delta)

        trapPool.update(delta)
        background.draw(batch)

        trapPool.draw(batch)
        submarine.update(delta)
        submarine.draw(batch)

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

    override fun touchDown(touch: Vector2, pointer: Int) {
        submarine.touchDown(touch, pointer)
    }

    override fun touchUp(touch: Vector2, pointer: Int) {
        submarine.touchUp(touch, pointer)
    }
}