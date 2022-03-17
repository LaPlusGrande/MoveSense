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

import de.fhkiel.srcms.apps.therapy.physical.p.workout.R;
import de.fhkiel.srcms.apps.therapy.physical.p.workout.database.AddUser;

public class Demo extends RobotActivity implements RobotLifecycleCallbacks {

    private static final String TAG = Demo.class.getName();
    private QiContext qiContext;

    private Button back_button;

    public Button Applepicking_button;
    public Button ArmRotation_button;
    public Button Armside_button;
    public Button Boxing_button;
    public Button Clapping_button;
    public Button ElbowHand_button;
    public Button ElbowStretch_button;
    public Button FollowArms_button;
    public Button LiftArms_button;
    public Button OpenHand_button;
    public Button TendBody_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demo);
        QiSDK.register(this, this);
        Log.d(TAG,"start class");

        back_button = (Button) findViewById(R.id.back_button2);
        Applepicking_button = (Button) findViewById(R.id.demo_applePicking);
        ArmRotation_button = (Button) findViewById(R.id.demo_armRotation);
        Armside_button = (Button) findViewById(R.id.demo_armSide);
        Boxing_button = (Button) findViewById(R.id.demo_airBoxing);
        Clapping_button = (Button) findViewById(R.id.demo_clappingHands);
        ElbowHand_button = (Button) findViewById(R.id.demo_ellbowHand);
        ElbowStretch_button = (Button) findViewById(R.id.demo_elbowStretch);
        FollowArms_button = (Button) findViewById(R.id.demo_followArms);
        LiftArms_button = (Button) findViewById(R.id.demo_liftArms);
        OpenHand_button = (Button) findViewById(R.id.demo_openHand);
        TendBody_button = (Button) findViewById(R.id.demo_tendBody);

        back_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent startIntent = new Intent(Demo.this, AddUser.class);
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

        this.qiContext = qiContext;

        Applepicking_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent performIntent = new Intent(Demo.this, PerformDemo.class);
                performIntent.putExtra("keyDemo", "valueApplepicking");
                startActivity(performIntent);
                }
            });
        ArmRotation_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent performIntent = new Intent(Demo.this, PerformDemo.class);
                performIntent.putExtra("keyDemo", "valueArmRotation");
                startActivity(performIntent);
            }
        });
        Armside_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent performIntent = new Intent(Demo.this, PerformDemo.class);
                performIntent.putExtra("keyDemo", "valueArmSide");
                startActivity(performIntent);
            }
        });
        Boxing_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent performIntent = new Intent(Demo.this, PerformDemo.class);
                performIntent.putExtra("keyDemo", "valueBoxing");
                startActivity(performIntent);
                }
            });
            Clapping_button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent performIntent = new Intent(Demo.this, PerformDemo.class);
                    performIntent.putExtra("keyDemo", "valueClapping");
                    startActivity(performIntent);
                }
            });
            ElbowHand_button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent performIntent = new Intent(Demo.this, PerformDemo.class);
                    performIntent.putExtra("keyDemo", "valueElbowhand");
                    startActivity(performIntent);
                }
            });
            ElbowStretch_button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent performIntent = new Intent(Demo.this, PerformDemo.class);
                    performIntent.putExtra("keyDemo", "valueElbowstretch");
                    startActivity(performIntent);
                }
            });
            FollowArms_button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent performIntent = new Intent(Demo.this, PerformDemo.class);
                    performIntent.putExtra("keyDemo", "valueFollowArms");
                    startActivity(performIntent);
                }
            });
            LiftArms_button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent performIntent = new Intent(Demo.this, PerformDemo.class);
                    performIntent.putExtra("keyDemo", "valueLiftArms");
                    startActivity(performIntent);
                }
            });
            OpenHand_button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent performIntent = new Intent(Demo.this, PerformDemo.class);
                    performIntent.putExtra("keyDemo", "valueOpenhand");
                    startActivity(performIntent);
                }
            });
            TendBody_button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent performIntent = new Intent(Demo.this, PerformDemo.class);
                    performIntent.putExtra("keyDemo", "valueTendBody");
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