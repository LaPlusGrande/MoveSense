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

public final class ActivityStartBinding implements ViewBinding {
  @NonNull
  private final ConstraintLayout rootView;

  @NonNull
  public final Button btnGroupe;

  @NonNull
  public final TextView txtWelcome;

  private ActivityStartBinding(@NonNull ConstraintLayout rootView, @NonNull Button btnGroupe,
      @NonNull TextView txtWelcome) {
    this.rootView = rootView;
    this.btnGroupe = btnGroupe;
    this.txtWelcome = txtWelcome;
  }

  @Override
  @NonNull
  public ConstraintLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static ActivityStartBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static ActivityStartBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.activity_start, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static ActivityStartBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.btn_groupe;
      Button btnGroupe = ViewBindings.findChildViewById(rootView, id);
      if (btnGroupe == null) {
        break missingId;
      }

      id = R.id.txt_welcome;
      TextView txtWelcome = ViewBindings.findChildViewById(rootView, id);
      if (txtWelcome == null) {
        break missingId;
      }

      return new ActivityStartBinding((ConstraintLayout) rootView, btnGroupe, txtWelcome);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}
