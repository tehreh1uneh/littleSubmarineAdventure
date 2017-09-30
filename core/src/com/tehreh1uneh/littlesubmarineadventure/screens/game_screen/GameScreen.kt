package com.tehreh1uneh.littlesubmarineadventure.screens.game_screen

import com.badlogic.gdx.Game
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.TextureAtlas
import com.badlogic.gdx.graphics.g2d.TextureRegion
import com.badlogic.gdx.math.Vector2
import com.tehreh1uneh.littlesubmarineadventure.Submarine
import com.tehreh1uneh.littlesubmarineadventure.common.Background
import com.tehreh1uneh.littlesubmarineadventure.common.PATH_BACKGROUND_MASK
import com.tehreh1uneh.littlesubmarineadventure.common.PATH_GAME_ATLAS
import com.tehreh1uneh.littlesubmarineadventure.engine.Base2DScreen
import com.tehreh1uneh.littlesubmarineadventure.engine.Math.Axis
import com.tehreh1uneh.littlesubmarineadventure.engine.Math.Rect
import com.tehreh1uneh.littlesubmarineadventure.engine.Sprite2DTexture

class GameScreen(game: Game) : Base2DScreen(game) {

    private val backgroundTextures: Array<Texture> = Array(4) { Sprite2DTexture(PATH_BACKGROUND_MASK.replace("%", it.toString())) }
    private val background = Background(TextureRegion(backgroundTextures[0]), TextureRegion(backgroundTextures[1]), TextureRegion(backgroundTextures[2]), TextureRegion(backgroundTextures[3]), vX = -0.05f, moveDirection = Axis.X)

    private val atlas = TextureAtlas(PATH_GAME_ATLAS)

    private val submarine = Submarine(atlas.findRegion("submarine"))

    override fun show() {
        super.show()
    }

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