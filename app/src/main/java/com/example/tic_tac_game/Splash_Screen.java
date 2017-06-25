package com.example.tic_tac_game;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class Splash_Screen extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash__screen);

        //Setting the custom font for the game title
        TextView game_title = (TextView) findViewById(R.id.game_title);
        Typeface tf = Typeface.createFromAsset(getAssets(), "fonts/bubbler_one.ttf");

        game_title.setTypeface(tf);
    }

    public void openMainActivity(View v){
        Intent main_page = new Intent(Splash_Screen.this, MainActivity.class);
        startActivity(main_page);
        finish();
    }
}
