package com.shirinov.joshgun.bubblegame2;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

public class MainActivity extends AppCompatActivity {

    private String TAG = "dddd";
    RecyclerView recyclerView;
    RecyclerView.Adapter adapter;
    RecyclerView.LayoutManager manager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerView);

        manager = new LinearLayoutManager(this);

        recyclerView.setLayoutManager(manager);

        adapter = new MyAdapter();

        ((MyAdapter) adapter).setClickListener(new MyAdapter.OnItemClick() {
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


                Intent intent = new Intent(getApplicationContext(), SecondActivity.class);
                intent.putExtra("limit", limit);
                startActivity(intent);
                Log.d(TAG, String.valueOf(limit));

            }
        });

        recyclerView.setAdapter(adapter);

    }
}
