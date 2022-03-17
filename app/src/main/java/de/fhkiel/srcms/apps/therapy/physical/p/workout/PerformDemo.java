package de.fhkiel.srcms.apps.therapy.physical.p.workout;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.aldebaran.qi.sdk.QiContext;
import com.aldebaran.qi.sdk.QiSDK;
import com.aldebaran.qi.sdk.RobotLifecycleCallbacks;
import com.aldebaran.qi.sdk.design.activity.RobotActivity;

import de.fhkiel.srcms.apps.therapy.physical.R;
import de.fhkiel.srcms.apps.therapy.physical.p.workout.actions.PepperAnimation;

public class PerformDemo extends RobotActivity implements RobotLifecycleCallbacks {

    private Button cancel_back_button;
    private final static String TAG = PerformDemo.class.getName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfrom_demo);
        QiSDK.register(this, this);

        cancel_back_button = (Button) findViewById(R.id.cancel_back_button2);

        cancel_back_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Exercise.cancel();
                Intent backIntent = new Intent(PerformDemo.this,Demo.class);
                startActivity(backIntent);
            }
        });
    }

    @Override
    public void onRobotFocusGained(QiContext qiContext) {

        Bundle extrasDemo = getIntent().getExtras();
        Log.d(TAG,"check Bundle");
        if (extrasDemo== null){
            Log.d(TAG,"an error occured");
        } else {
            String demoMethod = extrasDemo.getString("keyDemo");

            try {
                if (demoMethod.equals("valueApplepicking")) {
                    PepperAnimation.doApplePicking(qiContext);
                }
                if (demoMethod.equals("valueArmRotation")) {
                    PepperAnimation.doArmRotation(qiContext);
                }
                if (demoMethod.equals("valueArmSide")) {
                    PepperAnimation.doArmSide(qiContext);
                }
                if (demoMethod.equals("valueBoxing")) {
                    PepperAnimation.doBoxing(qiContext);
                }
                if (demoMethod.equals("valueClapping")) {
                    PepperAnimation.doClapping(qiContext);
                }
                if (demoMethod.equals("valueElbowhand")) {
                    PepperAnimation.doElbowHand(qiContext);
                }
                if (demoMethod.equals("valueElbowstretch")) {
                    PepperAnimation.doElbowStretch(qiContext);
                }
                if (demoMethod.equals("valueFollowArms")) {
                    PepperAnimation.doFollowArm(qiContext);
                }
                if (demoMethod.equals("valueLiftArms")) {
                    PepperAnimation.doLiftArm(qiContext);
                }
                if (demoMethod.equals("valueOpenhand")) {
                    PepperAnimation.doOpenHand(qiContext);
                }
                if (demoMethod.equals("valueTendBody")) {
                    PepperAnimation.doTendBody(qiContext);
                }
            }catch (Exception e) {
                Log.e("exception in switch ", e.getMessage(), e);
            }
        }
    }

    @Override
    protected void onDestroy() {
        // Unregister the RobotLifecycleCallbacks for this Activity.
        QiSDK.unregister(this, this);
        super.onDestroy();
    }

    @Override
    public void onRobotFocusLost() {

    }

    @Override
    public void onRobotFocusRefused(String reason) {

    }
}