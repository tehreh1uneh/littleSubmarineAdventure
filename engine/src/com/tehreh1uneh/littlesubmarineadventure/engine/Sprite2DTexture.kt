package com.tehreh1uneh.littlesubmarineadventure.engine

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.Texture
import com.tehreh1uneh.littlesubmarineadventure.engine.utils.USE_MIPMAPS
import com.tehreh1uneh.littlesubmarineadventure.engine.utils.setOptimalFilter

class Sprite2DTexture(path: String, useMipMaps: Boolean = USE_MIPMAPS) : Texture(Gdx.files.internal(path), useMipMaps) {
    init {
        if (useMipMaps) setOptimalFilter()
    }
}