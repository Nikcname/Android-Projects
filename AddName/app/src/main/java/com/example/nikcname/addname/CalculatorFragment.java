package com.example.nikcname.addname;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class CalculatorFragment extends DialogFragment {

    Button btnAdd;
    View v;
    TextView tvNumOne;
    TextView tvNumTwo;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        v = inflater.inflate(R.layout.frag_calc, container,false);
        tvNumOne = v.findViewById(R.id.numOne);
        tvNumTwo = v.findViewById(R.id.numTwo);
        btnAdd = v.findViewById(R.id.btnToastSum);

        return v;
    }

    @Override
    public void onStart() {
        super.onStart();

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String first = tvNumOne.getText().toString();
                String second = tvNumTwo.getText().toString();
                if (!first.equals("") && !second.equals("")){
                    String sum = String.valueOf(Integer.parseInt(first)
                            + Integer.parseInt(second));
                    Toast.makeText(getContext(), sum, Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
