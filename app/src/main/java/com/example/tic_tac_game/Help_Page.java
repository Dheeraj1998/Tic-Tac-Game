package com.example.tic_tac_game;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class Help_Page extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help__page);

        //Setting the custom font for the help text
        TextView help_text = (TextView) findViewById(R.id.help_text);
        Typeface tf = Typeface.createFromAsset(getAssets(), "fonts/spectral_light.ttf");

        help_text.setTypeface(tf);
    }

    public void openMainPage(View v){
        Intent temp = new Intent(Help_Page.this, MainActivity.class);
        startActivity(temp);
        finish();
    }

}
