package com.example.athreyaanand.insider;

import android.content.Intent;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button toLogin = (Button) findViewById(R.id.toLogin);
            toLogin.setOnClickListener(view -> {
                Intent intent = new Intent(this, LoginActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);

                this.startActivity(intent);
                overridePendingTransition(0, R.anim.activity_fade_out);
            });

        Button toLobby = (Button) findViewById(R.id.toLobby);
            toLobby.setOnClickListener(view -> {
                Intent intent = new Intent(this, LobbyActivity.class);

                this.startActivity(intent);
                overridePendingTransition(0, R.anim.activity_fade_out);
            });

        Button toTabbed = (Button) findViewById(R.id.toTabbed);
            toTabbed.setOnClickListener(view -> {
                Intent intent = new Intent(this, TabbedActivity.class);

                this.startActivity(intent);
                overridePendingTransition(0, R.anim.activity_fade_out);
            });
    }

}
