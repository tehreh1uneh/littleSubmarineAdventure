package com.tehreh1uneh.littlesubmarineadventure.engine

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.audio.Music
import com.tehreh1uneh.littlesubmarineadventure.engine.utils.PATH_MAIN_MUSIC
import com.tehreh1uneh.littlesubmarineadventure.engine.utils.playMusic
import com.tehreh1uneh.littlesubmarineadventure.engine.utils.playSounds

object Audio {

    private var music: Music = Gdx.audio.newMusic(Gdx.files.internal(PATH_MAIN_MUSIC))

    var musicVolume: Float
        set(value) {
            music.volume = value
        }
        get() = music.volume


    init {
        music.isLooping = true
        musicVolume = 1f
    }

    fun turnAudio(musicOn: Boolean = false, soundsOn: Boolean = false) {
        playMusic = musicOn
        playSounds = soundsOn
    }

    fun play() {
        if (playMusic) {
            music.play()
        } else {
            music.stop()
        }
    }

    fun dispose() {
        music.dispose()
    }

}