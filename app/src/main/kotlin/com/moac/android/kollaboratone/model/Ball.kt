package com.moac.android.kollaboratone.model

data class Ball(val id: String,
                val octaveNote: OctaveNote,
                val color: Long,
                val radius: Float,
                val xpos: Float,
                val ypos: Float,
                val velocity: Velocity,
                val isTouched: Boolean = false) {

    fun copy(velocity: Velocity = this.velocity) = Ball(id, octaveNote, color, radius, xpos, ypos, velocity, isTouched)

    fun copy(xpos: Float, ypos: Float) = Ball(id, octaveNote, color, radius, xpos, ypos, velocity, isTouched)

}
