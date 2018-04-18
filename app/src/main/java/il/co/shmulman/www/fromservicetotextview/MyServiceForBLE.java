package il.co.shmulman.www.fromservicetotextview;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class MyServiceForBLE extends Service {

    String st = "Test String from service";

    public MyServiceForBLE() {
    }

    public class LocalBinder extends Binder {
        MyServiceForBLE getService() {
            return MyServiceForBLE.this;
        }
    }

    @Override
    public IBinder onBind(Intent intent) {
        return mBinder;
    }

    @Override
    public boolean onUnbind(Intent intent) {
        return super.onUnbind(intent);
    }

    private final IBinder mBinder = new LocalBinder();

    public String getCurrentTime(){
        SimpleDateFormat df = new SimpleDateFormat("HH:mm:ss",Locale.ITALY);
        return (df.format(new Date()));
    }

    public String getParam(){
        return st;
    }

    public String getString(String str){
        return str;
    }
}
