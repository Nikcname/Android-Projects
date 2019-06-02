package com.company.app.bubblegame2;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

public class MainActivity extends AppCompatActivity {

    private String TAG = "MainActivity";
    RecyclerView recyclerView;
    RecyclerView.Adapter recyclerAdapter;
    RecyclerView.LayoutManager recyclerManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerView);

        recyclerManager = new LinearLayoutManager(this);

        recyclerView.setLayoutManager(recyclerManager);

        recyclerAdapter = new MyAdapter();

        ((MyAdapter) recyclerAdapter).setClickListener(new MyAdapter.OnItemClick() {
            @Override
            public void onClicked(int level) {

                int limit = 0;

                switch (level){
                    case 0:
                        limit = 5;
                        break;
                    case 1:
                        limit = 10;
                        break;
                    case 2:
                        limit = 15;
                        break;
                    case 3:
                        limit = 20;
                        break;
                    case 4:
                        limit = 25;
                        break;
                }

                Intent secondActivity = new Intent(getApplicationContext(), SecondActivity.class);
                secondActivity.putExtra("limit", limit);
                startActivity(secondActivity);
                Log.d(TAG, String.valueOf(limit));

            }
        });

        recyclerView.setAdapter(recyclerAdapter);
    }
}
