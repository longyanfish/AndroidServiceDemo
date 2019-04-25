package longyan.com.aidl_ipc_client;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import longyan.com.aidl_ipc_service.IMyAidlInterface;

public class MainActivity extends AppCompatActivity {

    Button btn_bind,btn_unbind;
    ServiceConnection serviceConnection=new MyServiceConnection();
    //一个接口必须实现两个抽象方法
    boolean istrue=false;
    Intent bindService=null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn_bind=(Button)findViewById(R.id.btn_bind);
        btn_unbind=(Button)findViewById(R.id.btn_unbind);
        bindService=new Intent();
        bindService.setAction("AIDL");
        bindService.setPackage("longyan.com.aidl_ipc_service");
        btn_bind.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //绑定后台服务
                istrue=getApplicationContext().bindService(bindService,serviceConnection,BIND_AUTO_CREATE);
            }
        });
        btn_unbind.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(istrue)
                    if(serviceConnection!=null)
                        getApplicationContext().unbindService(serviceConnection);
                istrue=false;
            }
        });


    }

    public class MyServiceConnection implements ServiceConnection{
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            Log.e("aidl","Service回调到Activity页面调用onServiceConnected()");
            IMyAidlInterface i=IMyAidlInterface.Stub.asInterface(service);
            try {
                i.SayHello();
            } catch (RemoteException e) {
                Log.e("aidl","远程调用失败！");
            }
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            Log.e("BindService","执行了onServiceDisconnected()方法");
        }
    }
}
