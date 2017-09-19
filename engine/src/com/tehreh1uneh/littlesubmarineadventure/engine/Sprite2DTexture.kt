package com.tehreh1uneh.littlesubmarineadventure.engine

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.Texture

private const val USE_MIPMAPS = true

private fun Texture.setOptimalFilter(){
    setFilter(Texture.TextureFilter.MipMapLinearNearest, Texture.TextureFilter.Linear)
}

class Sprite2DTexture(path: String, useMipMaps: Boolean = USE_MIPMAPS) : Texture(Gdx.files.internal(path), useMipMaps) {
    init {
        if (useMipMaps) setOptimalFilter()
    }
}

