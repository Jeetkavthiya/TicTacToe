package com.jeetkavthiya.tictactoe;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class GameActivity extends AppCompatActivity {
    boolean gameActive = true;
    int player1score = 0;
    int player2score = 0;
    //Player Representation
    //0 - X
    //1 - O



    int activePlayer = 0;
    int[] gameState =  {2,2,2,2,2,2,2,2,2};

    //State Meaning
    //0 - X
    //1 - O
    //2 - Null

    int[][] winPositions = {    {0,1,2},{3,4,5},{6,7,8},
            {0,3,6},{1,4,7},{2,5,8},
            {0,4,8},{2,4,6}     };

    public void playerTap(View view){
        TextView player1 = findViewById(R.id.player1Name);
        TextView player2 = findViewById(R.id.player2Name);
        String player1name = getIntent().getStringExtra("player1");
        String player2name = getIntent().getStringExtra("player2");
        player1.setText(player1name);
        player2.setText(player2name);
        ImageView img = (ImageView) view;
        int tappedImage = Integer.parseInt(img.getTag().toString());
        if(!gameActive)
        {
            gameReset(view,activePlayer);
        }
        if(gameState[tappedImage] == 2 ) {
            gameState[tappedImage] = activePlayer;
            img.setTranslationY(-1000f);
            if (activePlayer == 0) {
                img.setImageResource(R.drawable.x);
                activePlayer = 1;
                TextView status = findViewById(R.id.status);
                status.setText(player2name+"'s Turn");
            } else {
                img.setImageResource(R.drawable.o);
                activePlayer = 0;
                TextView status = findViewById(R.id.status);
                status.setText(player1name+"'s Turn");
            }
            img.animate().translationYBy(1000f).setDuration(300);
        }
        //check if any player has won
        for (int[] winPosition : winPositions) {
            if(gameState[winPosition[0]] == gameState[winPosition[1]] &&
                    gameState[winPosition[1]] == gameState[winPosition[2]] &&
                    gameState[winPosition[0]] != 2){
                String winnerStr;
                if(gameState[winPosition[0]] == 0){
                    //winnerStr = player1name + " has won";
                    Toast.makeText(GameActivity.this,player1name+" has Won",Toast.LENGTH_SHORT).show();
                    player1score++;
                    activePlayer = 0;
                    gameReset(view,activePlayer);
                    gameActive = false;
                    TextView player1s = findViewById(R.id.player1Score);
                    TextView player2s = findViewById(R.id.player2Score);
                    player1s.setText(String.valueOf(player1score));
                    player2s.setText(String.valueOf(player2score));
                }
                else{
                    gameActive = false;
                    //winnerStr = player2name+" has won";
                    Toast.makeText(GameActivity.this,player2name+" has won",Toast.LENGTH_SHORT).show();
                    player2score++;
                    activePlayer=1;
                    gameReset(view,activePlayer);
                    TextView player1s = findViewById(R.id.player1Score);
                    TextView player2s = findViewById(R.id.player2Score);
                    player1s.setText(String.valueOf(player1score));
                    player2s.setText(String.valueOf(player2score));
                }
            }
            else if(gameState[0]!=2 && gameState[1]!=2 && gameState[2]!=2 && gameState[3]!=2 && gameState[4]!=2 && gameState[5]!=2 && gameState[6]!=2 && gameState[7]!=2 && gameState[8]!=2)
            {
                Toast.makeText(GameActivity.this,"Draw Match",Toast.LENGTH_SHORT).show();
                gameReset(view,activePlayer);
                TextView player1s = findViewById(R.id.player1Score);
                TextView player2s = findViewById(R.id.player2Score);
                player1s.setText(String.valueOf(player1score));
                player2s.setText(String.valueOf(player2score));
            }

        }
    }

    public void gameReset(View view,int ap){
        TextView player1 = findViewById(R.id.player1Name);
        TextView player2 = findViewById(R.id.player2Name);
        String player1name = getIntent().getStringExtra("player1");
        String player2name = getIntent().getStringExtra("player2");

        gameActive = true;
        activePlayer = ap;
        for (int i=0;i<gameState.length;i++)
            gameState[i]=2;
        ((ImageView)findViewById(R.id.imageView1)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView2)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView3)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView4)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView5)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView6)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView7)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView8)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView9)).setImageResource(0);
        TextView status = findViewById(R.id.status);
        if(activePlayer == 0)
        {
            status.setText(player1name+"'s Turn");
        }
        else {
            status.setText(player2name+"'s Turn");
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        final Button StartGame = (Button)findViewById(R.id.backButton);
        StartGame.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent1 = new Intent(GameActivity.this, MainActivity.class);
                startActivity(intent1);
            }
        });

        final Button Reset = (Button)findViewById(R.id.resetButton);
        Reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                player1score = 0;
                player2score = 0;
                TextView player1s = findViewById(R.id.player1Score);
                TextView player2s = findViewById(R.id.player2Score);
                player1s.setText(String.valueOf(player1score));
                player2s.setText(String.valueOf(player2score));
                gameReset(v,activePlayer);
            }
        });
        TextView player1 = findViewById(R.id.player1Name);
        TextView player2 = findViewById(R.id.player2Name);
        String player1name = getIntent().getStringExtra("player1");
        String player2name = getIntent().getStringExtra("player2");
        player1.setText(player1name);
        player2.setText(player2name);
    }
}
