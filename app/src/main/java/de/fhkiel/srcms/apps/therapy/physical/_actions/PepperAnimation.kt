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

        moveFuture(AnimateBuilder.with(qiContext)
            .withAnimation(myArmSide)
            .build()
            .async().run())
    }
    fun doBreathBreak(qiContext: QiContext){
        val myBreathBreak: Animation = AnimationBuilder.with(qiContext)
            .withResources(R.raw.breath_break)
            .build()

        moveFuture(AnimateBuilder.with(qiContext)
            .withAnimation(myBreathBreak)
            .build()
            .async().run())
    }
    fun doClapping(qiContext: QiContext){
        val myClapping: Animation = AnimationBuilder.with(qiContext)
            .withResources(R.raw.clapping)
            .build()

        moveFuture(AnimateBuilder.with(qiContext)
            .withAnimation(myClapping)
            .build()
            .async().run())
    }
    fun doElbowHand(qiContext: QiContext){
        val myElbowHand: Animation = AnimationBuilder.with(qiContext)
            .withResources(R.raw.combine_elbow_hand)
            .build()

        moveFuture(AnimateBuilder.with(qiContext)
            .withAnimation(myElbowHand)
            .build()
            .async().run())
    }
    fun doElbowStretch(qiContext: QiContext){
        val myElbowStretch: Animation = AnimationBuilder.with(qiContext)
            .withResources(R.raw.elbowstretch)
            .build()

        moveFuture(AnimateBuilder.with(qiContext)
            .withAnimation(myElbowStretch)
            .build()
            .async().run())
    }
    fun doFollowArms(qiContext: QiContext){
        val myFollowArms: Animation = AnimationBuilder.with(qiContext)
            .withResources(R.raw.follow_arm)
            .build()

        moveFuture(AnimateBuilder.with(qiContext)
            .withAnimation(myFollowArms)
            .build()
            .async().run())
    }
    fun doLiftArms(qiContext: QiContext){
        val myLiftArms: Animation = AnimationBuilder.with(qiContext)
            .withResources(R.raw.lift_arms)
            .build()

        moveFuture(AnimateBuilder.with(qiContext)
            .withAnimation(myLiftArms)
            .build()
            .async().run())
    }
    fun doOpenHand(qiContext: QiContext){
        val myOpenHand: Animation = AnimationBuilder.with(qiContext)
            .withResources(R.raw.openhand)
            .build()

        moveFuture(AnimateBuilder.with(qiContext)
            .withAnimation(myOpenHand)
            .build()
            .async().run())
    }
    fun doTendBody(qiContext: QiContext){
        val myTendBody: Animation = AnimationBuilder.with(qiContext)
            .withResources(R.raw.tend_body)
            .build()

        moveFuture(AnimateBuilder.with(qiContext)
            .withAnimation(myTendBody)
            .build()
            .async().run())
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


