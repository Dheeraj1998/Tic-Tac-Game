package com.example.tic_tac_game;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;

/**
 * Created by dheeraj on 27/06/17.
 */

public class custom_dialog_box extends Dialog implements
        android.view.View.OnClickListener {

    public Activity c;
    public Dialog d;
    public Button two_player, stupid_ai;

    public custom_dialog_box(Activity a) {
        super(a);
        // TODO Auto-generated constructor stub
        this.c = a;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.custom_dialog_box);

        two_player = (Button) findViewById(R.id.two_player);
        two_player.setOnClickListener(this);

        stupid_ai = (Button) findViewById(R.id.stupid_ai);
        stupid_ai.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent play_area = new Intent(getContext(), Play_Area.class);
        switch (v.getId()) {
            case R.id.two_player:
                play_area.putExtra("game_type","two_player");
                c.startActivity(play_area);
                break;

            case R.id.stupid_ai:
                play_area.putExtra("game_type","stupid_ai");
                c.startActivity(play_area);
                break;

            default:
                break;
        }

        dismiss();
    }
}