package uikitcom.chaowang.uikit.widget;

import android.content.Context;
import android.os.CountDownTimer;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import uikitcom.chaowang.uikit.R;

/**
 * 验证码发送的UI控件
 * Created by chao.wang on 2016/1/19.
 */
public class ValidateCodeView extends RelativeLayout implements View.OnClickListener,
        TextWatcher{
    private final static int VALUE_NOME = -1;
    private final static int DEFAULT_MILLIS_INFUTURE = 60 * 1000;
    private final static int DEFAULT_COUNTDOWN_INTERVAL = 1000;

    private LabelEditText mEditText;  //验证码输入框
    private TextView mSendBtn;        //验证码发送按钮

    private Context mContext;
    private MCountDownTimer mTimer;
    private int mMillisInfuture = VALUE_NOME;
    private int mCountdownInterval = VALUE_NOME;
    private boolean mIsLegal = false; //用户填写的验证码是否合法(

    public ValidateCodeView(Context context) {
        super(context);
        init(context);
    }

    public ValidateCodeView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    /**
     * 初始化
     * @param context 上下文
     */
    private void init(Context context){
        LayoutInflater.from(context).inflate(R.layout.widget_validate_code, this);
        mEditText = (LabelEditText)findViewById(R.id.et_validate_code);
        mSendBtn  = (TextView)findViewById(R.id.tv_send);
        mEditText.addTextChangedListener(this);
        mSendBtn.setOnClickListener(this);
    }

    /**
     * 开始倒计时
     */
    public void start(){
        mMillisInfuture    = mMillisInfuture == VALUE_NOME ? DEFAULT_MILLIS_INFUTURE
                                                           : mMillisInfuture;
        mCountdownInterval = mCountdownInterval == VALUE_NOME ? DEFAULT_COUNTDOWN_INTERVAL
                                                              : mCountdownInterval;
        if(mTimer == null){
            mTimer = new MCountDownTimer(mMillisInfuture, mCountdownInterval);
        }
        mTimer.start();
    }

    /**
     * 结束倒计时
     */
    public void cancel(){
        if(mTimer != null){
            mTimer.cancel();
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.tv_send:
                if(mContext != null && mContext instanceof ValidateCallBack){ //上下文是否合法
                    ((ValidateCallBack)mContext).onSendButtonClick(); //回调发送按钮点击的事件
                }
                break;
        }
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {}
    @Override
    public void afterTextChanged(Editable s) {
        boolean isLegal = (s != null) && s.toString().trim().length() == 6;
        boolean needFeedBack = isLegal != mIsLegal;
        if(needFeedBack){ //是否需要反馈验证码的合法性
            if(mContext != null && mContext instanceof ValidateCallBack) { //上下文是否合法
                ((ValidateCallBack)mContext).onValidateChanged(isLegal); //通知验证码的合法性发生变化
            }
        }
        mIsLegal = isLegal;
    }

    /**
     * SDK自带倒计时工具
     */
    class MCountDownTimer extends CountDownTimer{
        public MCountDownTimer(long millisInFuture, long countDownInterval) {
            super(millisInFuture, countDownInterval);
        }

        @Override
        public void onTick(long millisUntilFinished) {
            mSendBtn.setClickable(false);//设置不能点击
            mSendBtn.setText(millisUntilFinished / 1000 + "秒后可重新发送");//设置倒计时时间
            mSendBtn.setBackgroundResource(R.drawable.edit_text_common_gray);
        }

        @Override
        public void onFinish() {
            mSendBtn.setClickable(true);//恢复点击性
            mSendBtn.setBackgroundResource(R.drawable.red_selector);
            mSendBtn.setText(getResources().getString(R.string.register_verify_code_resend_btn));
        }
    }

    /**
     * 验证码控件相关回调
     */
    public interface ValidateCallBack{
        void onSendButtonClick();  //用户点击发送验证码按钮
        void onValidateChanged(boolean isLegal); //用户填写的验证码合法性发生变化
    }

    public ValidateCodeView setMillisInfuture(int millisInfuture){
        this.mMillisInfuture = millisInfuture;
        return this;
    }

    public ValidateCodeView setCountdownInterval(int countdownInterval){
        this.mCountdownInterval = countdownInterval;
        return this;
    }

    public ValidateCodeView setContext(Context context){
        this.mContext = context;
        return this;
    }

    /**
     * 手动设置内容
     * @param charSequence 验证码或其他内容
     */
    public void setText(CharSequence charSequence){
        mEditText.setText(charSequence);
    }

    /**
     * 用户当前输入的验证码是否合法
     * @return 用户输入得到验证码的合法性
     */
    public boolean isCodeLegal(){
        return mIsLegal;
    }

    public void setInputBackGroundResource(int backGroundId){
        if(backGroundId <= 0){
            throw new IllegalArgumentException("backGroundId is invalidate");
        }
        mEditText.setBackgroundResource(backGroundId);
    }
}
