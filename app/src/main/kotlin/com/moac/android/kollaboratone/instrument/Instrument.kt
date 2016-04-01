package com.moac.android.kollaboratone.instrument

import com.moac.android.kollaboratone.model.Arena
import com.moac.android.kollaboratone.player.NotePlayer

class Instrument(val arena: Arena, val notePlayer: NotePlayer) {

    fun update() {
        arena.update()
    }
}