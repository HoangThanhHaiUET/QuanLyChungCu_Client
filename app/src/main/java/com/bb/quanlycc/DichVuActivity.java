package com.bb.quanlycc;

import android.content.Intent;
import android.support.v4.view.GravityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.support.v7.widget.Toolbar;


import com.bb.quanlycc.Adapter.listAdapter;
import com.bb.quanlycc.Model.danhsach;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class DichVuActivity extends AppCompatActivity {
    ListView listdichvu;
    Toolbar dichvu;
    ArrayList<danhsach> mangdichvu;
    listAdapter newsAdapter ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dich_vu);
        Anhxa();
        ActionBarDichVu();
        GetDichVu();
        LoadTTDichVu();
    }
    private void Anhxa(){

        listdichvu = findViewById(R.id.listdichvu);
        dichvu = findViewById(R.id.toolbardsdichvu);
    }
    private void ActionBarDichVu() {
        setSupportActionBar(dichvu);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        dichvu.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }
    public void GetDichVu() {
        mangdichvu = new ArrayList<>();
        mangdichvu.add( new danhsach(1,"Dịch Vụ Thuê Nhà",R.drawable.home));
        mangdichvu.add( new danhsach(2,"Dịch Vụ Nước",R.drawable.water));
        mangdichvu.add( new danhsach(3,"Dịch Vụ Điện",R.drawable.electric));
        mangdichvu.add( new danhsach(4,"Dịch Vụ Gửi Xe",R.drawable.car));
        newsAdapter = new listAdapter(mangdichvu,this);
        listdichvu.setAdapter(newsAdapter);

    }
    private void LoadTTDichVu() {
        listdichvu.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int i, long id) {
                Intent intent = new Intent(DichVuActivity.this,DichVuChiTietActivity.class);
                intent.putExtra("ThongtinDV",mangdichvu.get(i));
                startActivity(intent);
            }
        });
    }

}
