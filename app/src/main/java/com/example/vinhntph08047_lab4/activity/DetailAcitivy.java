package com.example.vinhntph08047_lab4.activity;

import android.app.WallpaperManager;
import android.content.ContentValues;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.example.vinhntph08047_lab4.Constant;
import com.example.vinhntph08047_lab4.R;
import com.example.vinhntph08047_lab4.async.DownloadImageAsync;
import com.example.vinhntph08047_lab4.model.RootModel;
import com.facebook.share.model.SharePhoto;
import com.facebook.share.model.SharePhotoContent;
import com.facebook.share.widget.ShareDialog;
import com.flipboard.bottomsheet.BottomSheetLayout;

import java.io.IOException;

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
            Animation animation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.image_anim);
            img.startAnimation(animation);
        }
        View sheet_view = LayoutInflater.from(this).inflate(R.layout.bottom_sheet, null, false);
        img.setOnLongClickListener(view -> {
            bottomSheetLayout.showWithSheetView(sheet_view);
            return true;
        });
        initView(sheet_view);
        actionDownloadAndSave();
    }

    private void actionDownloadAndSave() {
        tvShare.setOnClickListener(view -> {
            Bitmap icon = img.getDrawingCache();
            ContentValues values = new ContentValues();
            values.put(MediaStore.Images.Media.TITLE, "title");
            values.put(MediaStore.Images.Media.MIME_TYPE, "image/jpeg");
            Uri uri = getContentResolver().insert(MediaStore.Audio.Media.EXTERNAL_CONTENT_URI,
                    values);
            Intent sharingIntent = new Intent(android.content.Intent.ACTION_SEND);
            sharingIntent.setType("image/*");
            sharingIntent.putExtra(Intent.EXTRA_STREAM, uri);

            startActivity(Intent.createChooser(sharingIntent, "Share with"));
        });
        tvSet.setOnClickListener(view -> {
            img.buildDrawingCache();
            Bitmap bmap = img.getDrawingCache();
            WallpaperManager m = WallpaperManager.getInstance(this);
            bottomSheetLayout.dismissSheet();
            Toast.makeText(this, "success", Toast.LENGTH_SHORT).show();
            try {
                m.setBitmap(bmap);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        tvShare.setOnClickListener(view -> {
            Bitmap image = ((BitmapDrawable) (img.getDrawable())).getBitmap();
            SharePhoto photo = new SharePhoto.Builder()
                    .setBitmap(image)
                    .build();
            SharePhotoContent content = new SharePhotoContent.Builder()
                    .addPhoto(photo)
                    .build();
            ShareDialog shareDialog = new ShareDialog(this);
            shareDialog.show(content, ShareDialog.Mode.AUTOMATIC);
            bottomSheetLayout.dismissSheet();
        });
        tvUrlM.setOnClickListener(view -> {
            downloadImageAsync = new DownloadImageAsync(this);
            downloadImageAsync.execute(photo.getUrlM());
            bottomSheetLayout.dismissSheet();
        });
        tvUrlSq.setOnClickListener(view -> {
            downloadImageAsync = new DownloadImageAsync(this);
            downloadImageAsync.execute(photo.getUrlSq());
            bottomSheetLayout.dismissSheet();

        });
        tvUrlT.setOnClickListener(view -> {
            downloadImageAsync = new DownloadImageAsync(this);
            downloadImageAsync.execute(photo.getUrlT());
            bottomSheetLayout.dismissSheet();

        });
        tvUrlS.setOnClickListener(view -> {
            downloadImageAsync = new DownloadImageAsync(this);
            downloadImageAsync.execute(photo.getUrlS());
            bottomSheetLayout.dismissSheet();

        });
        tvUrlZ.setOnClickListener(view -> {
            downloadImageAsync = new DownloadImageAsync(this);
            downloadImageAsync.execute(photo.getUrlZ());
            bottomSheetLayout.dismissSheet();

        });

    }

    private void initView(View sheet_view) {
        tvShare = sheet_view.findViewById(R.id.tv_share);
        tvSet = sheet_view.findViewById(R.id.tv_set);
        tvUrlSq = sheet_view.findViewById(R.id.tv_url_sq);
        tvUrlT = sheet_view.findViewById(R.id.tv_url_t);
        tvUrlS = sheet_view.findViewById(R.id.tv_url_s);
        tvUrlM = sheet_view.findViewById(R.id.tv_url_m);
        tvUrlZ = sheet_view.findViewById(R.id.tv_url_z);
    }
}