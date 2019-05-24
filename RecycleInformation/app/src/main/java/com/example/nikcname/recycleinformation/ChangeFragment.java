package com.example.nikcname.recycleinformation;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

public class ChangeFragment extends DialogFragment {

    TextView textViewName;
    TextView textViewSurname;
    Button buttonCancel;
    Button buttonOk;
    ClickFeedback clickListeners;

    public static ChangeFragment newInstance(String aName, String aSurname) {

        ChangeFragment fragment = new ChangeFragment();
        Bundle args = new Bundle();
        args.putString("name", aName);
        args.putString("surname", aSurname);
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.dialog_fragmet, container, false);
        textViewName = view.findViewById(R.id.editTextName);
        textViewSurname = view.findViewById(R.id.editTextSurname);
        buttonOk = view.findViewById(R.id.buttonOk);
        buttonCancel = view.findViewById(R.id.buttonCancel);

        return view;
    }

    @Override
    public void onStart() {
        super.onStart();

        textViewName.setText(getArguments().getString("name"));
        textViewSurname.setText(getArguments().getString("surname"));

        buttonOk.setOnClickListener(e->{
            clickListeners.onReturnedVariables(
                    textViewName.getText().toString(),
                    textViewSurname.getText().toString()
            );
            dismiss();
        });

        buttonCancel.setOnClickListener(e->{
            dismiss();
        });

    }

    interface ClickFeedback{
        void onReturnedVariables(String newName, String newSurname);
    }

    public void setOnClickListeners(ClickFeedback clickListeners){
        this.clickListeners = clickListeners;
    }
}
