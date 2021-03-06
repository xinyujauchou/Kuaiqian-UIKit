package uikitcom.chaowang.uikit.widget;

import android.content.Context;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.TextView;

import uikitcom.chaowang.uikit.R;

/**
 * 次要按钮
 * created by chao.wang on 2015/12/23.
 */
public class SecondaryButton extends TextView {
    private static int SYSTEM_TEXT_SIZE; //系统字体大小
    private Context mContext; //上下文

    public SecondaryButton(Context context) {
        super(context);
        init(context, null);
    }

    public SecondaryButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    /**
     * 初始化
     * @param context 上下文
     * @param attrs 属性集合
     */
    private void init(Context context, AttributeSet attrs){
        this.mContext = context;
        SYSTEM_TEXT_SIZE = getResources().getDimensionPixelSize(R.dimen.dp_15);
        int DEFAULT_TEXTSIZE = getResources().getDimensionPixelSize(R.dimen.dp_17); //设置默认字体大小的值
//        if(getPaint().getTextSize() <= SYSTEM_TEXT_SIZE){  //默认字体大小，一定程度上说明用户没有设置字体大小,这时候启用自己的默认字体大小
            setRawTextSize(DEFAULT_TEXTSIZE); //重新设置字体大小
//        }

        setGravity(Gravity.CENTER); //设置内容居中
        if(getBackground() == null){
            setBackgroundResource(R.drawable.red_stroke_corners_selector); //设置背景色
        }
        if(TextUtils.isEmpty(getText())){ //设置默认字符串资源
            setText(R.string.cancel);
        }
        setEnabled(isEnabled());
    }

    @Override
    public void setEnabled(boolean enabled) {
        super.setEnabled(enabled);
        setTextColor(enabled ? getResources().getColor(R.color.important_color)
                : getResources().getColor(R.color.white));
        setBackgroundResource(enabled ? R.drawable.red_stroke_corners_selector
                                      : R.drawable.grey_round_corners_4px_press);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int height = getSuggestedMinimumHeight();
        int heightSpecMode = MeasureSpec.getMode(heightMeasureSpec);
        int heightSpecSize = MeasureSpec.getSize(heightMeasureSpec);
        switch (heightSpecMode){
            case MeasureSpec.EXACTLY:
                height = heightSpecSize;
                break;
            case MeasureSpec.AT_MOST:
                height = getResources().getDimensionPixelOffset(R.dimen.dp_50);
                break;
            case MeasureSpec.UNSPECIFIED:
                height = getResources().getDimensionPixelOffset(R.dimen.dp_50);
                break;
        }
        setMeasuredDimension(getDefaultSize(getSuggestedMinimumWidth(), widthMeasureSpec), height);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        if (ev.getAction() == MotionEvent.ACTION_UP) {
            hideSystemKeyboard(this, mContext);
        }
        return super.dispatchTouchEvent(ev);
    }

    /**
     * 设置字体大小
     * @param size 字体大小数值
     */
    public void setRawTextSize(float size){
        if (size != getPaint().getTextSize()) {
            getPaint().setTextSize(size);
            requestLayout();
            invalidate();
        }
    }

    public static void hideSystemKeyboard(View view, Context context) {
        InputMethodManager imm = (InputMethodManager) context
                .getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }
}
