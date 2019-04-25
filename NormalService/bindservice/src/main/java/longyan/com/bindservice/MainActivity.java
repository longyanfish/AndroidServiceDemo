package longyan.com.bindservice;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

/**
 * 例子采用绑定服务的方式来启动服务，这种服务在程序关闭时也会停止，bindService(),unbindService(),
 * 这种服务在Service页面执行时可以把数据再传递给前面的Activity页面，Service子类的onBind()方法会返回一个
 * IBind接口的子类的对象，这个方法执行完就去调用Activity的ServiceConnection接口的子类的onServiceConnected()
 * 方法，IBind接口的子类的对象会作为参数传递进去供调用Service里面的方法。实现方法互相调用，数据传递的效果
 */
public class MainActivity extends AppCompatActivity {
    Button btn_bind,btn_unbind;
    ServiceConnection serviceConnection=new MyServiceConnection();
    //一个接口必须实现两个抽象方法
    boolean istrue=false;
    Intent bindService;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn_bind=(Button)findViewById(R.id.btn_bind);
        btn_unbind=(Button)findViewById(R.id.btn_unbind);
        bindService=new Intent(MainActivity.this,ServiceActivity.class);

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
                /**
                 * bug记录，解绑时 java.lang.IllegalArgumentException:
                 *Service not registered: longyan.com.bindservice.MainActivity$MyServiceConnection@4451a8
                 * 的报错，在Manifest中明明已经注册过了Service还是报错，因为调用bindService和unbindService的对象不一致
                 */
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
            Log.e("BindService","Service回调到Activity页面调用onServiceConnected()");
            ServiceActivity.MyBind binder= (ServiceActivity.MyBind) service;//Binder是实现了IBinder接口的子类
            binder.showBind();//调用服务端的方法
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
          Log.e("BindService","执行了onServiceDisconnected()方法");
        }
    }
}
