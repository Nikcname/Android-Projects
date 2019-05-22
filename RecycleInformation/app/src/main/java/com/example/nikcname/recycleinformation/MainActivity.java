package com.example.nikcname.recycleinformation;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private RecyclerView.Adapter myAdapter;
    private RecyclerView.LayoutManager myLayoutManager;
    private List<Student> students;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        students = new ArrayList<>();
        recyclerView = findViewById(R.id.recycleInfoView);
        myLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(myLayoutManager);
        myAdapter = new InfoAdapter(students);
        recyclerView.setAdapter(myAdapter);
    }

    @Override
    protected void onStart() {
        super.onStart();

        String jsonSt = "{\"name\":\"Farrukh\",\"surname\":\"Nabiyev\",\"phone\":5578211,\"grade\":82.4,\"age\":22}";

        Gson gson = new Gson();

        Student student1 = gson.fromJson(jsonSt, Student.class);

        try {
            JSONObject obj = new JSONObject(jsonSt);

            for (int i = 0; i < obj.length(); i++){

                Log.d("tedd", String.valueOf(obj.names().get(i)));
            }


        } catch (JSONException e) {
            Log.e("tedd", e.toString());
        }

        students.add(new Student("Farrukh", "Nabiyev",
                5578211, 82.4, 22));

        students.add(new Student("Cavid", "Eliyev",
                4791544, 74.4, 22));

        ((InfoAdapter) myAdapter).setOnItemClickListener(new InfoAdapter.Click() {
            @Override
            public void onItemClick(int position) {

                Intent intent = new Intent(getApplicationContext(), ExtendedInfoActivity.class);

                Bundle bundle = new Bundle();

                bundle.putSerializable("info", students.get(position));

                intent.putExtras(bundle);

                startActivity(intent);

            }
        });

    }
}
