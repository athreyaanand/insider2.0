package com.example.athreyaanand.insider;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by athreyaanand on 7/26/17.
 */

public class LobbyAdapter extends ArrayAdapter<Player> implements View.OnClickListener{

    ArrayList<Player> playersList;
    Context mContext;

    // View lookup cache
    private static class ViewHolder {
        TextView playerName;
        ImageView readyCheck;
    }

    public LobbyAdapter(ArrayList<Player> playersList, Context context) {
        super(context, R.layout.row_item, playersList);
        this.playersList = playersList;
        this.mContext=context;
    }

    @Override
    public void onClick(View v) {
        int position=(Integer) v.getTag();
        Object object= getItem(position);
        Toast.makeText(mContext, "Position "+position+" clicked!", Toast.LENGTH_SHORT).show();
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Player player = getItem(position);
        ViewHolder viewHolder; // view lookup cache stored in tag

        if (convertView == null) {

            viewHolder = new ViewHolder();
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.row_item, parent, false);
            viewHolder.playerName = convertView.findViewById(R.id.profileName);
            viewHolder.readyCheck = convertView.findViewById(R.id.isReady);

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        viewHolder.playerName.setText(player.getName());

        if (player.getReady())
            viewHolder.readyCheck.setImageResource(R.drawable.ic_check_purple_24dp);
        else
            viewHolder.readyCheck.setImageResource(R.drawable.ic_check);

        return convertView;
    }
}
