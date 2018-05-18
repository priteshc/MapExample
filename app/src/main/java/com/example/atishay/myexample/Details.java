package com.example.atishay.myexample;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.pm.PackageManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.github.kayvannj.permission_utils.Func2;
import com.github.kayvannj.permission_utils.PermissionUtil;
import com.thekhaeng.pushdownanim.PushDownAnim;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import static android.Manifest.permission.WRITE_CONTACTS;
import static android.Manifest.permission.WRITE_EXTERNAL_STORAGE;

/**
 * Created by Atishay on 14-03-2018.
 */

public class Details extends AppCompatActivity {

    private Button trans1;
    private ProgressDialog progressDialog;

   String apiUrl = "http://atishay.co.in/android/AndroidAPI/MoneyTransferValidate?Username=phoenix&Password=atishay2000&Type=6&Reg=9769363548$&otp=&benef=ramakant$ANDB0000001$27870100010669$1583710703$04052018105300$&pay=100$IMPS";

    String apiUrl1 = "http://mobileappdatabase.in/demo/smartnews/app_dashboard/jsonUrl/single-article.php?article-id=71";

    private PermissionUtil.PermissionRequestObject mBothPermissionRequest;
    private static final int REQUEST_CODE_BOTH = 3;

    public static final String WRITE_EXTERNAL_STORAGE = Manifest.permission.WRITE_EXTERNAL_STORAGE;
    public static final String WRITE_CONTACTS = Manifest.permission.WRITE_CONTACTS;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.benificiary_details);

        trans1 = findViewById(R.id.trans);

        PushDownAnim.setPushDownAnimTo(trans1)
                .setScale(PushDownAnim.MODE_STATIC_DP, 2)
                .setDurationPush(PushDownAnim.DEFAULT_PUSH_DURATION)
                .setDurationRelease(PushDownAnim.DEFAULT_RELEASE_DURATION)
                .setInterpolatorPush(PushDownAnim.DEFAULT_INTERPOLATOR)
                .setInterpolatorRelease(PushDownAnim.DEFAULT_INTERPOLATOR);

        progressDialog = new ProgressDialog(Details.this);
        progressDialog.setMessage("Please Wait");
        progressDialog.setCancelable(false);

        trans1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                int result = ContextCompat.checkSelfPermission(Details.this, Manifest.permission.WRITE_EXTERNAL_STORAGE);

                Toast.makeText(Details.this,String.valueOf(result),Toast.LENGTH_SHORT).show();


                mBothPermissionRequest = PermissionUtil.with(Details.this).request(WRITE_EXTERNAL_STORAGE, WRITE_CONTACTS).onResult(new Func2() {
                    @Override protected void call(int requestCode, String[] permissions, int[] grantResults) {
                        for (int i = 0; i < permissions.length; i++) {
                            if (grantResults[i] == PackageManager.PERMISSION_GRANTED) {
                                doOnPermissionGranted(permissions[i]);
                            } else {
                                doOnPermissionDenied(permissions[i]);
                            }
                        }
                    }
                }).ask(REQUEST_CODE_BOTH);


                //  Toast.makeText(Details.this,"Click",Toast.LENGTH_SHORT).show();

              /*  new GetMethodDemo().execute();

                progressDialog.show();*/
            }
        });

    }

    private void doOnPermissionDenied(String permission) {
       // updateStatus(permission + " Permission Denied or is on \"Do Not SHow Again\"");
    }

    private void doOnPermissionGranted(String permission) {
     //   updateStatus(permission + " Permission Granted");
    }



    @Override public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {

        if (mBothPermissionRequest != null) {

            mBothPermissionRequest.onRequestPermissionsResult(requestCode, permissions, grantResults);
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }


    public class GetMethodDemo extends AsyncTask<String , Void ,String> {
        String server_response;

        @Override
        protected String doInBackground(String... strings) {

            URL url;
          //  HttpURLConnection urlConnection = null;

            try {

                url = new URL(apiUrl);



                HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();

                urlConnection.setConnectTimeout(15000 /* milliseconds */);
                urlConnection.setReadTimeout(15000 /* milliseconds */);
                urlConnection.setRequestMethod("GET");
                //urlConnection.setDoInput(true);
                //urlConnection.setDoOutput(true);

                urlConnection.connect();

                urlConnection.setReadTimeout(15000 /* milliseconds */);
                urlConnection.setConnectTimeout(15000 /* milliseconds */);

                int responseCode = urlConnection.getResponseCode();

                Log.d("CatalogClient", String.valueOf(responseCode));

                if(responseCode == HttpURLConnection.HTTP_OK){
              //      server_response = readStream(urlConnection.getInputStream());

                    server_response = "Yes";
                    Log.d("CatalogClient", server_response);
                    urlConnection.disconnect();
                }
                else {

                    urlConnection.disconnect();

                }

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }


            return null;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);

            progressDialog.dismiss();
            Log.e("Response", "" + server_response);


        }
    }
/*
    public class MyAsyncTasks extends AsyncTask<String, String, String> {


        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            // display a progress dialog for good user experiance

            progressDialog.show();
        }

        @Override
        protected String doInBackground(String... params) {

            // implement API in background and store the response in current variable
            String current = "";
            try {
                URL url;
                HttpURLConnection urlConnection = null;
                try {
                    url = new URL(apiUrl);

                    urlConnection = (HttpURLConnection) url
                            .openConnection();

                    InputStream in = urlConnection.getInputStream();

                    InputStreamReader isw = new InputStreamReader(in);

                    int data = isw.read();
                    while (data != -1) {
                        current += (char) data;
                        data = isw.read();
                        System.out.print(current);

                    }
                    // return the data to onPostExecute method
                    return current;

                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    if (urlConnection != null) {
                        urlConnection.disconnect();
                    }
                }

            } catch (Exception e) {
                e.printStackTrace();
                return "Exception: " + e.getMessage();
            }
            return current;
        }

        @Override
        protected void onPostExecute(String s) {

            Log.d("data", s.toString());
            // dismiss the progress dialog after receiving data from API
            progressDialog.dismiss();
           */
/* try {
                // JSON Parsing of data
                JSONArray jsonArray = new JSONArray(s);

                JSONObject oneObject = jsonArray.getJSONObject(0);
                // Pulling items from the array
                title = oneObject.getString("title");
                category = oneObject.getString("category");
                image = oneObject.getString("image");
                // display the data in UI
                titleTextView.setText("Title: "+title);
                categoryTextView.setText("Category: "+category);
                // Picasso library to display the image from URL
                Picasso.with(getApplicationContext())
                        .load(image)
                        .into(imageView);


            } catch (JSONException e) {
                e.printStackTrace();
            }*//*



        }

    }
*/

}
