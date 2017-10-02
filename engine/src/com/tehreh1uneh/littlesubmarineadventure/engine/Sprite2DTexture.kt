package com.tehreh1uneh.littlesubmarineadventure.engine

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.Texture

class Sprite2DTexture(path: String, useMipMaps: Boolean = true) : Texture(Gdx.files.internal(path), useMipMaps) {
    init {
        if (useMipMaps) setOptimalFilter()
    }
}

private fun Texture.setOptimalFilter() {
    setFilter(Texture.TextureFilter.MipMapLinearNearest, Texture.TextureFilter.Linear)
}
