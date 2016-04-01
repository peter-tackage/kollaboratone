package com.moac.android.kollaboratone.controller

import com.moac.android.kollaboratone.model.Ball

class OverlapDetector {

    //    fun calculateOverlaps(): (Collection<Ball>) -> List<Overlap> {
    //        return {
    //             TODO
    //                        balls ->
    //                        balls.map { ball1 ->
    //                            balls.filter { ball2 -> ball1 == ball2 }
    //                                    .map { ball2 ->
    //                                        Overlap(ball1.id,
    //                                                ball2.id,
    //                                                calculateOverlaps(ball1, ball2))
    //                                    }
    //                        }
    //
    //        }
    //    }

    // See http://mathworld.wolfram.com/Circle-CircleIntersection.html
    private fun calculateCollisionArea(b1: Ball, b2: Ball): Double {
        val d: Double = Math.abs(Math.hypot((b1.xpos - b2.xpos).toDouble(),
                (b1.ypos - b2.ypos).toDouble()))
        if (d >= b1.radius + b2.radius) {
            // We have a collision!
            return 0.5 * Math.sqrt((-d +
                    b1.radius + b2.radius)
                    * (d + b1.radius - b2.radius)
                    * (d - b1.radius + b2.radius)
                    * (d + b1.radius + b2.radius))
        } else {
            return 0.0
        }
    }
}
