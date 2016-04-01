package com.moac.android.kollaboratone.model

data class Velocity(val xSpeed: Int,
                    val ySpeed: Int,
                    val xDirection: Direction,
                    val yDirection: Direction) {

    fun copy(xDirection: Direction, yDirection: Direction) = Velocity(xSpeed, ySpeed, xDirection, yDirection)

    companion object {
        val ZERO_VELOCITY: Velocity = Velocity(0, 0, Direction.NONE, Direction.NONE);
    }
}