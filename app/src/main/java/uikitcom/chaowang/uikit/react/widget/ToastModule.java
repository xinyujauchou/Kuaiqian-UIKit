package uikitcom.chaowang.uikit.react.widget;

import android.widget.Toast;

import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;

/**
 * 封装基础Toast模块
 * Created by chao on 2017/12/22.
 */

public class ToastModule extends ReactContextBaseJavaModule {
    private final static String TOAST = "ToastN";
    private ReactContext mReactContext;

    public ToastModule(ReactApplicationContext reactContext) {
        super(reactContext);
        this.mReactContext = reactContext;
    }

    @ReactMethod
    public void toast(String msg){
        Toast.makeText(mReactContext, "Native_TOAST"+msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public String getName() {
        return TOAST;
    }
}
