package uikitcom.chaowang.uikit.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.widget.FrameLayout;

import uikitcom.chaowang.uikit.R;

/**
 * Created by chao.wang on 2015/12/24.
 */
public class DockButtonLayout extends FrameLayout {
    public final static int SUBMIT_BUTTON_ID = R.id.submit_area;

    private static int mPaddingLeft = 0;   //左padding
    private static int mPaddingRight = 0;  //右padding
    private static int mPaddingTop = 0;    //上padding
    private static int mPaddingBottom = 0; //下padding

    private Context mContext; //上下文
    private ImportantButton ib; //按钮
    private Paint mPaint = new Paint();

    public DockButtonLayout(Context context) {
        super(context);
        init(context);
    }

    public DockButtonLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    private void init(Context context){
        this.mContext = context;
        initPaint();
        initPadding(); //初始化padding参数
        if(getBackground() == null){
            setBackgroundColor(getResources().getColor(R.color.bg_mock));
        }

        int paddingLeft = getPaddingLeft() == 0 ? mPaddingLeft : getPaddingLeft();
        int paddingRight = getPaddingRight() == 0 ? mPaddingRight : getPaddingRight();
        int paddingTop = getPaddingTop() == 0 ? mPaddingTop : getPaddingTop();
        int paddingBottom = getPaddingBottom() == 0 ? mPaddingBottom : getPaddingBottom();

        setPadding(paddingLeft, paddingTop, paddingRight, paddingBottom);
        addImportantButton();
        setEnabled(false);
    }

    @Override
    protected void dispatchDraw(Canvas canvas) {
        super.dispatchDraw(canvas);
        canvas.drawLine(0, 1, getMeasuredWidth(), 1, mPaint);
    }

    private void initPadding(){
        mPaddingLeft = getResources().getDimensionPixelSize(R.dimen.dp_10);
        mPaddingRight = mPaddingLeft;
        mPaddingTop = getResources().getDimensionPixelSize(R.dimen.dp_10);
        mPaddingBottom = getResources().getDimensionPixelSize(R.dimen.dp_10);
    }

    private void initPaint(){
        mPaint.setColor(getResources().getColor(R.color.divider_color));
        mPaint.setStrokeWidth(1);
        mPaint.setAntiAlias(true);
    }

    /** 添加按钮 **/
    private void addImportantButton(){
        removeAllViews();
        ib = new ImportantButton(mContext);
        ib.setDefaultHeight(getResources().getDimensionPixelSize(R.dimen.dp_44));
        addView(ib, new FrameLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT));
    }

    @Override
    public void setOnClickListener(OnClickListener l) {
        if(ib != null){
            ib.setId(SUBMIT_BUTTON_ID);
            ib.setOnClickListener(l);
        }
    }

    @Override
    public void setEnabled(boolean enabled) {
        super.setEnabled(enabled);
        if(ib != null){
            ib.setEnabled(enabled);
        }
    }

    /**
     * 设置按钮内容
     * @param resId 资源ID
     */
    public void setText(int resId){
        if(resId <= 0){
            return;
        }
        setText(getResources().getText(resId));
    }

    /**
     * 设置字符串资源
     * @param res 字符串资源
     */
    public void setText(CharSequence res){
        if(ib != null){
            ib.setText(res);
        }
    }
}
