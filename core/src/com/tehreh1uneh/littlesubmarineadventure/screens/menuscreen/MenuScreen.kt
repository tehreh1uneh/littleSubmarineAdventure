package com.tehreh1uneh.littlesubmarineadventure.screens.menuscreen

import com.badlogic.gdx.Game
import com.badlogic.gdx.graphics.g2d.TextureAtlas
import com.badlogic.gdx.math.Vector2
import com.tehreh1uneh.littlesubmarineadventure.common.*
import com.tehreh1uneh.littlesubmarineadventure.engine.Audio
import com.tehreh1uneh.littlesubmarineadventure.engine.Base2DScreen
import com.tehreh1uneh.littlesubmarineadventure.engine.math.Axis
import com.tehreh1uneh.littlesubmarineadventure.engine.math.Rect
import com.tehreh1uneh.littlesubmarineadventure.engine.sprites.OnTouchScalingButton
import com.tehreh1uneh.littlesubmarineadventure.engine.ui.TouchListener
import com.tehreh1uneh.littlesubmarineadventure.screens.gamescreen.GameScreen

private const val BUBBLES_AMOUNT = 30
private const val V_BUBBLE_MIN = 0.05f
private const val V_BUBBLE_MAX = 0.1f

class MenuScreen(game: Game) : Base2DScreen(game), TouchListener {

    private val backgroundTextures = getBgTextures()
    private val background = Background(*backgroundTextures.toTextureRegion(), vX = 0f, vY = 0f)

    private val menuAtlas = TextureAtlas(PATH_MENU_ATLAS)

    private val startButton = OnTouchScalingButton(menuAtlas.findRegion("button_start_game"), touchListener = this, scale = BUTTON_SCALE)

    private val bubbles: Array<Bubble> = Array(BUBBLES_AMOUNT) {
        Bubble(menuAtlas.findRegion("bubble(${evalRandomFloat(1f, 4f).toInt()})"), vY = evalRandomFloat(V_BUBBLE_MIN, V_BUBBLE_MAX), reactionAxis = Axis.X)
    }

    //region ScreenEvents

    override fun show() {
        super.show()
        startButton.setWidthProportion(BUTTON_WIDTH)
        Audio.setMusic(PATH_MAIN_MUSIC)
        Audio.musicVolume = 0.2f
        Audio.play()
    }

    override fun resize(worldBounds: Rect) {
        background.resize(worldBounds)

        startButton.left = worldBounds.left + worldBounds.width * 0.02f
        startButton.bottom = worldBounds.bottom + worldBounds.height * 0.2f
        bubbles.forEach { it.resize(worldBounds) }
    }

    override fun render(delta: Float) {
        batch.begin()

        update(delta)
        draw()

        batch.end()
    }

    private fun update(delta: Float) {
        background.update(delta)
        bubbles.forEach { it.update(delta) }
    }

    private fun draw() {
        background.draw(batch)
        bubbles.forEach { it.draw(batch) }
        startButton.draw(batch)
    }

    override fun hide() {
        dispose()
    }

    override fun dispose() {
        backgroundTextures.forEach { it.dispose() }
        menuAtlas.dispose()
        Audio.dispose()

        super.dispose()
    }
    //endregion

    override fun actionPerformed(src: Any) {
        if (src == startButton) {
            game.screen = GameScreen(game)
        } else {
            throw IllegalArgumentException(""""Unknown source of fun "actionPerformed" in menuScreen""")
        }
    }

    //region UserActions

    override fun touchDown(touch: Vector2, pointer: Int) {
        startButton.touchDown(touch, pointer)
        background.touchDown(touch, pointer)
        bubbles.forEach { it.touchDown(touch, pointer) }
    }

    override fun touchMove(touch: Vector2, pointer: Int) {
        background.touchMove(touch, pointer)
        bubbles.forEach { it.touchMove(touch, pointer) }
    }

    override fun touchUp(touch: Vector2, pointer: Int) {
        startButton.touchUp(touch, pointer)
        background.touchUp(touch, pointer)
        bubbles.forEach { it.touchUp(touch, pointer) }
    }
    //endregion

}