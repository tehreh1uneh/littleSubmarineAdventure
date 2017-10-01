package com.tehreh1uneh.littlesubmarineadventure.screens.gamescreen

import com.badlogic.gdx.Game
import com.badlogic.gdx.graphics.g2d.TextureAtlas
import com.badlogic.gdx.math.Vector2
import com.tehreh1uneh.littlesubmarineadventure.Submarine
import com.tehreh1uneh.littlesubmarineadventure.common.Background
import com.tehreh1uneh.littlesubmarineadventure.common.PATH_GAME_ATLAS
import com.tehreh1uneh.littlesubmarineadventure.common.getBgTextures
import com.tehreh1uneh.littlesubmarineadventure.common.toTextureRegion
import com.tehreh1uneh.littlesubmarineadventure.engine.Base2DScreen
import com.tehreh1uneh.littlesubmarineadventure.engine.math.Rect

class GameScreen(game: Game) : Base2DScreen(game) {

    private val backgroundTextures = getBgTextures()
    private val background = Background(*backgroundTextures.toTextureRegion())

    //TODO initialize background speed

    private val atlas = TextureAtlas(PATH_GAME_ATLAS)

    private val submarine = Submarine(atlas.findRegion("submarine"))

    override fun resize(worldBounds: Rect) {
        background.resize(worldBounds)
        submarine.resize(worldBounds)
    }

    override fun render(delta: Float) {
        batch.begin()

        background.update(delta)
        background.draw(batch)
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

        super.dispose()
    }

    override fun touchDown(touch: Vector2, pointer: Int) {
        submarine.touchDown(touch, pointer)
    }

    override fun touchUp(touch: Vector2, pointer: Int) {
        submarine.touchUp(touch, pointer)
    }
}