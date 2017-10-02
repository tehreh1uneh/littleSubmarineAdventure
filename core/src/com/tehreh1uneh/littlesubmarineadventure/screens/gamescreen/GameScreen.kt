package com.tehreh1uneh.littlesubmarineadventure.screens.gamescreen

import com.badlogic.gdx.Game
import com.badlogic.gdx.graphics.g2d.TextureAtlas
import com.badlogic.gdx.math.Vector2
import com.tehreh1uneh.littlesubmarineadventure.Enemies.Trap
import com.tehreh1uneh.littlesubmarineadventure.Submarine
import com.tehreh1uneh.littlesubmarineadventure.common.Background
import com.tehreh1uneh.littlesubmarineadventure.common.PATH_GAME_ATLAS
import com.tehreh1uneh.littlesubmarineadventure.common.getBgTextures
import com.tehreh1uneh.littlesubmarineadventure.common.toTextureRegion
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
    private val trap = Trap(atlas.findRegion("trap(0)"), atlas.findRegion("trap(1)"), vX = 0f, vY = 0f)
    private val submarine = Submarine(atlas.findRegion("submarine"))


    override fun show() {
        super.show()
        Audio.play()
    }

    override fun resize(worldBounds: Rect) {
        background.resize(worldBounds)

        trap.resize(worldBounds)
        trap.setHeightProportion(worldBounds.height * 0.3f)

        trap.centerPos.set(worldBounds.centerPos)

        submarine.resize(worldBounds)
    }

    override fun render(delta: Float) {
        batch.begin()

        background.update(delta)
        trap.update(delta)

        background.draw(batch)
        trap.draw(batch)
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