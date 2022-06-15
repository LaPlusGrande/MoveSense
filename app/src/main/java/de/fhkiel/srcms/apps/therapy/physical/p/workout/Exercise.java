package de.fhkiel.srcms.apps.therapy.physical.p.workout;

import android.util.Log;

import com.aldebaran.qi.sdk.QiContext;

import de.fhkiel.srcms.apps.therapy.physical.p.workout.actions.PepperAction;
import de.fhkiel.srcms.apps.therapy.physical.p.workout.actions.PepperAnimation;
import de.fhkiel.srcms.apps.therapy.physical.p.workout.actions.PepperSay;

public class Exercise extends PepperAction {

    private static final String TAG = Exercise.class.getName();

    public static HttpClient logging = new HttpClient();


    public static void cancel(){
        PepperAction.abortPepper();
    }

    public static void doExercise (QiContext qiContext, int[] array, int iteration){

    PepperAnimation pepperAnimation = new PepperAnimation();
    int setback = 1;

    PepperAction.resetPepper();
    try {
        for (int count2 = 0; count2 < iteration; count2++) {

            for (int count1 = 0; count1 < array.length; count1++) {

                switch (array[count1]) {

                    case 0:
                        count1 = array.length;
                        if (setback == iteration) {
                            PepperSay.doSay(qiContext, "\\rspd=80\\ das hat Spaß gemacht ich hoffe wir sehen und bald wieder");
                        }
                        else {setback++;}
                    Log.d(TAG,"executed > outro");
                        break;
                    case 1:
                        Log.i(TAG, "executed > start apple picking");
                        PepperSay.doSay(qiContext, "\\rspd=80\\ jetzt pfluecken wir Äpfel. \\pau=200\\ dafür strecken wir abwechselnd unsere Arme in die Luft");
                        waitForSay();
                        PepperSay.doSay(qiContext,"\\rspd=80\\ es geht ein Arm nach dem anderen hoch \\pau=200\\ immer schön abwechselnd\\pau=200\\"+
                                        "\\rspd=80\\ ein Arm nach dem anderen strecken wir in, die Höhe");
                        pepperAnimation.doApplePicking(qiContext);
                        PepperAnimation.doRotation(qiContext);
                        break;
                    case 2:
                        Log.i(TAG, "executed > start arm rotation");
                        PepperSay.doSay(qiContext, "\\rspd=80\\ jetzt lassen wir unsere Arme Kreisen.");
                        waitForSay();
                        PepperSay.doSay(qiContext, "\\rspd=80\\wir heben unsere Arme an \\pau=200\\ und bewegen sie einmal in einem großen Kreis \\pau=200\\ so große,wie ihr könnt \\pau=200\\"+
                                "\\rspd=80\\ ihr macht, das viel besser, als ich \\pau=200\\ Leider sind meine Schultergelenke etwas eingeschränkt");
                        pepperAnimation.doArmRotation(qiContext);
                        PepperAnimation.doRotation(qiContext);
                        break;
                    case 3:
                        Log.i(TAG, "executed > start arm side ");
                        PepperSay.doSay(qiContext,"\\rspd=80\\ jetzt heben wir unsere Arme zur Seite");
                        waitForSay();
                        PepperSay.doSay(qiContext, "\\rspd=80\\ versucht eure Arme so weit es geht seitlich anzuheben \\pau=200\\ und ein T zu bilden\\pau=200\\"+
                                "\\rspd=80\\ wir nehmen sie langsam hoch und wieder runter \\pau=200\\ immer weiter so \\pau=200\\ versucht dabei möglich beide Arme gleichzeitig anzuheben");
                        pepperAnimation.doArmSide(qiContext);
                        PepperAnimation.doRotation(qiContext);
                        break;
                    case 4:
                        Log.i(TAG, "executed > start airboxing ");
                        PepperSay.doSay(qiContext, "\\rspd=80\\ jetzt machen wir eine Faust und boxen nach Vorn");
                        waitForSay();
                        PepperSay.doSay(qiContext, "\\rspd=80\\ mit einem Arm nach dem anderen boxen wir nach vorn \\pau=200\\ immer schön abwechseln \\pau=500\\"+
                                "\\rspd=80\\ erst mit der einen Faust und dann mit der anderen Faust");
                        pepperAnimation.doBoxing(qiContext);
                        PepperAnimation.doRotation(qiContext);
                        break;
                    case 5:
                        Log.i(TAG, "executed > start clapping ");
                        PepperSay.doSay(qiContext,"\\rspd=80\\ jetzt klatschen wir in unsere Hände");
                        waitForSay();
                        PepperSay.doSay(qiContext,"\\rspd=80\\ ihr könnt ganz normal klatschen \\pau=200\\ leider bekomme ich wegen meines dicken Bauch \\pau=200\\ meine Hände nicht ganz zusammen");
                        pepperAnimation.doClapping(qiContext);
                        PepperAnimation.doRotation(qiContext);
                        break;
                    case 6:
                        Log.i(TAG, "executed > start elbow hand ");
                        PepperSay.doSay(qiContext, "\\rspd=80\\ jetzt machen, wir beides zusammen");
                        waitForSay();
                        PepperSay.doSay(qiContext, "\\rspd=80\\ wir öffnen und schließen unsere Händer \\pau=200\\ und gleichzeitig versuchen wir eine Faust zu machen\\pau=200\\"+
                                "\\rspd=80\\ versucht euch auf beide Bewegungen zur selben Zeit zu konzentrieren \\pau=200\\ ich kann das auch nur weil es mir vorgemacht wurde");
                        pepperAnimation.doElbowHand(qiContext);
                        PepperAnimation.doRotation(qiContext);
                        break;
                    case 7:
                        Log.i(TAG, "executed > start elbow stretch ");
                        PepperSay.doSay(qiContext,"\\rspd=80\\ jetzt beugen, und strecken wir unsere Ellenbogen");
                        waitForSay();
                        PepperSay.doSay(qiContext, "\\rspd=80\\  ihr solltet versuchen eure beiden Arme zur selben Zeit anzuwinkeln \\pau=200\\"+
                                "\\rspd=80\\ unsere Arme gehen in einen Rechtenwinkel und wieder zurück");
                        pepperAnimation.doElbowStretch(qiContext);
                        PepperAnimation.doRotation(qiContext);
                        break;
                    case 8:
                        Log.i(TAG, "executed > start follow arms ");
                        PepperSay.doSay(qiContext, "\\rspd=80\\ jetzt versucht mit euren Augen die Bewegung der Hand zu verfolgen\\pau=200\\");
                        waitForSay();
                        PepperSay.doSay(qiContext, "\\rspd=80\\ Unsere Arme gehen in die höhe \\pau=200\\ und unser Augen versuchen sie zu verfolgen \\pau=200\\"+
                                "\\rspd=80\\ unser Kopf bewegt sich nun zusätzlich hoch und runter\\pau=200\\ versucht dies wieder in eurem eigenen Tempo durchzuführen");
                        pepperAnimation.doFollowArm(qiContext);
                        PepperAnimation.doRotation(qiContext);
                        break;
                    case 9:
                        Log.i(TAG, "executed > start lift arms");
                        PepperSay.doSay(qiContext,"\\rspd=80\\ jetzt versucht eure Arme so weit es geht in die Höhe zustrecken\\pau=200\\"+
                                "\\rspd=80\\beim runterkommen versuchen wir uns so weit wie möglich nach vorn zu beugen");
                        waitForSay();
                        PepperSay.doSay(qiContext,"\\rspd=80\\ wir müssen uns so lang wie möglich machen\\pau=200\\ und versuchen nach dem Himmel zu greifen\\pau=200\\"+
                                "\\rspd=80\\ beim vorbeugen machen wir uns so klein wie es geht");
                        pepperAnimation.doLiftArm(qiContext);
                        PepperAnimation.doRotation(qiContext);
                        break;
                    case 10:
                        Log.i(TAG, "executed > start open hand");
                        PepperSay.doSay(qiContext,"\\rspd=80\\ jetzt oeffnen und schließen wir unsere Hände");
                        waitForSay();
                        PepperSay.doSay(qiContext,"\\rspd=80\\ wir öffnen unsrere Hände und schließen sie mit aller Kraft \\pau=200\\"+
                                "\\rspd=80\\und wieder öffnen und schließen \\pau=200\\ öffnen und, schließen");
                        pepperAnimation.doOpenHand(qiContext);
                        PepperAnimation.doRotation(qiContext);
                        break;
                    case 11:
                        Log.i(TAG, "executed > start tend body");
                        PepperSay.doSay(qiContext,"\\rspd=80\\ jetzt bewegen wir unseren Oberkörper von links nach rechts");
                        waitForSay();
                        PepperSay.doSay(qiContext,"\\rspd=80\\ bewegt lediglich euren oberkörper von der einen zur anderen Seite \\pau=200\\"+
                                "\\rspd=80\\ und versucht dabei möglichst nicht vom Stuhl zu fallen \\pau=200\\ wir bewegen uns hin und her \\pau=200\\ hin und her");
                        pepperAnimation.doBendBody(qiContext);
                        PepperAnimation.doRotation(qiContext);
                        break;
                    case 12:
                        Log.i(TAG, "executed > start breath break");
                        PepperSay.doSay(qiContext, "\\rspd=80\\ jetzt dürft ihr eine kleine Pause machen \\pau=200\\ legt dafür die Hände auf den Bauch \\pau=200\\ und versucht die Atmung zu spüren\\pau=500\\"+
                                "\\rspd=80\\ versucht euch einen moment lang zu entspannen \\pau=200\\ atmet ein und wieder aus \\pau=200\\ und wieder tief ein und langsam aus \\pau=200\\"+
                                "\\rspd=80\\ Spürt ihr wie euer Bauch sich bewegt?");
                        pepperAnimation.doBreathbreak(qiContext);
                        PepperAnimation.doRotation(qiContext);
                        break;
                }
                PepperAction.waitForAction();
            }
        }
    }catch (Exception e){
        Log.e("exception in switch ", e.getMessage(), e);
    }

    }
}
