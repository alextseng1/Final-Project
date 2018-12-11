package com.example.alextseng.s;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Button;
import android.util.Log;
import android.widget.Switch;
import android.widget.CompoundButton;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "tag";

    private TextView mTextMessage;

    private static RequestQueue requestQueue;

    private JSONObject js;
    private String s = "Help";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestQueue = Volley.newRequestQueue(this);
        setContentView(R.layout.activity_main);

        mTextMessage = (TextView) findViewById(R.id.message);


        Button btn1 = (Button) findViewById(R.id.generatebutton);
        btn1.setOnClickListener(new View.OnClickListener() {
            public void onClick (View v) {
                Log.e("My button" , "This is log message!");
            }
        } );


        final TextView txtbox = findViewById(R.id.textView);

        startAPICall();
        btn1.setOnClickListener(v ->{
            startAPICall();
            try {
                s = js.getString("insult");
            } catch (JSONException e) {
                e.printStackTrace();
                s = "Tou are a failure";
            } catch (Exception e) {
                e.printStackTrace();
                s = "Tou are a failure";
            }
            txtbox.setText(s);
        });
    }

    /**
     * Run when this activity is no longer visible.
     */
    @Override
    protected void onPause() {
        super.onPause();
    }

    /**
     * Make a call to the IP geolocation API.
     *
     * @param ipAddress IP address to look up
     */
    void startAPICall() {
        try {
            JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
                    Request.Method.GET,
                    "https://insult.mattbas.org/api/insult.json",
                    null,
                    new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(final JSONObject response) {
                            apiCallDone(response);
                        }
                    }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(final VolleyError error) {
                    Log.e(TAG, error.toString());
                }
            });
            jsonObjectRequest.setShouldCache(false);
            requestQueue.add(jsonObjectRequest);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Handle the response from our IP geolocation API.
     *
     * @param response response from our IP geolocation API.
     */
    void apiCallDone(final JSONObject response) {
        try {
            Log.d(TAG, response.toString(2));
            // Example of how to pull a field off the returned JSON object
            try {
                js = response;
            } catch (Exception e) {
            }
        } catch (JSONException ignored) { }
    }

}
