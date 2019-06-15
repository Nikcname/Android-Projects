package com.company.app.bubblegame2;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


public class LimitFragment extends Fragment {

    TextView textViewLimit;
    TextView textViewScore;

    public static LimitFragment newInstance(int limit) {

        Bundle args = new Bundle();
        args.putInt("limit", limit);
        LimitFragment fragment = new LimitFragment();
        fragment.setArguments(args);
        return fragment;
    }

    public LimitFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_limit, container, false);

        textViewLimit = v.findViewById(R.id.tv_limit);
        textViewLimit.setText("Limit: " +String.valueOf(getArguments().getInt("limit")));
        textViewScore = v.findViewById(R.id.tv_score);

        return v;
    }

    @Override
    public void onStart() {
        super.onStart();

        ((SecondActivity) getActivity()).setOnLimitScoreListener(new SecondActivity.ScoreLimit() {
            @Override
            public void setScore(int score) {
                textViewScore.setText("Score: " + String.valueOf(score));
            }
        });
    }
}
