package com.example.vinhntph08047_lab4.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.vinhntph08047_lab4.R;

public class DetailAcitivy extends AppCompatActivity {
    ImageView img;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_acitivy);
        img = findViewById(R.id.img_detail);
        Intent intent = getIntent();
        String link = intent.getStringExtra("KEY");
        if (link != null) {
            Glide.with(this).load(link).into(img);
        }

    }
}