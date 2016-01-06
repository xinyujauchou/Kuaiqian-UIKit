package uikitcom.chaowang.uikit.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import uikitcom.chaowang.uikit.R;

/**
 * Created by chao.wang on 2015/12/27.
 */
public class ListItemLayout extends RelativeLayout {
    private ImageView iv_icon;
    private TextView tv_name;
    private TextView tv_time;
    private TextView tv_money;
    private TextView tv_status;

    public ListItemLayout(Context context) {
        super(context);
        init(context);
    }

    public ListItemLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    private void init(Context context){
        LayoutInflater.from(context).inflate(R.layout.item_bill_info_view, this);
        iv_icon = (ImageView)findViewById(R.id.icon);
        tv_name = (TextView)findViewById(R.id.title);
        tv_time = (TextView)findViewById(R.id.date);
        tv_money = (TextView)findViewById(R.id.money);
        tv_status = (TextView)findViewById(R.id.status);
    }

    /**
     * 设置图标
     * @param imgId 图片资源ID
     */
    private void setIcon(int imgId){
        if(imgId > 0){
            iv_icon.setImageResource(imgId);
        }
    }

    /**
     * 设置交易名称
     * @param name 名称
     */
    public void setName(String name){
        tv_name.setText(name);
    }

    /**
     * 设置交易时间
     * @param time 交易时间
     */
    public void setTime(String time){
        tv_time.setText(time);
    }

    /**
     * 设置金额
     * @param amt 金额
     * @param in 充值 0R 消费
     */
    public void setAmt(String amt, boolean in){
        StringBuilder sb = new StringBuilder("");
        sb.append(in ? "+" : "-");
        sb.append(amt);
        tv_money.setText(sb.toString());
    }

    /**
     * 设置交易状态
     * @param status 交易状态
     * @param done 交易是否完成
     */
    public void setStatus(String status, boolean done){
        tv_status.setText(status);
        tv_status.setTextColor(done ? getResources().getColor(R.color.tip_color)
                                    : getResources().getColor(R.color.important_color));
    }

}
