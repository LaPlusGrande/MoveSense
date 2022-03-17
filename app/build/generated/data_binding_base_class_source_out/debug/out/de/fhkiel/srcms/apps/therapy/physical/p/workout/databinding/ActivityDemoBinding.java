// Generated by view binder compiler. Do not edit!
package de.fhkiel.srcms.apps.therapy.physical.p.workout.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import de.fhkiel.srcms.apps.therapy.physical.p.workout.R;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class ActivityDemoBinding implements ViewBinding {
  @NonNull
  private final ConstraintLayout rootView;

  @NonNull
  public final Button backButton2;

  @NonNull
  public final Button demoAirBoxing;

  @NonNull
  public final Button demoApplePicking;

  @NonNull
  public final Button demoArmRotation;

  @NonNull
  public final Button demoArmSide;

  @NonNull
  public final Button demoClappingHands;

  @NonNull
  public final Button demoElbowStretch;

  @NonNull
  public final Button demoEllbowHand;

  @NonNull
  public final Button demoFollowArms;

  @NonNull
  public final Button demoLiftArms;

  @NonNull
  public final Button demoOpenHand;

  @NonNull
  public final Button demoTendBody;

  @NonNull
  public final TextView textView1;

  private ActivityDemoBinding(@NonNull ConstraintLayout rootView, @NonNull Button backButton2,
      @NonNull Button demoAirBoxing, @NonNull Button demoApplePicking,
      @NonNull Button demoArmRotation, @NonNull Button demoArmSide,
      @NonNull Button demoClappingHands, @NonNull Button demoElbowStretch,
      @NonNull Button demoEllbowHand, @NonNull Button demoFollowArms, @NonNull Button demoLiftArms,
      @NonNull Button demoOpenHand, @NonNull Button demoTendBody, @NonNull TextView textView1) {
    this.rootView = rootView;
    this.backButton2 = backButton2;
    this.demoAirBoxing = demoAirBoxing;
    this.demoApplePicking = demoApplePicking;
    this.demoArmRotation = demoArmRotation;
    this.demoArmSide = demoArmSide;
    this.demoClappingHands = demoClappingHands;
    this.demoElbowStretch = demoElbowStretch;
    this.demoEllbowHand = demoEllbowHand;
    this.demoFollowArms = demoFollowArms;
    this.demoLiftArms = demoLiftArms;
    this.demoOpenHand = demoOpenHand;
    this.demoTendBody = demoTendBody;
    this.textView1 = textView1;
  }

  @Override
  @NonNull
  public ConstraintLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static ActivityDemoBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static ActivityDemoBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.activity_demo, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static ActivityDemoBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.back_button2;
      Button backButton2 = ViewBindings.findChildViewById(rootView, id);
      if (backButton2 == null) {
        break missingId;
      }

      id = R.id.demo_airBoxing;
      Button demoAirBoxing = ViewBindings.findChildViewById(rootView, id);
      if (demoAirBoxing == null) {
        break missingId;
      }

      id = R.id.demo_applePicking;
      Button demoApplePicking = ViewBindings.findChildViewById(rootView, id);
      if (demoApplePicking == null) {
        break missingId;
      }

      id = R.id.demo_armRotation;
      Button demoArmRotation = ViewBindings.findChildViewById(rootView, id);
      if (demoArmRotation == null) {
        break missingId;
      }

      id = R.id.demo_armSide;
      Button demoArmSide = ViewBindings.findChildViewById(rootView, id);
      if (demoArmSide == null) {
        break missingId;
      }

      id = R.id.demo_clappingHands;
      Button demoClappingHands = ViewBindings.findChildViewById(rootView, id);
      if (demoClappingHands == null) {
        break missingId;
      }

      id = R.id.demo_elbowStretch;
      Button demoElbowStretch = ViewBindings.findChildViewById(rootView, id);
      if (demoElbowStretch == null) {
        break missingId;
      }

      id = R.id.demo_ellbowHand;
      Button demoEllbowHand = ViewBindings.findChildViewById(rootView, id);
      if (demoEllbowHand == null) {
        break missingId;
      }

      id = R.id.demo_followArms;
      Button demoFollowArms = ViewBindings.findChildViewById(rootView, id);
      if (demoFollowArms == null) {
        break missingId;
      }

      id = R.id.demo_liftArms;
      Button demoLiftArms = ViewBindings.findChildViewById(rootView, id);
      if (demoLiftArms == null) {
        break missingId;
      }

      id = R.id.demo_openHand;
      Button demoOpenHand = ViewBindings.findChildViewById(rootView, id);
      if (demoOpenHand == null) {
        break missingId;
      }

      id = R.id.demo_tendBody;
      Button demoTendBody = ViewBindings.findChildViewById(rootView, id);
      if (demoTendBody == null) {
        break missingId;
      }

      id = R.id.textView1;
      TextView textView1 = ViewBindings.findChildViewById(rootView, id);
      if (textView1 == null) {
        break missingId;
      }

      return new ActivityDemoBinding((ConstraintLayout) rootView, backButton2, demoAirBoxing,
          demoApplePicking, demoArmRotation, demoArmSide, demoClappingHands, demoElbowStretch,
          demoEllbowHand, demoFollowArms, demoLiftArms, demoOpenHand, demoTendBody, textView1);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}
