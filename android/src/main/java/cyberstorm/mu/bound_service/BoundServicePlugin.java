package cyberstorm.mu.bound_service;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.widget.Toast;

import io.flutter.Log;
import io.flutter.plugin.common.MethodCall;
import io.flutter.plugin.common.MethodChannel;
import io.flutter.plugin.common.MethodChannel.MethodCallHandler;
import io.flutter.plugin.common.MethodChannel.Result;
import io.flutter.plugin.common.PluginRegistry.Registrar;

/** BoundServicePlugin */
public class BoundServicePlugin implements MethodCallHandler {
  // To get the application Context
  private Registrar registrar;

  //Service Binder
  private LocalService mService;

  //To check if we have been bound
  private boolean mBound = false;


  private BoundServicePlugin(Registrar registrar){
    this.registrar = registrar;
  }

  /** Plugin registration. */
  public static void registerWith(Registrar registrar) {
    final MethodChannel channel = new MethodChannel(registrar.messenger(), "bound_service");
    channel.setMethodCallHandler(new BoundServicePlugin(registrar));
  }

  @Override
  public void onMethodCall(MethodCall call, Result result) {
    if (call.method.equals("getPlatformVersion")) {


      Log.d("PLATFORMCHANNEL", "Im in");
      if (mBound) {
        Log.d("PLATFORMCHANNEL", "JUST GO BINDED");

        // Call a method from the LocalService.
        // However, if this call were something that might hang, then this request should
        // occur in a separate thread to avoid slowing down the activity performance.
        int num = mService.getRandomNumber();
        Toast.makeText(registrar.context(), "number: " + num, Toast.LENGTH_SHORT).show();
        result.success("Android " + android.os.Build.VERSION.RELEASE+ " "+ num);
      }else {
        Log.d("PLATFORMCHANNEL", "NOT BINDED YET");

        Intent intent = new Intent(registrar.context(), LocalService.class);
        registrar.activity().bindService(intent, connection, registrar.context().BIND_AUTO_CREATE);

        result.success("Unbound");
      }

      // Unbind from the service
//      if (mBound) {
//        unbindService(mConnection);
//        bound = false;
//      }


    } else {
      result.notImplemented();
    }
  }


  private ServiceConnection connection = new ServiceConnection() {
    public void onServiceConnected(ComponentName className, IBinder service) {
      // This is called when the connection with the service has been
      // established, giving us the service object we can use to
      // interact with the service.  Because we have bound to a explicit
      // service that we know is running in our own process, we can
      // cast its IBinder to a concrete class and directly access it.
      mService = ((LocalService.LocalBinder)service).getService();
      mBound = true;

    }

    public void onServiceDisconnected(ComponentName className) {
      // This is called when the connection with the service has been
      // unexpectedly disconnected -- that is, its process crashed.
      // Because it is running in our same process, we should never
      // see this happen.
      mBound = false;
    }
  };
}
