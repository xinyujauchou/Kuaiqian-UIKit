package uikitcom.chaowang.uikit;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import uikitcom.chaowang.uikit.widget.FormInputView;

public class FormActivity extends AppCompatActivity implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form);
        findViewById(R.id.back).setVisibility(View.VISIBLE);

//        findViewById(R.id.fi_selectType).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case FormInputView.ARROW_ID:
            case FormInputView.VALUE_ID:
                Toast.makeText(this, "点击了哦", Toast.LENGTH_SHORT).show();
                break;
        }
    }
}
