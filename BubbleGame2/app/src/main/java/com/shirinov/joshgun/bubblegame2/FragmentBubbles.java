package com.shirinov.joshgun.bubblegame2;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class FragmentBubbles extends Fragment {


    private ConstraintLayout constraintLayout;
    private Timer timer;
    int random;
    private CountInterface countInterface;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.bubbles_fragment, container, false);

        constraintLayout  = (ConstraintLayout) view.findViewById(R.id.constraintDynamicBubbles);

        timer=new Timer();

        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {

                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {

                        final Button buttonBubble = new Button(getContext());
                        buttonBubble.setText("");
                        buttonBubble.setLayoutParams(new ViewGroup.LayoutParams(150, 150));
                        buttonBubble.setX(new Random().nextInt(((constraintLayout.getWidth()-150)) + 1));
                        buttonBubble.setY(new Random().nextInt(((constraintLayout.getHeight()-150)) + 1));
                        buttonBubble.setBackground(getResources().getDrawable(R.drawable.button_style));
                        constraintLayout.addView(buttonBubble);
                        countInterface.countIncrease();

                        buttonBubble.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {

                                constraintLayout.removeView(buttonBubble);
                                countInterface.countDecrease();
                            }
                        });
                    }
                });

            }
        }, 1000, 1000);


    }

    @Override
    public void onStop() {
        super.onStop();
        timer.cancel();
    }

    public interface CountInterface{
         void countIncrease();
         void countDecrease();
    }

    public void setOnCounter(CountInterface countInterface){
        this.countInterface = countInterface;

    }
}
