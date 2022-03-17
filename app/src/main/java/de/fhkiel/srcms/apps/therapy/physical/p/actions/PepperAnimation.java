package de.fhkiel.srcms.apps.therapy.physical.p.actions;

import com.aldebaran.qi.sdk.QiContext;
import com.aldebaran.qi.sdk.builder.AnimateBuilder;
import com.aldebaran.qi.sdk.builder.AnimationBuilder;
import com.aldebaran.qi.sdk.object.actuation.Animation;
import de.fhkiel.srcms.apps.therapy.physical.R;

import java.util.concurrent.ExecutionException;

public class PepperAnimation extends PepperAction {

    public static void doApplePicking (QiContext qiContext) throws ExecutionException, InterruptedException {

        Animation myApplePicking = AnimationBuilder.with(qiContext)
                .withResources(R.raw.apple_picking)
                .build();

        moveFuture(AnimateBuilder.with(qiContext)
                .withAnimation(myApplePicking)
                .build()
                .async().run());
    }

    public static void doArmRotation (QiContext qiContext) throws ExecutionException, InterruptedException {

        Animation myRotation = AnimationBuilder.with(qiContext)
                .withResources(R.raw.arm_rotation)
                .build();

        moveFuture(AnimateBuilder.with(qiContext)
                .withAnimation(myRotation)
                .build()
                .async().run());
    }

    public static void doArmSide(QiContext qiContext) throws ExecutionException, InterruptedException {
        Animation myArmside = AnimationBuilder.with(qiContext)
                .withResources(R.raw.arm_side)
                .build();

        moveFuture(AnimateBuilder.with(qiContext)
                .withAnimation(myArmside)
                .build()
                .async().run());
    }

    public static void doBoxing(QiContext qiContext) throws ExecutionException, InterruptedException {

        Animation myAirBoxing = AnimationBuilder.with(qiContext)
                .withResources(R.raw.airboxing)
                .build();

        moveFuture(AnimateBuilder.with(qiContext)
                .withAnimation(myAirBoxing)
                .build()
                .async().run());
    }

    public static void doBreathbreak(QiContext qiContext) throws ExecutionException, InterruptedException {

        Animation myBreakBreath = AnimationBuilder.with(qiContext)
                .withResources(R.raw.breath_break)
                .build();

        moveFuture(AnimateBuilder.with(qiContext)
                .withAnimation(myBreakBreath)
                .build()
                .async().run());
    }

    public static void doClapping (QiContext qiContext) throws ExecutionException, InterruptedException {

        Animation myClapping = AnimationBuilder.with(qiContext)
                .withResources(R.raw.clapping)
                .build();

        moveFuture(AnimateBuilder.with(qiContext)
                .withAnimation(myClapping)
                .build()
                .async().run());
    }

    public static void doElbowHand (QiContext qiContext) throws ExecutionException, InterruptedException {

        Animation myCombination = AnimationBuilder.with(qiContext)
                .withResources(R.raw.combine_elbow_hand)
                .build();

        moveFuture(AnimateBuilder.with(qiContext)
                .withAnimation(myCombination)
                .build()
                .async().run());
    }

    public static void doElbowStretch (QiContext qiContext) throws ExecutionException, InterruptedException {

        Animation myElbowstretch = AnimationBuilder.with(qiContext)
                .withResources(R.raw.elbowstretch)
                .build();

        moveFuture(AnimateBuilder.with(qiContext)
                .withAnimation(myElbowstretch)
                .build()
                .async().run());
    }

    public static void doFollowArm (QiContext qiContext) throws ExecutionException, InterruptedException {

        Animation myFollowArm = AnimationBuilder.with(qiContext)
                .withResources(R.raw.follow_arm)
                .build();

        moveFuture(AnimateBuilder.with(qiContext)
                .withAnimation(myFollowArm)
                .build()
                .async().run());
    }

    public static void doLiftArm (QiContext qiContext) throws ExecutionException, InterruptedException {

        Animation myLiftArms = AnimationBuilder.with(qiContext)
                .withResources(R.raw.lift_arms)
                .build();

        moveFuture(AnimateBuilder.with(qiContext)
                .withAnimation(myLiftArms)
                .build()
                .async().run());

    }

    public static void doOpenHand (QiContext qiContext) throws ExecutionException, InterruptedException {
        Animation myOpenhand = AnimationBuilder.with(qiContext)
                .withResources(R.raw.openhand)
                .build();

        moveFuture(AnimateBuilder.with(qiContext)
                .withAnimation(myOpenhand)
                .build()
                .async().run());
    }

    public static void doTendBody(QiContext qiContext) throws ExecutionException, InterruptedException {

        Animation myBodyTend = AnimationBuilder.with(qiContext)
                .withResources(R.raw.tend_body)
                .build();

        moveFuture(AnimateBuilder.with(qiContext)
                .withAnimation(myBodyTend)
                .build()
                .async().run());
    }

    public static void doRotation(QiContext qiContext) throws ExecutionException, InterruptedException {

        Animation myRotation = AnimationBuilder.with(qiContext)
                .withResources(R.raw.rotation)
                .build();

        moveFuture(AnimateBuilder.with(qiContext)
                .withAnimation(myRotation)
                .build()
                .async().run());
    }

}
