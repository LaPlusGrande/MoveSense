package de.fhkiel.srcms.apps.therapy.physical.p.workout.movesens;

import com.google.gson.annotations.SerializedName;

public class AccDataResponse {

    @SerializedName("Body")
    public final Body body;

    public AccDataResponse(Body body) {
        this.body = body;
    }

    public static class Body {
        // timestamp in millisec
        @SerializedName("Timestamp")
        public final long timestamp;

        // the g selected rang is -32767 and 32767, unit is m/s^2
        @SerializedName("ArrayAcc")
        public final Array[] array;

        @SerializedName("Headers")
        public final Headers header;

        public Body(long timestamp, Array[] array, Headers header) {
            this.timestamp = timestamp;
            this.array = array;
            this.header = header;
        }
    }

    public static class Array {
        @SerializedName("x")
        public final double x;

        @SerializedName("y")
        public final double y;

        @SerializedName("z")
        public final double z;

        public Array(double x, double y, double z) {
            this.x = x;
            this.y = y;
            this.z = z;
        }
    }

    public static class Headers {
        @SerializedName("Param0")
        public final int param0;

        public Headers(int param0) {
            this.param0 = param0;
        }
    }
}
