package com.example.vinhntph08047_lab4.async;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.provider.MediaStore;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.core.content.ContextCompat;

import java.io.BufferedInputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class DownloadImageAsync extends AsyncTask<String, Integer, Bitmap> {
    private Context context;
    private ImageView img;

    public DownloadImageAsync(Context context, ImageView img) {
        this.context = context;
        this.img = img;
    }

    @Override
    protected Bitmap doInBackground(String... strings) {
        URL url = null;
        HttpURLConnection connection = null;
        try {
            url = new URL(strings[0]);
            connection = (HttpURLConnection) url.openConnection();
            connection.connect();
            InputStream inputStream = connection.getInputStream();
            BufferedInputStream bufferedInputStream = new BufferedInputStream(inputStream);
            Bitmap bmp = BitmapFactory.decodeStream(bufferedInputStream);
            return bmp;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            connection.disconnect();
        }
        return null;
    }

    @Override
    protected void onPostExecute(Bitmap bitmap) {
        super.onPostExecute(bitmap);
        if (bitmap != null) {
            String savedImageURL = MediaStore.Images.Media.insertImage(
                    context.getContentResolver(),
                    bitmap,
                    "Image",
                    "Image of bird"
            );
            Toast.makeText(context, savedImageURL.toString(), Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(context, "lol", Toast.LENGTH_SHORT).show();
        }
    }
}
