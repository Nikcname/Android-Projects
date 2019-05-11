package com.example.nikcname.addname;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class GiveNameFragment extends DialogFragment {

    View v;
    EditText mName;
    EditText mSurname;
    Button btnShowName;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        v = inflater.inflate(R.layout.frag_give_name, container, false);
        mName = v.findViewById(R.id.txtName);
        mSurname = v.findViewById(R.id.txtSurname);
        btnShowName = v.findViewById(R.id.btnToastName);

        return v;
    }

    @Override
    public void onStart() {
        super.onStart();

        btnShowName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append(mName.getText().toString());
                stringBuilder.append(" ");
                stringBuilder.append(mSurname.getText().toString());

                Toast.makeText(getContext(), stringBuilder.toString(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
