package com.bt.Smart.Hox.service;

import android.app.IntentService;
import android.app.Notification;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.support.annotation.Nullable;
import android.support.v4.app.NotificationCompat;
import android.widget.RemoteViews;
import android.widget.Toast;
import com.bt.Smart.Hox.R;
import com.bt.Smart.Hox.utils.DownloadUtil;
import java.io.File;

/**
 * @创建者 AndyYan
 * @创建时间 2018/11/12 10:56
 * @描述 ${TODO}
 * @更新者 $Author$
 * @更新时间 $Date$
 * @更新描述 ${TODO}
 */

public class LoadingApkService extends IntentService {
    NotificationManager nm;
    private String url, path;
    private SharedPreferences sharedPreferences;

    public LoadingApkService(String name) {
        super(name);
    }

    public LoadingApkService() {
        super("MyService");
    }

    public static void startUploadImg(Context context) {
        Intent intent = new Intent(context, LoadingApkService.class);
        context.startService(intent);
    }

    public void onCreate() {
        super.onCreate();
        nm = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        sharedPreferences = getSharedPreferences("data", MODE_PRIVATE);
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        updateApk();
    }

    //开始下载apk  网络请求使用的是xutils框架
    private void updateApk() {
        url = sharedPreferences.getString("url", "");
        path = sharedPreferences.getString("path", "");
        DownloadUtil.get().download(url, path, new DownloadUtil.OnDownloadListener() {
            @Override
            public void onDownloadSuccess() {
                nm.cancel(R.layout.notification_item);
                Toast.makeText(LoadingApkService.this, "下载成功...", Toast.LENGTH_SHORT).show();
                installApk();//下载成功 打开安装界面
                stopSelf();//结束服务
                sendBroadcast(new Intent().setAction("android.intent.action.loading_over"));//发送下载结束的广播
            }

            @Override
            public void onDownloading(int progress) {
                createNotification(100, progress);
                sendBroadcast(new Intent().setAction("android.intent.action.loading"));//发送正在下载的广播
            }

            @Override
            public void onDownloadFailed() {
                Toast.makeText(LoadingApkService.this, "下载失败...", Toast.LENGTH_SHORT).show();
                sendBroadcast(new Intent().setAction("android.intent.action.loading_over"));//发送下载结束的广播
                nm.cancel(R.layout.notification_item);
                stopSelf();
            }
        });
    }

    /**
     * 安装下载的新版本
     */
    protected void installApk() {
        Intent intent = new Intent();
        intent.addCategory(Intent.CATEGORY_DEFAULT);
        File file = new File(path);
        Uri uri = Uri.fromFile(file);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.setDataAndType(uri, "application/vnd.android.package-archive");
        this.startActivity(intent);
    }

    //发送通知 实时更新通知栏下载进度
    private void createNotification(final long total, final long current) {
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this);
        builder.setSmallIcon(R.mipmap.ic_launcher);//必须要设置这个属性，否则不显示
        RemoteViews contentView = new RemoteViews(this.getPackageName(), R.layout.notification_item);
        contentView.setProgressBar(R.id.progress, (int) total, (int) current, false);
        builder.setOngoing(true);//设置左右滑动不能删除
        Notification notification = builder.build();
        notification.contentView = contentView;
        nm.notify(R.layout.notification_item, notification);//发送通知
    }
}
