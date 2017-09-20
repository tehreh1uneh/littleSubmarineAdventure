package com.tehreh1uneh.littlesubmarineadventure.screens.menu_screen

import com.badlogic.gdx.Game
import com.badlogic.gdx.graphics.g2d.TextureAtlas
import com.badlogic.gdx.graphics.g2d.TextureRegion
import com.badlogic.gdx.math.Vector2
import com.tehreh1uneh.littlesubmarineadventure.common.PATH_BG
import com.tehreh1uneh.littlesubmarineadventure.common.PATH_MENU_ATLAS
import com.tehreh1uneh.littlesubmarineadventure.common.PATH_SEABED
import com.tehreh1uneh.littlesubmarineadventure.engine.Base2DScreen
import com.tehreh1uneh.littlesubmarineadventure.engine.Sprite2DTexture
import com.tehreh1uneh.littlesubmarineadventure.engine.sprites.OnTouchScalingButton
import com.tehreh1uneh.littlesubmarineadventure.engine.sprites.Rect
import com.tehreh1uneh.littlesubmarineadventure.engine.sprites.Sprite
import com.tehreh1uneh.littlesubmarineadventure.engine.ui.TouchListener

private const val BUTTON_WIDTH = 0.1f
private const val BUTTON_SCALE = 0.9f

class MenuScreen(game: Game) : Base2DScreen(game), TouchListener {

    val textureBg = Sprite2DTexture(PATH_BG)
    val background = Sprite(TextureRegion(textureBg))

    val textureSeabed = Sprite2DTexture(PATH_SEABED)
    val seabed = Sprite(TextureRegion(textureSeabed))

    val menuAtlas = TextureAtlas(PATH_MENU_ATLAS)

    val textureRegionStartButton = menuAtlas.findRegion("button_start_game")
    val startButton = OnTouchScalingButton(textureRegionStartButton, touchListener = this, scale = BUTTON_SCALE)

    //region ScreenEvents
    override fun show() {
        super.show()
        background.setWidthProportion()
        seabed.setWidthProportion()
        startButton.setWidthProportion(BUTTON_WIDTH)
    }

    override fun resize(worldBounds: Rect) {
        super.resize(worldBounds)
        seabed.bottom = worldBounds.bottom
        startButton.left = worldBounds.left + worldBounds.width * 0.02f
        startButton.bottom = worldBounds.bottom + worldBounds.height * 0.1f

    }

    override fun render(delta: Float) {
        batch.begin()

        background.draw(batch)
        seabed.draw(batch)
        startButton.draw(batch)

        batch.end()
    }

    override fun pause() {
        super.pause()
    }

    override fun resume() {
        super.resume()
    }

    override fun hide() {
        super.hide()
    }

    override fun dispose() {
        textureBg.dispose()
        textureSeabed.dispose()
        menuAtlas.dispose()

        super.dispose()
    }
    //endregion

    override fun actionPerformed(src: Any) {
        if (src == startButton) {
            println("Start button pressed!")
        }
    }

    //region UserActions

    override fun touchDown(touch: Vector2, pointer: Int) {
//        println("height(${worldBounds.bottom} to ${worldBounds.top})\nwidth(${worldBounds.left} to ${worldBounds.right})\nx:${touch.x}, y:${touch.y}")
        startButton.touchDown(touch, pointer)
    }

    override fun touchUp(touch: Vector2, pointer: Int) {
        startButton.touchUp(touch, pointer)
    }
    //endregion

}