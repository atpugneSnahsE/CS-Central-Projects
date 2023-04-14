package com.lovishprabhakar.tic_tac_toe;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

//    Player representation:
//    0 - x's turn
//    1 - o's turn
    boolean gameActive = true;
    int activePlayer = 0;
    int[] gameState = {2,2,2,2,2,2,2,2,2};
//    State representation:
//    0 - x in the cell
//    1 - o in the cell
//    2 - Blank cell
    int[][] winPositions = { {0,1,2}, {3,4,5}, {6,7,8},
                             {0,3,6}, {1,4,7}, {2,5,8},
                             {0,4,8}, {2,4,6} };
    public void playerTap(View view){
        ImageView img = (ImageView) view;
        int tappedImg = Integer.parseInt(img.getTag().toString());
        if(!gameActive){
            gameReset(view);
            return;
        }
        if(gameState[tappedImg]==2){
            gameState[tappedImg] = activePlayer;
            img.setTranslationY(-1000f);
            if(activePlayer==0){
                img.setImageResource(R.drawable.x);
                activePlayer = 1;
                TextView status = findViewById(R.id.status);
                status.setText("O's Turn - Tap to play");
            }
            else{
                img.setImageResource(R.drawable.o);
                activePlayer = 0;
                TextView status = findViewById(R.id.status);
                status.setText("X's Turn - Tap to play");
            }
            img.animate().translationYBy(1000f).setDuration(300);
        }
//        Checking if somebody has won:
        for(int[] winPosition : winPositions){
            if(gameState[winPosition[0]]==gameState[winPosition[1]] && gameState[winPosition[1]]==gameState[winPosition[2]] && gameState[winPosition[0]]!=2){
//      Somebody has won - find out who:
                String winnerStr;
                gameActive = false;
                if(gameState[winPosition[0]]==0){
                        winnerStr = "X has won! Tap any cell to reset.";
                }
                else{
                    winnerStr = "O has won! Tap any cell to reset.";
                }
                TextView status = findViewById(R.id.status);
                status.setText(winnerStr);
            }
        }
//        Checking for draw:
        boolean emptySquare = false;
        for (int squareState : gameState) {
            if (squareState == 2) {
                emptySquare = true;
                break;
            }
        }
        if (!emptySquare && gameActive) {
            // Game is a draw
            gameActive = false;
            String winnerStr;
            winnerStr = "It's a draw! Tap any cell to reset.";
            TextView status = findViewById(R.id.status);
            status.setText(winnerStr);
        }


    }

    public void gameReset(View view){
        gameActive = true;
        activePlayer = 0;
        for(int i=0;i<gameState.length;i++){
            gameState[i]=2;
        }
        ((ImageView) findViewById(R.id.imageView0)).setImageResource(0);
        ((ImageView) findViewById(R.id.imageView1)).setImageResource(0);
        ((ImageView) findViewById(R.id.imageView2)).setImageResource(0);
        ((ImageView) findViewById(R.id.imageView3)).setImageResource(0);
        ((ImageView) findViewById(R.id.imageView4)).setImageResource(0);
        ((ImageView) findViewById(R.id.imageView5)).setImageResource(0);
        ((ImageView) findViewById(R.id.imageView6)).setImageResource(0);
        ((ImageView) findViewById(R.id.imageView7)).setImageResource(0);
        ((ImageView) findViewById(R.id.imageView8)).setImageResource(0);
        TextView status = findViewById(R.id.status);
        status.setText("X's Turn - Tap to play");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}