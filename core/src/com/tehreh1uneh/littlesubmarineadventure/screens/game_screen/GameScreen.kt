package com.tehreh1uneh.littlesubmarineadventure.screens.game_screen

import com.badlogic.gdx.Game
import com.badlogic.gdx.graphics.g2d.TextureAtlas
import com.badlogic.gdx.graphics.g2d.TextureRegion
import com.badlogic.gdx.math.Vector2
import com.tehreh1uneh.littlesubmarineadventure.Submarine
import com.tehreh1uneh.littlesubmarineadventure.common.PATH_BG
import com.tehreh1uneh.littlesubmarineadventure.common.PATH_GAME_ATLAS
import com.tehreh1uneh.littlesubmarineadventure.common.PATH_SEABED
import com.tehreh1uneh.littlesubmarineadventure.engine.Base2DScreen
import com.tehreh1uneh.littlesubmarineadventure.engine.Sprite2DTexture
import com.tehreh1uneh.littlesubmarineadventure.engine.sprites.Rect
import com.tehreh1uneh.littlesubmarineadventure.engine.sprites.Sprite

class GameScreen(game: Game) : Base2DScreen(game) {

    private val textureBg = Sprite2DTexture(PATH_BG)
    private val background = Sprite(TextureRegion(textureBg))

    private val textureSeabed = Sprite2DTexture(PATH_SEABED)
    private val seabed = Sprite(TextureRegion(textureSeabed))

    private val atlas = TextureAtlas(PATH_GAME_ATLAS)

    private val submarine = Submarine(atlas.findRegion("submarine"))

    override fun show() {
        super.show()
        background.setWidthProportion()
        seabed.setWidthProportion()
    }

    override fun resize(worldBounds: Rect) {
        seabed.bottom = worldBounds.bottom
        submarine.resize(worldBounds)
    }

    override fun render(delta: Float) {
        batch.begin()

        background.draw(batch)
        seabed.draw(batch)
        submarine.update(delta)
        submarine.draw(batch)

        batch.end()
    }

    override fun hide() {
        dispose()
    }

    override fun dispose() {
        textureBg.dispose()
        textureSeabed.dispose()
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