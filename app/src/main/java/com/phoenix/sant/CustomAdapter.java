package com.phoenix.sant;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.zip.Inflater;

public class CustomAdapter extends BaseAdapter {

    private Context context;
    private LayoutInflater inflater;
    private ArrayList<SanteModel>item;
    private TextView txtId,txtTaille,txtPoids,txtAge,txtCalcule,txtMessage,txtDate;
    private SanteModel sm;

    public CustomAdapter (Context context,ArrayList<SanteModel> item)
    {
        this.context=context;
        this.item=item;
        this.inflater=LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return item.size();
    }

    @Override
    public SanteModel getItem(int position) {
        return item.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (inflater==null)
        {
            inflater=(LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }
        if (convertView==null)
        {
            convertView=inflater.inflate(R.layout.raw,null);
        }
        txtId=(TextView)convertView.findViewById(R.id.txtid);
        txtTaille=(TextView)convertView.findViewById(R.id.txtTaille);
        txtPoids=(TextView)convertView.findViewById(R.id.txtPoids);
        txtAge=(TextView)convertView.findViewById(R.id.txtAge);
        txtCalcule=(TextView)convertView.findViewById(R.id.txtResul);
        txtMessage=(TextView)convertView.findViewById(R.id.txtMessage);
        txtDate=(TextView)convertView.findViewById(R.id.txtDate);

        SanteModel sm=item.get(position);
        txtId.setText(sm.getId());
        txtTaille.setText(sm.getTaille());
        txtPoids.setText(sm.getPoids());
        txtAge.setText(sm.getAge());
        txtCalcule.setText(sm.getCalcule());
        txtMessage.setText(sm.getMsg());
        txtDate.setText(sm.getDate());


        return convertView;
    }
}
