package uikitcom.chaowang.uikit;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import uikitcom.chaowang.uikit.widget.DockButtonLayout;

public class KindsOfButtonActivity extends AppCompatActivity implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kinds_of_button);
        findViewById(R.id.back).setVisibility(View.VISIBLE);
        initListener();
    }

    private void initListener(){
        findViewById(R.id.btn1).setOnClickListener(this);
        findViewById(R.id.btn2).setOnClickListener(this);
        findViewById(R.id.btn3).setOnClickListener(this);
        findViewById(R.id.btn4).setOnClickListener(this);
        findViewById(R.id.btn5).setOnClickListener(this);
        findViewById(R.id.btn6).setOnClickListener(this);
        findViewById(R.id.btn6).setOnClickListener(this);
        ((DockButtonLayout)findViewById(R.id.btn6)).setEnabled(true);
    }

    @Override
    public void onClick(View v) {

    }
}
