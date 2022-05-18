package de.fhkiel.srcms.apps.therapy.physical.p.workout;

import android.annotation.SuppressLint;
import android.os.Build;
import android.util.Log;

import androidx.annotation.RequiresApi;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

import de.fhkiel.srcms.apps.therapy.physical.p.workout.actions.PepperAnimation;

public class HttpClient extends Thread {


    private static String TAG = HttpClient.class.getName();
    private PepperAnimation pepperAnimation = new PepperAnimation();

    public HttpClient(){
        super("HttpClient");
    }

    public void run(){
//        try {
//            dataGet();
//        } catch (MalformedURLException e) {
//            e.printStackTrace();
//        }
        try {
            System.out.println("inside thread");
                dataPost();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

//    public void dataGet() throws MalformedURLException {
//
//        URLConnection connection = null;
//        try{
//            URL url = new URL("http://10.0.2.2:5837/generate/key");
//            connection = url.openConnection();
//        }catch (IOException e){
//            e.printStackTrace();
//        }
//        try {
//            System.out.println(connection.getInputStream());
//        }catch (IOException e){
//            System.out.println(e +":(");
//        }
//
//        BufferedReader in = null;
//        try {
//            in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//        String line;
//        try {
//            while ((line = in.readLine()) != null) {
//                System.out.println(line);
//            }
//        }catch (IOException e){
//            e.printStackTrace();
//        }
//    }

    public void dataPost() throws IOException {

        URLConnection connection =null;
        try {
            URL url = new URL("http://10.0.2.2:5837/app/Physio-Workout/log/app");
//            URL url = new URL("http://localhost:5837/app/Physio-Workout/log/app");
            connection = url.openConnection();
            connection.setDoOutput(true);

            String test = "{'key': 'wgGzBZ4JwhdL7<p1NPyM4kyPBQC#z}U4Y<@Eg3-YzdZl.*tI5-FWBi9<Sm%emeK-','data':'"+pepperAnimation.dataLog.toString() +"'}";
//            String test = "{'key': 'hn<K/Z&L=YB03}gLiufe)(!9;YIm2%6h?4DPd=3ZLe1E8x??BbAJ0md#u4v=48:2','data':'test test test'}";

            connection.setRequestProperty("Content-Type", "text/plain");
            connection.setRequestProperty("Content-Length", Integer.toString(test.length()));

            try (DataOutputStream dos = new DataOutputStream(connection.getOutputStream())) {
                dos.writeBytes(test);
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
