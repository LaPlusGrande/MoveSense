package de.fhkiel.srcms.apps.therapy.physical._actions

import com.aldebaran.qi.sdk.QiContext
import com.aldebaran.qi.sdk.`object`.actuation.Animate
import com.aldebaran.qi.sdk.`object`.actuation.Animation
import com.aldebaran.qi.sdk.builder.AnimateBuilder
import com.aldebaran.qi.sdk.builder.AnimationBuilder
import de.fhkiel.srcms.apps.therapy.physical.R
import java.util.concurrent.Future

class PepperAnimation : PepperAction() {

    fun doAirBoxing(qiContext: QiContext){
        val myAirboxing: Animation = AnimationBuilder.with(qiContext)
            .withResources(R.raw.airboxing)
            .build()

        moveFuture(AnimateBuilder.with(qiContext)
            .withAnimation(myAirboxing)
            .build()
            .async().run())
    }

    fun doApplePicking(qiContext: QiContext){
        val myApplePicking: Animation = AnimationBuilder.with(qiContext)
            .withResources(R.raw.apple_picking)
            .build()

        moveFuture(AnimateBuilder.with(qiContext)
            .withAnimation(myApplePicking)
            .build()
            .async().run())
    }

    fun doArmRotation(qiContext: QiContext){
        val myArmRotation: Animation = AnimationBuilder.with(qiContext)
            .withResources(R.raw.arm_rotation)
            .build()

        moveFuture(AnimateBuilder.with(qiContext)
            .withAnimation(myArmRotation)
            .build()
            .async().run())
    }
    fun doArmSide (qiContext: QiContext){
        val myArmSide: Animation = AnimationBuilder.with(qiContext)
            .withResources(R.raw.arm_side)
            .build()

        val animate: Animate = AnimateBuilder.with(qiContext)
            .withAnimation(myArmSide)
            .build()
        animate.async().run()
    }
    fun doBreathBreak(qiContext: QiContext){
        val myBreathBreak: Animation = AnimationBuilder.with(qiContext)
            .withResources(R.raw.breath_break)
            .build()

        val animate: Animate = AnimateBuilder.with(qiContext)
            .withAnimation(myBreathBreak)
            .build()
        animate.async().run()
    }
    fun doClapping(qiContext: QiContext){
        val myClapping: Animation = AnimationBuilder.with(qiContext)
            .withResources(R.raw.clapping)
            .build()

        val animate: Animate = AnimateBuilder.with(qiContext)
            .withAnimation(myClapping)
            .build()
        animate.async().run()
    }
    fun doElbowHand(qiContext: QiContext){
        val myElbowHand: Animation = AnimationBuilder.with(qiContext)
            .withResources(R.raw.combine_elbow_hand)
            .build()

        val animate: Animate = AnimateBuilder.with(qiContext)
            .withAnimation(myElbowHand)
            .build()
        animate.async().run()
    }
    fun doElbowStretch(qiContext: QiContext){
        val myElbowStretch: Animation = AnimationBuilder.with(qiContext)
            .withResources(R.raw.elbowstretch)
            .build()

        val animate: Animate = AnimateBuilder.with(qiContext)
            .withAnimation(myElbowStretch)
            .build()
        animate.async().run()
    }
    fun doFollowArms(qiContext: QiContext){
        val myFollowArms: Animation = AnimationBuilder.with(qiContext)
            .withResources(R.raw.follow_arm)
            .build()

        val animate: Animate = AnimateBuilder.with(qiContext)
            .withAnimation(myFollowArms)
            .build()
        animate.async().run()
    }
    fun doLiftArms(qiContext: QiContext){
        val myLiftArms: Animation = AnimationBuilder.with(qiContext)
            .withResources(R.raw.lift_arms)
            .build()

        val animate: Animate = AnimateBuilder.with(qiContext)
            .withAnimation(myLiftArms)
            .build()
        animate.async().run()
    }
    fun doOpenHand(qiContext: QiContext){
        val myOpenHand: Animation = AnimationBuilder.with(qiContext)
            .withResources(R.raw.openhand)
            .build()

        val animate: Animate = AnimateBuilder.with(qiContext)
            .withAnimation(myOpenHand)
            .build()
        animate.async().run()
    }
    fun doTendBody(qiContext: QiContext){
        val myTendBody: Animation = AnimationBuilder.with(qiContext)
            .withResources(R.raw.tend_body)
            .build()

        val animate: Animate = AnimateBuilder.with(qiContext)
            .withAnimation(myTendBody)
            .build()
        animate.async().run()
    }
    fun doRotation(qiContext: QiContext){
        val myRotation: Animation = AnimationBuilder.with(qiContext)
            .withResources(R.raw.rotation)
            .build()

        val animate: Animate = AnimateBuilder.with(qiContext)
            .withAnimation(myRotation)
            .build()
        animate.async().run()
    }
}


