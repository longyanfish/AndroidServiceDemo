# AndroidServiceDemo
这里面有几个代码例子，直接运行都能运行成功，是我在学习Android的Service时根据服务的各种不同类型而练习的几个程序。

service的启动和关闭方法，总的来说只有两种，startService()\stopService()和bindService()和unbindService()

1.程序名字叫service的

这是最普通的service,属于本地服务，同一个进程（apk程序）里调用服务，采用startService开启服务，采用stopService()关闭服务。
执行路线:activity-service

2.程序名字叫bindservice

这属于本地服务，也是运行在同一个程序里面的一个Activity和service的子类进行数据通信的例子，和service例子不同的是，它能在service这个类里面
系统自动的调用回来activity页面的方法，然后activity拥有了service页面的内部类的对象的一个引用，activity再次调用到service里的方法。
执行路线:activity-service-activity-service

3.程序名字叫notificationservice的

这和其他的例子都不一样，其他例子是后台服务，这是一个前台服务，它能把消息显示在手机消息通知任务栏里面。
执行路线：看选择什么样的启动服务方法

4.程序名字叫ipc_service_server和ipc_service_client的

这是一对一起运行的两个不同程序，和前面三个不一样的是，这是某一程序调用了另一个程序的服务，也就是远程服务。
执行路线：activity-service

5.程序名字叫AIDL_IPC_Service和AIDL_IPC_Client的两个程序

这也是一对一起运行的远程服务，也是两个程序之间的通信，远程通信服务，它和ipc那一对不一样的地方在于，它不仅可以调用另一个程序的服务，还可以
进行方法的互相调用，和bindService类似，service又可以反过来回到Activity的程序，然后Activity又去调用Service的方法，只是这是两个程序之间,
不像bindService只是一个程序内部，也不像ipc执行的路线是activity-service,
aidl的路线是activity-service-activity-service
