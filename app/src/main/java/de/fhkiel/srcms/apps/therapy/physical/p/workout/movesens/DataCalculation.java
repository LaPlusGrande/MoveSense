package de.fhkiel.srcms.apps.therapy.physical.p.workout.movesens;

import static java.lang.Math.abs;
import static java.lang.Math.sqrt;

import static de.fhkiel.srcms.apps.therapy.physical.p.workout.movesens.MainMovesense.URI_EVENTLISTENER;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.gson.Gson;
import com.movesense.mds.Mds;
import com.movesense.mds.MdsException;
import com.movesense.mds.MdsNotificationListener;
import com.movesense.mds.MdsSubscription;

import java.util.Arrays;

import de.fhkiel.srcms.apps.therapy.physical.p.workout.GroupEntry;
import de.fhkiel.srcms.apps.therapy.physical.p.workout.R;

public class DataCalculation extends AppCompatActivity {

    private static final String TAG = GroupEntry.class.getName();

    double x,y,z;

    double currentX, currentY, currentZ, prevX, prevY, prevZ, realX, realY, realZ, realprevX, realprevY, realprevZ;
    double cosAngleOld, cosAngleNew;
    double g = 9.81;

    double velX,disX,velY, disY,velZ,disZ;
    double r, v;

    long startCounter, stopCounter;
    long timestamp;
    double time;
    boolean timerChecker = false;

    String[] arr= new String[4];

    public void accelData(String data)
    {
        AccDataResponse accResponse = new Gson().fromJson(data, AccDataResponse.class);
        this.x = accResponse.body.array[0].x;
        this.y = accResponse.body.array[0].y;
        this.z = accResponse.body.array[0].z;
        this.timestamp = accResponse.body.timestamp;

        Thread newThread = new Thread(() -> {
            currentX = x;
            currentY = y;
            currentZ = z;
            try {
                // keep value unchanged for 100 ms
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        newThread.start();

        trackX(x,currentX);
        trackY(y,currentY);
        trackZ(z,currentZ);

    }

    private void trackX (double x, double currentX) {

        double prevg, newg;

        if((int)currentX > (int)x+1 || (int)currentX < (int)x-1 && !timerChecker){
            startCounter = System.currentTimeMillis();
            prevX = currentX;
            timerChecker = true;
        }
        if ((int)currentX == (int)x && timerChecker){
            stopCounter = System.currentTimeMillis();
            time = stopCounter - startCounter;
            timerChecker = false;

            cosAngleOld = prevX/Math.sqrt(prevX*prevX+prevY*prevY+prevZ*prevZ);
            prevg = g*cosAngleOld;
            realprevX = prevX-prevg;

            cosAngleNew = prevX/Math.sqrt(currentX*currentX+currentY*currentY+currentZ*currentZ);
            newg = g*cosAngleNew;
            realX = prevX-newg;

            disX=distance(realX,realprevX,time);
            velX=velocity(realX,realprevX,time);

//            if(prevX < 0 && time >= 50) {System.out.println("Movement: neg x-axis");}
//            if(prevX > 0 && time >= 50) {System.out.println("Movement: pos x-axis");}

            calculation(velX,velY,velZ,disX,disY,disZ);
            arr[0]= " x: "+this.x;
            arr[1]= "\n y: "+this.y;
            arr[2]= "\n z: "+this.z;
            arr[3]= "\n timestamp: "+timestamp;

            System.out.println(Arrays.toString(arr));
        }
    }

    private void trackY (double y, double currentY) {

        if((int)currentY > (int)y+1 || (int)currentY < (int)y-1 && !timerChecker){
            startCounter = System.currentTimeMillis();
            prevY = currentY;
            timerChecker = true;
        }
        if ((int)currentY == (int)y && timerChecker){
            stopCounter = System.currentTimeMillis();
            time = stopCounter - startCounter;
            timerChecker = false;

            //calculate velocity and route
//            System.out.println("current Y = " + currentY);
//            System.out.println("previous Y = " + previousY);
//            velY = velocity(currentY,previousY,time);
//            disY = distance(currentY,previousY,time);

            if(prevY < 0 && time >= 50) {System.out.println("Movement: neg y-axis");}
            if(prevY > 0 && time >= 50) {System.out.println("Movement: pos y-axis");}
            calculation(velX,velY,velZ,disX,disY,disZ);
            arr[0]= " x: "+this.x;
            arr[1]= "\n y: "+this.y;
            arr[2]= "\n z: "+this.z;
            arr[3]= "\n timestamp: "+this.timestamp;

            System.out.println(Arrays.toString(arr));
        }

    }

    private void trackZ (double z, double currentZ) {

        if((int)currentZ > (int)z+1 || (int)currentZ < (int)z-1 && !timerChecker){
            startCounter = System.currentTimeMillis();
            prevZ = currentZ;
            timerChecker = true;
        }
        if ((int)currentZ == (int)z && timerChecker){
            stopCounter = System.currentTimeMillis();
            time = stopCounter - startCounter;
            timerChecker = false;

            //calculate velocity and route
//            System.out.println("current Z = " + currentZ);
//            System.out.println("previous Z = " + previousZ);

//            velZ = velocity(currentZ,previousZ,time);
//            disZ = distance(currentZ,previousZ,time);

            if(currentZ < 0 && time >= 50) {System.out.println("Movement: neg z-axis");}
            if(currentZ > 0 && time >= 50) {System.out.println("Movement: pos z-axis");}

            calculation(velX,velY,velZ,disX,disY,disZ);
            arr[0]= "x: "+this.x;
            arr[1]= "\n y: "+this.y;
            arr[2]= "\n z: "+this.z;
            arr[3]= "\n timestamp: "+timestamp;

            System.out.println(Arrays.toString(arr));
        }
    }

    private void calculation (double vx, double vy, double vz, double rx, double ry, double rz) {
        v = sqrt(vx*vx+vy*vy+vz*vz);
        r = sqrt(rx*rx+ry*ry+rz*rz);

        System.out.println("velocity v = "+v +"m/s");
        System.out.println("Distance r = "+r +"m");
    }

    private double velocity (double currentVal, double previousVal, double time){
        double velVal = 0;
        velVal = (abs(currentVal-previousVal)*time/1000);
        return (velVal);
    }
    private double distance (double currentVal, double prevVal, double time){
        double disVal = 0;
        disVal =(0.5*abs(currentVal-prevVal)*((time/1000)*(time/1000)));
        return (disVal);
    }
}