package de.fhkiel.srcms.apps.therapy.physical;

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
import com.aldebaran.qi.sdk.object.conversation.Say;
import com.bumptech.glide.Glide;
import de.fhkiel.srcms.apps.therapy.physical.R;

import de.fhkiel.srcms.apps.therapy.physical._database.UpdateUser;

public class PerformActivity extends RobotActivity implements RobotLifecycleCallbacks {

    private final static String TAG = PerformActivity.class.getName();
    public Button cancel_button;
    ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perform_activity);
        QiSDK.register(this, this);

        // implement random gif
        TypedArray images = getResources().obtainTypedArray(R.array.gif_array);
        int choice = (int) (Math.random()*images.length());
        imageView = findViewById(R.id.my_gif);
        Glide.with(PerformActivity.this).load(images.getResourceId(choice,R.drawable.banana)).into(imageView);
        images.recycle();

        cancel_button = (Button) findViewById(R.id.cancel_back_button);
        cancel_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Exercise.cancel();
                    Intent backIntent = new Intent(PerformActivity.this, Welcome.class);
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
            String performMethod = extrasActivity.getString("keyPerform");
            if (performMethod.equals("valueHardPerform")){
                int[] arrayHard = {10,7,6,1,4,9,8,2,5,12,0};
                int iteration = 2;

                Say intro = SayBuilder.with(qiContext)
                    .withText("\\rspd=80\\ schön euch zu sehen \\pau=500\\ lasst uns ein paar schwere Übungen machen")
                    .build();
                intro.run();
                Exercise.doExercise(qiContext, arrayHard, iteration);
            }
            if (performMethod.equals("valueEasyPerform")){

                int[] arrayEasy = {10,7,6,5,11,3,12,0};
                int iteration=2;

                Say intro = SayBuilder.with(qiContext)
                        .withText("\\rspd=80\\ schön dass ihr da seid \\pau=500\\ lasst uns ein paar einfache Übungen machen")
                        .build();
                intro.run();
                Exercise.doExercise(qiContext, arrayEasy, iteration);
            }
            if (performMethod.equals("valueIndividual")){
                int iteration = UpdateUser.iter;
                int [] array = UpdateUser.array;
                String name = UpdateUser.userName;

                Say intro = SayBuilder.with(qiContext)
                        .withText("\\rspd=80\\ schön dass du mit mir ein paar Übungen machen möchtest.")
                        .build();
                intro.run();
                Exercise.doExercise(qiContext, array, iteration);
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