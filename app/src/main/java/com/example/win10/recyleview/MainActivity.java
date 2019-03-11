package com.example.win10.recyleview;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    RecyclerView.Adapter mAdapter;
    RecyclerView.LayoutManager layoutManager;

    List<Padi> personUtilsList;

    RequestQueue rq;

    String request_url = "http://192.168.0.193:5000/padi";

    public void showAlertDialogButtonClicked(View view) {

        // setup the alert builder
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("AlertDialog");
        builder.setMessage("Would you like to continue learning how to use Android alerts?");

        // add the buttons
        builder.setPositiveButton("Continue", null);
        builder.setNegativeButton("Cancel", null);

        // create and show the alert dialog
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        rq = Volley.newRequestQueue(this);

        recyclerView = (RecyclerView) findViewById(R.id.recycleViewContainer);
        recyclerView.setHasFixedSize(true);

        layoutManager = new LinearLayoutManager(this);

        recyclerView.setLayoutManager(layoutManager);

        personUtilsList = new ArrayList<>();

        sendRequest();

    }


    public void sendRequest(){

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, request_url, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {

                for(int i = 0; i < response.length(); i++){

                    Padi personUtils = new Padi();

                    try {
                        JSONObject jsonObject = response.getJSONObject(i);

                        personUtils.setId(Integer.valueOf(jsonObject.getString("id")));
                        personUtils.setLuas_lahan(Integer.valueOf(jsonObject.getString("luas_lahan")));
                        personUtils.setTgl_tanam(jsonObject.getString("tgl_tanam"));
                        personUtils.setTgl_siap_panen(jsonObject.getString("tgl_siap_panen"));
                        personUtils.setHasil_panen(jsonObject.getString("hasil_panen"));
                        personUtils.setPemilik(jsonObject.getString("pemilik"));
                        personUtils.setNik(Integer.valueOf(jsonObject.getString("nik")));
                        personUtils.setPekerja(Integer.valueOf(jsonObject.getString("pekerja")));

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                    personUtilsList.add(personUtils);

                }

                mAdapter = new CustomRecyclerAdapter(MainActivity.this, personUtilsList);

                recyclerView.setAdapter(mAdapter);

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.i("Volley Error: ", String.valueOf(error));
            }
        });

        rq.add(jsonArrayRequest);

    }


}
