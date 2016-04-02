package com.moac.android.kollaboratone.player

import com.moac.android.kollaboratone.model.OctaveNote

interface NotePlayer {

    fun init()

    fun startAudio()

    fun stopAudio()

    fun dispose()

    fun playNotes(note1: OctaveNote, note2: OctaveNote)

    fun silence()

}