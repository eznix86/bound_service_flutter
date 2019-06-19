package cyberstorm.mu.bound_service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;


import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.widget.Toast;

import java.util.Random;

public class LocalService extends Service {
    // Binder given to clients
    private final IBinder binder = new LocalBinder();
    // Random number generator
    private final Random mGenerator = new Random();

    public class LocalBinder extends Binder {
        LocalService getService() {
            // When the app closes and reopens, you need to find the service to bind again with it.
            // So we use this
            // To be able to call public methods
            return LocalService.this;
        }
    }



    @Override
    public IBinder onBind(Intent intent) {
        Toast.makeText(this, "Service OnBind()", Toast.LENGTH_LONG).show();
        return binder;
    }

    /** method for clients */
    public int getRandomNumber() {
        return mGenerator.nextInt(100);
    }


    @Override
    public void onCreate() {
        // TODO Auto-generated method stub
        super.onCreate();
        Toast.makeText(this, "Service Created", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onDestroy() {
        // TODO Auto-generated method stub
        super.onDestroy();
        Toast.makeText(this, "Service Destroyed ", Toast.LENGTH_SHORT).show();
    }

    @Override
    public boolean onUnbind(Intent intent) {
        // TODO Auto-generated method stub
        return super.onUnbind(intent);
    }

}

