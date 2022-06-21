package de.fhkiel.srcms.apps.therapy.physical.p.workout;

import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

import de.fhkiel.srcms.apps.therapy.physical.p.workout.actions.PepperAnimation;

public class HttpClient
{

    private static final String TAG = HttpClient.class.getName();
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

            Log.d(TAG, "login to " + url.toString());
            try (DataOutputStream dos = new DataOutputStream(connection.getOutputStream())) {
                dos.writeBytes(physioKey);
            }catch (IOException e){
                Log.e(TAG,"Exception sending login data: " + e);
            }

            try (BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream()))) {
                key = br.readLine();
                Log.d(TAG, "got login key" + key);
                return key;
            }catch (IOException e){
                Log.e(TAG, "cannot read response: "+ e);
            }
        }catch (IOException e){
            Log.e(TAG, "cannot login to logging server " + loggingServerIP.toString());
        }

        return null;
    }

    public void dataPost(Object data){
        Log.d(TAG, "logging data ...\n" + data.toString());

        new Thread(new Runnable() {
            @Override
            public void run() {

                if (key == null || key.length() <= 0) {
                    login();
                }

                URLConnection connection;
                try {
                    URL url = new URL("http://" + loggingServerIP + ":5837/app/Physio-Workout/log/app");

                    connection = url.openConnection();
                    connection.setDoOutput(true);

                    LoggingData loggingData = new LoggingData(key);
                    loggingData.setData(data);
                    String stringData = loggingData.getRoot().toString();

                    connection.setRequestProperty("Content-Type", "text/plain");
                    connection.setRequestProperty("Content-Length", Integer.toString( stringData.length() ));

                    try (DataOutputStream dos = new DataOutputStream(connection.getOutputStream())) {
                        dos.writeBytes(stringData);
                        Log.d(TAG, "data send");
                    } catch (IOException e) {
                        Log.e(TAG, "failed to write data: " + e);
                    }

                    try (BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream()))) {
                        String line = br.readLine();
                        Log.d(TAG, "received response: " + line);

                        if (!line.contains("OK")){
                            Log.e(TAG, "data not send!");
                            key = "";       // reset key
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                } catch (NullPointerException | IOException e) {
                    Log.e(TAG, "error sending Data" + e);
                }
            }
        }).start();

    }

    private class LoggingData{
        private JSONObject root = new JSONObject();

        public LoggingData(String sessionKey){
            try {
                root.put("key", sessionKey);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        public Boolean setData(Object data){
            try {
                root.put("data", data);
                return true;
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return false;
        }

        public JSONObject getRoot() {
            return root;
        }
    }
}
