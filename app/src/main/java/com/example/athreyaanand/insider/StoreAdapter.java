package com.example.athreyaanand.insider;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class StoreAdapter extends BaseAdapter{

    StoreItem [] itemList;
    Context context;

    private static LayoutInflater inflater=null;

    public StoreAdapter(Activity context, StoreItem[] itemList) {
        this.context = context;
        this.itemList = itemList;
        inflater = ( LayoutInflater )context.
                getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }
    @Override
    public int getCount() {
        return itemList.length;
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public class Holder
    {
        TextView title, description;
        ImageView img;
    }
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        Holder holder=new Holder();
        View rowView;
        if (position==0){
            rowView = inflater.inflate(R.layout.list_noads, null);
            rowView.setOnClickListener(v -> Toast.makeText(context, "NO ADS!", Toast.LENGTH_LONG).show());
            return rowView;
        }else {
            rowView = inflater.inflate(R.layout.list_store, null);
            holder.title = (TextView) rowView.findViewById(R.id.itemName);
            holder.description = rowView.findViewById(R.id.itemDesc);
            holder.img = (ImageView) rowView.findViewById(R.id.itemImage);

            holder.title.setText(itemList[position].getItemName());
            holder.description.setText(itemList[position].getItemDesc());
            holder.img.setImageResource(itemList[position].getItemImage());
            rowView.setOnClickListener(v -> Toast.makeText(context, "You Clicked " + itemList[position].getItemName(), Toast.LENGTH_LONG).show());
            return rowView;
        }
    }

}