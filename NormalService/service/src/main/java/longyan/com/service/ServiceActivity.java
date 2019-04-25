package longyan.com.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

/**
 * 服务开启后系统自动调用onCreate()-onStartCommand()
 * 服务关闭后，onDestroy()方法
 */
public class ServiceActivity extends Service {
    @Override
    public IBinder onBind(Intent intent) {
        Log.d("Service","执行了onBind()方法");
        return null;
    }

    @Override
    public void onCreate() {//第一次启动服务时才第一步执行，已经启动了就不会执行
        super.onCreate();
        Log.d("Service","执行了onCreate()方法");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        //启动服务时第二步执行，已经启动过也会执行
        Log.d("Service","执行了onStartCommand()方法");
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {//关闭服务时才执行
        Log.d("Service","执行了onDestroy()方法");
        super.onDestroy();
    }
}
