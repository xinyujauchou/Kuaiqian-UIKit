package uikitcom.chaowang.uikit.react.callback;

import com.facebook.react.bridge.Callback;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;

import org.json.JSONObject;

/**
 * Native回调结果给React
 * Created by chao on 2017/12/23.
 */

public class NativeCallBackModule extends ReactContextBaseJavaModule {
    private final static String CALL_BACK = "KQNative";

    public NativeCallBackModule(ReactApplicationContext reactContext) {
        super(reactContext);
    }

    @ReactMethod
    private void callNative(String msg, Callback success, Callback errorCallback){
        try{
            JSONObject jsonObject = new JSONObject(msg);
            String result = new ParseCall().parse(jsonObject, getCurrentActivity());
            success.invoke("解析后"+result);
        }catch (Exception e){
            errorCallback.invoke("失败了"+e.getMessage());
        }
    }

    @Override
    public String getName() {
        return CALL_BACK;
    }
}
