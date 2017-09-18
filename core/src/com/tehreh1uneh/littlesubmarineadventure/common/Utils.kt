package com.tehreh1uneh.littlesubmarineadventure.common

import com.badlogic.gdx.graphics.Texture

internal const val USE_MIPMAP = true
internal const val PATH_BG = "textures/bg.png"


internal fun Texture.setOptimalFilter(){
    setFilter(Texture.TextureFilter.MipMapLinearNearest, Texture.TextureFilter.Linear)
}