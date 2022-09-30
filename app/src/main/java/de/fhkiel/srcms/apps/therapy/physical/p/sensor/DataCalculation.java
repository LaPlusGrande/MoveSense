package de.fhkiel.srcms.apps.therapy.physical.p.sensor;

import static java.lang.Math.abs;
import static java.lang.Math.sqrt;

import androidx.appcompat.app.AppCompatActivity;

import com.google.gson.Gson;

public class DataCalculation extends AppCompatActivity {

    double x,y,z;

    double currentX, currentY, currentZ, realX, realY, realZ, realpreviousX, realpreviousY, realpreviousZ;
    double velX,disX,velY, disY,velZ,disZ;
    double g = 9.81;
    double alpha, beta, gamma;

    double gx = 0, gy = 0, gz = 0;
    double regressionTime =0;

    long timestamp;
    double time;
    boolean timerChecker = false;

    int millisec = 50;
    double scale = 10000d;


    public void accelData(String data) {
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
                Thread.sleep(millisec);
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

        double cosAngleOld, cosAngleNew;
        double prevg, newg;

        if((int)currentX > (int)x+1 || (int)currentX < (int)x-1){

            time = millisec;

            cosAngleOld = currentX/Math.sqrt(currentX*currentX+currentY*currentY+currentZ*currentZ);
            prevg = g*cosAngleOld;
            realpreviousX = currentX-prevg;

            cosAngleNew = x/Math.sqrt(x*x+y*y+z*z);
            newg = g*cosAngleNew;
            realX = x-newg;

            alpha =Math.toDegrees(Math.asin(cosAngleNew));
//            if(abs(alpha)> 10){System.out.println("alpha ist:" + alpha + "°");}

            disX=distance(realX,realpreviousX,time);
            velX=velocity(realX,realpreviousX,time);

//            if(previousX < 0 ) {System.out.println("Movement: neg x-axis");}
//            if(previousX > 0 ) {System.out.println("Movement: pos x-axis");}

            calculation(velX,velY,velZ,disX,disY,disZ,time);

        }
    }

    private void trackY (double y, double currentY) {

        double cosAngleOld, cosAngleNew;
        double prevg, newg;

        if((int)currentY > (int)y+1 || (int)currentY < (int)y-1 && !timerChecker){

            time = millisec;

            cosAngleOld = currentY/Math.sqrt(currentX*currentX+currentY*currentY+currentZ*currentZ);
            prevg = g*cosAngleOld;
            realpreviousY = currentY-prevg;

            cosAngleNew = y/Math.sqrt(x*x+y*y+z*z);
            newg = g*cosAngleNew;
            realY = y-newg;

            beta = Math.toDegrees(Math.asin(cosAngleNew));
//            if(abs(beta) > 10){System.out.println("beta ist: " + beta + "°");}


            disY=distance(realY, realpreviousY,time);
            velY=velocity(realY, realpreviousY,time);

//            if(previousY < 0 ) {System.out.println("Movement: neg y-axis");}
//            if(previousY > 0 ) {System.out.println("Movement: pos y-axis");}

            calculation(velX,velY,velZ,disX,disY,disZ, time);

        }

    }

    private void trackZ (double z, double currentZ) {

        double cosAngleOld, cosAngleNew;
        double prevg, newg;

        if((int)currentZ > (int)z+1 || (int)currentZ < (int)z-1 && !timerChecker){

            time = millisec;

            cosAngleOld = currentZ/Math.sqrt(currentX*currentX+currentY*currentY+currentZ*currentZ);
            prevg = g*cosAngleOld;
            realpreviousZ = currentZ-prevg;

            cosAngleNew = z/Math.sqrt(x*x+y*y+z*z);
            newg = g*cosAngleNew;
            realX = z-newg;

            gamma = Math.toDegrees(Math.asin(cosAngleNew));
//            if(abs(gamma)>10){System.out.println("gamma ist: " + gamma + "°");}

            disZ=distance(realZ,realpreviousZ,time);
            velZ=velocity(realZ,realpreviousZ,time);

//            if(currentZ < 0 ) {System.out.println("Movement: neg z-axis");}
//            if(currentZ > 0 ) {System.out.println("Movement: pos z-axis");}

            calculation(velX,velY,velZ,disX,disY,disZ, time);

        }
    }

    private void calculation (double vx, double vy, double vz, double rx, double ry, double rz, double time) {

        double calR, calV, generalR, regressionFunction, testR;

        rx = (double) Math.round(rx * scale)/scale;
        ry = (double) Math.round(ry * scale)/scale;
        rz = (double) Math.round(rz * scale)/scale;

        vx = (double) Math.round(vx * scale)/scale;
        vy = (double) Math.round(vy * scale)/scale;
        vz = (double) Math.round(vz * scale)/scale;

        System.out.println(" x = " + rx);
        System.out.println(" y = " + ry);
        System.out.println(" z = " + rz);
        System.out.println(" dx = " + vx);
        System.out.println(" dy = " + vy);
        System.out.println(" dz = " + vz);

//        regressionTime += time;
//
//        if (rx != 0 || ry != 0 || rz != 0){
//            gx = (double) Math.round(gx * scale)/scale + (double) Math.round(rx * scale)/scale;
//            gy = (double) Math.round(gy * scale)/scale + (double) Math.round(ry * scale)/scale;
//            gz = (double) Math.round(gz * scale)/scale + (double) Math.round(rz * scale)/scale;
//
//            generalR = sqrt(gx*gx+gy*gy+gz*gz);
//
//            // leveling of values
//            regressionFunction = 0.000009*regressionTime;
////            generalR -= regressionFunction;
//            testR = generalR/regressionFunction;
//
//            System.out.println("general Time =" + regressionTime);
//            System.out.println("regression Function = " + regressionFunction);
//            System.out.println("änderung in bezug auf startpunkt = " + generalR);
//            System.out.println("test Regression = " + testR);
//
//            System.out.println(" x werte = " + rx);
//            System.out.println("summe x werte = " + gx);
//            System.out.println(" y werte = " + ry);
//            System.out.println("summe y werte = " + gy);
//            System.out.println(" z werte = " + rz);
//            System.out.println("summe z werte = " + gz);
//        }

//        calV = sqrt(vx*vx+vy*vy+vz*vz);
//        calV = (double) Math.round(calV * scale)/scale;
//        calR = sqrt(rx*rx+ry*ry+rz*rz);
//        calR = (double) Math.round(calR * scale)/scale;
//
//        System.out.println("velocity v = "+calV +"m/s");
//        System.out.println("Distance r = "+calR +"m");
//        System.out.println("-----------------------------------------------");

    }

    private double velocity (double currentVal, double previousVal, double time){

        double velVal = 0;

        double roundCurrentVal, roundPreviousVal;
        roundCurrentVal = Math.round(currentVal * scale) /scale;
        roundPreviousVal = Math.round(previousVal * scale)/ scale;

        velVal = ((roundCurrentVal-roundPreviousVal)*time/1000);
        return (velVal);
    }
    private double distance (double currentVal, double previousVal, double time){
        double disVal = 0;

        currentVal = (double) Math.round(currentVal * scale)/scale;
        previousVal = (double) Math.round(previousVal * scale)/scale;

        disVal =(0.5*(previousVal-currentVal)*((time/1000)*(time/1000)));
        disVal = (double) Math.round(disVal * scale)/scale;
        return (disVal);
    }

}