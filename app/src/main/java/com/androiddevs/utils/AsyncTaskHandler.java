package com.androiddevs.utils;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import java.io.IOException;
import java.net.URL;
import java.util.Map;

public class AsyncTaskHandler extends AsyncTask<URL, String, String> {

    private final static String TAG = AsyncTaskHandler.class.getSimpleName();
    private ProgressDialog dialog;
    private Context context;
    private String url;
    private Map<String, String> params;
    private String reqMethod;
    ResponseHandler responseHandler;

    public AsyncTaskHandler(Context context, String url, Map<String, String> params, String reqMethod, ResponseHandler responseHandler) {
        this.context = context;
        this.url = url;
        this.params = params;
        this.reqMethod = reqMethod;
        this.responseHandler = responseHandler;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        Log.e(TAG + "onPreExecute", ">>>>>");
        dialog = new ProgressDialog(context, ProgressDialog.THEME_HOLO_LIGHT);
        dialog.setMessage("Please Wait...");
        dialog.setCancelable(false);
        dialog.show();
    }

    @Override
    protected String doInBackground(URL... strings) {
        Log.e(TAG + "doInBackground", ">>>>>");
        String mResponse = "";
        switch (reqMethod) {
            case "GET":
                Log.e(TAG + "GET", ">>>>>");
                // test sending GET request
                try {
                    HttpUtility.sendGetRequest(url);
                    mResponse = HttpUtility.readStreamToString();
                    Log.e(TAG + "HttpUtilityTester", "FINAL RESPONSE>>>> " + mResponse);
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
                HttpUtility.disconnect();
                break;
            case "POST":
                Log.e(TAG + "POST", ">>>>>");
                // test sending POST request
                params.put("Email", "your_email");
                params.put("Passwd", "your_password");
                try {
                    HttpUtility.sendPostRequest(url, params);
                    mResponse = HttpUtility.readStreamToString();
                    Log.e(TAG + "HttpUtilityTester", "FINAL RESPONSE>>>> " + mResponse);
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
                HttpUtility.disconnect();
                break;
            default:
        }
        return mResponse;
    }

    @Override
    protected void onProgressUpdate(String... values) {
        super.onProgressUpdate(values);
    }

    @Override
    protected void onPostExecute(String result) {
        super.onPostExecute(result);
        Log.e(TAG + ">>onPostExecute", ">>" + result);
        dialog.dismiss();
        responseHandler.handleResponse(result);
//        try {
//            Log.e(TAG+"result", ">>" + result);
//            JSONObject resObj = new JSONObject(result);
//            String message = resObj.getString("result");
//            Log.e(TAG+"message", ">>" + message);
//            String error = resObj.optString("error");
//            if (error != null && error.equals("")) {
//                if (message.equals("ok")) {
//                    responseHandler.handleResponse(result);
//                }
//            } else {
//                responseHandler.handleError(error);
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
    }
}


