package com.amakenapp.website.amakenapp.activities;

        import android.app.ProgressDialog;
        import android.content.Context;
        import android.os.Bundle;
        import android.support.v7.app.AppCompatActivity;
        import android.support.v7.widget.Toolbar;
        import android.text.TextUtils;
        import android.view.View;
        import android.widget.ArrayAdapter;
        import android.widget.Button;
        import android.widget.EditText;
        import android.widget.RadioGroup;
        import android.widget.RatingBar;
        import android.widget.Spinner;
        import android.widget.Toast;

        import com.amakenapp.website.amakenapp.R;
        import com.amakenapp.website.amakenapp.helper.Constants;
        import com.amakenapp.website.amakenapp.helper.MySingleton;
        import com.android.volley.AuthFailureError;
        import com.android.volley.Request;
        import com.android.volley.Response;
        import com.android.volley.VolleyError;
        import com.android.volley.toolbox.StringRequest;

        import org.json.JSONArray;
        import org.json.JSONException;
        import org.json.JSONObject;

        import java.util.ArrayList;
        import java.util.HashMap;
        import java.util.List;
        import java.util.Map;

        import static com.amakenapp.website.amakenapp.R.id.ratingBar;

public class AddReview extends AppCompatActivity implements  View.OnClickListener {
//////////////////////////////////////////
    private Button button2;
    private Spinner spinner;
    private ProgressDialog progressDialog;

    private EditText editText2, editTextMessage;

    private boolean isSendAllChecked;
    private List<String> devices;
    /////////////////////////////////////////////

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_review);

        ////////////////////////////////////////////////////////////
        spinner = (Spinner) findViewById(R.id.spinnerDevices);
        button2 = (Button) findViewById(R.id.button2);

        editText2 = (EditText) findViewById(R.id.editText2);
        editTextMessage = (EditText) findViewById(R.id.editTextMessage);

        devices = new ArrayList<>();


        button2.setOnClickListener(this);

        loadRegisteredDevices();
        /////////////////////////////////////////////////////

        Toolbar toolbar = (Toolbar) findViewById(R.id.add_review_activity_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        final Context x = this;

        RatingBar ratingbar1 = (RatingBar) findViewById(ratingBar);
        ratingbar1.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                Toast.makeText(x, Float.toString(rating), Toast.LENGTH_LONG).show();
            }

        });

    }

    /////////////////////////////////////////////////////////

    //method to load all the devices from database
    private void loadRegisteredDevices() {

        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Fetching Devices...");
        progressDialog.show();

        StringRequest stringRequest = new StringRequest(Request.Method.GET, Constants.URL_FETCH_DEVICES,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        progressDialog.dismiss();
                        JSONObject obj = null;
                        try {
                            obj = new JSONObject(response);
                            if (!obj.getBoolean("error")) {
                                JSONArray jsonDevices = obj.getJSONArray("devices");

                                for (int i = 0; i < jsonDevices.length(); i++) {
                                    JSONObject d = jsonDevices.getJSONObject(i);
                                    devices.add(d.getString("email"));
                                }

                                ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(
                                        AddReview.this,
                                        android.R.layout.simple_spinner_dropdown_item,
                                        devices);

                                spinner.setAdapter(arrayAdapter);
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                }) {

        };
        MySingleton.getInstance(this).addToRequestQueue(stringRequest);

    }

    //this method will send the push
    //from here we will call sendMultiple() or sendSingle() push method
    //depending on the selection
    private void sendPush(){

        final String title = editText2.getText().toString();
        final String message = editTextMessage.getText().toString();
      //  final String image = editTextImage.getText().toString();
        final String email = spinner.getSelectedItem().toString();

        progressDialog.setMessage("Sending Push");
        progressDialog.show();

        StringRequest stringRequest = new StringRequest(Request.Method.POST, Constants.URL_SEND_SINGLE_PUSH,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        progressDialog.dismiss();

                        Toast.makeText(AddReview.this, response, Toast.LENGTH_LONG).show();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("title", title);
                params.put("message", message);

               // if (!TextUtils.isEmpty(image))
                 //   params.put("image", image);

                params.put("email", email);
                return params;
            }
        };

        MySingleton.getInstance(this).addToRequestQueue(stringRequest);


    }

    private void sendMultiplePush(){

    }

    private void sendSinglePush(){

    }

    @Override
    public void onClick(View view) {
        //calling the method send push on button click
        sendPush();
    }

}

