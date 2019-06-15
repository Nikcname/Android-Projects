package com.company.app.bubblegame2;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Build;
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
import android.widget.ImageButton;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class FragmentBubbles extends Fragment {

    private List<Integer> randomsArrayX = new ArrayList<>();
    private List<Integer> randomsArrayY = new ArrayList<>();

    private ConstraintLayout constraintLayout;
    private CountInterface countInterface;
    private Timer appearanceTimer;

    public static FragmentBubbles newInstance(int limit) {

        Bundle args = new Bundle();

        args.putInt("limit", limit);

        FragmentBubbles fragment = new FragmentBubbles();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.bubbles_fragment, container, false);
        constraintLayout  = view.findViewById(R.id.constraintDynamicBubbles);
        appearanceTimer = new Timer();

        ((SecondActivity)getActivity()).setStopTimer(new SecondActivity.StopTimer() {
            @Override
            public void stopTimer() {
                appearanceTimer.cancel();
            }
        });

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

                        for (int i = 0; i < getArguments().getInt("limit")/5; i++) {

                            final Button buttonBubble = new Button(getContext());
                            int randomX;
                            int randomY;
                            boolean overlap;

                            do {
                                overlap = false;
                                randomX = new Random().nextInt(((constraintLayout.getWidth() - 150)) + 1);
                                randomY = new Random().nextInt(((constraintLayout.getHeight() - 150)) + 1);

                                for (int j = 0; j < randomsArrayX.size(); j++){

                                    if (
                                            Math.abs(randomsArrayX.get(j) - randomX) < 150 &&
                                            Math.abs(randomsArrayY.get(j) - randomY) < 150
                                            )
                                    {
                                            overlap = true;
                                            break;
                                    }
                                }
                            }while (overlap);

                            randomsArrayX.add(randomX);
                            randomsArrayY.add(randomY);

                            int randomRadius = new Random().nextInt(75) + 75; // [0, 60] + 20 => [20, 80]

                            buttonBubble.setText("");
                            buttonBubble.setLayoutParams(new ViewGroup.LayoutParams(randomRadius, randomRadius));
                            buttonBubble.setX(randomX);
                            buttonBubble.setY(randomY);
                            buttonBubble.setSoundEffectsEnabled(false);
                            buttonBubble.setBackground(getResources().getDrawable(R.drawable.button_style));

                            constraintLayout.addView(buttonBubble);
                            countInterface.countIncrease();

                            buttonBubble.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {

                                    getActivity().startService(new Intent(getActivity(), SoundEffect.class));

                                    constraintLayout.removeView(buttonBubble);
                                    countInterface.countDecrease();
                                    countInterface.countScore();
                                    randomsArrayX.remove(Integer.valueOf((int) buttonBubble.getX()));
                                    randomsArrayY.remove(Integer.valueOf((int) buttonBubble.getY()));
                                }
                            });
                        }
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
         void countScore();
    }

    public void setOnCounter(CountInterface countInterface){
        this.countInterface = countInterface;
    }
}
