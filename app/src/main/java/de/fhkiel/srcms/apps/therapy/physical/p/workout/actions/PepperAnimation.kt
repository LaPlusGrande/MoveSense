package de.fhkiel.srcms.apps.therapy.physical.p.workout.actions

import android.content.ContentValues.TAG
import android.util.Log
import com.aldebaran.qi.sdk.QiContext
import com.aldebaran.qi.sdk.`object`.actuation.Animate
import com.aldebaran.qi.sdk.`object`.actuation.Animation
import com.aldebaran.qi.sdk.builder.AnimateBuilder
import com.aldebaran.qi.sdk.builder.AnimationBuilder
import de.fhkiel.srcms.apps.therapy.physical.p.workout.R

class PepperAnimation : PepperAction() {

    private val TAG = PepperAnimation::class.java.name

    fun doAirBoxing(qiContext: QiContext){
        val myAirboxing: Animation = AnimationBuilder.with(qiContext)
            .withResources(R.raw.airboxing)
            .build()

        val animate: Animate = AnimateBuilder.with(qiContext)
            .withAnimation(myAirboxing)
            .build()

        moveFuture(animate.async().run())

        animate.addOnLabelReachedListener(Animate.OnLabelReachedListener { label: String, time: Long ->
            Log.i(TAG, "label:$label")
            Log.i(TAG, "time:$time")
        })
    }

    fun doApplePicking(qiContext: QiContext){
        val myApplePicking: Animation = AnimationBuilder.with(qiContext)
            .withResources(R.raw.apple_picking)
            .build()

        val animate: Animate = AnimateBuilder.with(qiContext)
            .withAnimation(myApplePicking)
            .build()

        moveFuture(animate.async().run())

        animate.addOnLabelReachedListener(Animate.OnLabelReachedListener { label: String, time: Long ->
            Log.i(TAG, "label:$label")
            Log.i(TAG, "time:$time")
        })
    }

    fun doArmRotation(qiContext: QiContext){
        val myArmRotation: Animation = AnimationBuilder.with(qiContext)
            .withResources(R.raw.arm_rotation)
            .build()

        val animate: Animate = AnimateBuilder.with(qiContext)
            .withAnimation(myArmRotation)
            .build()

        moveFuture(animate.async().run())

        animate.addOnLabelReachedListener(Animate.OnLabelReachedListener { label: String, time: Long ->
            Log.i(TAG, "label:$label")
            Log.i(TAG, "time:$time")
        })
    }
    fun doArmSide (qiContext: QiContext){
        val myArmSide: Animation = AnimationBuilder.with(qiContext)
            .withResources(R.raw.arm_side)
            .build()

        val animate: Animate = AnimateBuilder.with(qiContext)
            .withAnimation(myArmSide)
            .build()

        moveFuture(animate.async().run())

        animate.addOnLabelReachedListener(Animate.OnLabelReachedListener { label: String, time: Long ->
            Log.i(TAG, "label:$label")
            Log.i(TAG, "time:$time")
        })
    }
    fun doBreathBreak(qiContext: QiContext){
        val myBreathBreak: Animation = AnimationBuilder.with(qiContext)
            .withResources(R.raw.breath_break)
            .build()

        val animate: Animate = AnimateBuilder.with(qiContext)
            .withAnimation(myBreathBreak)
            .build()

        moveFuture(animate.async().run())

        animate.addOnLabelReachedListener(Animate.OnLabelReachedListener { label: String, time: Long ->
            Log.i(TAG, "label:$label")
            Log.i(TAG, "time:$time")
        })
    }
    fun doClapping(qiContext: QiContext){
        val myClapping: Animation = AnimationBuilder.with(qiContext)
            .withResources(R.raw.clapping)
            .build()

        val animate: Animate = AnimateBuilder.with(qiContext)
            .withAnimation(myClapping)
            .build()

        moveFuture(animate.async().run())

        animate.addOnLabelReachedListener(Animate.OnLabelReachedListener { label: String, time: Long ->
            Log.i(TAG, "label:$label")
            Log.i(TAG, "time:$time")
        })
    }
    fun doElbowHand(qiContext: QiContext){
        val myElbowHand: Animation = AnimationBuilder.with(qiContext)
            .withResources(R.raw.combine_elbow_hand)
            .build()

        val animate: Animate = AnimateBuilder.with(qiContext)
            .withAnimation(myElbowHand)
            .build()

        moveFuture(animate.async().run())

        animate.addOnLabelReachedListener(Animate.OnLabelReachedListener { label: String, time: Long ->
            Log.i(TAG, "label:$label")
            Log.i(TAG, "time:$time")
        })
    }
    fun doElbowStretch(qiContext: QiContext){
        val myElbowStretch: Animation = AnimationBuilder.with(qiContext)
            .withResources(R.raw.elbowstretch)
            .build()

        val animate: Animate = AnimateBuilder.with(qiContext)
            .withAnimation(myElbowStretch)
            .build()

        moveFuture(animate.async().run())

        animate.addOnLabelReachedListener(Animate.OnLabelReachedListener { label: String, time: Long ->
            Log.i(TAG, "label:$label")
            Log.i(TAG, "time:$time")
        })
    }
    fun doFollowArms(qiContext: QiContext){
        val myFollowArms: Animation = AnimationBuilder.with(qiContext)
            .withResources(R.raw.follow_arm)
            .build()

        val animate: Animate = AnimateBuilder.with(qiContext)
            .withAnimation(myFollowArms)
            .build()

        moveFuture(animate.async().run())

        animate.addOnLabelReachedListener(Animate.OnLabelReachedListener { label: String, time: Long ->
            Log.i(TAG, "label:$label")
            Log.i(TAG, "time:$time")
        })
    }
    fun doLiftArms(qiContext: QiContext){
        val myLiftArms: Animation = AnimationBuilder.with(qiContext)
            .withResources(R.raw.lift_arms)
            .build()

        val animate: Animate = AnimateBuilder.with(qiContext)
            .withAnimation(myLiftArms)
            .build()

        moveFuture(animate.async().run())

        animate.addOnLabelReachedListener(Animate.OnLabelReachedListener { label: String, time: Long ->
            Log.i(TAG, "label:$label")
            Log.i(TAG, "time:$time")
        })
    }
    fun doOpenHand(qiContext: QiContext){
        val myOpenHand: Animation = AnimationBuilder.with(qiContext)
            .withResources(R.raw.openhand)
            .build()

        val animate: Animate = AnimateBuilder.with(qiContext)
            .withAnimation(myOpenHand)
            .build()

        moveFuture(animate.async().run())

        animate.addOnLabelReachedListener(Animate.OnLabelReachedListener { label: String, time: Long ->
            Log.i(TAG, "label:$label")
            Log.i(TAG, "time:$time")
        })
    }
    fun doBendBody(qiContext: QiContext){
        val myBendBody: Animation = AnimationBuilder.with(qiContext)
            .withResources(R.raw.bend_body)
            .build()

        val animate: Animate = AnimateBuilder.with(qiContext)
            .withAnimation(myBendBody)
            .build()

        moveFuture(animate.async().run())

        animate.addOnLabelReachedListener(Animate.OnLabelReachedListener { label: String, time: Long ->
            Log.i(TAG, "label:$label")
            Log.i(TAG, "time:$time")
        })
    }
    fun doRotation(qiContext: QiContext){
        val myRotation: Animation = AnimationBuilder.with(qiContext)
            .withResources(R.raw.rotation)
            .build()

        moveFuture(AnimateBuilder.with(qiContext)
            .withAnimation(myRotation)
            .build()
            .async().run())
    }
}


