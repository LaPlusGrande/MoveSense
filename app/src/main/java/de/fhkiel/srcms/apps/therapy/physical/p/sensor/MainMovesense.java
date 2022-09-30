package de.fhkiel.srcms.apps.therapy.physical.p.sensor;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.google.gson.Gson;
import com.movesense.mds.Mds;
import com.movesense.mds.MdsConnectionListener;
import com.movesense.mds.MdsException;
import com.movesense.mds.MdsNotificationListener;
import com.movesense.mds.MdsSubscription;
import com.opencsv.CSVWriter;
import com.polidea.rxandroidble2.RxBleClient;
import com.polidea.rxandroidble2.RxBleDevice;
import com.polidea.rxandroidble2.scan.ScanSettings;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import de.fhkiel.srcms.apps.therapy.physical.p.workout.R;
import io.reactivex.disposables.Disposable;

public class MainMovesense extends AppCompatActivity implements AdapterView.OnItemLongClickListener, AdapterView.OnItemClickListener  {
    private static final String LOG_TAG = MainMovesense.class.getSimpleName();
    private static final int MY_PERMISSIONS_REQUEST_LOCATION = 1;

    private  Mds mds;
//    public static final String URI_CONNECTEDDEVICES = "suunto://MDS/ConnectedDevices";
    public static final String URI_EVENTLISTENER = "suunto://MDS/EventListener";
//    public static final String SCHEME_PREFIX = "suunto://";

    // BleClient singleton
    static private RxBleClient mBleClient;

    private ArrayList<MyScanResult> mScanResArrayList = new ArrayList<>();
    ArrayAdapter<MyScanResult> mScanResArrayAdapter;

    private MdsSubscription mdsSubscription;
    private String subscribedDeviceSerial;

    List<String[]> list = new ArrayList<>();
    private static final DecimalFormat df = new DecimalFormat("0.00");
    Calendar c = Calendar.getInstance();
    int sec = c.get(Calendar.SECOND);
    int min = c.get(Calendar.MINUTE);
    int hour = c.get(Calendar.HOUR);
    int day = c.get(Calendar.DAY_OF_MONTH);
    int month = c.get(Calendar.MONTH);
    int year = c.get(Calendar.YEAR);
    String date = year + "-" + (month+1) + "-" + day + "-" + hour + "-" + min + "-" + sec;

