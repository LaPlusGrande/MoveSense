package de.fhkiel.srcms.apps.therapy.physical.p.workout.movesens;

import static java.lang.Math.abs;
import static java.lang.Math.sqrt;

import androidx.appcompat.app.AppCompatActivity;

import com.google.gson.Gson;

import java.util.Arrays;

public class DataCalculation {

    public double x,y,z;
    public String data;

    public double previousX, previousY, previousZ, currentX, currentY, currentZ;

    public double velX,disX,velY, disY,velZ,disZ;
    public double r, v;

    public long startCounter, stopCounter;
    public long timestamp;
    public double time;
    public boolean timerChecker = false;

    String[] arr= new String[4];

    public void accelData()
    {
        AccDataResponse accResponse = new Gson().fromJson(data, AccDataResponse.class);
        this.x = accResponse.body.array[0].x;
        this.y = accResponse.body.array[0].y;
        this.z = accResponse.body.array[0].z;
        this.timestamp = accResponse.body.timestamp;

        timestamp = this.timestamp-offsetTimer();

        // run asynchronously to actual process and keep value unchanged
        Thread newThread = new Thread(() -> {
            previousX = x;
            previousY = y;
            previousZ = z;
            try {
                // keep value unchanged for 100 ms
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        newThread.start();

        trackX(x,previousX);
        trackY(y,previousY);
        trackZ(z,previousZ);

    }

    private void trackX (double x, double previousX) {

        if((int)previousX > (int)x+1 || (int)previousX < (int)x-1 && !timerChecker){
            startCounter = System.currentTimeMillis();
            currentX = previousX;
            timerChecker = true;
        }
        if ((int)previousX == (int)x && timerChecker){
            stopCounter = System.currentTimeMillis();
            time = stopCounter - startCounter;
            timerChecker = false;

            //calculate velocity and route
//            System.out.println("current X = " + currentX);
//            System.out.println("previousX = " + previousX);
            velX = velocity(currentX,previousX,time);
            disX =distance(currentX,previousX,time);

            if(currentX < 0 && time >= 100) {System.out.println("Movement: neg x-axis");}
            if(currentX > 0 && time >= 100) {System.out.println("Movement: pos x-axis");}
            calculation(velX,velY,velZ,disX,disY,disZ);
            arr[0]= " x: "+this.x;
            arr[1]= "\n y: "+this.y;
            arr[2]= "\n z: "+this.z;
            arr[3]= "\n timestamp: "+timestamp;

            System.out.println(Arrays.toString(arr));
        }
    }

    private void trackY (double y, double previousY) {

        if((int)previousY > (int)y+1 || (int)previousY < (int)y-1 && !timerChecker){
            startCounter = System.currentTimeMillis();
            currentY = previousY;
            timerChecker = true;
        }
        if ((int)previousY == (int)y && timerChecker){
            stopCounter = System.currentTimeMillis();
            time = stopCounter - startCounter;
            timerChecker = false;

            //calculate velocity and route
//            System.out.println("current Y = " + currentY);
//            System.out.println("previous Y = " + previousY);
            velY = velocity(currentY,previousY,time);
            disY = distance(currentY,previousY,time);

            if(currentY < 0 && time >= 100) {System.out.println("Movement: neg y-axis");}
            if(currentY > 0 && time >= 100) {System.out.println("Movement: pos y-axis");}
            calculation(velX,velY,velZ,disX,disY,disZ);
            arr[0]= " x: "+this.x;
            arr[1]= "\n y: "+this.y;
            arr[2]= "\n z: "+this.z;
            arr[3]= "\n timestamp: "+this.timestamp;

            System.out.println(Arrays.toString(arr));
        }
    }

    private void trackZ (double z, double previousZ) {

        if((int)previousZ > (int)z+1 || (int)previousZ < (int)z-1 && !timerChecker){
            startCounter = System.currentTimeMillis();
            currentZ = previousZ;
            timerChecker = true;
        }
        if ((int)previousZ == (int)z && timerChecker){
            stopCounter = System.currentTimeMillis();
            time = stopCounter - startCounter;
            timerChecker = false;

            //calculate velocity and route
//            System.out.println("current Z = " + currentZ);
//            System.out.println("previous Z = " + previousZ);
            velZ = velocity(currentZ,previousZ,time);
            disZ = distance(currentZ,previousZ,time);

            if(currentZ < 0 && time >= 100) {System.out.println("Movement: neg z-axis");}
            if(currentZ > 0 && time >= 100) {System.out.println("Movement: pos z-axis");}

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

        System.out.println("velocity during movenment v = "+v);
        System.out.println("Distance old position to new position r = "+r);
    }

    private double velocity (double currentVal, double previousVal, double time){
        double velVal = 0;

        velVal = (abs(currentVal-previousVal)*time/1000);

        return (velVal);
    }

    private double distance (double currentVal, double previousVal, double time){
        double disVal = 0;

        disVal =(0.5*abs(currentVal-previousVal)*(time/1000*time/1000));

        return (disVal);
    }

    public long offsetTimer(){
        long offSetTime;
        offSetTime = this.timestamp;
        return (offSetTime);
    }

//    public String getData() {
//        return (data);
//    }
//
//    public void setData(String data) {
//        this.data=data;
//    }

    public DataCalculation (String data){
        this.data = data;
    }

//    @Override
//    public void run() {
////        String data = getData();
//        accelData(data);
//    }
}