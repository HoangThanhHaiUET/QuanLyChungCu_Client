package com.bb.quanlycc.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.bb.quanlycc.Model.DSThanhtoan;
import com.bb.quanlycc.Model.news;
import com.bb.quanlycc.R;

import java.util.ArrayList;

public class ThanhtoanAdapter extends BaseAdapter {


    ArrayList<DSThanhtoan> arrayListnews;
    Context context ;

    public ThanhtoanAdapter(ArrayList<DSThanhtoan> arrayListnews, Context context) {
        this.arrayListnews = arrayListnews;
        this.context = context;
    }

    @Override
    public int getCount() {
        return arrayListnews.size();
    }

    @Override
    public Object getItem(int i) {
        return arrayListnews.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    public  class ViewHolder{
        TextView txttitle;
        TextView status;

    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ThanhtoanAdapter.ViewHolder viewHolder =null;
        if(view ==null){
            viewHolder = new ThanhtoanAdapter.ViewHolder();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.listview_dsdichvu,null);
            viewHolder.txttitle = (TextView) view.findViewById(R.id.texttenDV);
            viewHolder.status = view.findViewById(R.id.txttinhtrang);

            view.setTag(viewHolder);
        }else {

            viewHolder = (ThanhtoanAdapter.ViewHolder) view.getTag();
        }
        DSThanhtoan New = (DSThanhtoan) getItem(i);
        viewHolder.txttitle.setText(New.getName());
        viewHolder.status.setText(New.getStatus());

        return view;
    }
}
