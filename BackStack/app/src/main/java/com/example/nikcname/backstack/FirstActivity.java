package com.example.nikcname.backstack;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

public class FirstActivity extends AppCompatActivity {

    private final String TAG = "debugging";
    private final String ID = "First";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i(TAG, "On Create " + ID);
        setContentView(R.layout.activity_first);
    }

    public void secondButtonClicked(View view) {

        Intent intent = new Intent(this, SecondActivity.class);
        startActivity(intent);

    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.i(TAG, "On Start " + ID);
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i(TAG, "On Resume " + ID);
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.i(TAG, "On Pause " + ID);
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.i(TAG, "On Stop " + ID);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i(TAG, "On Destroy " + ID);
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.i(TAG, "On Restart " + ID);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        Log.i(TAG, "On saved instance state " + ID);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        Log.i(TAG, "On restore instance state " + ID);
    }

    public void firstBottomButtonClicked(View view) {
        Intent intent = new Intent(this, FirstActivity.class);
        startActivity(intent);
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        Toast.makeText(getApplicationContext(), "Single top", Toast.LENGTH_SHORT).show();
    }

    public void startNewTaskButtonClicked(View view) {

        Intent intent = new Intent(this, SecondActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);

    }
}
