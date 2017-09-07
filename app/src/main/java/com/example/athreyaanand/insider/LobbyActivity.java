package com.example.athreyaanand.insider;

import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class LobbyActivity extends AppCompatActivity {

    ArrayList<Player> playerNames;
    ListView playerLV;
    LobbyAdapter lobbyAdapter;

    Button joinLobbyButt, readyButt;
    TextView playerCount;

    Boolean inLobby = false;
    int maxPlayers = 15;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lobby);

        joinLobbyButt = (Button) findViewById(R.id.joinButton);
        joinLobbyButt.setOnClickListener(view -> updateLobby());

        readyButt = (Button) findViewById(R.id.readyButton);
        readyButt.setOnClickListener(view -> updatePlayer());

        playerNames = new ArrayList<>();
            playerNames.add(new Player("Simba", false));
            playerNames.add(new Player("Nala", true));
            playerNames.add(new Player("Rafiki", true));
            playerNames.add(new Player("Timon", true));
            playerNames.add(new Player("Pumba", false));
            playerNames.add(new Player("Scar", false));
            playerNames.add(new Player("Mufasa", true));

        playerCount = (TextView) findViewById(R.id.playerCount);
            playerCount.setText(playerNames.size() + "/" + maxPlayers);

        playerLV = (ListView) findViewById(R.id.playerList);
        lobbyAdapter = new LobbyAdapter(playerNames, getApplicationContext());

        playerLV.setAdapter(lobbyAdapter);

        playerLV.setOnItemClickListener((adapterView, view, i, l) -> {
                String person = playerNames.get(i).getName();

                Snackbar.make(view, (person + " is ready?: " + playerNames.get(i).getReady()), Snackbar.LENGTH_LONG)
                        .setAction("No action", null).show();
        });

    }

    private void updatePlayer() {
        Player player = playerNames.get(0);

        if (player.getReady()) {
            player.setReady(false);
            readyButt.setBackgroundColor(getResources().getColor(R.color.gem_icon_color));
            readyButt.setText("READY");
        } else {
            player.setReady(true);
            readyButt.setBackgroundColor(getResources().getColor(R.color.worse_10));
            readyButt.setText("YDAER");
        }

        lobbyAdapter.notifyDataSetChanged();
    }

    private void updateLobby(){
        if (!inLobby){
            inLobby = true;
            joinLobbyButt.setBackgroundColor(getResources().getColor(R.color.worse_50));
            joinLobbyButt.setText("Leave Lobby");
            playerNames.add(0, new Player("Athi", false));

            readyButt.setVisibility(View.VISIBLE);
        } else{
            inLobby = false;
            joinLobbyButt.setBackgroundColor(getResources().getColor(R.color.gem_icon_color));
            joinLobbyButt.setText("Join Lobby");
            playerNames.remove(0);

            readyButt.setVisibility(View.INVISIBLE);
        }

        lobbyAdapter.notifyDataSetChanged();
        playerCount.setText(playerNames.size() + "/" + maxPlayers);
    }
}
