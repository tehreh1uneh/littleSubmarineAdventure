package com.tehreh1uneh.littlesubmarineadventure.screens.menu_screen

import com.badlogic.gdx.Game
import com.badlogic.gdx.graphics.g2d.TextureRegion
import com.badlogic.gdx.math.Vector2
import com.tehreh1uneh.littlesubmarineadventure.common.PATH_BG
import com.tehreh1uneh.littlesubmarineadventure.common.PATH_START_BUTTON
import com.tehreh1uneh.littlesubmarineadventure.engine.Base2DScreen
import com.tehreh1uneh.littlesubmarineadventure.engine.Sprite2DTexture
import com.tehreh1uneh.littlesubmarineadventure.engine.sprites.OnTouchScalingButton
import com.tehreh1uneh.littlesubmarineadventure.engine.sprites.Rect
import com.tehreh1uneh.littlesubmarineadventure.engine.sprites.Sprite
import com.tehreh1uneh.littlesubmarineadventure.engine.ui.TouchListener

private const val BUTTON_SCALE = 0.9f

class MenuScreen(game: Game) : Base2DScreen(game), TouchListener {

    val textureBg = Sprite2DTexture(PATH_BG)
    val background = Sprite(TextureRegion(textureBg))

    val textureStartButton = Sprite2DTexture(PATH_START_BUTTON)
    val startButton = OnTouchScalingButton(TextureRegion(textureStartButton), touchListener = this, scale = BUTTON_SCALE)


    //region ScreenEvents
    override fun show() {
        super.show()
        background.setWidthProportion(1f)
        startButton.setWidthProportion(0.1f)
        startButton.centerPos.x -= worldBounds.width * 0.4f
        startButton.centerPos.y -= worldBounds.height * 0.4f

    }

    override fun resize(worldBounds: Rect) {
        super.resize(worldBounds)
    }

    override fun render(delta: Float) {
        batch.begin()

        background.draw(batch)
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
        textureStartButton.dispose()

        super.dispose()
    }
    //endregion

    override fun actionPerformed(src: Any) {
        if (src == startButton){
            println("Start button pressed!")
        }
    }

    //region UserActions

    override fun touchDown(touch: Vector2, pointer: Int) {
        startButton.touchDown(touch, pointer)
    }

    override fun touchUp(touch: Vector2, pointer: Int) {
        startButton.touchUp(touch, pointer)
    }
    //endregion
}