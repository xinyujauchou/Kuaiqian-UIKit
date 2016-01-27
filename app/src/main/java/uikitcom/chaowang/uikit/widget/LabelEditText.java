/*
 * FileName:	LabelEditText.java
 * Copyright:	gsywc
 * Author: 		wangchao
 * Description:	<文件描述>
 * History:		2014年8月21日 1.00 初始版本
 */
package uikitcom.chaowang.uikit.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.FontMetrics;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Gravity;
import android.widget.EditText;

import uikitcom.chaowang.uikit.R;

/**
 * 带标签的输入框 </Br>
 * @author  wangchao
 */
public class LabelEditText extends EditText implements TextWatcher {
	private static int LABEL_PADDING_LEFT_DEFAULT;
	private static int DEFAULT_PADDING_RIGHT;
	private final static int DEFAULT_TEXT_SIZE = 17;
	private static int LABEL_PADDING_RIGHT_DEFAULT = 48;
	private final static int LABEL_COLOR_DEFAULT = Color.parseColor("#181818");
	private float labelBaseLine = 0;
	private String label = "字段名";
	private int labelColor = 0x181818; //lable颜色
	private Paint labelPaint; //label画笔

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
		if(attrs != null){
			TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.labelEdittext);
			label = ta.getString(R.styleable.labelEdittext_label);
			labelColor = ta.getInt(R.styleable.labelEdittext_labelColor, LABEL_COLOR_DEFAULT);
			ta.recycle();
		}
		DEFAULT_PADDING_RIGHT = getResources().getDimensionPixelSize(R.dimen.dp_16);
		LABEL_PADDING_RIGHT_DEFAULT = getResources().getDimensionPixelSize(R.dimen.dp_16);
		LABEL_PADDING_LEFT_DEFAULT = getResources().getDimensionPixelSize(R.dimen.dp_16); //设置padding,为内容预留出绘制间距
		labelPaint = new Paint();
		labelPaint.setAntiAlias(true);
		setTextSize(DEFAULT_TEXT_SIZE); //重新设置输入框字体大小
		labelPaint.setTextSize(getPaint().getTextSize()); //重新设置lable字体大小
		labelPaint.setColor(labelColor);
		
		if(TextUtils.isEmpty(label)){
			label = "";
		}
		int paddindLeft = (int) (labelPaint.measureText(label));
		setPadding(paddindLeft + LABEL_PADDING_LEFT_DEFAULT + LABEL_PADDING_RIGHT_DEFAULT, 0,
				DEFAULT_PADDING_RIGHT, 0);
		setHintTextColor(getResources().getColor(R.color.hint_color));
		setSingleLine();
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
		if(labelBaseLine == 0){
			FontMetrics metrics = labelPaint.getFontMetrics();
			labelBaseLine = getMeasuredHeight()/2 + (metrics.bottom - metrics.top)/2 - metrics.bottom;
		}
		canvas.drawText(label, LABEL_PADDING_LEFT_DEFAULT + getScrollX(), labelBaseLine, labelPaint);
		super.onDraw(canvas);
	}

	/**
	 * 设置标签
	 * @param label 标题
	 */
	public void setLabel(String label) {
		this.label = label;
		int paddindLeft = (int) (labelPaint.measureText(label));
		setPadding(paddindLeft + LABEL_PADDING_LEFT_DEFAULT + LABEL_PADDING_RIGHT_DEFAULT, getPaddingTop(),
				getPaddingRight(), getPaddingBottom());
		invalidate();
	}

	@Override
	public void afterTextChanged(Editable s) {

	}

	@Override
	public void beforeTextChanged(CharSequence s, int start, int count, int after) {

	}

	@Override
	public void onTextChanged(CharSequence text, int start, int lengthBefore, int lengthAfter) {

	}
}
