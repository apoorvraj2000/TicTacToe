package com.example.tictactoe;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.logging.XMLFormatter;

public class MainGame extends AppCompatActivity {
    boolean gameActive=true;

    //Representation
    //0->X
    //1->0
    int activePlayer=0;
    int [] gameState={2,2,2,2,2,2,2,2,2};
    //Meaning
    // 0->X
    // 1->0
    // 2->null
    int[][]winPositions={{0,1,2},{3,4,5},{6,7,8},
                        {0,3,6},{1,4,7},{2,5,8},
                        {0,4,8},{2,4,6}};
    boolean gameOver = false;
    @SuppressLint("SetTextI18n")
    public void playerTap(View view) {
        ImageView img=(ImageView)view;
        int tapedImage=Integer.parseInt(img.getTag().toString());
//        if(!gameActive){
//            gameReset(view);
//        } //Remove because we are using Reset button
        if(gameState[tapedImage]==2 && !gameOver){
            gameState[tapedImage]=activePlayer;
            img.setTranslationY(-1000f);
            if(activePlayer==0){
                img.setImageResource(R.drawable.x);
                activePlayer=1;
                TextView status=findViewById(R.id.status);
                status.setText("O's Turn - Tap to play");
            }
            else{
                img.setImageResource(R.drawable.o);
                activePlayer=0;
                TextView status=findViewById(R.id.status);
                status.setText("X's Turn - Tap to play");
            }
            img.animate().translationYBy(1000f).setDuration(300);
        }
        //check for winning
        for(int []winpos:winPositions){
            if(gameState[winpos[0]]==gameState[winpos[1]]&&gameState[winpos[1]]==gameState[winpos[2]]&&gameState[winpos[0]]!=2){
                String str;
                gameActive=false;
                //to check who is winner
                if(gameState[winpos[0]]==0){
                    str="X has Won!";
                }else {
                    str="O has won!";
                }
                TextView status=findViewById(R.id.status);
                status.setText(str+"\n"+"Please Restart The Game");
                gameOver = true;
            }

        }

            //Check for Game Draw
        boolean emptySquare = false;
        for (int squareState : gameState) {
            if (squareState == 2) {
                emptySquare = true;
                break;
            }
        }

        if (!emptySquare && !gameOver) {
            // Game is a draw
            gameOver = true;
            // Set draw message here...
            TextView status=findViewById(R.id.status);
            status.setText("Game is a draw"+"\n"+"Please Restart The Game");
        }


    }

    public void gameReset(View view) {
        gameActive=true;
        gameOver = false;
        activePlayer=0;
        for(int i=0;i<gameState.length;i++){
            gameState[i]=2;
        }
        ((ImageView)findViewById(R.id.imageView0)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView1)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView2)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView3)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView4)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView5)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView6)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView7)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView8)).setImageResource(0);
        TextView status=findViewById(R.id.status);
        status.setText("X's Turn - Tap to play");
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_game);
    }


}