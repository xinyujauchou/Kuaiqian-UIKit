/*
 * FileName:	LabelEditText.java
 * Copyright:	gsywc
 * Author: 		wangchao
 * Description:	<文件描述>
 * History:		2014年8月21日 1.00 初始版本
 */
package uikitcom.chaowang.uikit.widget;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Paint.FontMetrics;
import android.graphics.RectF;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.util.Log;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.MotionEvent;
import android.widget.EditText;

import uikitcom.chaowang.uikit.R;

/**
 * 带标签的输入框 </Br>
 * @author  wangchao
 */
public class LabelEditText extends EditText implements TextWatcher {
	private final static int CLEAR_IMG_MARGIN_RIGHT = 9;
	private static int LABEL_PADDING_LEFT_DEFAULT;
	private static int DEFAULT_PADDING_RIGHT;
	private final static int DEFAULT_TEXT_SIZE = 17;
	private static int LABEL_PADDING_RIGHT_DEFAULT = 48;
	private final static int LABEL_COLOR_DEFAULT = Color.parseColor("#181818");
	private int mLabelPaddingLeft;
	private int mLabelPaddingRight;
	private float labelBaseLine = 0;
	private String label = "字段名";
	private int labelColor = 0x181818; //lable颜色
	private float mLabelSize = 0;
	private Paint labelPaint; //label画笔
	private int mDrawableEnd; //右侧提示小图标
	private int mIconHeight; //右端提示小图标高度
	private RectF mDrawableTouchRect = new RectF();//图标相应点击事件的区域
	private RectF mClearDrawableTouchRect = new RectF();//清除的图标相应点击事件的区域
	private Bitmap mIconBmp;   //右侧提示小图标  Bitmap对象
	private Bitmap mClearBmp;  //右侧清空小图标  Bitmap对象
	private boolean mShowClear; //是否显示清空小图标
	private int mPaddingRight;
	private int mContentLength; //只保存上次编辑内容的长度,不保存实际内容

	private OnClickListener onClickListener;

	public LabelEditText(Context context) {
		super(context);
		init(context, null);
	}

	public LabelEditText(Context context, AttributeSet attrs) {
		super(context, attrs);
		init(context, attrs);
	}

	public LabelEditText(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		init(context, attrs);
	}


	private void init(Context context, AttributeSet attrs){
		DEFAULT_PADDING_RIGHT = getResources().getDimensionPixelSize(R.dimen.dp_16);
		LABEL_PADDING_RIGHT_DEFAULT = getResources().getDimensionPixelSize(R.dimen.dp_16);
		LABEL_PADDING_LEFT_DEFAULT = getResources().getDimensionPixelSize(R.dimen.dp_16); //设置padding,为内容预留出绘制间距
		if(attrs != null){
			TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.labelEdittext);
			label = ta.getString(R.styleable.labelEdittext_label);
			labelColor = ta.getInt(R.styleable.labelEdittext_labelColor, LABEL_COLOR_DEFAULT);
			mLabelSize = ta.getDimension(R.styleable.labelEdittext_labelSize, 0);
			mDrawableEnd = ta.getResourceId(R.styleable.labelEdittext_endIcon, 0);
			mIconHeight = ta.getDimensionPixelOffset(R.styleable.labelEdittext_endIconHeight, (int) getPaint().getTextSize());
			mShowClear = ta.getBoolean(R.styleable.labelEdittext_showClear, false);
			mLabelPaddingLeft = ta.getDimensionPixelOffset(R.styleable.labelEdittext_labelPaddingLeft, LABEL_PADDING_LEFT_DEFAULT);
			mLabelPaddingRight = ta.getDimensionPixelOffset(R.styleable.labelEdittext_labelPaddingRight, DEFAULT_PADDING_RIGHT);
			ta.recycle();
		}
		labelPaint = new Paint();
		labelPaint.setAntiAlias(true);
		getPaint().setTextSize(mLabelSize == 0 ? getRawTextSize(TypedValue.COMPLEX_UNIT_SP, DEFAULT_TEXT_SIZE) : mLabelSize);
		labelPaint.setTextSize(getPaint().getTextSize()); //重新设置lable字体大小
		labelPaint.setColor(labelColor);

