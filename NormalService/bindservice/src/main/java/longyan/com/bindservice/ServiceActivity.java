package longyan.com.bindservice;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;

/**
 * 开启服务后，系统自动执行onCreate()-onBind()--Activity页面的onServiceConnected()--调用Service页面的
 * 实现了IBind()接口的方法
 * 关闭服务后，系统自动执行onUnbind()--onDestroy()
 */
public class ServiceActivity extends Service {
    MyBind myBind=new MyBind();
    @Override
    public void onCreate() {//第一次启动(绑定)服务时才第一步执行，已经启动了就不会执行
        super.onCreate();
        Log.e("BindService","执行了onCreate()方法");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        //启动服务时第二步执行，已经启动过也会执行，但是绑定服务不会自动调用这个方法
        Log.e("BindService","执行了onStartCommand()方法");
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {//关闭服务时才执行
        Log.e("BindService","执行了onDestroy()方法");
        super.onDestroy();
    }
    @Override
    public IBinder onBind(Intent intent) {
        //执行到这一方法以后系统自动回调到Activity界面的onServiceConnected()方法
        //再次绑定服务还会执行这个方法
        Log.e("BindService","执行了onBind()方法");
        return myBind;
    }

    @Override
    public boolean onUnbind(Intent intent) {
        Log.e("BindService","执行了onUnbind()方法");
        return super.onUnbind(intent);
    }

    class MyBind extends Binder {
         void showBind(){
             Log.e("BindService","执行了Binder子类的showBind()方法");
         }
    }
}
