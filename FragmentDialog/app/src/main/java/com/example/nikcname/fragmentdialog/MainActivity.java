package com.example.nikcname.fragmentdialog;

import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button buttonExit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        buttonExit = findViewById(R.id.btnExit);
    }

    @Override
    protected void onStart() {
        super.onStart();

        buttonExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DialogFragment alert = new ExitAlertDialogFragment();
                alert.show(getSupportFragmentManager(), "alert");
            }
        });
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {

        switch (keyCode){
            case KeyEvent.KEYCODE_BACK:
                DialogFragment alert = new ExitAlertDialogFragment();
                alert.show(getSupportFragmentManager(), "alert");
                return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}
