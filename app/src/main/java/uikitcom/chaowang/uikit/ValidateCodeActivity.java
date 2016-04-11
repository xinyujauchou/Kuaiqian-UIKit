package uikitcom.chaowang.uikit;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import uikitcom.chaowang.uikit.widget.ValidateCodeView;

public class ValidateCodeActivity extends AppCompatActivity implements ValidateCodeView.ValidateCallBack {

    private ValidateCodeView mValidateCodeView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_validate_code);
        initView();
    }

    private void initView(){
        mValidateCodeView = (ValidateCodeView)findViewById(R.id.validateView);

        mValidateCodeView.setContext(this) //设置上下文
                         .setMillisInfuture(10 * 1000) //冷却时间
                         .setCountdownInterval(1000)   //计时间隔
                         .start();                     //启动计时器
        Toast.makeText(this, "验证码发送成功", Toast.LENGTH_SHORT).show();
        findViewById(R.id.submit).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        mValidateCodeView.setInputBackGroundResource(R.drawable.red_corners_nomal);

//        mValidateCodeView.postDelayed(new Runnable() {
//            @Override
//            public void run() {
//                TextView tv = new TextView(ValidateCodeActivity.this);
//                tv.setText("我是TextView");
//                tv.setGravity(Gravity.CENTER);
//                tv.setBackgroundColor(Color.GREEN);
//                tv.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
//                        100));
//                ((ViewGroup) findViewById(R.id.container)).addView(tv);
//            }
//        }, 2000);

    }

    @Override
    public void onSendButtonClick() {
        Toast.makeText(this, "验证码发送成功", Toast.LENGTH_SHORT).show();
        mValidateCodeView.start();
    }

    @Override
    public void onValidateChanged(boolean isLegal) {
    }
}
