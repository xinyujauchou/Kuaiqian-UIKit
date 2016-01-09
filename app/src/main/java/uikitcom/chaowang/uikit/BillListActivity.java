package uikitcom.chaowang.uikit;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;

import uikitcom.chaowang.uikit.widget.ListItemLayout;

public class BillListActivity extends AppCompatActivity {
    private ListView mListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bill_list);
        findViewById(R.id.back).setVisibility(View.VISIBLE);
        mListView = (ListView)findViewById(R.id.listview);
        mListView.setAdapter(new BillAdapter());
    }


    class BillAdapter extends BaseAdapter{

        @Override
        public int getCount() {
            return 20;
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            boolean isDone = position/2 == 0;
            String status = isDone ? "交易成功" : "未完成";
            ListItemLayout listItemLayout = new ListItemLayout(BillListActivity.this);
            listItemLayout.setName("快钱包-转出到银行卡");
            listItemLayout.setTime("07:06");
            listItemLayout.setAmt("9000", true);
            listItemLayout.setStatus(status, isDone);
            return listItemLayout;
        }
    }
}
