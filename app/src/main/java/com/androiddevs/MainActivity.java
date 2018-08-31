package com.androiddevs;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.androiddevs.utils.AsyncTaskHandler;
import com.androiddevs.utils.ResponseHandler;
import com.androiddevs.utils.UrlConfig;

import java.util.HashMap;

public class MainActivity extends AppCompatActivity {

    private HashMap params;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        params = new HashMap<String, String>();
        params.put("key", "value"); // key - value POST request
        new AsyncTaskHandler(MainActivity.this, UrlConfig.TEST_URL, null, "GET", new ResponseHandler() {  // CHANGE METHOD NAME GET/POST AND ADD PARAMS IN POST METHOD
            @Override
            public void handleResponse(String response) {
                Log.e("RESPONSE response>>", "" + response);
            }

            @Override
            public void handleError(String error) {
                Log.e("RESPONSE error>>", "" + error);
            }
        }).execute();
    }
}
