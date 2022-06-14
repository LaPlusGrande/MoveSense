package de.fhkiel.srcms.apps.therapy.physical.p.workout;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.aldebaran.qi.sdk.design.activity.RobotActivity;
import com.aldebaran.qi.sdk.design.activity.conversationstatus.SpeechBarDisplayStrategy;

public class Welcome extends RobotActivity {

    private static final String TAG = Welcome.class.getName();

    public Button groupe_button, demo_button;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        // disable speech bar
        setSpeechBarDisplayStrategy(SpeechBarDisplayStrategy.IMMERSIVE);

        groupe_button = (Button) findViewById(R.id.btn_groupe);
        demo_button = (Button) findViewById(R.id.btn_demo);

        groupe_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent groupeIntent = new Intent(Welcome.this, GroupEntry.class);
                startActivity(groupeIntent);
            }
        });

        demo_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent individualIntent = new Intent(Welcome.this,Demo.class);
                startActivity(individualIntent);
            }
        });

        Thread runPost = new HttpClient();
        runPost.start();

    }

}

