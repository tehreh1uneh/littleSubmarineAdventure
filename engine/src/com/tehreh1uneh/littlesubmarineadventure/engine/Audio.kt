package com.tehreh1uneh.littlesubmarineadventure.engine

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.audio.Music
import com.tehreh1uneh.littlesubmarineadventure.engine.utils.playMusic
import com.tehreh1uneh.littlesubmarineadventure.engine.utils.playSounds

object Audio {

    private lateinit var music: Music

    var musicVolume: Float
        set(value) {
            music.volume = value
        }
        get() = music.volume

    fun setMusic(path: String, looping: Boolean = true) {
        music = Gdx.audio.newMusic(Gdx.files.internal(path))
        music.isLooping = looping
    }

    fun turnAudio(musicOn: Boolean = false, soundsOn: Boolean = false) {
        playMusic = musicOn
        playSounds = soundsOn

        play()
    }

    fun play() {
        if (playMusic) {
            music.play()
        } else if (music.isPlaying) {
            music.stop()
        }
    }

    fun dispose() {
        music.dispose()
    }

}