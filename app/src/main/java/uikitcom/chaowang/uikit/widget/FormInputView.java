package uikitcom.chaowang.uikit.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import uikitcom.chaowang.uikit.R;

/**
 * 表单控件   具备输入框、选择 的功能
 * Created by chao.wang on 2016/1/1.
 */
public class FormInputView  extends RelativeLayout{
    private final static int VALUE_NONE = -1;
    private final static int DEFAULT_VALUE_COLOR = 0x181818; //表单值默认颜色
    private static int BOTTOM_LINE_PADDING_lEFT;         //下分割线左padding
    private static int BOTTOM_LINE_PADDING_RIGHT = 0;    //下分割线右padding
    private static int TOP_LINE_PADDING_lEFT;            //上分割线左padding
    private static int TOP_LINE_PADDING_RIGHT = 0;       //上分割线右padding
    private TextView tvName;  //名称
    public TextView tvValue; //值
    private ImageView ivArrow;//箭头

    private Mode mMode;                  //当前模式
    private String mName;                //表单名称
    private String mContent;             //内容值
    private boolean hasTopLine;          //是否有上分割线
    private boolean hasBottomLine;       //是否有下分割线
    private int mTopLinePaddingLeft;     //上分割线左边距
    private int mTopLinePaddingRight;    //上分割线右边距
    private int mBottomLinePaddingLeft;  //下分割线左边距
    private int mBottomLinePaddingRight; //下分割线右边距
    private int mNameColor = VALUE_NONE;
    private int mValueColor = DEFAULT_VALUE_COLOR;
    private int mValueSize = VALUE_NONE;

    private Paint mDividerPaint;

    public FormInputView(Context context) {
        super(context);
        init(context, null);
    }

    public FormInputView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs){
        BOTTOM_LINE_PADDING_lEFT = (int)getResources().getDimension(R.dimen.dp_16);  //上分割线左padding
        LayoutInflater.from(context).inflate(R.layout.widget_form_input, this);
        tvName = (TextView)findViewById(R.id.tv_name);
        tvValue = (TextView)findViewById(R.id.et_value);
        ivArrow = (ImageView)findViewById(R.id.iv_arrow);

        /** 解析布局属性 **/
        if(attrs != null){
            TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.form_input_view);
            mMode = Mode.getMode(ta.getInt(R.styleable.form_input_view_mode, 0));
            mName = ta.getString(R.styleable.form_input_view_named);
            mContent = ta.getString(R.styleable.form_input_view_content);
            mNameColor = ta.getColor(R.styleable.form_input_view_nameColor, DEFAULT_VALUE_COLOR);
            mValueColor = ta.getColor(R.styleable.form_input_view_valueColor, DEFAULT_VALUE_COLOR);
            mValueSize = ta.getDimensionPixelSize(R.styleable.form_input_view_valueSize, VALUE_NONE);
            hasBottomLine = ta.getBoolean(R.styleable.form_input_view_hasBottomLine, false);
            hasTopLine = ta.getBoolean(R.styleable.form_input_view_hasTopLine, false);
            mTopLinePaddingLeft = ta.getDimensionPixelSize(R.styleable.form_input_view_topLinePaddingLeft, TOP_LINE_PADDING_lEFT);
            mTopLinePaddingRight = ta.getDimensionPixelSize(R.styleable.form_input_view_topLinePaddingRight, TOP_LINE_PADDING_RIGHT);
            mBottomLinePaddingLeft = ta.getDimensionPixelSize(R.styleable.form_input_view_bottomLinePaddingLeft, BOTTOM_LINE_PADDING_lEFT);
            mBottomLinePaddingRight = ta.getDimensionPixelSize(R.styleable.form_input_view_bottomLinePaddingRight, BOTTOM_LINE_PADDING_RIGHT);
            ta.recycle();
        }
        initViewData();
        if(getBackground() == null){
            setBackgroundResource(R.drawable.white_with_border_selector);
        }
    }

    private void initViewData(){
        initPaint();
        tvName.setText(mName);
        tvValue.setText(mContent);
        if(mMode == Mode.SELECT){  //选择模式
            tvValue.setPadding(getPaddingLeft(), getPaddingTop(), 0, getPaddingBottom());
            findViewById(R.id.iv_arrow).setVisibility(View.VISIBLE); //箭头设置为可见
        }else if(mMode == Mode.DISPLAY){
            tvName.setTextColor(getResources().getColor(R.color.tip_color));
        }
        if(mValueColor != DEFAULT_VALUE_COLOR){
            tvValue.setTextColor(mValueColor);
        }
        if(mNameColor != DEFAULT_VALUE_COLOR){
            tvName.setTextColor(mValueColor);
        }
        if(mValueSize != VALUE_NONE){
            tvValue.getPaint().setTextSize(mValueSize);
        }
    }

    /** 初始化画笔 **/
    private void initPaint(){
        if(hasBottomLine || hasTopLine){ //需要绘制分割线
            mDividerPaint = new Paint();
            mDividerPaint.setAntiAlias(true);
            mDividerPaint.setStrokeWidth(1);
            mDividerPaint.setColor(getResources().getColor(R.color.divider_color));
        }
    }

    @Override
    protected void dispatchDraw(Canvas canvas) {
        super.dispatchDraw(canvas);
        drawDivider(canvas);
    }

    /** 绘制分割线 **/
    private void drawDivider(Canvas canvas){
        if(hasBottomLine){ //需要绘制下分割线
            canvas.drawLine(mBottomLinePaddingLeft, getMeasuredHeight(), getMeasuredWidth() - mBottomLinePaddingRight,
                    getMeasuredHeight(), mDividerPaint);
        }
        if(hasTopLine){   //需要绘制上分割线
            canvas.drawLine(mTopLinePaddingLeft, 1, getMeasuredWidth() - mTopLinePaddingRight,
                    1, mDividerPaint);
        }
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
     * 设置值的颜色
     * @param color 表单值的颜色
     */
    public void setValueColor(int color){
        if(color > 0){
            tvValue.setTextColor(color);
        }
    }

    /**
     * 设置名称
     * @param name 名称
     */
    public void setName(String name){
        mName = name;
        tvName.setText(name);
    }

    /**
     * 设置提示问题
     * @param resId 提示文字资源
     */
    public void setHint(int resId){
        if(resId <= 0){
            return;
        }
    }

    /**
     * 设置EditText 显示内容
     * @param value 显示内容
     */
    public void setValue(String value){
        mContent = value;
        tvValue.setText(value);
    }

    /**
     * 获取EditText内容
     * @return 获得EditText内容
     */
    public String getValue(){
        return tvValue.getText().toString().trim();
    }

    /** 表单模式 **/
    public enum Mode{
        SELECT(1), //选择模式
        DISPLAY(2);//显示模式

        public int mMode;

        Mode(int mode){
            this.mMode = mode;
        }

        public static Mode getMode(int mode){
            switch (mode){
                case 1:
                    return SELECT;
                case 2:
                    return DISPLAY;
                default:
                    return DISPLAY;
            }
        }
    }
}
