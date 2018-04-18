package il.co.shmulman.www.fromservicetotextview;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    MyServiceForBLE mMyServiceForBLE;
    boolean mServiceConnected = false;

    private final static String TAG = "MainActivity 17.4.2018";

    private TextView mCapsenseValue;
    private Button start_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent i = new Intent(this, MyServiceForBLE.class);
        bindService(i, mServiceConnection, Context.BIND_AUTO_CREATE);

        start_button = findViewById(R.id.start_button);
        mCapsenseValue = findViewById(R.id.capsense_value);

        // Check button functionality onClick gets the view that was clicked
        start_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCapsenseValue.append("\n");
                mCapsenseValue.append("start_button");
                mCapsenseValue.append("\n");
                mCapsenseValue.append(mMyServiceForBLE.getCurrentTime());
                mCapsenseValue.append("\n");
                mCapsenseValue.append(mMyServiceForBLE.getParam());
                mCapsenseValue.append("\n");
                mCapsenseValue.append(mMyServiceForBLE.getString("String"));
            }
        });
    }

    private final ServiceConnection mServiceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName componentName, IBinder service) {
            Log.i(TAG, "onServiceConnected");
            mMyServiceForBLE = ((MyServiceForBLE.LocalBinder) service).getService();
            mServiceConnected = true;
            //mMyServiceForBLE.initialize();
        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {
            Log.i(TAG, "onServiceDisconnected");
            //mMyServiceForBLE = null; //ERROR ******************************************************************************************
            mServiceConnected = false;
        }
    };
}