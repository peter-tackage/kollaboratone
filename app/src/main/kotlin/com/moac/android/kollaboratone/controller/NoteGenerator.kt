package com.moac.android.kollaboratone.controller

import com.moac.android.kollaboratone.model.Ball
import com.moac.android.kollaboratone.model.OctaveNote
import com.moac.android.kollaboratone.model.Overlap

class NoteGenerator {

    fun handleOverlaps(balls: Collection<Ball>, f: (Collection<Pair<OctaveNote, OctaveNote>>) -> Unit) {
        f.invoke(calculateOverlaps(balls).map { thing -> Pair(thing.note1, thing.note2) })
    }

    private fun calculateOverlaps(balls: Collection<Ball>): List<Overlap> {
        val overlaps: MutableList<Overlap> = mutableListOf()

        for (ball1 in balls) {
            for (ball2 in balls) {
                if (ball1 == ball2) continue
                val overlapArea = calculateCollisionArea(ball1, ball2)
                if (overlapArea) {
                    overlaps.add(Overlap(ball1.octaveNote, ball2.octaveNote, 10.toDouble()))
                }
            }
        }
        return overlaps
    }

    // See http://mathworld.wolfram.com/Circle-CircleIntersection.html
    private fun calculateCollisionArea(b1: Ball, b2: Ball): Boolean {
        val d: Double = Math.abs(Math.hypot((b1.xpos - b2.xpos).toDouble(),
                (b1.ypos - b2.ypos).toDouble()))
        return (d < b1.radius + b2.radius)
        //            // We have a collision!
        //            return 0.5 * Math.sqrt((-d +
        //                    b1.radius + b2.radius)
        //                    * (d + b1.radius - b2.radius)
        //                    * (d - b1.radius + b2.radius)
        //                    * (d + b1.radius + b2.radius))


    }
}
