package com.moac.android.kollaboratone.instrument

import com.moac.android.kollaboratone.model.Arena
import com.moac.android.kollaboratone.model.Ball
import com.moac.android.kollaboratone.player.NotePlayer

class Instrument(private val arena: Arena,
                 private val notePlayer: NotePlayer) {

    fun update() {
        arena.update()
    }

    fun holdShape(ball: Ball) {
        arena.addShape(ball.copy(true))
    }

    fun moveShape(ball: Ball, xpos: Float, ypos: Float) {
        arena.addShape(ball.copy(xpos, ypos))
    }

    fun releaseShape(ball: Ball) {
        arena.addShape(ball.copy(false))
    }

    fun shapes(): Collection<Ball> = arena.getShapes();

    // This thing should listen to the Arena to determine overlaps and notify the NotePlayer
}