package uikitcom.chaowang.uikit.react.emitter;

import android.support.annotation.NonNull;

import com.facebook.react.bridge.ReactContext;
import com.facebook.react.modules.core.DeviceEventManagerModule;

/**
 * 发送消息给JS
 * Created by chao on 2017/12/22.
 */

public class MessageEmitter{

    public void sendMessage(@NonNull ReactContext reactContext, String message){
        reactContext.getJSModule(DeviceEventManagerModule.RCTDeviceEventEmitter.class)
                     .emit("msg", message);
    }

}
