package uikitcom.chaowang.uikit.widget;

import android.content.Context;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.widget.LinearLayout;

import uikitcom.chaowang.uikit.R;

/**
 * 订单信息的容器
 * Created by chao.wang on 2016/1/6.
 */
public class TradeInfoContainer extends LinearLayout {
    private static int PADDING_TOP;
    private static int PADDING_BOTTOM;
    private Context mContext;

    public TradeInfoContainer(Context context) {
        super(context);
        this.mContext = context;
        init();
    }

    public TradeInfoContainer(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.mContext = context;
        init();
    }

    /**
     * 初始化
     */
    private void init(){
        setOrientation(VERTICAL);
        PADDING_TOP = PADDING_BOTTOM = getResources().getDimensionPixelSize(R.dimen.dp_15);
        setPadding(getPaddingLeft(), getPaddingTop() == 0 ? PADDING_TOP : getPaddingTop(), getPaddingRight(),  //设置padding
                   getPaddingBottom() == 0 ? PADDING_BOTTOM : getPaddingBottom());
        setBackgroundResource(R.drawable.white_with_border);
    }

    /**
     * 添加订单信息
     * @param name  名称
     * @param state 状态
     * @return 容器
     */
    public TradeInfoContainer addInfo(String name, String state){
        if(!TextUtils.isEmpty(name) || !TextUtils.isEmpty(state)){
            HoribalTextView ht = new HoribalTextView(mContext);
            ht.setName(name);
            ht.setStatus(state);
            addView(ht, LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
        }
        return this;
    }

}
