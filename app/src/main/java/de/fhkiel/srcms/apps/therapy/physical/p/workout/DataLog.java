package de.fhkiel.srcms.apps.therapy.physical.p.workout;

import android.util.Log;

public class DataLog {

    private static String TAG = DataLog.class.getName();
    public String label;
    public long time;
    public Integer i;
    public Enum pleasureState;
    public Enum excitementState;
    public Enum engagementIntentionState;
    public Enum smileState;
    public Enum attentionState;
    public double distance;

    public void animation(String label, long time){
        Log.d("'animation'", label);
        Log.d("'timestamp'", String.valueOf(time));

        this.label = label;
        this.time = time;
    }

    public void logHuman(Integer i, Enum pleasureState, Enum excitementState, Enum engagementIntentionState, Enum smileState, Enum attentionState, double distance) {

        Log.i(TAG, "Human: " + i);
        Log.i(TAG, "Pleasure state: " + pleasureState);
        Log.i(TAG, "Excitement state: " + excitementState);
        Log.i(TAG, "Engagement state: " + engagementIntentionState);
        Log.i(TAG, "Smile state: " + smileState);
        Log.i(TAG, "Attention state: " + attentionState);
        Log.i(TAG, "Distance: " + distance + " meter(s).");

        this.i=i;
        this.pleasureState = pleasureState;
        this.excitementState = excitementState;
        this.engagementIntentionState = engagementIntentionState;
        this. smileState = smileState;
        this.attentionState = attentionState;
        this.distance = distance;
    }
    @Override
    public String toString (){
        return "\n animation:\"{\n" +
                "\t\"label\":" + label + ", \n" +
                "\t\"time\":" + time + ", \n" +
                "\t\"number\":" + i + ", \n" +
                "\t\"pleasureState\":" + pleasureState+ ", \n" +
                "\t\"excitementState\":" + excitementState + ", \n" +
                "\t\"engagementIntentionState\":" + engagementIntentionState + ", \n" +
                "\t\"smileState\":" + smileState + ", \n" +
                "\t\"attentionState\":" + attentionState + ", \n" +
                "\t\"distance\":" + distance + ", \n" +
                "}";
    }
}