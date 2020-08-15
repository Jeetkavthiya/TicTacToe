package com.jeetkavthiya.tictactoe;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        final Button StartGame = (Button)findViewById(R.id.startGame);
        StartGame.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                //Intent intent = new Intent(MainActivity.this, GameActivity.class);
                //startActivity(intent);

                Intent intent = new Intent(getApplicationContext() , GameActivity.class);
                EditText editText1 = findViewById(R.id.player1);
                EditText editText2 = findViewById(R.id.player2);
                String message1 = editText1.getText().toString();
                String message2 = editText2.getText().toString();
                intent.putExtra("player1",message1);
                intent.putExtra("player2",message2);
                if(editText1.length() == 0)
                {
                    editText1.setError("Enter Player1 Name");
                }
                else if(editText2.length() == 0)
                {
                    editText2.setError("Enter Player2 Name");
                }
                else
                {
                    startActivity(intent);
                }
            }
        });
    }
}
