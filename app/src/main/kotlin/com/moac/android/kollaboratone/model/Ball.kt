package com.moac.android.kollaboratone.model

data class Ball(val id: String,
                val octaveNote: OctaveNote,
                val color: Long,
                val radius: Float,
                val xpos: Float,
                val ypos: Float,
                val velocity: Velocity,
                val isHeld: Boolean = false) {

    fun copy(velocity: Velocity = this.velocity) = Ball(id, octaveNote, color, radius, xpos, ypos, velocity, isHeld)

    fun copy(xpos: Float, ypos: Float) = Ball(id, octaveNote, color, radius, xpos, ypos, velocity, isHeld)

    fun copy(isHeld: Boolean) = Ball(id, octaveNote, color, radius, xpos, ypos, velocity, isHeld)

}
