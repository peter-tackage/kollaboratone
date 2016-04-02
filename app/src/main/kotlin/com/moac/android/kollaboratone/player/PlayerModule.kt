package com.moac.android.kollaboratone.player

import android.content.Context

class PlayerModule(val context: Context) {

    fun createPlayer() = PdNotePlayer(context.applicationContext)
}