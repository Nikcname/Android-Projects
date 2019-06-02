package com.shirinov.joshgun.bubblegame2;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

public class SecondActivity extends AppCompatActivity {
    private int counter = 0;
    private int score = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        FragmentBubbles fragment = new FragmentBubbles();
        fragment.setOnCounter(new FragmentBubbles.CountInterface() {
            @Override
            public void countIncrease() {
                counter++;
                score++;
//                Toast.makeText(getApplicationContext(),counter + " " + score,Toast.LENGTH_SHORT).show();


            }

            @Override
            public void countDecrease() {
                counter--;
//                Toast.makeText(getApplicationContext(), counter + " " + score, Toast.LENGTH_SHORT).show();



            }
        });

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        fragmentTransaction.add(R.id.bubbles_fragment_element, fragment);
        fragmentTransaction.commit();


    }




}
