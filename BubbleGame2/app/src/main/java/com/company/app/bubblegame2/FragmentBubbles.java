package com.company.app.bubblegame2;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class FragmentBubbles extends Fragment {


    private ConstraintLayout constraintLayout;
    private CountInterface countInterface;
    private Timer appearanceTimer;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.bubbles_fragment, container, false);
        constraintLayout  = view.findViewById(R.id.constraintDynamicBubbles);
        appearanceTimer = new Timer();
        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        appearanceTimer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {

                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {

                        final Button buttonBubble = new Button(getContext());
                        final int randomX = new Random().nextInt(((constraintLayout.getWidth()-150)) + 1);
                        final int randomY = new Random().nextInt(((constraintLayout.getHeight()-150)) + 1);

                        buttonBubble.setText("");
                        buttonBubble.setLayoutParams(new ViewGroup.LayoutParams(150, 150));
                        buttonBubble.setX(randomX);
                        buttonBubble.setY(randomY);
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
        appearanceTimer.cancel();
    }

    public interface CountInterface{
         void countIncrease();
         void countDecrease();
    }

    public void setOnCounter(CountInterface countInterface){
        this.countInterface = countInterface;
    }
}
