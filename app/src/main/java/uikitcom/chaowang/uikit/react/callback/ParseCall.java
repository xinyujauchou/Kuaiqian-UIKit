package uikitcom.chaowang.uikit.react.callback;

import android.content.Context;
import android.support.annotation.NonNull;

import org.json.JSONObject;

/**
 * 解析React调用的意图
 * Created by chao on 2017/12/23.
 */

public class ParseCall {
    private final static String KEY_TYPE = "type";

    public String parse(@NonNull JSONObject json, Context context){
        String type = "0";
        String content = "?????????????";
        type = json.optString(KEY_TYPE, "1");
        if("2".equals(type)){
            content = "2是很2的意思";
        }else if("3".equals(type)){
            content = "3是单数不喜欢";
        }else if("4".equals(type)){
            content = "4是一个不太让人喜欢的数字";
        }else if("5".equals(type)){
            content = "5有点想呜呜呜";
        }
//        Toast.makeText(context, content, Toast.LENGTH_LONG).show();
        return content;
    }
}
