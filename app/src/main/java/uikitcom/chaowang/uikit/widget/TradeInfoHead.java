package uikitcom.chaowang.uikit.widget;

import android.content.Context;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import uikitcom.chaowang.uikit.R;

/**
 * 交易清单头部状态描述信息<p>
 * 直接在布局中引用此控件，通过{@link #setIsSuccessd(boolean)}来设置状态图标,控件会根据是否成功来设置对应的图标<p>
 * 通过{@link #setState(String)} 和 {@link #setDesc(String)}分别设置交易状态以及交易描述<p>
 * 如果传输的字段为空,则相应控件不显示
 * Created by chao.wang on 2016/1/6.
 */
public class TradeInfoHead extends RelativeLayout {
    private ImageView iv_state;  //根据交易状态显示对应的状态图片
    private TextView tv_state;   //交易状态
    private TextView tv_desc;    //交易描述

    private boolean isSuccessd; //是否成功

    public TradeInfoHead(Context context) {
        super(context);
        init(context);
    }

    public TradeInfoHead(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    /**
     * 初始化布局控件
     * @param context
     */
    private void init(Context context){
        LayoutInflater.from(context).inflate(R.layout.widget_bill_info_head, this);
        iv_state = (ImageView)findViewById(R.id.iv_state);
        tv_state = (TextView)findViewById(R.id.tv_state);
        tv_desc = (TextView)findViewById(R.id.tv_desc);
    }

    /**
     * 设置交易是否成功
     * @param isSuccessd 交易是否成功
     */
    public void setIsSuccessd(boolean isSuccessd){
        this.isSuccessd = isSuccessd;
        iv_state.setImageResource(isSuccessd ? R.mipmap.ic_billings_completed
                                             : R.mipmap.ic_billings_canceled);
    }

    /**
     * 设置交易状态<p>
     * 如果 设置tradeState 为空,则改控件不显示
     * @param tradeState 交易状态  例如: 交易成功/认购成功/认购失败/充值成功。。等
     */
    public void setState(String tradeState){
        if(TextUtils.isEmpty(tradeState)){
            tv_state.setVisibility(View.GONE);
            return;
        }
        tv_state.setText(tradeState);
    }

    /**
     * 设置交易描述
     * @param desc 交易描述
     */
    public void setDesc(String desc){
        if(TextUtils.isEmpty(desc)){
            tv_desc.setVisibility(View.GONE);
            return;
        }
        tv_desc.setText(desc);
    }
}
