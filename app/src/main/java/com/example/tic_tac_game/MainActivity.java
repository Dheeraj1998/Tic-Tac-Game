package com.example.tic_tac_game;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Setting the custom font for the game title
        TextView game_title = (TextView) findViewById(R.id.game_title);
        Typeface tf = Typeface.createFromAsset(getAssets(), "fonts/bubbler_one.ttf");

        game_title.setTypeface(tf);
    }

    public void openHelpPage(View v){
        Intent help_page = new Intent(MainActivity.this, Help_Page.class);
        startActivity(help_page);
        finish();
    }

    public void openPlayArea(View v){
        custom_dialog_box cdb = new custom_dialog_box(MainActivity.this);
        cdb.show();
    }
}
