package com.bb.quanlycc;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.support.v7.widget.Toolbar;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.bb.quanlycc.Adapter.ThanhtoanAdapter;
import com.bb.quanlycc.Model.Account;
import com.bb.quanlycc.Model.DSThanhtoan;
import com.bb.quanlycc.Model.apartment;
import com.bb.quanlycc.Model.config;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;
;


public class TienNuocActivity extends AppCompatActivity  {
    ListView thanhtoan;
    Toolbar toolbarthanhtoan;
    private Account account;
    public String url2;
    public String apartmentid;
    ArrayList<DSThanhtoan> mangDS;
    ThanhtoanAdapter danhsachAdapter;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tien_nuoc);
        Intent intent = getIntent();
        account = new Account();
        account = (Account) intent.getSerializableExtra("login");
        Anhxa();
        ActionBarThanhToan();
        GetID();
        GetBillDetail();

    }
    public void GetID(){
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        String url = config.getUrl()+"/api/user/"+account.getId();
        StringRequest stringRequest =new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.d("response datauser", response);
                        try {

                            JSONObject jsonObject = new JSONObject(response);
                            JSONObject jsonObjectdatauser = jsonObject.getJSONObject("data");
                            JSONObject jsonapartment = jsonObjectdatauser.getJSONObject("href");
                             url2 = jsonapartment.getString("apartment");
                             Getid();

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
    public void Getid(){
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        String url = url2 ;
        StringRequest stringRequest =new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.d("response datauser", response);
                        try {

                            JSONObject jsonObject = new JSONObject(response);
                            JSONObject jsonObjectdatauser = jsonObject.getJSONObject("data");
                             apartmentid = jsonObjectdatauser.getString("id");
                            GetDSbill();
                            /**Set value*/

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
    public void GetDSbill(){
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        String url = config.getUrl()+"/api/bill/apartment="+apartmentid; ;
        StringRequest stringRequest =new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.d("response datauser", response);
                        try {

                            JSONObject jsonObject = new JSONObject(response);
                            JSONArray databill = jsonObject.getJSONArray("data");

                            for(int i = 0 ; i < databill.length();i++){
                                DSThanhtoan DS = new DSThanhtoan();
                                JSONObject bill = (JSONObject) databill.get(i);
                                DS.setId(bill.getString("id"));
                                DS.setName(bill.getString("bill_name"));
                                DS.setStatus(bill.getString("bill_status"));
                                JSONObject link = bill.getJSONObject("href");
                                DS.setLink(link.getString("bill_show"));
                                mangDS.add(DS);
                                danhsachAdapter.notifyDataSetChanged();


                            }
                           // apartmentid = jsonObjectdatauser.getString("id");
                            /**Set value*/

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



    private void ActionBarThanhToan() {
        setSupportActionBar(toolbarthanhtoan);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbarthanhtoan.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });



    }
    private void GetBillDetail() {
        thanhtoan.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int i, long id) {
                Intent intent = new Intent(TienNuocActivity.this,ThanhToanChiTietActivity.class);
                intent.putExtra("ThongtinBill",mangDS.get(i));
                startActivity(intent);
            }
        });
    }



    public  void  Anhxa(){
         thanhtoan = findViewById(R.id.listthanhtoan);
         toolbarthanhtoan = findViewById(R.id.toolbarthanhtoan);
         mangDS = new ArrayList<>();
         danhsachAdapter = new ThanhtoanAdapter(mangDS,getApplicationContext());
        thanhtoan.setAdapter(danhsachAdapter);
    }


}

