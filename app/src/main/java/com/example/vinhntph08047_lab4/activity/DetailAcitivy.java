package com.example.vinhntph08047_lab4.activity;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.example.vinhntph08047_lab4.R;
import com.flipboard.bottomsheet.BottomSheetLayout;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

public class DetailAcitivy extends AppCompatActivity {
    ImageView img;
    BottomSheetLayout bottomSheetLayout;
    private TextView tvShare;
    private TextView tvSet;
    private TextView tvUrlSq;
    private TextView tvUrlT;
    private TextView tvUrlS;
    private TextView tvUrlM;
    private TextView tvUrlZ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_acitivy);
        img = findViewById(R.id.img_detail);
        bottomSheetLayout = findViewById(R.id.bottom_sheet);
        Intent intent = getIntent();
        String link = intent.getStringExtra("KEY");
        if (link != null) {
            Glide.with(this).load(link).into(img);
        }
        View sheet_view = LayoutInflater.from(this).inflate(R.layout.bottom_sheet, bottomSheetLayout, false);
        tvShare = sheet_view.findViewById(R.id.tv_share);
        tvSet = sheet_view.findViewById(R.id.tv_set);
        tvUrlSq = sheet_view.findViewById(R.id.tv_url_sq);
        tvUrlT = sheet_view.findViewById(R.id.tv_url_t);
        tvUrlS = sheet_view.findViewById(R.id.tv_url_s);
        tvUrlM = sheet_view.findViewById(R.id.tv_url_m);
        tvUrlZ = sheet_view.findViewById(R.id.tv_url_z);
        img.setOnLongClickListener(view -> {
            bottomSheetLayout.showWithSheetView(sheet_view);
            return true;
        });
        tvShare.setOnClickListener(view -> {
        });
        tvUrlM.setOnClickListener(view -> {

        });
    }
}