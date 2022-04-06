package de.fhkiel.srcms.apps.therapy.physical.p.workout;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.aldebaran.qi.sdk.QiContext;
import com.aldebaran.qi.sdk.QiSDK;
import com.aldebaran.qi.sdk.RobotLifecycleCallbacks;
import com.aldebaran.qi.sdk.design.activity.RobotActivity;

public class GroupEntry extends RobotActivity implements RobotLifecycleCallbacks {

    private static final String TAG = GroupEntry.class.getName();

    public Button hard_button;
    public Button easy_button;
    public Button back_button;

    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Register the RobotLifecycleCallbacks for this Activity.
        QiSDK.register(this, this);

        setContentView(R.layout.activity_group_entry);

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

        hard_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent performIntent = new Intent(GroupEntry.this, PerformActivity.class);
                performIntent.putExtra("keyPerform", "valueHardPerform");
                startActivity(performIntent);
            }
        });
        easy_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent performIntent = new Intent(GroupEntry.this, PerformActivity.class);
                performIntent.putExtra("keyPerform", "valueEasyPerform");
                startActivity(performIntent);
            }
        });
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



