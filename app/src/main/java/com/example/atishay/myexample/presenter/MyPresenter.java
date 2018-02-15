package com.example.atishay.myexample.presenter;

import android.os.AsyncTask;
import android.util.Log;

import com.example.atishay.myexample.Interface.CallMe;
import com.example.atishay.myexample.Interface.FingureView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.XML;

/**
 * Created by Atishay on 31-01-2018.
 */

public class MyPresenter {

    private CallMe callMe;
    private FingureView fingureView;


    private String msj, fmsg, value, error, info;

    public MyPresenter(CallMe callMe, FingureView fingureView) {

        this.callMe = callMe;
        this.fingureView = fingureView;

    }


    public void getid(String a) {


        msj = a;

        new GetContacts().execute();

        callMe.showprogress();

    }

    public void gefing(String a) {


        fmsg = a;

        new GetFingure().execute();

//        fingureView.showprogress();

    }


    private class GetContacts extends AsyncTask<Void, Void, Void> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

        }

        @Override
        protected Void doInBackground(Void... voids) {


            String test = null;

            JSONObject jsonObj = null;
            try {
                jsonObj = XML.toJSONObject(msj);
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
                    value = c.getString("value");

                    Log.d("My", name);
                    Log.d("My1", value);


                }

                //  test = (String) json2.get("rdsVer");
            } catch (JSONException e) {
                e.printStackTrace();
            }


            return null;
        }


        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);

            callMe.showFLoginSuccessMsg(value);

            callMe.hideprogress();
        }
    }


    private class GetFingure extends AsyncTask<Void, Void, Void> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

        }

        @Override
        protected Void doInBackground(Void... voids) {


            String test = null;

            JSONObject jsonObj = null;
            try {
                jsonObj = XML.toJSONObject(fmsg);
            } catch (JSONException e) {
                Log.e("JSON exception", e.getMessage());
                e.printStackTrace();
            }


            try {
                JSONObject json1 = jsonObj.getJSONObject("PidData");
                JSONObject json2 = json1.getJSONObject("Resp");

                error = json2.getString("errCode");
                info = json2.getString("errInfo");


                //  test = (String) json2.get("rdsVer");
            } catch (JSONException e) {
                e.printStackTrace();
            }


            return null;
        }


        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);

            fingureView.showFingSuccessMsg(error, info);

            fingureView.hideprogress();
        }
    }
}


/*
    public void getCoverge(String username,String password){

        Call<BillerCovergePojo> pojoCall = mRestApis.getBillerCoverage(username,password);

        pojoCall.enqueue(new Callback<BillerCovergePojo>() {
            @Override
            public void onResponse(Call<BillerCovergePojo> call, Response<BillerCovergePojo> response) {

                poperatorView.hideprogress();

                if(response.isSuccessful()) {


                    if (response.body().getResponseMessage().equals("Successful")) {

                        poperatorView.showCovergeSuccessMsg(response.body().getResponseMessage(),response.body().getResponse());

                    } else
                    {

                        if (response.body().getResponseMessage().equals(Constants.FAILED)) {

                            poperatorView.showCovergeFailedMeassage(response.body().getResponseMessage());
                        }

                    }
                }





            @Override
            public void onFailure(Call<BillerCovergePojo> call, Throwable t) {

                poperatorView.hideprogress();

                poperatorView.showFErrorMeassage(AtishayOnlineApp.getAppInstance().getString(R.string.error_network_client_error));

            }
        });





        //  clientFactory.get1().doComplain(username,password,id,msg);


    }



}
*/
