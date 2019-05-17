package com.bb.quanlycc;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.support.v7.widget.Toolbar;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.bb.quanlycc.Model.news;

import org.json.JSONException;
import org.json.JSONObject;

public class NewsDetailActivity extends AppCompatActivity {
    TextView Title,ngay,status;
    Toolbar toolbarthongbao;
    news news;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_detail);
        Title = findViewById(R.id.txtTitle);
        ngay = findViewById(R.id.txtngay);
        status = findViewById(R.id.txtstatus);
        toolbarthongbao = findViewById(R.id.toobarthongbao);
        news = new news();
        news = (news) getIntent().getSerializableExtra("Thongtin");
        ActionBar();
        GetThongBao();

    }

    public void GetThongBao(){
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        StringRequest stringRequest =new StringRequest(Request.Method.GET,news.getStatus(),
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.d("response datauser", response);
                        try {

                            JSONObject jsonObject = new JSONObject(response);
                            JSONObject ThongBaodetail = jsonObject.getJSONObject("data");
                            Title.setText(ThongBaodetail.getString("title"));
                            status.setText(ThongBaodetail.getString("body"));
                            ngay.setText(ThongBaodetail.getString("created_at"));

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
        setSupportActionBar(toolbarthongbao);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbarthongbao.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }
}
