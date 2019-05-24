package com.example.nikcname.recycleinformation;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.Moshi;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private RecyclerView.Adapter myAdapter;
    private RecyclerView.LayoutManager myLayoutManager;
    private StudentAdapter studentAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recycleInfoView);
        myLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(myLayoutManager);


        String jsonString = "{\"students\":[{\"aGrade\":8.5,\"aName\":\"John\",\"aPhoneNumber\":5550123,\"aSurname\":\"Lawless\",\"anAge\":45},{\"aGrade\":7.9,\"aName\":\"Kara\",\"aPhoneNumber\":4541123,\"aSurname\":\"Gold\",\"anAge\":19},{\"aGrade\":6.1,\"aName\":\"Lancer\",\"aPhoneNumber\":7845466,\"aSurname\":\"Bow\",\"anAge\":28}]}";
        Moshi moshi = new Moshi.Builder().build();
        JsonAdapter<StudentAdapter> jsonAdapter = moshi.adapter(StudentAdapter.class);

        try {
            studentAdapter = jsonAdapter.fromJson(jsonString);
        } catch (IOException e) {
            e.printStackTrace();
        }


        myAdapter = new InfoAdapter(studentAdapter.getStudents());
        recyclerView.setAdapter(myAdapter);

    }

    @Override
    protected void onStart() {
        super.onStart();

        ((InfoAdapter) myAdapter).setOnItemClickListener(new InfoAdapter.Click() {
            @Override
            public void onItemClick(int position) {

                Intent intent = new Intent(getApplicationContext(), ExtendedInfoActivity.class);

                Bundle bundle = new Bundle();

                bundle.putSerializable("info", studentAdapter.getStudents().get(position));

                intent.putExtras(bundle);

                startActivity(intent);

                myAdapter.notifyDataSetChanged();

            }
        });

    }
}
