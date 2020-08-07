package com.example.vinhntph08047_lab4.async;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Environment;
import android.provider.MediaStore;
import android.widget.Toast;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Locale;
import java.util.Random;

public class DownloadImageAsync extends AsyncTask<String, Integer, Bitmap> {
    private Context context;

    public DownloadImageAsync(Context context) {
        this.context = context;
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

    /**
     * 1
     * //            String savedImageURL = MediaStore.Images.Media.insertImage(
     * //                    context.getContentResolver(),
     * //                    bitmap,
     * //                    "Image",
     * //                    "Image of bird"
     * //            );
     * //            Toast.makeText(context, "Done!", Toast.LENGTH_SHORT).show();
     *
     * @param bitmap
     */
    @Override
    protected void onPostExecute(Bitmap bitmap) {
        super.onPostExecute(bitmap);
        if (bitmap != null) {
            saveImage(bitmap);


        } else {
            Toast.makeText(context, "Fail", Toast.LENGTH_SHORT).show();
        }
    }

    public void saveImage(Bitmap segg) {
        OutputStream fOut = null;
        Random generator = new Random();
        int n = 10000;
        n = generator.nextInt(n);
        String fileName = "Image-" + n + ".png";
        final String appDirectoryName = "TBStego";
        final File imageRoot = new File(Environment.getExternalStoragePublicDirectory(
                Environment.DIRECTORY_PICTURES), appDirectoryName);

        imageRoot.mkdirs();
        final File file = new File(imageRoot, fileName);
        try {
            fOut = new FileOutputStream(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        segg.compress(Bitmap.CompressFormat.PNG, 100, fOut);
        try {
            Toast.makeText(context,
                    file.getAbsolutePath(),
                    Toast.LENGTH_LONG).show();
            fOut.flush();
            fOut.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        ContentValues values = new ContentValues();
        values.put(MediaStore.Images.Media.TITLE, "stego");
        values.put(MediaStore.Images.Media.DESCRIPTION, "stego description");
        values.put(MediaStore.Images.Media.DATE_TAKEN, System.currentTimeMillis());
        values.put(MediaStore.Images.ImageColumns.BUCKET_ID, file.toString().toLowerCase(Locale.US).hashCode());
        values.put(MediaStore.Images.ImageColumns.BUCKET_DISPLAY_NAME, file.getName().toLowerCase(Locale.US));
        values.put("_data", file.getAbsolutePath());
        Toast.makeText(context,
                file.getAbsolutePath(),
                Toast.LENGTH_LONG).show();
        ContentResolver cr = context.getContentResolver();
        Uri insert = cr.insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values);
        Toast.makeText(context, "The Image thumbnail created in Gallery ", Toast.LENGTH_LONG).show();
    }
}
