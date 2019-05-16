package com.bb.quanlycc.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.bb.quanlycc.Model.tiendien;
import com.bb.quanlycc.R;

import java.util.ArrayList;

public class tiendienAdapter extends BaseAdapter {

    ArrayList<tiendien> arrayListtiendien;
    Context context ;

    public tiendienAdapter(ArrayList<tiendien> arrayListtiendien, Context context) {
        this.arrayListtiendien = arrayListtiendien;
        this.context = context;
    }

    @Override
    public int getCount() { return arrayListtiendien.size(); }
    @Override
    public Object getItem(int i) {
        return arrayListtiendien.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    public  class ViewHolder{
        TextView txtngay, txtthangtruoc,txtthangsau,txttien,txtstatus;

    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder =null;
        if(view ==null){
            viewHolder = new ViewHolder();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.dong_dien_listview,null);
            viewHolder.txtngay = (TextView) view.findViewById(R.id.textngay);
            viewHolder.txtthangtruoc = (TextView) view.findViewById(R.id.textthangtruoc);
            viewHolder.txtthangsau = (TextView) view.findViewById(R.id.textthangsau);
            viewHolder.txttien = (TextView) view.findViewById(R.id.texttongtien);
            viewHolder.txtstatus = (TextView) view.findViewById(R.id.textstatus);


            view.setTag(viewHolder);
        }else {

            viewHolder = (ViewHolder) view.getTag();
        }
        tiendien New = (tiendien) getItem(i);
        viewHolder.txtngay.setText(New.getNgay());
        viewHolder.txtthangtruoc.setText(String.valueOf(New.getTiendienthangtruoc()));
        viewHolder.txtthangsau.setText(String.valueOf(New.getTiendienthangsau()));
        viewHolder.txttien.setText(String.valueOf(New.getTiendien()));
        viewHolder.txtstatus.setText(New.getStatus());

        return view;
    }
}