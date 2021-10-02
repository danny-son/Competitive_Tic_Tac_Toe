package com.example.comptictactoe.Model.Animation

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.widget.ImageView

class AnimationController {
    private val animationDuration: Long = 500L

    fun animatePlace(imageView: ImageView) {
        val scaleXAnimator: ObjectAnimator = ObjectAnimator.ofFloat(
            imageView,
            "scaleX",
            0.0f, 1.0f)
        val scaleYAnimator: ObjectAnimator = ObjectAnimator.ofFloat(
            imageView,
        "scaleY",
        0.0f, 1.0f)
        val rotationAnimator: ObjectAnimator = ObjectAnimator.ofFloat(
            imageView,
            "rotation",
        0.0f,
        360.0f)
        scaleXAnimator.duration = animationDuration
        scaleYAnimator.duration = animationDuration
        rotationAnimator.duration = animationDuration
        with(AnimatorSet()) {
            playTogether(scaleXAnimator,scaleYAnimator,rotationAnimator)
            duration = animationDuration
            start()
        }
    }
}