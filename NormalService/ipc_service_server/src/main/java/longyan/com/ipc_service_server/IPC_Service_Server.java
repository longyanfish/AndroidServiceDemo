package longyan.com.ipc_service_server;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

/**
 * 跨进程通信，是在同一个设备上的两个程序之间通信，本例是一个程序的服务端，另一个客户端程序
 * 通过intent.startService()调用到这个程序的后台服务。在这个服务端的service注册时必须填写
 * exported属性，设置为true，表示允许外部进程来访问，如果是false那么只允许本程序内部调用，
 * action属性必须和调用端的action值一模一样才能被调用，这是被调用的关键
 */
public class IPC_Service_Server extends Service {

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.e("ipc_server","onCreate()");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.e("ipc_server","孤独的羊");

        return super.onStartCommand(intent, flags, startId);

    }

    @Override
    public void onDestroy() {
        Log.e("ipc_server","关闭服务");

        super.onDestroy();
    }
}
