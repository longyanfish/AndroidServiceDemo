package longyan.com.ipc_service_client;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
/**
 * 跨进程通信，是在同一个设备上的两个程序之间通信，本例是一个程序的服务端，另一个客户端程序
 * 通过intent.startService()调用到这个程序的后台服务。在这个服务端的service注册时必须填写
 * exported属性，设置为true，表示允许外部进程来访问，如果是false那么只允许本程序内部调用，
 * action属性必须和调用端的action值一模一样才能被调用，action值一样这是被调用的关键，
 * 还有package属性值必须是服务端的那个package的值
 */
public class MainActivity extends AppCompatActivity {
    Button btn_start,btn_stop;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn_start=(Button)findViewById(R.id.btn_start);
        btn_stop=(Button)findViewById(R.id.btn_stop);
        btn_start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentStart=new Intent();
                intentStart.setAction("longyan_ipc_server");
                intentStart.setPackage("longyan.com.ipc_service_server");
                getApplication().startService(intentStart);
            }
        });
        btn_stop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentStop=new Intent();
                intentStop.setAction("longyan_ipc_server");
                intentStop.setPackage("longyan.com.ipc_service_server");
                getApplication().stopService(intentStop);
            }
        });
    }
}