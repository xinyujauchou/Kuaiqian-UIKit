package uikitcom.chaowang.uikit;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class FormActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form);
        findViewById(R.id.back).setVisibility(View.VISIBLE);
    }
}
