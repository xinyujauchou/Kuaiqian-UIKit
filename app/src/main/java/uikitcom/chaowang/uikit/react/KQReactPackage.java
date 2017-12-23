package uikitcom.chaowang.uikit.react;

import com.facebook.react.ReactPackage;
import com.facebook.react.bridge.NativeModule;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.uimanager.ViewManager;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import uikitcom.chaowang.uikit.react.callback.NativeCallBackModule;
import uikitcom.chaowang.uikit.react.widget.ToastModule;

/**
 * 注册模块
 * Created by chao on 2017/12/22.
 */

public class KQReactPackage implements ReactPackage {

    @Override
    public List<NativeModule> createNativeModules(ReactApplicationContext reactContext) {
        List<NativeModule> nativeModules = new ArrayList<>();
        nativeModules.add(new ToastModule(reactContext));
        nativeModules.add(new NativeCallBackModule(reactContext));
        return nativeModules;
    }

    @Override
    public List<ViewManager> createViewManagers(ReactApplicationContext reactContext) {
        return Collections.emptyList();
    }
}
