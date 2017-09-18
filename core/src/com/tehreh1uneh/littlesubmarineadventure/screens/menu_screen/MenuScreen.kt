package com.tehreh1uneh.littlesubmarineadventure.screens.menu_screen

import com.badlogic.gdx.Game
import com.badlogic.gdx.graphics.g2d.TextureRegion
import com.tehreh1uneh.littlesubmarineadventure.common.PATH_BG
import com.tehreh1uneh.littlesubmarineadventure.engine.Base2DScreen
import com.tehreh1uneh.littlesubmarineadventure.engine.Sprite2DTexture
import com.tehreh1uneh.littlesubmarineadventure.engine.sprites.Rect
import com.tehreh1uneh.littlesubmarineadventure.engine.sprites.Sprite
import com.tehreh1uneh.littlesubmarineadventure.engine.ui.TouchListener

class MenuScreen(game: Game) : Base2DScreen(game), TouchListener {

    val textureBg = Sprite2DTexture(PATH_BG)
    val background = Sprite(TextureRegion(textureBg))

    val textureLogo = Sprite2DTexture("textures/badlogic.jpg")
    val logo = Sprite(TextureRegion(textureLogo))

    //region ScreenEvents
    override fun show() {
        super.show()
        background.setWidthProportion(1f)
        logo.setWidthProportion(0.3f)
    }

    override fun resize(worldBounds: Rect) {
        super.resize(worldBounds)
    }

    override fun render(delta: Float) {
        batch.begin()

        background.draw(batch)

        logo.rotation--
        logo.draw(batch)

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
        textureLogo.dispose()

        super.dispose()
    }
    //endregion

    override fun actionPerformed(src: Any) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}