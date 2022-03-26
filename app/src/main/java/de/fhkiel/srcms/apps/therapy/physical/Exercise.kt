package de.fhkiel.srcms.apps.therapy.physical

import android.util.Log
import com.aldebaran.qi.sdk.QiContext
import de.fhkiel.srcms.apps.therapy.physical._actions.PepperAction
import de.fhkiel.srcms.apps.therapy.physical._actions.PepperAnimation
import de.fhkiel.srcms.apps.therapy.physical._actions.PepperSay

class Exercise : PepperAction(){

    fun doExercise(qiContext: QiContext, arr: IntArray, iter: Int){

        var mySay = PepperSay()
        var myAnimation = PepperAnimation()
        var setback : Int = 1

        resetPepper()
        try {
            for (i in 1..iter ) {
                for (item in arr) {
                    when(item){
                        0 -> {
                            if (setback == iter){
                                mySay.doSay(qiContext,"\\rspd=80\\ das hat Spaß gemacht ich hoffe wir sehen und bald wieder" )
                            }else{
                                setback += 1
                            }
                        }
                        1 -> {
                            mySay.doSay(qiContext,"\\rspd=80\\ jetzt pfluecken wir Äpfel. \\pau=200\\ dafür strecken wir abwechselnd unsere Arme in die Luft")
                            waitForSay()
                            mySay.doSay(qiContext,"\\rspd=80\\ es geht ein Arm nach dem anderen hoch \\pau=200\\ immer schön abwechselnd\\pau=200\\" +
                                        "\\rspd=80\\ ein Arm nach dem anderen strecken wir in, die Höhe")
                            myAnimation.doApplePicking(qiContext)
                            myAnimation.doRotation(qiContext)
                        }
                        2 -> {
                            mySay.doSay(qiContext,"\\rspd=80\\ jetzt lassen wir unsere Arme Kreisen.")
                            waitForSay()
                            mySay.doSay(qiContext,"\\rspd=80\\wir heben unsere Arme an \\pau=200\\ und bewegen sie einmal in einem großen Kreis \\pau=200\\ so große,wie ihr könnt \\pau=200\\" +
                                        "\\rspd=80\\ ihr macht, das viel besser, als ich \\pau=200\\ Leider sind meine Schultergelenke etwas eingeschränkt")
                            myAnimation.doArmRotation(qiContext)
                            myAnimation.doRotation(qiContext)
                        }
                        3 -> {
                            mySay.doSay(qiContext,"\\rspd=80\\ jetzt heben wir unsere Arme zur Seite")
                            waitForSay()
                            mySay.doSay(qiContext,"\\rspd=80\\ versucht eure Arme so weit es geht seitlich anzuheben \\pau=200\\ und ein T zu bilden\\pau=200\\" +
                                        "\\rspd=80\\ wir nehmen sie langsam hoch und wieder runter \\pau=200\\ immer weiter so \\pau=200\\ versucht dabei möglich beide Arme gleichzeitig anzuheben")
                            myAnimation.doArmSide(qiContext)
                            myAnimation.doRotation(qiContext)
                        }
                        4 -> {
                            mySay.doSay(qiContext, "\\rspd=80\\ jetzt machen wir eine Faust und boxen nach Vorn")
                            waitForSay()
                            mySay.doSay(qiContext,"\\rspd=80\\ mit einem Arm nach dem anderen boxen wir nach vorn \\pau=200\\ immer schön abwechseln \\pau=500\\" +
                                        "\\rspd=80\\ erst mit der einen Faust und dann mit der anderen Faust")
                            myAnimation.doAirBoxing(qiContext)
                            myAnimation.doRotation(qiContext)
                        }
                        5 -> {
                            mySay.doSay(qiContext,"\\rspd=80\\ jetzt klatschen wir in unsere Hände")
                            waitForSay()
                            mySay.doSay(qiContext,"\\rspd=80\\ ihr könnt ganz normal klatschen \\pau=200\\ leider bekomme ich wegen meines dicken Bauch \\pau=200\\ meine Hände nicht ganz zusammen")
                            myAnimation.doClapping(qiContext)
                            myAnimation.doRotation(qiContext)
                        }
                        6 -> {
                            mySay.doSay(qiContext,"\\rspd=80\\ jetzt machen, wir beides zusammen")
                            waitForSay()
                            mySay.doSay(qiContext,"\\rspd=80\\ wir öffnen und schließen unsere Händer \\pau=200\\ und gleichzeitig versuchen wir eine Faust zu machen\\pau=200\\" +
                                        "\\rspd=80\\ versucht euch auf beide Bewegungen zur selben Zeit zu konzentrieren \\pau=200\\ ich kann das auch nur weil es mir vorgemacht wurde")
                            myAnimation.doElbowHand(qiContext)
                            myAnimation.doRotation(qiContext)
                        }
                        7 -> {
                            mySay.doSay(qiContext, "\\rspd=80\\ jetzt beugen, und strecken wir unsere Ellenbogen")
                            waitForSay()
                            mySay.doSay(qiContext,"\\rspd=80\\  ihr solltet versuchen eure beiden Arme zur selben Zeit anzuwinkeln \\pau=200\\" +
                                        "\\rspd=80\\ unsere Arme gehen in einen Rechtenwinkel und wieder zurück")
                            myAnimation.doElbowStretch(qiContext)
                            myAnimation.doRotation(qiContext)
                        }
                        8 -> {
                            mySay.doSay(qiContext, "\\rspd=80\\ jetzt versucht mit euren Augen die Bewegung der Hand zu verfolgen\\pau=200\\")
                            waitForSay()
                            mySay.doSay(qiContext,
                                "\\rspd=80\\ Unsere Arme gehen in die höhe \\pau=200\\ und unser Augen versuchen sie zu verfolgen \\pau=200\\" +
                                        "\\rspd=80\\ unser Kopf bewegt sich nun zusätzlich hoch und runter\\pau=200\\ versucht dies wieder in eurem eigenen Tempo durchzuführen" )
                            myAnimation.doFollowArms(qiContext)
                            myAnimation.doRotation(qiContext)
                        }
                        9 -> {
                            mySay.doSay(qiContext,"\\rspd=80\\ jetzt versucht eure Arme so weit es geht in die Höhe zustrecken\\pau=200\\" +
                                        "\\rspd=80\\beim runterkommen versuchen wir uns so weit wie möglich nach vorn zu beugen")
                            waitForSay()
                            mySay.doSay(qiContext,"\\rspd=80\\ wir müssen uns so lang wie möglich machen\\pau=200\\ und versuchen nach dem Himmel zu greifen\\pau=200\\" +
                                        "\\rspd=80\\ beim vorbeugen machen wir uns so klein wie es geht")
                            myAnimation.doLiftArms(qiContext)
                            myAnimation.doRotation(qiContext)
                        }
                        10 -> {
                            mySay.doSay(qiContext,"\\rspd=80\\ jetzt oeffnen und schließen wir unsere Hände")
                            waitForSay()
                            mySay.doSay(qiContext,"\\rspd=80\\ wir öffnen unsrere Hände und schließen sie mit aller Kraft \\pau=200\\" +
                                        "\\rspd=80\\und wieder öffnen und schließen \\pau=200\\ öffnen und, schließen")
                            myAnimation.doOpenHand(qiContext)
                            myAnimation.doRotation(qiContext)
                        }
                        11 -> {
                            mySay.doSay(qiContext,"\\rspd=80\\ jetzt bewegen wir unseren Oberkörper von links nach rechts")
                            waitForSay()
                            mySay.doSay(qiContext,"\\rspd=80\\ bewegt lediglich euren oberkörper von der einen zur anderen Seite \\pau=200\\" +
                                        "\\rspd=80\\ und versucht dabei möglichst nicht vom Stuhl zu fallen \\pau=200\\ wir bewegen uns hin und her \\pau=200\\ hin und her")
                            myAnimation.doTendBody(qiContext)
                            myAnimation.doRotation(qiContext)
                        }
                        12 -> {
                            mySay.doSay(qiContext,"\\rspd=80\\ jetzt dürft ihr eine kleine Pause machen \\pau=200\\ legt dafür die Hände auf den Bauch \\pau=200\\ und versucht die Atmung zu spüren\\pau=500\\" +
                                        "\\rspd=80\\ versucht euch einen moment lang zu entspannen \\pau=200\\ atmet ein und wieder aus \\pau=200\\ und wieder tief ein und langsam aus \\pau=200\\" +
                                        "\\rspd=80\\ Spürt ihr wie euer Bauch sich bewegt?")
                            myAnimation.doBreathBreak(qiContext)
                            myAnimation.doRotation(qiContext)
                        }
                    }
//                    waitForAction()
                }
            }
        }catch (e : Exception){
            Log.e("exeption in switch",e.message,e)
        }
    }
}
