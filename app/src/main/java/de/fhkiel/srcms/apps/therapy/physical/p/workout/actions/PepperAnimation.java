package de.fhkiel.srcms.apps.therapy.physical.p.workout.actions;

import android.util.Log;

import com.aldebaran.qi.sdk.QiContext;
import com.aldebaran.qi.sdk.builder.AnimateBuilder;
import com.aldebaran.qi.sdk.builder.AnimationBuilder;
import com.aldebaran.qi.sdk.object.actuation.Animate;
import com.aldebaran.qi.sdk.object.actuation.Animation;

import java.util.concurrent.ExecutionException;

import de.fhkiel.srcms.apps.therapy.physical.p.workout.DataLog;
import de.fhkiel.srcms.apps.therapy.physical.p.workout.HttpClient;
import de.fhkiel.srcms.apps.therapy.physical.p.workout.R;
import de.fhkiel.srcms.apps.therapy.physical.p.workout.Welcome;

public class PepperAnimation extends PepperAction {

    private static String TAG = PepperAnimation.class.getName();
    public Animate animate;
    public DataLog dataLog = new DataLog();

    public static void doApplePicking (QiContext qiContext) throws ExecutionException, InterruptedException {

        Animation myApplePicking = AnimationBuilder.with(qiContext)
                .withResources(R.raw.apple_picking)
                .build();

        Animate animate = AnimateBuilder.with(qiContext)
                .withAnimation(myApplePicking)
                .build();

        moveFuture(animate.async().run());

        animate.addOnLabelReachedListener((label, time) -> {
            new DataLog().animation(label,time);
        });
    }

    public static void doArmRotation (QiContext qiContext) throws ExecutionException, InterruptedException {

        Animation myRotation = AnimationBuilder.with(qiContext)
                .withResources(R.raw.arm_rotation)
                .build();

        Animate animate = AnimateBuilder.with(qiContext)
                .withAnimation(myRotation)
                .build();

        moveFuture(animate.async().run());

        animate.addOnLabelReachedListener((label, time) -> {
            new DataLog().animation(label,time);
        });
    }

    public static void doArmSide(QiContext qiContext) throws ExecutionException, InterruptedException {
        Animation myArmside = AnimationBuilder.with(qiContext)
                .withResources(R.raw.arm_side)
                .build();

        Animate animate = AnimateBuilder.with(qiContext)
                .withAnimation(myArmside)
                .build();

        moveFuture(animate.async().run());

        animate.addOnLabelReachedListener((label, time) -> {
            new DataLog().animation(label,time);
        });
    }

    public static void doBoxing(QiContext qiContext) throws ExecutionException, InterruptedException {

        Animation myAirBoxing = AnimationBuilder.with(qiContext)
                .withResources(R.raw.airboxing)
                .build();

        Animate animate = AnimateBuilder.with(qiContext)
                .withAnimation(myAirBoxing)
                .build();

        moveFuture(animate.async().run());

        animate.addOnLabelReachedListener((label, time) -> {
            new DataLog().animation(label,time);
        });
    }

    public static void doBreathbreak(QiContext qiContext) throws ExecutionException, InterruptedException {

        Animation myBreakBreath = AnimationBuilder.with(qiContext)
                .withResources(R.raw.breath_break)
                .build();

        Animate animate = AnimateBuilder.with(qiContext)
                .withAnimation(myBreakBreath)
                .build();

        moveFuture(animate.async().run());

        animate.addOnLabelReachedListener((label, time) -> {
            new DataLog().animation(label,time);
        });
    }

    public static void doClapping (QiContext qiContext) throws ExecutionException, InterruptedException {

        Animation myClapping = AnimationBuilder.with(qiContext)
                .withResources(R.raw.clapping)
                .build();

        Animate animate = AnimateBuilder.with(qiContext)
                .withAnimation(myClapping)
                .build();

        moveFuture(animate.async().run());

        animate.addOnLabelReachedListener((label, time) -> {
            new DataLog().animation(label,time);
        });
    }

    public static void doElbowHand (QiContext qiContext) throws ExecutionException, InterruptedException {

        Animation myCombination = AnimationBuilder.with(qiContext)
                .withResources(R.raw.combine_elbow_hand)
                .build();

        Animate animate = AnimateBuilder.with(qiContext)
                .withAnimation(myCombination)
                .build();

        moveFuture(animate.async().run());

        animate.addOnLabelReachedListener((label, time) -> {
            new DataLog().animation(label,time);
        });
    }

    public static void doElbowStretch (QiContext qiContext) throws ExecutionException, InterruptedException {

        Animation myElbowstretch = AnimationBuilder.with(qiContext)
                .withResources(R.raw.elbowstretch)
                .build();

        Animate animate = AnimateBuilder.with(qiContext)
                .withAnimation(myElbowstretch)
                .build();

        moveFuture(animate.async().run());

        animate.addOnLabelReachedListener((label, time) -> {
            new DataLog().animation(label,time);
        });
    }

    public static void doFollowArm (QiContext qiContext) throws ExecutionException, InterruptedException {

        Animation myFollowArm = AnimationBuilder.with(qiContext)
                .withResources(R.raw.follow_arm)
                .build();

        Animate animate = AnimateBuilder.with(qiContext)
                .withAnimation(myFollowArm)
                .build();

        moveFuture(animate.async().run());

        animate.addOnLabelReachedListener((label, time) -> {
            new DataLog().animation(label,time);
        });
    }

    public static void doLiftArm (QiContext qiContext) throws ExecutionException, InterruptedException {

        Animation myLiftArms = AnimationBuilder.with(qiContext)
                .withResources(R.raw.lift_arms)
                .build();

        Animate animate = AnimateBuilder.with(qiContext)
                .withAnimation(myLiftArms)
                .build();

        moveFuture(animate.async().run());

        animate.addOnLabelReachedListener((label, time) -> {
            new DataLog().animation(label,time);
        });
    }

    public void doOpenHand (QiContext qiContext) throws ExecutionException, InterruptedException {
        Animation myOpenhand = AnimationBuilder.with(qiContext)
                .withResources(R.raw.openhand)
                .build();

        animate = AnimateBuilder.with(qiContext)
                .withAnimation(myOpenhand)
                .build();

        moveFuture(animate.async().run());

        animate.addOnLabelReachedListener((label, time) -> {
            dataLog.animation(label, time);
        });

    }

    public static void doBendBody(QiContext qiContext) throws ExecutionException, InterruptedException {

        Animation myBodyBend = AnimationBuilder.with(qiContext)
                .withResources(R.raw.bend_body)
                .build();

        Animate animate = AnimateBuilder.with(qiContext)
                .withAnimation(myBodyBend)
                .build();

        moveFuture(animate.async().run());

        animate.addOnLabelReachedListener((label, time) -> {
            new DataLog().animation(label,time);
        });
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
