package com.tehreh1uneh.littlesubmarineadventure.desktop

import com.badlogic.gdx.backends.lwjgl.LwjglApplication
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration
import com.tehreh1uneh.littlesubmarineadventure.SubmarineGame

fun main(args: Array<String>) {
    val config = LwjglApplicationConfiguration()
    // 16:9
    config.width = 1280 / 2
    config.height = 720 / 2
    LwjglApplication(SubmarineGame(), config)
}
