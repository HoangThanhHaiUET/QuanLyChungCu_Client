package com.bb.quanlycc;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.bb.quanlycc.Model.Account;
import com.bb.quanlycc.Model.DSThanhtoan;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class ThanhToanChiTietActivity extends AppCompatActivity implements View.OnClickListener {
    Toolbar toolbarthanhtoanchitiet;
    TextView txttendichvuthanhtoan,txttenphongthanhtoan,txttriso,
            txtgia,txtdiscout,txttongtien,txttinhtrangthanhtoan;
    Button thanhtoan;
    public static final String KEY_ID = "user_name" ;
    DSThanhtoan DS;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thanh_toan_chi_tiet);
        DS = new DSThanhtoan();
        DS = (DSThanhtoan) getIntent().getSerializableExtra("ThongtinBill");
        Anhxa();
        ActionBar();
        GetBill();
        event();


    }
    public void GetBill(){
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        StringRequest stringRequest =new StringRequest(Request.Method.GET,DS.getLink(),
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.d("response datauser", response);
                        try {

                            JSONObject jsonObject = new JSONObject(response);
                            JSONObject Billdetail = jsonObject.getJSONObject("data");
                            txttendichvuthanhtoan.setText(Billdetail.getString("name"));
                            txttenphongthanhtoan.setText(Billdetail.getString("use_date"));
                            txttriso.setText(Billdetail.getString("use_value"));
                            txtgia.setText(Billdetail.getString("service_price"));
                            txtdiscout.setText(Billdetail.getString("bill_discount"));
                            txttongtien.setText(Billdetail.getString("bill_sum"));
                            txttinhtrangthanhtoan.setText(Billdetail.getString("bill_status"));

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


    private void Anhxa(){
        toolbarthanhtoanchitiet = findViewById(R.id.toobarthanhtoanchitiet);
        txttendichvuthanhtoan = findViewById(R.id.txttendichvuthanhtoan);
        txttenphongthanhtoan = findViewById(R.id.txttenphongthanhtoan);
        txttriso = findViewById(R.id.txttriso);
        txtgia = findViewById(R.id.txtgia);
        txtdiscout = findViewById(R.id.txtdiscount);
        txttongtien = findViewById(R.id.txttongtien);
        txttinhtrangthanhtoan = findViewById(R.id.txttinhtrangthanhtoan);
        thanhtoan = findViewById(R.id.btn_thanhtoan);
    }
    private void ActionBar() {
        setSupportActionBar(toolbarthanhtoanchitiet);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbarthanhtoanchitiet.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }
    private void event(){
        thanhtoan.setOnClickListener(this);
    }

    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_thanhtoan:
                AlertDialog.Builder dialog = new AlertDialog.Builder(ThanhToanChiTietActivity.this);
                dialog.setMessage("Bạn có muốn thanh toán ngay?").setCancelable(false)
                        .setPositiveButton("Có", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                xuLyThanhToan();
                            }
                        }).setNegativeButton("Không", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
                dialog.show();
                break;
        }
    }

    private void xuLyThanhToan() {
        StringRequest requestThanhtoan = new StringRequest(Request.Method.POST, DS.getLink()+"/paid",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.d("ket qua tra ve ntn",response);
                        GetBill();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                    }

                }) {
            /**
             * set paramater
             * */
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<>();
                params.put(KEY_ID,DS.getId());
                return params;
            }

        };
        RequestQueue queue = (RequestQueue) Volley.newRequestQueue(getApplicationContext());
        queue.add(requestThanhtoan);
    }

}
