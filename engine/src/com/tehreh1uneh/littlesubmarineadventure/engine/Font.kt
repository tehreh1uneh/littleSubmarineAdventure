package com.tehreh1uneh.littlesubmarineadventure.engine

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.BitmapFont


class Font(fontDescriptionFilePath: String, fontImageFilePath: String) :
        BitmapFont(Gdx.files.internal(fontDescriptionFilePath),
                Gdx.files.internal(fontImageFilePath),
                false,
                false) {
    init {
        val primaryFilter = Texture.TextureFilter.Linear
        region.texture.setFilter(primaryFilter, primaryFilter)
    }

    var heightScale: Float
        get() = data.scaleY
        set(value) {
            data.setScale(value / capHeight)
        }
}