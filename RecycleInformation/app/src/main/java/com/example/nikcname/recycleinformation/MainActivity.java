package com.example.nikcname.recycleinformation;

import android.content.Intent;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.MenuItem;
import android.widget.PopupMenu;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.Moshi;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private RecyclerView.Adapter myAdapter;
    private RecyclerView.LayoutManager myLayoutManager;
    private StudentAdapter studentAdapter;
    private PopupMenu popupMenu;

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

            @Override
            public void onItemLongClick(final int position, View view) {

                popupMenu = new PopupMenu(getApplicationContext(), view);
                popupMenu.getMenuInflater().inflate(R.menu.popoupmenu, popupMenu.getMenu());
                popupMenu.setOnMenuItemClickListener(menuItem -> {

                    switch (menuItem.getTitle().toString()){

                        case "Delete":
                            studentAdapter.getStudents().remove(position);
                            myAdapter.notifyDataSetChanged();
                            break;
                        case "Edit":
                            DialogFragment fragmentChange = ChangeFragment.newInstance(
                                    studentAdapter.getStudents().get(position).getaName(),
                                    studentAdapter.getStudents().get(position).getaSurname()
                            );
                            ((ChangeFragment) fragmentChange).setOnClickListeners((newName, newSurname) -> {
                                studentAdapter.getStudents().get(position).setaName(newName);
                                studentAdapter.getStudents().get(position).setaSurname(newSurname);
                                myAdapter.notifyDataSetChanged();
                            });

                            fragmentChange.show(getSupportFragmentManager(), "cng");
                            break;
                    }

                    return true;
                });

                popupMenu.show();
            }
        });
    }
}
