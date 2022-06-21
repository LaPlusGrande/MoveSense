package de.fhkiel.srcms.apps.therapy.physical.p.workout;

import android.content.Intent;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.aldebaran.qi.sdk.QiContext;
import com.aldebaran.qi.sdk.QiSDK;
import com.aldebaran.qi.sdk.RobotLifecycleCallbacks;
import com.aldebaran.qi.sdk.builder.SayBuilder;
import com.aldebaran.qi.sdk.design.activity.RobotActivity;
import com.aldebaran.qi.sdk.design.activity.conversationstatus.SpeechBarDisplayStrategy;
import com.aldebaran.qi.sdk.object.conversation.Say;
import com.bumptech.glide.Glide;


public class PerformActivity extends RobotActivity implements RobotLifecycleCallbacks {

    private final static String TAG = PerformActivity.class.getName();
    public Button cancel_button;
    private ImageView imageView;

    private String loginKey = null;
    private HttpClient logging = new HttpClient();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perform_activity);
        setSpeechBarDisplayStrategy(SpeechBarDisplayStrategy.IMMERSIVE);

        QiSDK.register(this, this);

        Exercise.logging = logging;

        // get logging client key
        if (getIntent().hasExtra("login_key")){
            loginKey = getIntent().getStringExtra("login_key");
            logging.setKey(loginKey);
            DataLog data = new DataLog();
            data.activity = this.getLocalClassName();
            logging.dataPost( data );
        }

        // implement random gif
        TypedArray images = getResources().obtainTypedArray(R.array.gif_array);
        int choice = (int) (Math.random()*images.length());
        imageView = findViewById(R.id.my_gif);
        Glide.with(PerformActivity.this).load(images.getResourceId(choice,R.drawable.banana)).into(imageView);
        images.recycle();

        // get loginkey

        cancel_button = (Button) findViewById(R.id.cancel_back_button);
        cancel_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Exercise.cancel();
                    Intent backIntent = new Intent(PerformActivity.this, Welcome.class);
                    backIntent.putExtra("login_key", loginKey);
                    startActivity(backIntent);
            }
        });
    }

    @Override
    public void onRobotFocusGained(QiContext qiContext) {

        Bundle extrasActivity = getIntent().getExtras();
        if (extrasActivity== null){
            Log.d(TAG,"an error occured");
        } else {
            int iterationDemo = 1;
            String performMethod = extrasActivity.getString("keyPerform");

            switch(performMethod){
                case "valueHardPerform":
                    int[] arrayHard = {10,7,6,1,4,9,8,2,5,12,0};

                    int iterationHard = 2;

                    Say introHard = SayBuilder.with(qiContext)
                            .withText("\\rspd=80\\ schön euch zu sehen \\pau=500\\ lasst uns ein paar schwere Übungen machen")
                            .build();
                    introHard.run();
                    Exercise.doExercise(qiContext, arrayHard, iterationHard);
                    break;

                case "valueEasyPerform":
                    int[] arrayEasy = {10,7,6,5,11,3,12,0};
                    int iterationEasy=2;

                    Say introEasy = SayBuilder.with(qiContext)
                            .withText("\\rspd=80\\ schön dass ihr da seid \\pau=500\\ lasst uns ein paar einfache Übungen machen")
                            .build();
                    introEasy.run();
                    Exercise.doExercise(qiContext, arrayEasy, iterationEasy);
                    break;

                // Start Demo-Version
                case "demoApplepicking":
                    int [] arrayApple = {1};
                    Exercise.doExercise(qiContext, arrayApple, iterationDemo);
                    break;
                case "demoArmRotation":
                    int [] arrayArmRotation = {2};
                    Exercise.doExercise(qiContext, arrayArmRotation, iterationDemo);
                    break;
                case "demoArmSide":
                    int [] arrayArmSide = {3};
                    Exercise.doExercise(qiContext, arrayArmSide, iterationDemo);
                    break;
                case "demoBoxing":
                    int [] arrayBoxing = {4};
                    Exercise.doExercise(qiContext, arrayBoxing, iterationDemo);
                    break;
                case "demoClapping":
                    int [] arrayClapping = {5};
                    Exercise.doExercise(qiContext, arrayClapping, iterationDemo);
                    break;
                case "demoElbowhand":
                    int [] arrayElbowhand = {6};
                    Exercise.doExercise(qiContext, arrayElbowhand, iterationDemo);
                    break;
                case "demoElbowstretch":
                    int [] arrayElbowStretch = {7};
                    Exercise.doExercise(qiContext, arrayElbowStretch, iterationDemo);
                    break;
                case "demoFollowArms":
                    int [] arrayFollowArms = {8};
                    Exercise.doExercise(qiContext, arrayFollowArms, iterationDemo);
                    break;
                case "demoLiftArms":
                    int [] arrayLiftArms = {9};
                    Exercise.doExercise(qiContext, arrayLiftArms, iterationDemo);
                    break;
                case "demoOpenhand":
                    int [] arrayOpenhand = {10};
                    Exercise.doExercise(qiContext, arrayOpenhand, iterationDemo);
                    break;
                case "demoBendBody":
                    int [] arrayBendBody = {11};
                    Exercise.doExercise(qiContext, arrayBendBody, iterationDemo);
                    break;
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