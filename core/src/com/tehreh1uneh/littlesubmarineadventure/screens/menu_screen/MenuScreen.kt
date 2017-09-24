package com.tehreh1uneh.littlesubmarineadventure.screens.menu_screen

import com.badlogic.gdx.Game
import com.badlogic.gdx.Gdx
import com.badlogic.gdx.audio.Music
import com.badlogic.gdx.graphics.g2d.TextureAtlas
import com.badlogic.gdx.graphics.g2d.TextureRegion
import com.badlogic.gdx.math.Vector2
import com.tehreh1uneh.littlesubmarineadventure.common.*
import com.tehreh1uneh.littlesubmarineadventure.engine.Base2DScreen
import com.tehreh1uneh.littlesubmarineadventure.engine.Sprite2DTexture
import com.tehreh1uneh.littlesubmarineadventure.engine.sprites.Axis
import com.tehreh1uneh.littlesubmarineadventure.engine.sprites.OnTouchScalingButton
import com.tehreh1uneh.littlesubmarineadventure.engine.sprites.Rect
import com.tehreh1uneh.littlesubmarineadventure.engine.sprites.Sprite
import com.tehreh1uneh.littlesubmarineadventure.engine.ui.TouchListener
import com.tehreh1uneh.littlesubmarineadventure.screens.game_screen.GameScreen

private const val BUTTON_WIDTH = 0.1f
private const val BUTTON_SCALE = 0.9f
private const val BUBBLES_AMOUNT = 30
private const val V_BUBBLE_MIN = 0.05f
private const val V_BUBBLE_MAX = 0.1f


class MenuScreen(game: Game) : Base2DScreen(game), TouchListener {

    private val textureBg = Sprite2DTexture(PATH_BG)
    private val background = Sprite(TextureRegion(textureBg))

    private val textureSeabed = Sprite2DTexture(PATH_SEABED)
    private val seabed = Sprite(TextureRegion(textureSeabed))

    private val menuAtlas = TextureAtlas(PATH_MENU_ATLAS)

    private val startButton = OnTouchScalingButton(menuAtlas.findRegion("button_start_game"), touchListener = this, scale = BUTTON_SCALE)

    private val bubbles: Array<Bubble> = Array(BUBBLES_AMOUNT) {
        Bubble(menuAtlas.findRegion("bubble(${evalRandomFloat(1f, 4f).toInt()})"), vBoth = evalRandomFloat(V_BUBBLE_MIN, V_BUBBLE_MAX), axis = Axis.X, moveDirection = Axis.Y)
    }

    private lateinit var mainMusic: Music

    //region ScreenEvents

    override fun show() {
        super.show()
        background.setWidthProportion()
        seabed.setWidthProportion()
        startButton.setWidthProportion(BUTTON_WIDTH)
        if (playMusic) {
            mainMusic = Gdx.audio.newMusic(Gdx.files.internal(PATH_MAIN_MUSIC))
            mainMusic.isLooping = true
            mainMusic.volume = 0.2f
            mainMusic.play()
        }
    }

    override fun resize(worldBounds: Rect) {
        seabed.bottom = worldBounds.bottom
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
        bubbles.forEach { it.update(delta) }
    }

    private fun draw() {
        background.draw(batch)
        seabed.draw(batch)
        bubbles.forEach { it.draw(batch) }
        startButton.draw(batch)
    }

    override fun pause() {
        super.pause()
    }

    override fun resume() {
        super.resume()
    }

    override fun hide() {
        dispose()
    }

    override fun dispose() {
        textureBg.dispose()
        textureSeabed.dispose()
        menuAtlas.dispose()
        mainMusic.dispose()

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
        bubbles.forEach { it.touchDown(touch, pointer) }
    }

    override fun touchMove(touch: Vector2, pointer: Int) {
        bubbles.forEach { it.touchMove(touch, pointer) }
    }

    override fun touchUp(touch: Vector2, pointer: Int) {
        startButton.touchUp(touch, pointer)
        bubbles.forEach { it.touchUp(touch, pointer) }
    }
    //endregion

}