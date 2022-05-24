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


public class Demo extends RobotActivity implements RobotLifecycleCallbacks {

    private static final String TAG = Demo.class.getName();

    private Button back_button,Applepicking_button,ArmRotation_button,Armside_button,Boxing_button,Clapping_button, ElbowHand_button, ElbowStretch_button, FollowArms_button, LiftArms_button,OpenHand_button, BendBody_button;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demo);
        QiSDK.register(this, this);
        Log.d(TAG,"start class");

        back_button = findViewById(R.id.back_button2);
        Applepicking_button = findViewById(R.id.demo_applePicking);
        ArmRotation_button = findViewById(R.id.demo_armRotation);
        Armside_button = findViewById(R.id.demo_armSide);
        Boxing_button = findViewById(R.id.demo_airBoxing);
        Clapping_button = findViewById(R.id.demo_clappingHands);
        ElbowHand_button = findViewById(R.id.demo_ellbowHand);
        ElbowStretch_button = findViewById(R.id.demo_elbowStretch);
        FollowArms_button = findViewById(R.id.demo_followArms);
        LiftArms_button = findViewById(R.id.demo_liftArms);
        OpenHand_button = findViewById(R.id.demo_openHand);
        BendBody_button = findViewById(R.id.demo_tendBody);

        back_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent startIntent = new Intent(Demo.this, Welcome.class);
                startActivity(startIntent);
            }
        });
    }

    @Override
    protected void onDestroy() {
        QiSDK.unregister(this, this);
        super.onDestroy();
    }

    @Override
    public void onRobotFocusGained(QiContext qiContext) {

        Applepicking_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent performIntent = new Intent(Demo.this, PerformActivity.class);
                performIntent.putExtra("keyPerform", "demoApplepicking");
                startActivity(performIntent);
                }
            });
        ArmRotation_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent performIntent = new Intent(Demo.this, PerformActivity.class);
                performIntent.putExtra("keyPerform", "demoArmRotation");
                startActivity(performIntent);
            }
        });
        Armside_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent performIntent = new Intent(Demo.this, PerformActivity.class);
                performIntent.putExtra("keyPerform", "demoArmSide");
                startActivity(performIntent);
            }
        });
        Boxing_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent performIntent = new Intent(Demo.this, PerformActivity.class);
                performIntent.putExtra("keyPerform", "demoBoxing");
                startActivity(performIntent);
                }
            });
            Clapping_button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent performIntent = new Intent(Demo.this, PerformActivity.class);
                    performIntent.putExtra("keyPerform", "demoClapping");
                    startActivity(performIntent);
                }
            });
            ElbowHand_button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent performIntent = new Intent(Demo.this, PerformActivity.class);
                    performIntent.putExtra("keyPerform", "demoElbowhand");
                    startActivity(performIntent);
                }
            });
            ElbowStretch_button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent performIntent = new Intent(Demo.this, PerformActivity.class);
                    performIntent.putExtra("keyPerform", "demoElbowstretch");
                    startActivity(performIntent);
                }
            });
            FollowArms_button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent performIntent = new Intent(Demo.this, PerformActivity.class);
                    performIntent.putExtra("keyPerform", "demoFollowArms");
                    startActivity(performIntent);
                }
            });
            LiftArms_button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent performIntent = new Intent(Demo.this, PerformActivity.class);
                    performIntent.putExtra("keyPerform", "demoLiftArms");
                    startActivity(performIntent);
                }
            });
            OpenHand_button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent performIntent = new Intent(Demo.this, PerformActivity.class);
                    performIntent.putExtra("keyPerform", "demoOpenhand");
                    startActivity(performIntent);
                }
            });
            BendBody_button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent performIntent = new Intent(Demo.this, PerformActivity.class);
                    performIntent.putExtra("keyPerform", "demoBendBody");
                    startActivity(performIntent);
                }
            });

    }

    @Override
    public void onRobotFocusLost() {

    }

    @Override
    public void onRobotFocusRefused(String reason) {

    }
}