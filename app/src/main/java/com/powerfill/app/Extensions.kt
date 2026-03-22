package com.powerfill.app
import android.animation.ValueAnimator
import android.widget.TextView

fun TextView.animateNumber(from: Double, to: Double, suffix: String) {
    val anim = ValueAnimator.ofFloat(from.toFloat(), to.toFloat())
    anim.duration = 500
    anim.addUpdateListener {
        val v = it.animatedValue as Float
        text = String.format("%.2f%s", v, suffix)
    }
    anim.start()
}
