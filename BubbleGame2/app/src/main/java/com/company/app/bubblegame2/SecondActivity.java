package com.company.app.bubblegame2;

import android.app.Dialog;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

public class SecondActivity extends AppCompatActivity {
    private int counter = 0;
    private int score = 0;
    ScoreLimit scoreLimit;
    StopTimer stopTimer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        final int limit = getIntent().getExtras().getInt("limit");

        FragmentBubbles fragmentBubbles = FragmentBubbles.newInstance(limit);

        LimitFragment limitFragment = LimitFragment.newInstance(limit);

        fragmentBubbles.setOnCounter(new FragmentBubbles.CountInterface() {

            boolean flagDone = false;

            @Override
            public void countIncrease() {
                counter++;
                if ((counter >= limit) && !flagDone){

                    flagDone = true;

                    stopTimer.stopTimer();
                    LostDialog lostDialog = new LostDialog();
                    lostDialog.show(getSupportFragmentManager(), "lost");

                }
            }

            @Override
            public void countDecrease() {
                counter--;
            }

            @Override
            public void countScore() {
                score++;
                scoreLimit.setScore(score);
            }
        });

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        fragmentTransaction.add(R.id.bubbles_fragment_element, fragmentBubbles);
        fragmentTransaction.add(R.id.limit_score_element, limitFragment);
        fragmentTransaction.commit();
    }


    public interface ScoreLimit{
        void setScore(int score);
    }

    public interface StopTimer{
        void stopTimer();
    }

    public void setStopTimer(StopTimer stopTimer){
        this.stopTimer = stopTimer;
    }

    public void setOnLimitScoreListener(ScoreLimit scoreLimit){
        this.scoreLimit = scoreLimit;
    }

}
