package com.example.vinhntph08047_lab4;

import android.app.IntentService;
import android.app.NotificationManager;

import androidx.core.app.NotificationCompat;

public class BackgroundNotificationService extends IntentService {

    public BackgroundNotificationService() {
        super("Service");
    }

    private NotificationCompat.Builder notificationBuilder;
    private NotificationManager notificationManager;


//    @Override
//    protected void onHandleIntent(Intent intent) {
//
//        notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
//
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
//            NotificationChannel notificationChannel = new NotificationChannel("id", "an", NotificationManager.IMPORTANCE_LOW);
//
//            notificationChannel.setDescription("no sound");
//            notificationChannel.setSound(null, null);
//            notificationChannel.enableLights(false);
//            notificationChannel.setLightColor(Color.BLUE);
//            notificationChannel.enableVibration(false);
//            notificationManager.createNotificationChannel(notificationChannel);
//        }
//
//
//        notificationBuilder = new NotificationCompat.Builder(this, "id")
//                .setSmallIcon(android.R.drawable.stat_sys_download)
//                .setContentTitle("Download")
//                .setContentText("Downloading Image")
//                .setDefaults(0)
//                .setAutoCancel(true);
//        notificationManager.notify(0, notificationBuilder.build());
//
//        initRetrofit();
//
//    }
//
//    private void initRetrofit() {
//
//        Retrofit retrofit = new Retrofit.Builder()
//                .baseUrl("https://unsplash.com/")
//                .build();
//
//        Call<ResponseBody> request = retrofitInterface.downloadImage("photos/YYW9shdLIwo/download?force=true");
//        try {
//
//            downloadImage(request.execute().body());
//
//        } catch (IOException e) {
//            e.printStackTrace();
//            Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
//
//        }
//    }
//
//    private void downloadImage(ResponseBody body) throws IOException {
//
//        int count;
//        byte data[] = new byte[1024 * 4];
//        long fileSize = body.contentLength();
//        InputStream inputStream = new BufferedInputStream(body.byteStream(), 1024 * 8);
//        File outputFile = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS), "journaldev-image-downloaded.jpg");
//        OutputStream outputStream = new FileOutputStream(outputFile);
//        long total = 0;
//        boolean downloadComplete = false;
//        //int totalFileSize = (int) (fileSize / (Math.pow(1024, 2)));
//
//        while ((count = inputStream.read(data)) != -1) {
//
//            total += count;
//            int progress = (int) ((double) (total * 100) / (double) fileSize);
//
//
//            updateNotification(progress);
//            outputStream.write(data, 0, count);
//            downloadComplete = true;
//        }
//        onDownloadComplete(downloadComplete);
//        outputStream.flush();
//        outputStream.close();
//        inputStream.close();
//
//    }
//
//    private void updateNotification(int currentProgress) {
//
//
//        notificationBuilder.setProgress(100, currentProgress, false);
//        notificationBuilder.setContentText("Downloaded: " + currentProgress + "%");
//        notificationManager.notify(0, notificationBuilder.build());
//    }
//
//
//    private void sendProgressUpdate(boolean downloadComplete) {
//
//        Intent intent = new Intent(MainActivity.PROGRESS_UPDATE);
//        intent.putExtra("downloadComplete", downloadComplete);
//        LocalBroadcastManager.getInstance(BackgroundNotificationService.this).sendBroadcast(intent);
//    }
//
//    private void onDownloadComplete(boolean downloadComplete) {
//        sendProgressUpdate(downloadComplete);
//
//        notificationManager.cancel(0);
//        notificationBuilder.setProgress(0, 0, false);
//        notificationBuilder.setContentText("Image Download Complete");
//        notificationManager.notify(0, notificationBuilder.build());
//
//    }
//
//    @Override
//    public void onTaskRemoved(Intent rootIntent) {
//        notificationManager.cancel(0);
//    }

}
