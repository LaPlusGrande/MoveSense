package de.fhkiel.srcms.apps.therapy.physical.p.workout;

import android.util.Log;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.URL;
import java.net.URLConnection;

import de.fhkiel.srcms.apps.therapy.physical.p.workout.actions.PepperAnimation;

public class HttpClient
{

    private static final String TAG = HttpClient.class.getName();
    private PepperAnimation pepperAnimation = new PepperAnimation();
    private String key = "";

    // Raspberry Pi, IP 192.168.178.10, Port 5837
    private static final String loggingServerIP = "192.168.1.7";

    public void setKey(String key){
        this.key = key;
    }

    public String login (){

        try{

            URL url = new URL("http://" + loggingServerIP + ":5837/app/login/Physio-Workout");
            URLConnection connection = url.openConnection();
            connection.setDoOutput(true);

            connection.setRequestProperty("Content-Type", "text/plain");
            String physioKey = "{'key': '47654d4cfcefe1092b7afb12d18b55c9859d2bd9226e381c4f50a0447d1122afe84dd3cb94c8aa00fd09ee8a5847977efa5a7e4c4aa16f0754f9493923d96cef8b9ce21334700482b7a8b7d68ae852a0f4133a48b8fa3084c034978374a99169eedde1216d04eda810ed981b5cb946ff310fdf5770b581cde5951546adb238d7'}";
            connection.setRequestProperty("Content-Length", physioKey);

            try (DataOutputStream dos = new DataOutputStream(connection.getOutputStream())) {
                dos.writeBytes(physioKey);
            }catch (IOException e){
                Log.e(TAG,"Exception"+ e);
            }

            try (BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream()))) {
                key = br.readLine();
                    System.out.println("key" + key);
                return key;
            }catch (IOException e){
                Log.d(TAG, "ISR"+ e);
            }
        }catch (IOException e){
            e.printStackTrace();
        }

        return null;
    }

    public void dataPost(String text) throws IOException {

        URLConnection connection;
        try {
            URL url = new URL("http://" + loggingServerIP + ":5837/app/Physio-Workout/log/app");

            connection = url.openConnection();
            connection.setDoOutput(true);

            String test = "{'key': '"+ key +"','data':'"+ text +"'}";

            connection.setRequestProperty("Content-Type", "text/plain");
            connection.setRequestProperty("Content-Length", Integer.toString(test.length()));

            try (DataOutputStream dos = new DataOutputStream(connection.getOutputStream())) {
                dos.writeBytes(test);
                System.out.println(test);
            }catch (IOException e){
                Log.e(TAG,"DOS"+ e);
            }

            try (BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream()))) {
                String line;
                while ((line = br.readLine()) != null) {
                    System.out.println(line);
                }
            }catch (IOException e){
                e.printStackTrace();
            }
        }catch (NullPointerException e){
            Log.e(TAG,"sending Data"+e);
        }
    }
}
