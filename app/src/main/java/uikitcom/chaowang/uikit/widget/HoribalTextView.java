package uikitcom.chaowang.uikit.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import uikitcom.chaowang.uikit.R;

/**
 * @author chao.wang
 *         email: xinyujaychou@126.com
 * @date 2014-12-8
 */
public class HoribalTextView extends RelativeLayout {
	private final static int VALUE_NONE = -1;

	private TextView tv_status;//值
	private TextView tv_name;  //名称

	public HoribalTextView(Context context) {
		super(context);
		init(context, null);
	}

	public HoribalTextView(Context context, AttributeSet attrs) {
		super(context, attrs);
		init(context, attrs);
	}

	public HoribalTextView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		init(context, attrs);
	}

	private void init(Context context, AttributeSet attrs){
		LayoutInflater.from(context).inflate(R.layout.activity_setting_item, this);
		ImageView iv_arrow = (ImageView) findViewById(R.id.image_limit_manage);
		tv_name = (TextView) findViewById(R.id.tv_name);
		tv_status = (TextView) findViewById(R.id.tv_status);
		if(attrs == null){
			return;
		}
		TypedArray ta = getResources().obtainAttributes(attrs, R.styleable.horibalTextView);
		String name = ta.getString(R.styleable.horibalTextView_names);
		String status = ta.getString(R.styleable.horibalTextView_status);
		int arrowVisibility = ta.getInt(R.styleable.horibalTextView_arrowVisibility, View.GONE);
		int textSize = ta.getDimensionPixelOffset(R.styleable.horibalTextView_textSize, VALUE_NONE);
		int statusColor = ta.getColor(R.styleable.horibalTextView_textValueColor, VALUE_NONE);
		int backgroundColor = ta.getColor(R.styleable.horibalTextView_backgroundColor, VALUE_NONE);
		ta.recycle();
		
		if(arrowVisibility == View.GONE){
			iv_arrow.getLayoutParams().width = 0;
			iv_arrow.requestLayout();
		}
		iv_arrow.setVisibility(arrowVisibility);
		if(textSize != VALUE_NONE){
			tv_name.getPaint().setTextSize(textSize);
			tv_status.getPaint().setTextSize(textSize);
		}
		if(statusColor != VALUE_NONE){
			tv_status.setTextColor(statusColor);
		}
		if(backgroundColor != VALUE_NONE){
			getChildAt(0).setBackgroundColor(backgroundColor);
		}
		tv_name.setText(name);
		tv_status.setText(status);
		if(getBackground() == null){ //设置背景
			setBackgroundColor(getResources().getColor(R.color.white));
		}
	}

	public void setStatus(String status) {
		this.tv_status.setText(status);
	}
	
	public void setStatus(int status){
		if(status <= 0){
			return;
		}
		this.tv_status.setText(getResources().getString(status));
	}
	
	public void setName(String name){
		this.tv_name.setText(name);
	}
	
	public void setName(int nameId){
		if(nameId <=0){
			return;
		}
		this.tv_name.setText(getResources().getString(nameId));
	}
	
}
