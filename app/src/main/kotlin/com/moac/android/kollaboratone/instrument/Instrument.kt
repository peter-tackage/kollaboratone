package com.moac.android.kollaboratone.instrument

import com.moac.android.kollaboratone.controller.NoteGenerator
import com.moac.android.kollaboratone.model.Arena
import com.moac.android.kollaboratone.model.Ball
import com.moac.android.kollaboratone.player.NotePlayer

class Instrument(private val arena: Arena,
                 private val overlapDetector: NoteGenerator,
                 private val notePlayer: NotePlayer) {

    fun update() {
        arena.update()
        overlapDetector.handleOverlaps(arena.getShapes(), {
            if (it.isEmpty()) {
                notePlayer.silence()
            } else {
                it.forEach { notePair -> notePlayer.playNotes(notePair.first, notePair.second) }
            }
        })
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