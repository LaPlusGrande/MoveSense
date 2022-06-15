package de.fhkiel.srcms.apps.therapy.physical.p.workout;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.aldebaran.qi.Future;
import com.aldebaran.qi.sdk.QiContext;
import com.aldebaran.qi.sdk.QiSDK;
import com.aldebaran.qi.sdk.RobotLifecycleCallbacks;
import com.aldebaran.qi.sdk.design.activity.RobotActivity;
import com.aldebaran.qi.sdk.design.activity.conversationstatus.SpeechBarDisplayStrategy;
import com.aldebaran.qi.sdk.object.actuation.Actuation;
import com.aldebaran.qi.sdk.object.actuation.Frame;
import com.aldebaran.qi.sdk.object.geometry.Transform;
import com.aldebaran.qi.sdk.object.geometry.TransformTime;
import com.aldebaran.qi.sdk.object.geometry.Vector3;
import com.aldebaran.qi.sdk.object.human.AttentionState;
import com.aldebaran.qi.sdk.object.human.EngagementIntentionState;
import com.aldebaran.qi.sdk.object.human.ExcitementState;
import com.aldebaran.qi.sdk.object.human.Gender;
import com.aldebaran.qi.sdk.object.human.Human;
import com.aldebaran.qi.sdk.object.human.PleasureState;
import com.aldebaran.qi.sdk.object.human.SmileState;
import com.aldebaran.qi.sdk.object.humanawareness.HumanAwareness;

import java.io.IOException;
import java.util.List;

public class GroupEntry extends RobotActivity implements RobotLifecycleCallbacks {

    private static final String TAG = GroupEntry.class.getName();

    private String loginKey = null;
    private HttpClient logging = new HttpClient();

    public Button hard_button;
    public Button easy_button;
    public Button back_button;
    private static HumanAwareness humanAwareness;
    private static QiContext qiContext;
    public DataLog dataLog = new DataLog();

    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setSpeechBarDisplayStrategy(SpeechBarDisplayStrategy.IMMERSIVE);

        // Register the RobotLifecycleCallbacks for this Activity.
        QiSDK.register(this, this);

        setContentView(R.layout.activity_group_entry);

        // get logging client key
        if (getIntent().hasExtra("login_key")){
            loginKey = getIntent().getStringExtra("login_key");
            logging.setKey(loginKey);
        }

        hard_button = (Button) findViewById(R.id.button_hard_animation);
        easy_button = (Button) findViewById(R.id.button_easy_animation);
        back_button = (Button) findViewById(R.id.back_button);

        back_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent groupeIntent = new Intent(GroupEntry.this, Welcome.class);
                startActivity(groupeIntent);
            }
        });
    }

    @Override
    public void onRobotFocusGained(QiContext qiContext) {

        GroupEntry.qiContext = qiContext;
        humanAwareness = qiContext.getHumanAwareness();
        findHumansAround();

        hard_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent performIntent = new Intent(GroupEntry.this, PerformActivity.class);
                performIntent.putExtra("keyPerform", "valueHardPerform");
                performIntent.putExtra("login_key", loginKey);
                startActivity(performIntent);
            }
        });
        easy_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent performIntent = new Intent(GroupEntry.this, PerformActivity.class);
                performIntent.putExtra("keyPerform", "valueEasyPerform");
                performIntent.putExtra("login_key", loginKey);
                startActivity(performIntent);
            }
        });
    }
    public void findHumansAround() {
        // Get the humans around the robot.
        Future<List<Human>> humansAroundFuture = humanAwareness.async().getHumansAround();
        humansAroundFuture.andThenConsume(humansAround -> Log.i(TAG, humansAround.size() + " human(s) around."));

        // Retrieving characteristics
        humansAroundFuture.andThenConsume(humansAround -> {
            Log.i(TAG, humansAround.size() + " human(s) around.");
            retrieveCharacteristics(humansAround);
        });
    }

    public void retrieveCharacteristics(final List<Human> humans) {
        Actuation actuation = qiContext.getActuation();
        Frame robotFrame = actuation.robotFrame();

        for (int i = 0; i < humans.size(); i++) {
            // Get the human.
            Human human = humans.get(i);
            Frame humanFrame = human.getHeadFrame();
            // Compute the distance.
            double distance = computeDistance(humanFrame, robotFrame);
            PleasureState pleasureState = human.getEmotion().getPleasure();
            ExcitementState excitementState = human.getEmotion().getExcitement();
            EngagementIntentionState engagementIntentionState = human.getEngagementIntention();
            SmileState smileState = human.getFacialExpressions().getSmile();
            AttentionState attentionState = human.getAttention();
            dataLog.logHuman(i, pleasureState,excitementState, engagementIntentionState, smileState, attentionState, distance);

            HttpClient client = new HttpClient();
            new Thread(() -> {
                try {
                    client.dataPost( dataLog.toString() );
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }).start();
        }
    }
    public static double computeDistance(Frame humanFrame, Frame robotFrame) {
        // Here we will compute the distance between the human and the robot.
        // Get the TransformTime between the human frame and the robot frame.
        TransformTime transformTime = humanFrame.computeTransform(robotFrame);
        // Get the transform.
        Transform transform = transformTime.getTransform();
        // Get the translation.
        Vector3 translation = transform.getTranslation();
        // Get the x and y components of the translation.
        double x = translation.getX();
        double y = translation.getY();
        double z = translation.getZ();

        // Compute the distance and return it.
        return Math.sqrt(x * x + y * y);
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



