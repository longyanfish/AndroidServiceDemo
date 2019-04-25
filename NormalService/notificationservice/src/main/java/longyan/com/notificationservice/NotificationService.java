package longyan.com.notificationservice;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.IBinder;

/**
 * 让服务变为前台服务出现在手机任务栏消息通知中，优先级也会比后台服务高
 */
public class NotificationService extends Service {
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        //以下为把服务设置为前台服务的代码
        //生成一个Builder对象，Builder是Notification类的内部类，但是是静态的一个内部类
        Notification.Builder builder=new Notification.Builder(this);
        //点击了消息后会打开百度网页
        Intent intent=new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.baidu.com"));
        //待确定的Intent,启动后不会立刻跳转
        PendingIntent pendingIntent=
                PendingIntent.getActivity(NotificationService.this,0,intent,0);
        //设置大图
        builder.setLargeIcon(BitmapFactory.decodeResource(getResources(),R.mipmap.dog));
        builder.setSmallIcon(R.drawable.flower);//没有显示出来
        builder.setContentIntent(pendingIntent);
        builder.setContentTitle("这是服务的一个消息");
        builder.setContentText("消息详情");
        //通知系统服务
        NotificationManager notificationManager= (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        //构建通知
        Notification notification=builder.build();
        //显示通知
        notificationManager.notify(0,notification);
        //启动为前台服务
        startForeground(0,notification);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}
