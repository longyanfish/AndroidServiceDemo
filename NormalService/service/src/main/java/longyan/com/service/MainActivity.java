package longyan.com.service;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

/**
 * 例子演示了普通的基本的service使用，就是这个服务开启以后不会再和前端的页面有数据交互，
 * 采用stopService(),startService()的方式来开启关闭服务。
 * 后台服务都是运行在主线程中的，后台服务优先级比前台服务低。
 * Service的运行不依赖于任何用户界面，即使程序被切换到后台或者用户打开另一个应用程序，
 * Service仍然能够保持正常运行，这也正是Service的使用场景。当某个应用程序进程被杀掉时，
 * 所有依赖于该进程的Service也会停止运行
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
                Intent intentStart=new Intent(MainActivity.this,ServiceActivity.class);
                getApplication().startService(intentStart);
            }
        });
        btn_stop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentStop=new Intent(MainActivity.this,ServiceActivity.class);
                getApplication().stopService(intentStop);
            }
        });
    }
}
