package uikitcom.chaowang.uikit;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener{
    private ListView mListView;
    private List<String> kinds = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);




        setContentView(R.layout.activity_main);
//        getWindow().setStatusBarColor(Color.parseColor("#f94d4a"));
//                setTranslucentStatus();
        initView();
    }

    private void setTranslucentStatus() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            Window window = getWindow();
//            WindowManager.LayoutParams winParams = window.getAttributes();
//            final int bits = WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS;
//            winParams.flags |= bits;
//            window.setAttributes(winParams);

            window.setFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS,
                    WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
//
        }
//        SystemStatusManager tintManager = new SystemStatusManager(this);
//        tintManager.setStatusBarTintEnabled(true);
//        tintManager.setStatusBarTintResource(0);//状态栏无背景


    }

    private void initView(){
        kinds.add("交易结果");
        kinds.add("表单/组合表单");
        kinds.add("账单列表");
        kinds.add("各种按钮");
        kinds.add("验证码控件");

        mListView = (ListView)findViewById(R.id.listview);
        mListView.setAdapter(new MyAdapter());
        mListView.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        switch (position){
            case 0:
                startActivity(new Intent(this, TradeInfoActivity.class));
                break;
            case 1:
                startActivity(new Intent(this, FormActivity.class));
                break;
            case 2:
                startActivity(new Intent(this, BillListActivity.class));
                break;
            case 3:
                startActivity(new Intent(this, KindsOfButtonActivity.class));
                break;
            case 4:
                startActivity(new Intent(this, ValidateCodeActivity.class));
                break;
            case 5:

                break;
            default:
                break;
        }
    }

    /**
     * 适配器
     */
    class MyAdapter extends BaseAdapter{

        @Override
        public int getCount() {
            return kinds.size();
        }

        @Override
        public Object getItem(int position) {
            return kinds.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            TextView content = new TextView(MainActivity.this);
            content.setLayoutParams(new AbsListView.LayoutParams(AbsListView.LayoutParams.MATCH_PARENT,
                    AbsListView.LayoutParams.WRAP_CONTENT));
            content.setPadding(0, 20, 0, 20);
            content.setGravity(Gravity.CENTER);
//            if(position >= kinds.size()){
//                content.setText("后加的内容页");
//            }else{
                content.setText(kinds.get(position));
//            }
            content.setTextSize(17);
            return content;
        }
    }
}
