package longyan.com.aidl_ipc_service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;

/**
 * AIDL是安卓接口定义语言，在两个进程之间定义一个要进行通信的方法，
 * 这两个程序是两个进程之间通信的实例，结合了bindService的一些绑定服务的方法和
 * 远程访问进程的Intent.setAction的方法和AIDL技术。
 * AIDL和IPC(远程服务)的区别是:AIDL到服务端后还会回来另一个进程的客户端，这个客户端还可以调用
 * 服务端的方法(利用了IPC没有的AIDL和bindService方法).
 * AIDL和BindService(本地绑定服务)的区别是：AIDL在两个进程之间跳跃（利用intent的远程通信技术），
 * 而bindService是一个进程里面
 * 的服务和Activity调用。
 * 关键点是
 * 1。Aidl文件两边生成的都要一样，建立的目录也要一样，客户端的aidl文件直接从
 * 服务端复制过来
 * 2.AIDL文件要点击Build下的rebuildProject来生成对应的java文件。java文件里的方法要调用
 * 3.java文件在build/generated/source/aidl下面，java文件的Stub类下的方法asInterface()要重写和调用
 * 4.intent远程调用的Action服务端和客户端必须一致，还有package也要写对服务端的
 * 5.service注册的exported属性
 */
public class MyService extends Service {

        @Override
        public IBinder onBind(Intent intent) {
            return new IMyAidlInterface.Stub() {
                @Override
                public void basicTypes(int anInt, long aLong, boolean aBoolean, float aFloat, double aDouble, String aString) throws RemoteException {

                }

                @Override//因为Stub继承了Binder类
                public void SayHello() {
                    Log.e("aidl","来自AIDL_SERVICE的招呼，hello!");
                }
            };
        }

        @Override
        public void onCreate() {//第一次启动服务时才第一步执行，已经启动了就不会执行
            super.onCreate();
            Log.e("aidl","service的执行中");
        }

        @Override
        public int onStartCommand(Intent intent, int flags, int startId) {
            //启动服务时第二步执行，已经启动过也会执行
            return super.onStartCommand(intent, flags, startId);
        }

        @Override
        public void onDestroy() {//关闭服务时才执行
            super.onDestroy();
        }
    }

