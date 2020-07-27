package com.example.vinhntph08047_lab4.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.example.vinhntph08047_lab4.Constant;
import com.example.vinhntph08047_lab4.R;
import com.example.vinhntph08047_lab4.async.DownloadImageAsync;
import com.example.vinhntph08047_lab4.model.RootModel;
import com.flipboard.bottomsheet.BottomSheetLayout;

public class DetailAcitivy extends AppCompatActivity {
    private ImageView img;
    private BottomSheetLayout bottomSheetLayout;
    private TextView tvShare;
    private TextView tvUrlSq;
    private TextView tvUrlT;
    private TextView tvUrlS;
    private TextView tvUrlM;
    private TextView tvUrlZ;
    private TextView tvSet;
    private DownloadImageAsync downloadImageAsync;
    private RootModel.Photos.Photo photo;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_acitivy);
        img = findViewById(R.id.img_detail);
        bottomSheetLayout = findViewById(R.id.bottom_sheet);
        Intent intent = getIntent();
        Bundle bundle = intent.getBundleExtra(Constant.BUNDLE_KEY);
        if (bundle != null) {
            photo = (RootModel.Photos.Photo) bundle.getSerializable(Constant.OBJECT_KEY);
            Glide.with(this).load(photo.getUrlM()).into(img);
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
            bottomSheetLayout.dismissSheet();
        });
        tvUrlM.setOnClickListener(view -> {
            downloadImageAsync = new DownloadImageAsync(this, img);
            downloadImageAsync.execute(photo.getUrlM());
            bottomSheetLayout.dismissSheet();
        });
        tvUrlSq.setOnClickListener(view -> {
            downloadImageAsync = new DownloadImageAsync(this, img);
            downloadImageAsync.execute(photo.getUrlSq());
            bottomSheetLayout.dismissSheet();

        });
        tvUrlT.setOnClickListener(view -> {
            downloadImageAsync = new DownloadImageAsync(this, img);
            downloadImageAsync.execute(photo.getUrlT());
            bottomSheetLayout.dismissSheet();

        });
        tvUrlS.setOnClickListener(view -> {
            downloadImageAsync = new DownloadImageAsync(this, img);
            downloadImageAsync.execute(photo.getUrlS());
            bottomSheetLayout.dismissSheet();

        });
        tvUrlZ.setOnClickListener(view -> {
            downloadImageAsync = new DownloadImageAsync(this, img);
            downloadImageAsync.execute(photo.getUrlZ());
            bottomSheetLayout.dismissSheet();

        });

    }
}