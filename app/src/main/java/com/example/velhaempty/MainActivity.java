package com.example.velhaempty;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.Objects;

public class MainActivity extends AppCompatActivity {
    private Button btnReset,btnResetScore;
    private TextView score;
    private Button[][] buttons = new Button[3][3];
    private String player = "X";
    private boolean gameover = false;


    int scoreX = 0;
    int scoreO = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        btnReset = findViewById(R.id.btnReset);
        score = findViewById(R.id.tvPlacar);
        btnResetScore = findViewById(R.id.btnResetScore);

        btnReset.setOnClickListener(this::resetGame);
        btnResetScore.setOnClickListener(this::setBtnResetScore);
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                String buttonID = "bt" + i + j;
                int resID = getResources().getIdentifier(buttonID, "id", getPackageName());
                buttons[i][j] = findViewById(resID);
                buttons[i][j].setOnClickListener(this::onButtonClick);
            }
        }

    }


    private void onButtonClick(View view) {

        if(!gameover){
            Button button = (Button) view;


            if (button.getText().toString().isEmpty()) {
                button.setText(player);

                // Alterna o jogador
                player = player.equals("X") ? "O" : "X";


                if (checkForWin()) {
                    if(Objects.equals(player, "X")){
                        scoreX  += 1;
                    }else {
                        scoreO += 1;
                    }
                    this.scoreBoard(scoreX,scoreO);
                    gameover = true;


                }
            }


        }

    }


    private void setBtnResetScore(View view){
        scoreO = 0;
        scoreX = 0;
        scoreBoard(scoreX,scoreO);
        resetGame(view);
    }
    private void scoreBoard( int scoreO,int scoreX) {
        String placar = String.format("Placar: X - %d | O - %d", scoreX, scoreO);
        score.setText(placar);
    }
    private void resetGame(View view){
        gameover = false;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {

                buttons[i][j].setText("");
                buttons[i][j].setTextColor(Color.WHITE);
            }
        }

    }
    private boolean checkForWin() {
        Button[] winnerPlay = new Button[3];


        for (int i = 0; i < 3; i++) {
            if (buttons[i][0].getText().toString().equals(buttons[i][1].getText().toString()) &&
                    buttons[i][0].getText().toString().equals(buttons[i][2].getText().toString()) &&
                    !buttons[i][0].getText().toString().isEmpty()) {
                winnerPlay[0] = buttons[i][0];
                winnerPlay[1] = buttons[i][1];
                winnerPlay[2] = buttons[i][2];
                changeWinnerPlayColor(winnerPlay);
                return true;
            }
        }


        for (int j = 0; j < 3; j++) {
            if (buttons[0][j].getText().toString().equals(buttons[1][j].getText().toString()) &&
                    buttons[0][j].getText().toString().equals(buttons[2][j].getText().toString()) &&
                    !buttons[0][j].getText().toString().isEmpty()) {
                winnerPlay[0] = buttons[0][j];
                winnerPlay[1] = buttons[1][j];
                winnerPlay[2] = buttons[2][j];
                changeWinnerPlayColor(winnerPlay);
                return true;
            }
        }


        if (buttons[0][0].getText().toString().equals(buttons[1][1].getText().toString()) &&
                buttons[0][0].getText().toString().equals(buttons[2][2].getText().toString()) &&
                !buttons[0][0].getText().toString().isEmpty()) {
            winnerPlay[0] = buttons[0][0];
            winnerPlay[1] = buttons[1][1];
            winnerPlay[2] = buttons[2][2];
            changeWinnerPlayColor(winnerPlay);
            return true;
        }


        if (buttons[0][2].getText().toString().equals(buttons[1][1].getText().toString()) &&
                buttons[0][2].getText().toString().equals(buttons[2][0].getText().toString()) &&
                !buttons[0][2].getText().toString().isEmpty()) {
            winnerPlay[0] = buttons[0][2];
            winnerPlay[1] = buttons[1][1];
            winnerPlay[2] = buttons[2][0];
            changeWinnerPlayColor(winnerPlay);
            return true;
        }

        return false;
    }

    private void changeWinnerPlayColor(Button[] btns){
        for (Button bt : btns){
            bt.setTextColor(Color.GREEN);
        }
    }






}