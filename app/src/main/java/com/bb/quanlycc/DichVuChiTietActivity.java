package com.bb.quanlycc;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.bb.quanlycc.Model.danhsach;
import com.bb.quanlycc.Model.news;

import org.json.JSONException;
import org.json.JSONObject;

public class DichVuChiTietActivity extends AppCompatActivity {
    TextView tenDV ,KieuDV,GiaDV,ChiTietDV,Ptthanhtoan;
    Toolbar toolbardichvu;
    danhsach a ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dich_vu_chi_tiet);
        Anhxa();
        GetDichVuChiTiet();
        ActionBar();
    }
    private void Anhxa(){
        tenDV = findViewById(R.id.txtNameDV);
        KieuDV = findViewById(R.id.txttypeDV);
        GiaDV = findViewById(R.id.txtprice);
        ChiTietDV = findViewById(R.id.txtdescription);
        Ptthanhtoan = findViewById(R.id.txtpayment);
        toolbardichvu = findViewById(R.id.toobardichvuchitiet);
        a = new danhsach();
        a = (danhsach) getIntent().getSerializableExtra("ThongtinDV");

    }
    public void GetDichVuChiTiet(){
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        String url = "http://10.0.3.2:8000/api/service/"+a.getId();
        StringRequest stringRequest =new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.d("response datauser", response);
                        try {

                            JSONObject jsonObject = new JSONObject(response);
                            JSONObject jsonObjectdatauser = jsonObject.getJSONObject("data");
                            String name = jsonObjectdatauser.getString("name");
                            String type = jsonObjectdatauser.getString("type");
                            String payment = jsonObjectdatauser.getString("payment_method");
                            String price = jsonObjectdatauser.getString("price");
                            String description = jsonObjectdatauser.getString("description");

                            /**Set value*/
                            tenDV.setText(name);
                            KieuDV.setText(type);
                            Ptthanhtoan.setText(payment);
                            GiaDV.setText(price);
                            ChiTietDV.setText(description);

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
        setSupportActionBar(toolbardichvu);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbardichvu.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }

}
