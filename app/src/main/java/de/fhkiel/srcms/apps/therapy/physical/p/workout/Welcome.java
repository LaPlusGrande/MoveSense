package de.fhkiel.srcms.apps.therapy.physical.p.workout;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.aldebaran.qi.sdk.design.activity.RobotActivity;
import com.aldebaran.qi.sdk.design.activity.conversationstatus.SpeechBarDisplayStrategy;

import java.io.IOException;
import java.net.MalformedURLException;

import de.fhkiel.srcms.apps.therapy.physical.p.workout.actions.PepperAnimation;
import de.fhkiel.srcms.apps.therapy.physical.p.workout.movesens.MainMovesense;

public class Welcome extends RobotActivity {

//    private String loginKey = null;
//    private HttpClient logging = new HttpClient();

    private static final String TAG = Welcome.class.getName();

    public Button groupe_button, demo_button, exit_button;
    public Button no_sens_button,sens_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
        setSpeechBarDisplayStrategy(SpeechBarDisplayStrategy.IMMERSIVE);
        // disable speech bar
        setSpeechBarDisplayStrategy(SpeechBarDisplayStrategy.IMMERSIVE);

        groupe_button = findViewById(R.id.btn_groupe);
        demo_button = findViewById(R.id.btn_demo);
        exit_button = findViewById(R.id.btnWelcomeExit);
        no_sens_button = findViewById(R.id.button_no_sens);
        sens_button = findViewById(R.id.button_yes_sens);

        groupe_button.setOnClickListener(view -> {
            no_sens_button.setVisibility(View.VISIBLE);
            sens_button.setVisibility(View.VISIBLE);
            groupe_button.setVisibility(View.GONE);
            demo_button.setVisibility(View.GONE);
        });

        demo_button.setOnClickListener(view -> {
            Intent individualIntent = new Intent(Welcome.this, Demo.class);
            startActivity(individualIntent);
        });

        exit_button.setOnClickListener(v -> finishAffinity());

        no_sens_button.setOnClickListener(view -> {
            Intent groupeIntent = new Intent(Welcome.this, GroupEntry.class);
            startActivity(groupeIntent);
        });
        sens_button.setOnClickListener(view -> {
            Intent sensIntent = new Intent(Welcome.this, MainMovesense.class);
            startActivity(sensIntent);
        });

//        new Thread(() -> {
//            loginKey = logging.login();
//            DataLog data = new DataLog();
//            data.activity = this.getLocalClassName();
//            logging.dataPost( data );
//        }).start();
    }
}

