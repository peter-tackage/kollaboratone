package com.moac.android.kollaboratone.player

import com.moac.android.kollaboratone.model.OctaveNote

interface NotePlayer {

    fun isReady(): Boolean = false

    fun play(note: OctaveNote) = {}
}