package com.bb.quanlycc;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.support.v7.widget.Toolbar;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.bb.quanlycc.Model.Account;
import com.bb.quanlycc.Model.apartment;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Date;


public class ThongTinActivity extends AppCompatActivity {
    Toolbar toolbarthongtin;
    TextView ten ,phone,email,phong,using;
    private Account account;
    public String address;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thong_tin);
        Intent intent = getIntent();
        toolbarthongtin = findViewById(R.id.toobarthongtin);
        account = new Account();
        account = (Account) intent.getSerializableExtra("login");
        addControl();
        Getdatauser();
        ActionBar();

    }

    public void Getdatauser(){
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        String url = "http://10.0.3.2:8000/api/user/"+account.getId();
        StringRequest stringRequest =new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.d("response datauser", response);
                        try {

                            JSONObject jsonObject = new JSONObject(response);
                            JSONObject jsonObjectdatauser = jsonObject.getJSONObject("data");
                            String name = jsonObjectdatauser.getString("name");
                            String emailuser = jsonObjectdatauser.getString("email");
                            String phonenumber = jsonObjectdatauser.getString("phone_number");
                            JSONObject jsonapartment = jsonObjectdatauser.getJSONObject("href");
                            address = jsonapartment.getString("apartment");
                            Getdataapartment();
                                              /**Set value*/
                            ten.setText(name);
                            email.setText(emailuser);
                            phone.setText(phonenumber);
                           // phong.setText(address);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }

                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        requestQueue.add(stringRequest);
    }
    public void Getdataapartment(){
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        String url = address ;
        StringRequest stringRequest =new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.d("response datauser", response);
                        try {

                            JSONObject jsonObject = new JSONObject(response);
                            JSONObject jsonObjectdatauser = jsonObject.getJSONObject("data");
                            String  apartmentid = jsonObjectdatauser.getString("id");
                            String address = jsonObjectdatauser.getString("address");
                            apartment apartment = new apartment();
                            apartment.setId(apartmentid);
                            Intent intent = new Intent(ThongTinActivity.this,TienNuocActivity.class);
                            intent.putExtra("apartment",apartment);
                            /**Set value*/
                            phong.setText(address);
                            using.setText(apartmentid);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }

                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        requestQueue.add(stringRequest);
    }



    private void ActionBar() {
        setSupportActionBar(toolbarthongtin);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbarthongtin.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }
    private void addControl() {
        email = (TextView) findViewById(R.id.txtemailKH);
        ten = (TextView) findViewById(R.id.txtNameKH);
        phone= (TextView) findViewById(R.id.txtphone);
        phong = (TextView) findViewById(R.id.txtroom);
        using = (TextView)findViewById(R.id.txtusing);
        toolbarthongtin = findViewById(R.id.toobarthongtin);

    }

}
