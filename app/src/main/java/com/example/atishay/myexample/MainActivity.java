package com.example.atishay.myexample;

import android.Manifest;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentSender;
import android.location.Location;
import android.location.LocationManager;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.atishay.myexample.Interface.CallMe;
import com.example.atishay.myexample.Interface.FingureView;
import com.example.atishay.myexample.presenter.MyPresenter;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.LocationSettingsRequest;
import com.google.android.gms.location.LocationSettingsResult;
import com.google.android.gms.location.LocationSettingsStatusCodes;
import com.prof.rssparser.Parser;
import com.scottyab.aescrypt.AESCrypt;

import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.security.GeneralSecurityException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.spec.AlgorithmParameterSpec;
import java.util.ArrayList;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import static com.scottyab.aescrypt.AESCrypt.DEBUG_LOG_ENABLED;

public class MainActivity extends AppCompatActivity implements CallMe,FingureView,ActivityCompat.OnRequestPermissionsResultCallback,
        PermissionUtils.PermissionResultCallback,GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener {

    Button button1,button2;

    static final String ACTION_RDINFO = "in.gov.uidai.rdservice.fp.INFO";
    static final int RDINFO_REQUEST = 1;  // The request code
    static final String ACTION_RDCAPTURE = "in.gov.uidai.rdservice.fp.CAPTURE";
    static final int RDCAPTURE_REQUEST = 2;  // The request code

    Parser parser;

    private MyPresenter myPresenter;

    private ProgressDialog progressDialog;
    ArrayList<String> permissions=new ArrayList<>();
    PermissionUtils permissionUtils;

    boolean isPermissionGranted;
    private final static int PLAY_SERVICES_REQUEST = 1000;
    private final static int REQUEST_CHECK_SETTINGS = 2000;

    private Location mLastLocation;

    // Google client to interact with Google API

    private GoogleApiClient mGoogleApiClient;

    double latitude;
    double longitude;
    Context context = MainActivity.this;

 private LocationManager locationManager ;
    boolean GpsStatus ;

    String Latlang;

    private static final String AES_MODE = "AES/CBC/PKCS7Padding";
    private static final String CHARSET = "UTF-8";

    //AESCrypt-ObjC uses SHA-256 (and so a 256-bit key)
    private static final String HASH_ALGORITHM = "SHA-256";

    //AESCrypt-ObjC uses blank IV (not the best security, but the aim here is compatibility)
    byte[] ivBytes ;

    //togglable log option (please turn off in live!)
    public static boolean DEBUG_LOG_ENABLED = false;

    SecretKeySpec secretKeySpec,getSecretKeySpec1 ;

    byte[] b;

    String mg = "8040804080408080";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        permissionUtils=new PermissionUtils(this);

        permissions.add(Manifest.permission.ACCESS_FINE_LOCATION);
        permissions.add(Manifest.permission.ACCESS_COARSE_LOCATION);

        permissionUtils.check_permission(permissions,"Need GPS permission for getting your location",1);


        button1 = findViewById(R.id.b1);

        button2 = findViewById(R.id.b2);
        String t = "com.secugen.rdservice";

        progressDialog = new ProgressDialog(this);

        myPresenter = new MyPresenter(this,this);

       getSecretKeySpec1 = generateKey("8080808080808080");


        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                b = Constant.MESSAGE.getBytes(Charset.forName("UTF-8"));


                //   myPresenter.gefing(a);

                //  progressDialog.show();

                encrypt(b);


                if(Build.VERSION.SDK_INT >= 23) {

                    if (isPermissionGranted == true) {

                        getLocation();


                        if (mLastLocation != null) {
                            latitude = mLastLocation.getLatitude();
                            longitude = mLastLocation.getLongitude();

                            Log.d("Lat",String.valueOf(latitude));
                            Log.d("Lan",String.valueOf(longitude));

                            Latlang = String.valueOf(latitude)+","+String.valueOf(longitude);

                            Log.d("LatLan",Latlang);

                            Intent sendIntent = new Intent();
                            sendIntent.setAction(ACTION_RDINFO);

                            if (sendIntent.resolveActivity(getPackageManager()) != null) {

                                startActivityForResult(sendIntent, RDINFO_REQUEST);
                                progressDialog.show();

                            } else
                                {

                                Toast.makeText(MainActivity.this, "please install the given apk!", Toast.LENGTH_SHORT).show();
                            }

                        }

                    }

                    else {

                        permissionUtils.check_permission(permissions,"Need GPS permission for getting your location",1);

                    }
                }
                    else {

                        CheckGpsStatus();

                        if(GpsStatus == true){

                            getLocation();

                            if (mLastLocation != null) {
                                latitude = mLastLocation.getLatitude();
                                longitude = mLastLocation.getLongitude();


                                Latlang = String.valueOf(latitude)+","+String.valueOf(longitude);

                                Log.d("LatLan",Latlang);

                                Intent sendIntent = new Intent();
                                sendIntent.setAction(ACTION_RDINFO);

                                if (sendIntent.resolveActivity(getPackageManager()) != null) {

                                    startActivityForResult(sendIntent, RDINFO_REQUEST);
                                    progressDialog.show();

                                } else {
                                    Toast.makeText(MainActivity.this, "please install the given apk!", Toast.LENGTH_SHORT).show();
                                }

                            }

                        }
                        else {

                            permissionUtils.check_permission(permissions,"Need GPS permission for getting your location",1);

                            Toast.makeText(MainActivity.this,"Please turn on the gps",Toast.LENGTH_SHORT).show();

                        }


                    System.out.println("lat:"+latitude);

                    System.out.println("lan:"+longitude);


                }

            }
        });



        if (checkPlayServices()) {

            // Building the GoogleApi client
            buildGoogleApiClient();
        }

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent sendIntent = new Intent();
                sendIntent.setAction(ACTION_RDCAPTURE);
                sendIntent.putExtra("PID_OPTIONS","<PidOptions ver=\"1.0\"><Opts fCount=\"1\" fType=\"0\" format=\"0\" timeout=\"10000\" pidVer=\"2.0\" posh=\"LEFT_THUMB\"/></PidOptions>");
                startActivityForResult(sendIntent,RDCAPTURE_REQUEST);
            }
        });


    }


    private SecretKeySpec  generateKey(final String password){

        try {

            final MessageDigest digest = MessageDigest.getInstance(HASH_ALGORITHM);

            byte[] bytes = new byte[32];

             bytes  = password.getBytes("UTF-8");

            ivBytes = mg.getBytes("UTF-8");

            digest.update(bytes, 0, bytes.length);
            byte[] key = digest.digest();

            //   Log.d("SHA-256 key ", key);

            secretKeySpec = new SecretKeySpec(key, "AES");

        }
        catch (Exception e){


        }

        return secretKeySpec;

    }

    private void getLocation() {

        if(Build.VERSION.SDK_INT >= 23) {

            if (isPermissionGranted) {

                try {
                    mLastLocation = LocationServices.FusedLocationApi
                            .getLastLocation(mGoogleApiClient);
                } catch (SecurityException e) {
                    e.printStackTrace();
                }

            }
        }
            else {

                try {
                    mLastLocation = LocationServices.FusedLocationApi
                            .getLastLocation(mGoogleApiClient);
                } catch (SecurityException e) {
                    e.printStackTrace();
                }
            }


    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == RDINFO_REQUEST) {
            // Make sure the request was successful
            if (resultCode == RESULT_OK) {

                String a = data.getStringExtra("DEVICE_INFO");
        /*        AlertDialog alertDialog = new AlertDialog.Builder(MainActivity.this).create();
                alertDialog.setTitle("Return from RD");
                alertDialog.setMessage(a);
                alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        });
                alertDialog.show();
*/



                        myPresenter.getid(a);

              /*String test = null;

                JSONObject jsonObj = null;
                try {
                    jsonObj = XML.toJSONObject(a);
                } catch (JSONException e) {
                    Log.e("JSON exception", e.getMessage());
                    e.printStackTrace();
                }


                try {
                    JSONObject json1 = jsonObj.getJSONObject("DeviceInfo");
                    JSONObject json2 = json1.getJSONObject("additional_info");

                    JSONArray jsonArray = json2.getJSONArray("Param");

                    for (int i = 1; i <= 1; i++) {

                        JSONObject c = jsonArray.getJSONObject(1);

                        String name = c.getString("name");
                        String value = c.getString("value");

                        Log.d("My", name);
                        Log.d("My1", value);

                        Toast.makeText(MainActivity.this,value,Toast.LENGTH_SHORT).show();


                    }

                        //  test = (String) json2.get("rdsVer");
                } catch (JSONException e) {
                    e.printStackTrace();
                }
*/

//                Log.d("XML", test);

  //              Log.d("JSON", jsonObj.toString());

            }
        }

        if (requestCode == RDCAPTURE_REQUEST) {
            // Make sure the request was successful
            if (resultCode == RESULT_OK) {

                String a = data.getStringExtra("PID_DATA");

                b = Constant.MESSAGE.getBytes(Charset.forName("UTF-8"));


                //   myPresenter.gefing(a);

              //  progressDialog.show();

                encrypt(b);

             /*   AlertDialog alertDialog = new AlertDialog.Builder(MainActivity.this).create();
                alertDialog.setTitle("Return from RD");
                alertDialog.setMessage(a.replaceAll(">\\s*<", "><"));
                alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        });
                alertDialog.show();

*/


            }


                //      System.out.println("Finger:"+ a);

            }


            if(resultCode == Activity.RESULT_OK) {
                // All required changes were successfully made

                Toast.makeText(context,"ok",Toast.LENGTH_SHORT).show();
                getLocation();


            }

            if(resultCode == Activity.RESULT_CANCELED) {
                // All required changes were successfully made

                Toast.makeText(context,"cancel",Toast.LENGTH_SHORT).show();

                buildGoogleApiClient();

            //    permissionUtils.check_permission(permissions,"Need GPS permission for getting your location",1);


            }

    }



   public void encrypt( byte[] msg){

       try {


           byte[] messageAfterDecrypt = AESCrypt.encrypt(getSecretKeySpec1,ivBytes, msg);


           String str = Base64.encodeToString(messageAfterDecrypt,1);


           AlertDialog alertDialog = new AlertDialog.Builder(MainActivity.this).create();
           alertDialog.setTitle("Return from RD");
           alertDialog.setMessage(str);
           alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                   new DialogInterface.OnClickListener() {
                       public void onClick(DialogInterface dialog, int which) {
                           dialog.dismiss();
                       }
                   });
           alertDialog.show();


       }catch (GeneralSecurityException e){
           //handle error - could be due to incorrect password or tampered encryptedMsg
       }


   }


    @Override
    public void showFErrorMeassage() {

    }

    @Override
    public void showFLoginSuccessMsg(String msg) {


        if(msg.equals("")){

            Toast.makeText(MainActivity.this,"not connected",Toast.LENGTH_SHORT).show();

        }
        else {

            Toast.makeText(MainActivity.this,msg,Toast.LENGTH_SHORT).show();

            Intent sendIntent = new Intent();
            sendIntent.setAction(ACTION_RDCAPTURE);
            sendIntent.putExtra("PID_OPTIONS","<PidOptions ver=\"1.0\"><Opts fCount=\"1\" fType=\"0\" format=\"0\" timeout=\"10000\" pidVer=\"2.0\" posh=\"LEFT_THUMB\"/></PidOptions>");
            startActivityForResult(sendIntent,RDCAPTURE_REQUEST);


        }



    }

    @Override
    public void showFINGErrorMeassage() {


    }

    @Override
    public void showFingSuccessMsg(String errcode, String errinfo) {


        Toast.makeText(MainActivity.this,errcode,Toast.LENGTH_SHORT).show();

        Toast.makeText(MainActivity.this,errinfo,Toast.LENGTH_SHORT).show();


    }

    @Override
    public void showprogress() {

        progressDialog.show();
    }

    @Override
    public void hideprogress() {

        progressDialog.dismiss();
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        // redirects to utils
        permissionUtils.onRequestPermissionsResult(requestCode,permissions,grantResults);

    }


    @Override
    public void PermissionGranted(int request_code) {

        isPermissionGranted = true;

    }

    @Override
    public void PartialPermissionGranted(int request_code, ArrayList<String> granted_permissions) {

        isPermissionGranted = false;

    }

    @Override
    public void PermissionDenied(int request_code) {

        isPermissionGranted = false;

        Toast.makeText(MainActivity.this,String.valueOf(request_code),Toast.LENGTH_SHORT).show();

    }

    @Override
    public void NeverAskAgain(int request_code) {

        isPermissionGranted = false;


    }

    private boolean checkPlayServices() {

        GoogleApiAvailability googleApiAvailability = GoogleApiAvailability.getInstance();

        int resultCode = googleApiAvailability.isGooglePlayServicesAvailable(this);

        if (resultCode != ConnectionResult.SUCCESS) {
            if (googleApiAvailability.isUserResolvableError(resultCode)) {
                googleApiAvailability.getErrorDialog(this,resultCode,
                        PLAY_SERVICES_REQUEST).show();
            } else {
                Toast.makeText(getApplicationContext(),
                        "This device is not supported.", Toast.LENGTH_LONG)
                        .show();
                finish();
            }
            return false;
        }
        return true;
    }


    protected synchronized void buildGoogleApiClient() {
        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(LocationServices.API).build();

        mGoogleApiClient.connect();

        LocationRequest mLocationRequest = new LocationRequest();
        mLocationRequest.setInterval(10000);
        mLocationRequest.setFastestInterval(5000);
        mLocationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);

        LocationSettingsRequest.Builder builder = new LocationSettingsRequest.Builder()
                .addLocationRequest(mLocationRequest);

        PendingResult<LocationSettingsResult> result =
                LocationServices.SettingsApi.checkLocationSettings(mGoogleApiClient, builder.build());

        result.setResultCallback(new ResultCallback<LocationSettingsResult>() {
            @Override
            public void onResult(LocationSettingsResult locationSettingsResult) {

                final Status status = locationSettingsResult.getStatus();

                switch (status.getStatusCode()) {
                    case LocationSettingsStatusCodes.SUCCESS:
                        // All location settings are satisfied. The client can initialize location requests here
                        getLocation();

                        break;
                    case LocationSettingsStatusCodes.RESOLUTION_REQUIRED:
                        try {
                            // Show the dialog by calling startResolutionForResult(),
                            // and check the result in onActivityResult().
                            status.startResolutionForResult(MainActivity.this, REQUEST_CHECK_SETTINGS);
                       //     Toast.makeText(context,"yes",Toast.LENGTH_SHORT).show();

                        } catch (IntentSender.SendIntentException e) {
                            // Ignore the error.
                        }
                        break;
                    case LocationSettingsStatusCodes.SETTINGS_CHANGE_UNAVAILABLE:
                        break;
                }
            }
        });


    }

    @Override
    public void onConnected(@Nullable Bundle bundle) {

    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }


    public void CheckGpsStatus(){

        locationManager = (LocationManager)this.getSystemService(Context.LOCATION_SERVICE);

        GpsStatus = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
    }


}