		if(TextUtils.isEmpty(label)){
			label = "";
		}
		int paddindLeft = (int) (labelPaint.measureText(label));
		setPadding(paddindLeft + mLabelPaddingLeft + LABEL_PADDING_RIGHT_DEFAULT, 0,
				mLabelPaddingRight, 0);
		setHintTextColor(getResources().getColor(R.color.hint_color));
		int inputType = getInputType();
		setSingleLine();
		setInputType(inputType);  //setSingleLine 的  bug
		if(getGravity() == 8388627){
			setGravity(Gravity.CENTER_VERTICAL | Gravity.RIGHT);
		}

		addTextChangedListener(this);
		setBackgroundResource(R.drawable.white_with_border);
		setEnabled(isEnabled());
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
	public void setEnabled(boolean enabled) {
		super.setEnabled(enabled);
		setTextColor(enabled ? getResources().getColor(R.color.title_color)
				: getResources().getColor(R.color.tip_color));
	}

	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);
		if(labelBaseLine == 0){
			FontMetrics metrics = labelPaint.getFontMetrics();
			labelBaseLine = getMeasuredHeight()/2 + (metrics.bottom - metrics.top)/2 - metrics.bottom;
		}
		mPaddingRight = mLabelPaddingRight;
		canvas.drawText(label, mLabelPaddingLeft + getScrollX(), labelBaseLine, labelPaint);
		drawDrawableEnd(canvas); //绘制右侧小图标
		drawClearBitmap(canvas);
		setPadding(getPaddingLeft(), getPaddingTop(), mPaddingRight + 10, getPaddingBottom());
	}

	@Override
	protected void dispatchDraw(Canvas canvas) {
		super.dispatchDraw(canvas);
	}

	private float lastX;
	private float lastY;
	private long mDownTime;
	private final static int MOVE_LIMIT = 4;
	private final static long TIME_CLICK = 500;
	@Override
	public boolean onTouchEvent(MotionEvent event) {
		switch (event.getAction()){
			case MotionEvent.ACTION_DOWN:
				lastX = event.getX();
				lastY = event.getY();
				mDownTime = System.currentTimeMillis();
				break;
			case MotionEvent.ACTION_MOVE:
				float currentX = event.getX();
				float currentY = event.getY();
				if(Math.abs(currentX - lastX )> MOVE_LIMIT
						|| Math.abs(currentY - lastY )> MOVE_LIMIT){//非点击事件
				mDownTime -= TIME_CLICK;
			}
				break;
			case MotionEvent.ACTION_UP:
				float currentTime = System.currentTimeMillis();
				if(currentTime - mDownTime <= TIME_CLICK){  //时间间隔满足点击时间/位置
					if(hitDrawableRect(lastX, lastY)){
						feedBackClickListener();
					} else if(hitClearDrawableRect(lastX, lastY)){
						setText("");
					} else{
						break;
					}
					requestFocus();
					return true;
				}
				break;
		}
		return super.onTouchEvent(event);
	}

	private void feedBackClickListener(){
		if(onClickListener != null){
			onClickListener.onClick(this);
		}
	}

	private boolean hitDrawableRect(float x, float y){
		return mDrawableTouchRect.contains(x, y);
	}

	private boolean hitClearDrawableRect(float x, float y){
		return mClearDrawableTouchRect.contains(x, y);
	}

	/**
	 * 绘制右侧小图标
	 */
	private void drawDrawableEnd(Canvas canvas){
		if(mDrawableEnd <= 0){
			return;
		}
		if(mIconBmp == null){
			mIconBmp = getBitmap(mDrawableEnd, mIconHeight);
		}

		int drawableWidth = mIconBmp.getWidth();
		int drawableHeight =  mIconBmp.getHeight();

		Paint drawablePaint = new Paint();
		drawablePaint.setAntiAlias(true);
		float drawLeft = getMeasuredWidth() - mLabelPaddingRight - drawableWidth;
		float drawTop = (getMeasuredHeight() - drawableHeight) / 2;

		canvas.drawBitmap(mIconBmp, drawLeft + getScrollX(), drawTop, drawablePaint);
		mDrawableTouchRect.set(drawLeft, 0, drawLeft + drawableWidth, getMeasuredHeight());
		mPaddingRight += drawableWidth;
	}

	private void drawClearBitmap(Canvas canvas){
		if(!mShowClear || !isEnabled()){
			return;
		}
		Paint drawablePaint = new Paint();
		drawablePaint.setAntiAlias(true);
		if(mClearBmp == null){
			int bitmapHeight = getResources().getDimensionPixelOffset(R.dimen.dp_17);
			mClearBmp = getBitmap(R.drawable.del_btn, bitmapHeight);
		}
		int drawableWidth = mClearBmp.getWidth();
		int drawableHeight =  mClearBmp.getHeight();
		mPaddingRight += drawableWidth;
		if(mContentLength == 0){
			return;
		}

		float drawLeft = getMeasuredWidth() - mLabelPaddingRight - drawableWidth;
		float drawTop = (getMeasuredHeight() - drawableHeight)/2;
		if(mDrawableEnd > 0){ //有右侧小图标
			drawLeft =  mDrawableTouchRect.left - drawableWidth - CLEAR_IMG_MARGIN_RIGHT;
		}
		canvas.drawBitmap(mClearBmp, drawLeft + getScrollX(), drawTop, drawablePaint);
		mClearDrawableTouchRect.set(drawLeft, 0, drawLeft + drawableWidth, getMeasuredHeight());
	}

	private Bitmap getBitmap(int resId, int iconHeight){
		BitmapFactory.Options options = new BitmapFactory.Options();
		options.inJustDecodeBounds = true;
		BitmapFactory.decodeResource(getResources(), resId, options);
		int nHeight = options.outHeight;
		options.inSampleSize = nHeight/iconHeight;
		options.inJustDecodeBounds = false;
		Bitmap sourceBitmap = BitmapFactory.decodeResource(getResources(), resId, options);

		Matrix mIconMatrix = new Matrix();
		int bitmapHeight = sourceBitmap.getHeight();
		float scale = iconHeight*1.0f/bitmapHeight;
		mIconMatrix.postScale(scale, scale);
		return Bitmap.createBitmap(sourceBitmap, 0, 0, sourceBitmap.getWidth(), sourceBitmap.getHeight(), mIconMatrix, true);
	}

	/**
	 * 设置标签
	 * @param label 标题
	 */
	public void setLabel(String label) {
		this.label = label;
		int paddindLeft = (int) (labelPaint.measureText(label));
		setPadding(paddindLeft + mLabelPaddingLeft + LABEL_PADDING_RIGHT_DEFAULT, getPaddingTop(),
				getPaddingRight(), getPaddingBottom());
		invalidate();
	}

	public void setOnIconClickListener(OnClickListener l){
		if (!isClickable()) {
			setClickable(true);
		}
		this.onClickListener = l;
	}

	@Override
	public void setOnClickListener(OnClickListener l) {
		if (!isClickable()) {
			setClickable(true);
		}
		this.onClickListener = l;
	}

	private float getRawTextSize(int unit, int size){
		Context c = getContext();
		Resources r;

		if (c == null)
			r = Resources.getSystem();
		else
			r = c.getResources();
		return TypedValue.applyDimension(
				unit, size, r.getDisplayMetrics());
	}

	@Override
	public void afterTextChanged(Editable s) {
		int contentLength = s == null ? 0 : s.toString().length(); //当前编辑内容的长度
		if((contentLength != mContentLength) && (contentLength == 0 || mContentLength == 0)){
			mContentLength = contentLength;
			invalidate((int)mClearDrawableTouchRect.left, (int)mClearDrawableTouchRect.top,
										(int)mClearDrawableTouchRect.right, (int)mClearDrawableTouchRect.bottom);
		}

	}

	@Override
	public void beforeTextChanged(CharSequence s, int start, int count, int after) {

	}

	@Override
	public void onTextChanged(CharSequence text, int start, int lengthBefore, int lengthAfter) {

	}
}
