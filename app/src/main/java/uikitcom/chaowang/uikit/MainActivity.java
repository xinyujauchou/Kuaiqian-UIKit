package uikitcom.chaowang.uikit;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
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
        initView();
    }

    private void initView(){
        kinds.add("交易结果");
        kinds.add("表单/组合表单");
        kinds.add("账单列表");
        kinds.add("各种按钮");

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
            content.setText(kinds.get(position));
            content.setTextSize(17);
            return content;
        }
    }
}
