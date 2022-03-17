package de.fhkiel.srcms.apps.therapy.physical.p.actions;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

public class PepperAction {
    public static Future<Void> currentMove = null;
    public static Future<Void> currentSay = null;
    public static boolean cancelled = false;

    public static void abortPepper(){
        cancelled = true;
        if (currentMove != null)
            currentMove.cancel(true);
        if (currentSay != null)
            currentSay.cancel(true);
        currentMove = null;
        currentSay = null;

    }

    public static void sayFuture(Future<Void> say) throws ExecutionException, InterruptedException {
        waitForSay();
        currentSay = say;
    }

    public static void moveFuture(Future<Void> move) throws ExecutionException, InterruptedException {
        waitForMove();
        currentMove = move;
    }

    public static void waitForSay() throws ExecutionException, InterruptedException {
        if (currentSay != null)
            currentSay.get();
        currentSay = null;
    }

    public static void waitForMove() throws ExecutionException, InterruptedException {
        if (currentMove != null)
            currentMove.get();
        currentMove = null;
    }

    public static void waitForAction() throws ExecutionException, InterruptedException {
        waitForMove();
        waitForSay();
    }

    public static void resetPepper(){
        currentMove = null;
        currentSay = null;
        cancelled = false;
    }


}
