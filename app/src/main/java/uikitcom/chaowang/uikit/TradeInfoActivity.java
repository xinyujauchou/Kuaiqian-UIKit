package uikitcom.chaowang.uikit;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import uikitcom.chaowang.uikit.widget.TradeInfoContainer;
import uikitcom.chaowang.uikit.widget.TradeInfoHead;

public class TradeInfoActivity extends AppCompatActivity {
    private TradeInfoHead mTradeInfoHead;
    private TradeInfoContainer mTradeInfoContainer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trade_info);
        initView();
        initData();
    }

    private void initView(){
        findViewById(R.id.back).setVisibility(View.VISIBLE);
        mTradeInfoHead = (TradeInfoHead)findViewById(R.id.trandeInfoHead);
        mTradeInfoContainer = (TradeInfoContainer)findViewById(R.id.trandeInfoContainer);
    }

    private void initData(){
        /** 设置头部状态信息 **/
        mTradeInfoHead.setIsSuccessd(true);
        mTradeInfoHead.setState("交易成功");
        mTradeInfoHead.setDesc("在这边设置交易的详细描述以及引导信息~");

        /** 设置订单信息 **/
        mTradeInfoContainer.addInfo("商户名称", "家乐福")
                           .addInfo("商品名", "德芙巧克力")
                           .addInfo("订单号", "19283102381902381092312")
                           .addInfo("交易方式", "快易花")
                           .addInfo("交易金额", "8.66")
                           .addInfo("交易状态", "成功")
                           .addInfo("交易时间", "2016-01-06 20:08");
        /** 注册按钮事件 **/
        findViewById(R.id.tv_commit).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {}
        });
    }
}
