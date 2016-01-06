package uikitcom.chaowang.uikit.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import uikitcom.chaowang.uikit.R;

/**
 * Created by chao.wang on 2016/1/1.
 */
public class FormInputView  extends RelativeLayout {
    private TextView tv_name;  //名称
    public EditText et_value; //值
    private ImageView iv_arrow;//箭头

    private Mode mMode; //当前模式
    private String mName;
    private String mContent;
    private String mHint;

    public FormInputView(Context context) {
        super(context);
        init(context, null);
    }

    public FormInputView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs){
        LayoutInflater.from(context).inflate(R.layout.widget_form_input, this);
        tv_name = (TextView)findViewById(R.id.tv_name);
        et_value = (EditText)findViewById(R.id.et_value);
        iv_arrow = (ImageView)findViewById(R.id.iv_arrow);

        /** 解析布局属性 **/
        if(attrs != null){
            TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.form_input_view);
            mMode = Mode.getMode(ta.getInt(R.styleable.form_input_view_mode, 0));
            mName = ta.getString(R.styleable.form_input_view_named);
            mContent = ta.getString(R.styleable.form_input_view_content);
            mHint = ta.getString(R.styleable.form_input_view_hint);
            ta.recycle();
        }
        initViewData();
        if(getBackground() == null){
            setBackgroundResource(R.drawable.white_with_border);
        }

    }

    private void initViewData(){
        tv_name.setText(mName);
        et_value.setHint(mHint);
        et_value.setText(mContent);
        if(mMode == Mode.SELECT){  //此模式下改变EditText字体大小
            et_value.setTextSize(TypedValue.COMPLEX_UNIT_PX, getResources().getDimensionPixelSize(R.dimen.dp_15));
            et_value.setEnabled(false);
            et_value.setPadding(getPaddingLeft(), getPaddingTop(), 0, getPaddingBottom());
            findViewById(R.id.iv_arrow).setVisibility(View.VISIBLE); //箭头设置为可见
        }else{
            et_value.getPaint().setTextSize(getResources().getDimensionPixelSize(R.dimen.dp_17));
        }
    }

    public void setEnable(boolean enabled){
        setEnabled(enabled);
        et_value.setEnabled(enabled);
        et_value.setTextColor(enabled ? getResources().getColor(R.color.title_color)
                                      : getResources().getColor(R.color.tip_color));
    }

    /**
     * 设置名称
     * @param resId 资源Id
     */
    public void setName(int resId){
        if(resId <= 0){
            return;
        }
        setName(getResources().getString(resId));
    }

    /**
     * 设置名称
     * @param name 名称
     */
    public void setName(String name){
        mName = name;
        tv_name.setText(name);
    }

    /**
     * 设置提示问题
     * @param resId 提示文字资源
     */
    public void setHint(int resId){
        if(resId <= 0){
            return;
        }
        mHint = getResources().getString(resId);
        et_value.setHint(mHint);
    }

    /**
     * 设置EditText 显示内容
     * @param value 显示内容
     */
    public void setValue(String value){
        mContent = value;
        et_value.setText(value);
    }

    /**
     * 获取EditText内容
     * @return 获得EditText内容
     */
    public String getValue(){
        return et_value.getText().toString().trim();
    }

    /** 表单模式 **/
    public enum Mode{
        INPUT(0),  //输入模式
        SELECT(1); //选择模式

        public int mMode;

        Mode(int mode){
            this.mMode = mode;
        }

        public static Mode getMode(int mode){
            switch (mode){
                case 0:
                    return INPUT;
                case 1:
                    return SELECT;
                default:
                    return INPUT;
            }
        }
    }
}
