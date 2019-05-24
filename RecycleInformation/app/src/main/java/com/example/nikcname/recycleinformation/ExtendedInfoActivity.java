package com.example.nikcname.recycleinformation;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class ExtendedInfoActivity extends AppCompatActivity {

    ImageView imageViewPhoto;
    TextView textViewName;
    TextView textViewSurname;
    TextView textViewPhone;
    TextView textViewAge;
    TextView textViewGrade;
    Student student;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_extended_info);

        textViewName = findViewById(R.id.textViewInfoName);
        textViewSurname = findViewById(R.id.textViewInfoSurname);
        textViewAge = findViewById(R.id.textViewInfoAge);
        textViewGrade = findViewById(R.id.textViewInfoGrade);
        textViewPhone = findViewById(R.id.textViewInfoPhone);
        imageViewPhoto = findViewById(R.id.imageViewPicture);

        student = (Student) getIntent().getExtras().get("info");

    }

    @Override
    protected void onStart() {
        super.onStart();

        textViewName.setText(student.getaName());
        textViewSurname.setText(student.getaSurname());
        textViewPhone.setText(String.valueOf(student.getaPhoneNumber()));
        textViewGrade.setText(String.valueOf(student.getaGrade()));
        textViewAge.setText(String.valueOf(student.getAnAge()));
        Picasso.get()
                .load("https://source.unsplash.com/random")
                .resize(100, 100)
                .centerCrop()
                .into(imageViewPhoto);
    }
}
