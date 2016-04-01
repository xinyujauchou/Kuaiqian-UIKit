package uikitcom.chaowang.uikit;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import uikitcom.chaowang.uikit.widget.FormInputView;
import uikitcom.chaowang.uikit.widget.LabelEditText;

public class FormActivity extends AppCompatActivity implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form);
        findViewById(R.id.back).setVisibility(View.VISIBLE);

//        findViewById(R.id.fi_selectType).setOnClickListener(this);

        LabelEditText le = (LabelEditText)findViewById(R.id.icon_form);
        le.setOnIconClickListener(this);
        LabelEditText le2 = (LabelEditText)findViewById(R.id.icon_forms);
        le2.setOnIconClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case FormInputView.ARROW_ID:
            case FormInputView.VALUE_ID:
                Toast.makeText(this, "点击了哦", Toast.LENGTH_SHORT).show();
                break;
            case R.id.icon_form:
                Toast.makeText(this, "触发了Icon事件哦", Toast.LENGTH_SHORT).show();
                break;
            case R.id.icon_forms:
                Toast.makeText(this, "触发了Icon2事件哦", Toast.LENGTH_SHORT).show();
                break;
        }
    }
}
