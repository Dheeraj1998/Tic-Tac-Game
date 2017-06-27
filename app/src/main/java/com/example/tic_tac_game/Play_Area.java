package com.example.tic_tac_game;

import android.os.*;
import android.app.Activity;
import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.Random;

public class Play_Area extends Activity {
    int player_tag = 1;
    int[][] game_board = new int[3][3];
    int flag = 0, game_end_flag = 0, int_game_type = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play__area);

        initialize_board(findViewById(android.R.id.content));
    }

    public void initialize_board(View view){
        if(flag == 0){
            flag++;
        }

        else{
            Snackbar snackbar = Snackbar.make(view, "The game has been reset!", Snackbar.LENGTH_SHORT);
            snackbar.show();
        }

        game_board[0][0] = -1;
        game_board[0][1] = -2;
        game_board[0][2] = -3;

        game_board[1][0] = -4;
        game_board[1][1] = -5;
        game_board[1][2] = -6;

        game_board[2][0] = -7;
        game_board[2][1] = -8;
        game_board[2][2] = -9;

        ImageView image_00 = (ImageView) findViewById(R.id.image_00);
        ImageView image_01 = (ImageView) findViewById(R.id.image_01);
        ImageView image_02 = (ImageView) findViewById(R.id.image_02);

        ImageView image_10 = (ImageView) findViewById(R.id.image_10);
        ImageView image_11 = (ImageView) findViewById(R.id.image_11);
        ImageView image_12 = (ImageView) findViewById(R.id.image_12);

        ImageView image_20 = (ImageView) findViewById(R.id.image_20);
        ImageView image_21 = (ImageView) findViewById(R.id.image_21);
        ImageView image_22 = (ImageView) findViewById(R.id.image_22);

        image_00.setImageResource(0);
        image_01.setImageResource(0);
        image_02.setImageResource(0);

        image_10.setImageResource(0);
        image_11.setImageResource(0);
        image_12.setImageResource(0);

        image_20.setImageResource(0);
        image_21.setImageResource(0);
        image_22.setImageResource(0);

        player_tag = 1;
        TextView temp_text = (TextView) findViewById(R.id.player_hint);
        temp_text.setText("Player#" + player_tag + " has to play!");
    }

    public void stupid_ai(){
        Random rand = new Random();
        String image_id;
        int int_image_id;

        while(true) {
            int row_num = rand.nextInt(3);
            int col_num = rand.nextInt(3);

            if(game_board[row_num][col_num] < 0){
                image_id = "image_" + row_num + col_num;
                int_image_id = getResources().getIdentifier(image_id, "id", getPackageName());
                play_game(findViewById(int_image_id));
                break;
            }
        }
    }

    public void play_game(View view){
        ImageView temp = (ImageView) view;
        TextView temp_text = (TextView) findViewById(R.id.player_hint);
        game_end_flag = 0;

        if(((ImageView) view).getDrawable() != null){
            //Display error message in case of same position
            Snackbar snackbar = Snackbar.make(view, "The place is already filled!", Snackbar.LENGTH_SHORT);
            snackbar.show();
        }

        //For Player-1, the mark used is 'X'
        else if(player_tag == 1){
            temp.setImageResource(R.drawable.x_mark);
            fill_game_array(view);

            check_win();

            if(game_end_flag == 1){
                //do nothing
            }

            else {
                //Check if the game-type selected is AI or 2-player
                String game_type = getIntent().getStringExtra("game_type");
                player_tag = 2;
                temp_text.setText("Player#" + player_tag + " has to play!");

                if(game_type.equals("stupid_ai")){
                    int_game_type = 1;

                    temp_text.setText("AI is thinking :)");
                    final Handler handler = new Handler();
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            stupid_ai();
                        }
                    }, 1500);

                }
            }
        }

        //For Player-2, the mark used is 'O'
        else if (player_tag == 2){
            temp.setImageResource(R.drawable.o_mark);
            fill_game_array(view);

            check_win();

            if(game_end_flag == 1){
                //do nothing
            }

            else {
                player_tag = 1;
                temp_text.setText("Player#" + player_tag + " has to play!");
            }
        }
    }

    public void fill_game_array(View view){
        ImageView temp = (ImageView) view;
        int image_id = temp.getId();

        if(image_id == R.id.image_00){
            game_board[0][0] = player_tag;
        }

        else if(image_id == R.id.image_01){
            game_board[0][1] = player_tag;
        }

        else if(image_id == R.id.image_02){
            game_board[0][2] = player_tag;
        }

        else if(image_id == R.id.image_10){
            game_board[1][0] = player_tag;
        }

        else if(image_id == R.id.image_11){
            game_board[1][1] = player_tag;
        }

        else if(image_id == R.id.image_12){
            game_board[1][2] = player_tag;
        }

        else if(image_id == R.id.image_20){
            game_board[2][0] = player_tag;
        }

        else if(image_id == R.id.image_21){
            game_board[2][1] = player_tag;
        }

        else if(image_id == R.id.image_22){
            game_board[2][2] = player_tag;
        }
    }

    public void check_win(){

        Log.i("Row 0", "" + game_board[0][0] + " " + game_board[0][1] + " " + game_board[0][2]);
        Log.i("Row 1", "" + game_board[1][0] + " " + game_board[1][1] + " " + game_board[1][2]);
        Log.i("Row 2", "" + game_board[2][0] + " " + game_board[2][1] + " " + game_board[2][2]);

        //Win Situation - 1
        // 1 0 0
        // 0 1 0
        // 0 0 1
        if(game_board[0][0] == player_tag && game_board[1][1] == player_tag && game_board[2][2] == player_tag){
                Log.i("Win Type", "1");
                game_end_win();
        }

        //Win Situation - 2
        // 0 0 1
        // 0 1 0
        // 1 0 0
        else if(game_board[0][2] == player_tag && game_board[1][1] == player_tag && game_board[2][0] == player_tag){
                Log.i("Win Type", "2");
                game_end_win();
        }

        //Win Situation - 3
        // 1 1 1
        // 0 0 0
        // 0 0 0
        else if(game_board[0][0] == player_tag && game_board[0][1] == player_tag && game_board[0][2] == player_tag) {
                Log.i("Win Type", "3");
                game_end_win();
        }

        //Win Situation - 4
        // 0 0 0
        // 1 1 1
        // 0 0 0
        else if(game_board[1][0] == player_tag && game_board[1][1] == player_tag && game_board[1][2] == player_tag) {
                Log.i("Win Type", "4");
                game_end_win();
        }

        //Win Situation - 5
        // 0 0 0
        // 0 0 0
        // 1 1 1
        else if(game_board[2][0] == player_tag && game_board[2][1] == player_tag && game_board[2][2] == player_tag) {
                Log.i("Win Type", "5");
                game_end_win();
        }

        //Win Situation - 6
        // 1 0 0
        // 1 0 0
        // 1 0 0
        else if(game_board[0][0] == player_tag && game_board[1][0] == player_tag && game_board[2][0] == player_tag) {
                Log.i("Win Type", "6");
                game_end_win();
        }

        //Win Situation - 7
        // 0 1 0
        // 0 1 0
        // 0 1 0
        else if(game_board[0][1] == player_tag && game_board[1][1]== player_tag && game_board[2][1] == player_tag) {
                Log.i("Win Type", "7");
                game_end_win();
        }

        //Win Situation - 8
        // 0 0 1
        // 0 0 1
        // 0 0 1
        else if(game_board[0][2] == player_tag && game_board[1][2] == player_tag && game_board[2][2] == player_tag) {
                Log.i("Win Type", "8");
                game_end_win();
        }

        //Draw Situation
        //Minimum sum of board has to be greater than or equal to 9
        else if((game_board[0][0] + game_board[0][1] + game_board[0][2] + game_board[1][0] + game_board[1][1] + game_board[1][2] +
                game_board[2][0] + game_board[2][1] + game_board[2][2]) >= 9){
            game_end_draw();
        }

    }

    public void game_end_win(){
        if(int_game_type == 1){
            if(player_tag == 1){
                Snackbar snackbar = Snackbar.make(findViewById(android.R.id.content), "You won ;)", Snackbar.LENGTH_LONG);
                snackbar.show();
            }

            else{
                Snackbar snackbar = Snackbar.make(findViewById(android.R.id.content), "You lost :(", Snackbar.LENGTH_LONG);
                snackbar.show();
            }
        }

        else {
            Snackbar snackbar = Snackbar.make(findViewById(android.R.id.content), "Player #" + player_tag + " Won!", Snackbar.LENGTH_LONG);
            snackbar.show();
        }

        flag = 0;
        game_end_flag = 1;
        disable_play_area();

        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                enable_play_area();
                initialize_board(findViewById(android.R.id.content));
            }
        }, 3000);
    }

    public void game_end_draw(){
        Snackbar snackbar = Snackbar.make(findViewById(android.R.id.content), "It is a draw!", Snackbar.LENGTH_LONG);
        snackbar.show();

        flag = 0;
        game_end_flag = 1;
        disable_play_area();

        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                enable_play_area();
                initialize_board(findViewById(android.R.id.content));
            }
        }, 3000);
    }

    public void disable_play_area(){
        ImageView image_00 = (ImageView) findViewById(R.id.image_00);
        ImageView image_01 = (ImageView) findViewById(R.id.image_01);
        ImageView image_02 = (ImageView) findViewById(R.id.image_02);

        ImageView image_10 = (ImageView) findViewById(R.id.image_10);
        ImageView image_11 = (ImageView) findViewById(R.id.image_11);
        ImageView image_12 = (ImageView) findViewById(R.id.image_12);

        ImageView image_20 = (ImageView) findViewById(R.id.image_20);
        ImageView image_21 = (ImageView) findViewById(R.id.image_21);
        ImageView image_22 = (ImageView) findViewById(R.id.image_22);

        image_00.setEnabled(false);
        image_01.setEnabled(false);
        image_02.setEnabled(false);

        image_10.setEnabled(false);
        image_11.setEnabled(false);
        image_12.setEnabled(false);

        image_20.setEnabled(false);
        image_21.setEnabled(false);
        image_22.setEnabled(false);
    }

    public void enable_play_area(){
        ImageView image_00 = (ImageView) findViewById(R.id.image_00);
        ImageView image_01 = (ImageView) findViewById(R.id.image_01);
        ImageView image_02 = (ImageView) findViewById(R.id.image_02);

        ImageView image_10 = (ImageView) findViewById(R.id.image_10);
        ImageView image_11 = (ImageView) findViewById(R.id.image_11);
        ImageView image_12 = (ImageView) findViewById(R.id.image_12);

        ImageView image_20 = (ImageView) findViewById(R.id.image_20);
        ImageView image_21 = (ImageView) findViewById(R.id.image_21);
        ImageView image_22 = (ImageView) findViewById(R.id.image_22);

        image_00.setEnabled(true);
        image_01.setEnabled(true);
        image_02.setEnabled(true);

        image_10.setEnabled(true);
        image_11.setEnabled(true);
        image_12.setEnabled(true);

        image_20.setEnabled(true);
        image_21.setEnabled(true);
        image_22.setEnabled(true);
    }
}