    DataCalculation dataCalculation = new DataCalculation();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movesens);

        // Init Scan UI
        // UI
        ListView mScanResultListView = findViewById(R.id.listScanResult);
        mScanResArrayAdapter = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1, mScanResArrayList);
        mScanResultListView.setAdapter(mScanResArrayAdapter);
        mScanResultListView.setOnItemLongClickListener(this);
        mScanResultListView.setOnItemClickListener(this);

        // Make sure we have all the permissions this app needs
        requestNeededPermissions();

        // Initialize Movesense MDS library
        initMds();
    }

    private RxBleClient getBleClient() {
        // Init RxAndroidBle (Ble helper library) if not yet initialized
        if (mBleClient == null)
        {
            mBleClient = RxBleClient.create(this);
        }
        return mBleClient;
    }

    private void initMds() {
        mds = Mds.builder().build(this);
    }

    void requestNeededPermissions()
    {
        // Here, thisActivity is the current activity
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_COARSE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {

            // No explanation needed, we can request the permission.
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.ACCESS_COARSE_LOCATION},
                    MY_PERMISSIONS_REQUEST_LOCATION);

        }
    }

    Disposable mScanSubscription;
    public void onScanClicked(View view) {
        findViewById(R.id.buttonScan).setVisibility(View.GONE);
        findViewById(R.id.buttonScanStop).setVisibility(View.VISIBLE);
        // Start with empty list
        mScanResArrayList.clear();
        mScanResArrayAdapter.notifyDataSetChanged();

        mScanSubscription = getBleClient().scanBleDevices(
                        new ScanSettings.Builder()
                                // .setScanMode(ScanSettings.SCAN_MODE_LOW_LATENCY) // change if needed
                                // .setCallbackType(ScanSettings.CALLBACK_TYPE_ALL_MATCHES) // change if needed
                                .build()
                        // add filters if needed
                )
                .subscribe(
                        scanResult -> {
                            Log.d(LOG_TAG,"scanResult: " + scanResult);

                            // Process scan result here. filter movesense devices.
                            if (scanResult.getBleDevice()!=null &&
                                    scanResult.getBleDevice().getName() != null &&
                                    scanResult.getBleDevice().getName().startsWith("Movesense")) {

                                // replace if exists already, add otherwise
                                MyScanResult msr = new MyScanResult(scanResult);
                                if (mScanResArrayList.contains(msr))
                                    mScanResArrayList.set(mScanResArrayList.indexOf(msr), msr);
                                else
                                    mScanResArrayList.add(0, msr);

                                mScanResArrayAdapter.notifyDataSetChanged();
                            }
                        },
                        throwable -> {
                            Log.e(LOG_TAG,"scan error: " + throwable);
                            onScanStopClicked(null);
                        }
                );
    }

    public void onScanStopClicked(View view){
        if(mScanSubscription != null){
            mScanSubscription.dispose();
            mScanSubscription = null;
        }
        findViewById(R.id.buttonScan).setVisibility(View.VISIBLE);
        findViewById(R.id.buttonScanStop).setVisibility(View.GONE);
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {

        if (position < 0 || position >= mScanResArrayList.size())
            return;

        MyScanResult device = mScanResArrayList.get(position);
        if (!device.isConnected()) {
            // Stop scanning
            onScanStopClicked(null);

            // And connect to the device
            connectBLEDevice(device);
        }
        else {
            // Device is connected, trigger showing /Info
            subscribeToSensor(device.connectedSerial);

        }
    }

    public void subscribeToSensor(String connectedSerial) {
        // Clean up existing subscription (if there is one)
        if (mdsSubscription != null) {
            unsubscribe();
        }

        // Build JSON doc that describes what resource and device to subscribe
        // Here we subscribe to 13 hertz accelerometer data
        StringBuilder sb = new StringBuilder();
        // Sensor subscription
        String URI_MEAS_ACC_13 = "/Meas/Acc/13";
        String strContract = sb.append("{\"Uri\": \"").append(connectedSerial).append(URI_MEAS_ACC_13).append("\"}").toString();
        Log.d(LOG_TAG, strContract);
        final View sensorUI = findViewById(R.id.sensorUI);

        subscribedDeviceSerial = connectedSerial;

        mdsSubscription = mds.builder().build(this).subscribe(URI_EVENTLISTENER,
                strContract, new MdsNotificationListener() {
                    @Override
                    public void onNotification(String data) {
                        Log.d(LOG_TAG, "onNotification(): " + data);

                        // If UI not enabled, do it now
                        if (sensorUI.getVisibility() == View.GONE) {
                            sensorUI.setVisibility(View.VISIBLE);
                        }

                        AccDataResponse accResponse = new Gson().fromJson(data, AccDataResponse.class);
                        if (accResponse != null && accResponse.body.array.length > 0) {

                            if (accResponse.body.array[0].y >= -9.82 || accResponse.body.array[0].y <= -7.82){
                                Thread dataThread = new Thread(() -> {
                                    dataCalculation.accelData(data);
                                });
                                dataThread.start();
                            }else{
                                Toast.makeText(getApplicationContext(),R.string.startpos_text,Toast.LENGTH_LONG).show();
                            }

                            getCsvData(accResponse.body.array[0].x,accResponse.body.array[0].y,accResponse.body.array[0].z, accResponse.body.timestamp);

                                String accStr =
                                    String.format("%.02f, %.02f, %.02f", accResponse.body.array[0].x, accResponse.body.array[0].y, accResponse.body.array[0].z);

                            ((TextView)findViewById(R.id.sensorMsg)).setText(accStr);

                            }
                    }

                    @Override
                    public void onError(MdsException error) {
                        Log.e(LOG_TAG, "subscription onError(): ", error);
                        unsubscribe();
                    }
                });
    }

    @Override
    public boolean onItemLongClick(AdapterView<?> adapterView, View view, int position, long id) {
        if (position < 0 || position >= mScanResArrayList.size())
            return false;

        MyScanResult device = mScanResArrayList.get(position);

        // unsubscribe if there
        Log.d(LOG_TAG, "onItemLongClick, " + device.connectedSerial + " vs " + subscribedDeviceSerial);
        if (device.connectedSerial.equals(subscribedDeviceSerial))
            unsubscribe();

        Log.i(LOG_TAG, "Disconnecting from BLE device: " + device.macAddress);
        mds.disconnect(device.macAddress);

        return true;
    }

    private void connectBLEDevice(MyScanResult device) {
        RxBleDevice bleDevice = getBleClient().getBleDevice(device.macAddress);

        Log.i(LOG_TAG, "Connecting to BLE device: " + bleDevice.getMacAddress());
        mds.connect(bleDevice.getMacAddress(), new MdsConnectionListener() {

            @Override
            public void onConnect(String s) {
                Log.d(LOG_TAG, "onConnect:" + s);
            }

            @Override
            public void onConnectionComplete(String macAddress, String serial) {
                for (MyScanResult sr : mScanResArrayList) {
                    if (sr.macAddress.equalsIgnoreCase(macAddress)) {
                        sr.markConnected(serial);
                        break;
                    }
                }
                mScanResArrayAdapter.notifyDataSetChanged();
            }

            @Override
            public void onError(MdsException e) {
                Log.e(LOG_TAG, "onError:" + e);

                showConnectionError(e);
            }

            @Override
            public void onDisconnect(String bleAddress) {

                Log.d(LOG_TAG, "onDisconnect: " + bleAddress);
                for (MyScanResult sr : mScanResArrayList) {
                    if (bleAddress.equals(sr.macAddress))
                    {
                        // unsubscribe if was subscribed
                        if (sr.connectedSerial != null && sr.connectedSerial.equals(subscribedDeviceSerial))
                            unsubscribe();

                        sr.markDisconnected();
                    }
                }
                mScanResArrayAdapter.notifyDataSetChanged();
            }
        });
    }

    private void showConnectionError(MdsException e) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this)
                .setTitle("Connection Error:")
                .setMessage(e.getMessage());

        builder.create().show();
    }

    public void unsubscribe() {
        if (mdsSubscription != null) {
            mdsSubscription.unsubscribe();
            mdsSubscription = null;
        }

        subscribedDeviceSerial = null;

        // If UI not invisible, do it now
        final View sensorUI = findViewById(R.id.sensorUI);
        if (sensorUI.getVisibility() != View.GONE)
            sensorUI.setVisibility(View.GONE);

    }

    public void onUnsubscribeClicked(View view) {
        findViewById(R.id.buttonStart).setVisibility(View.VISIBLE);
        findViewById(R.id.buttonStop).setVisibility(View.GONE);
        unsubscribe();
    }

    public void toCSV(){
        if(ActivityCompat.checkSelfPermission(MainMovesense.this,
                Manifest.permission.WRITE_EXTERNAL_STORAGE)!= PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(
                    MainMovesense.this,new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},0);
        }else {
            try {
                String[] header = {"","x","y","z"};
                list.add(0,header);
                List<String[]> csvFile = list;
                File file = new File("/sdcard");
                file.mkdirs();
                String csv = "/sdcard/" + date + ".csv";
                CSVWriter csvWriter = new CSVWriter(new FileWriter(csv,true));
                csvWriter.writeAll(csvFile);
                csvWriter.close();
            } catch (IOException e) {
                Log.e("An error occurred :", String.valueOf(e));
                e.printStackTrace();}
        }
    }

    private void getCsvData( double x, double y, double z, long time) {

        time = time/1000;

        String stringX = df.format(x);
        stringX = stringX.replace(".",",");
        String stringY = df.format(y);
        stringY = stringY.replace(".",",");
        String stringZ = df.format(z);
        stringZ = stringZ.replace(".",",");
        String stringTime = df.format(time);
        stringTime = stringTime.replace(".",",");

        String[] row = {stringTime,stringX, stringY, stringZ};
        list.add(row);

    }

    public void onStartClicked(View view) {

        findViewById(R.id.buttonStart).setVisibility(View.GONE);
        findViewById(R.id.buttonStop).setVisibility(View.VISIBLE);

        list.clear();

    }

    public void onStopClicked(View view) {
        findViewById(R.id.buttonStart).setVisibility(View.VISIBLE);
        findViewById(R.id.buttonStop).setVisibility(View.GONE);
        toCSV();
    }
}