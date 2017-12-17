package uikitcom.chaowang.uikit;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import uikitcom.chaowang.uikit.widget.BottomMenuDialog;

public class DialogMenuActivity extends AppCompatActivity implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dialog_menu);
        findViewById(R.id.list_menu).setOnClickListener(this);
        findViewById(R.id.grid_menu).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.list_menu:
                showListMenuDialog();
                break;
            case R.id.grid_menu:
                showGridMenuDialog();
                break;
            default:
                    break;

        }
    }

    private void showListMenuDialog(){
        new BottomMenuDialog.BottomListSheetBuilder(this)
                .setTitle("this is title")
                .addItem("菜单1", "menu1")
                .addItem(R.drawable.abc_btn_radio_to_on_mtrl_000, "菜单2", "menu2")
                .addItem("菜单3", "menu3")
                .addItem(R.drawable.abc_btn_radio_to_on_mtrl_000, "菜单4", "menu2", true, false)
                .needMark(true)
                .setOnSheetItemClickListener(new BottomMenuDialog.BottomListSheetBuilder.OnSheetItemClickListener() {
                    @Override
                    public void onClick(BottomMenuDialog dialog, View itemView, int position, String tag) {
                        dialog.dismiss();
                        Toast.makeText(DialogMenuActivity.this, "click"+position+"   tag"+tag, Toast.LENGTH_SHORT).show();
                    }
                })
                .build()
                .show();
    }

    private void showGridMenuDialog(){
        final int TAG_SHARE_WECHAT_FRIEND = 0;
        final int TAG_SHARE_WECHAT_MOMENT = 1;
        final int TAG_SHARE_WEIBO = 2;
        final int TAG_SHARE_CHAT = 3;
        final int TAG_SHARE_LOCAL = 4;
        BottomMenuDialog.BottomGridSheetBuilder builder = new BottomMenuDialog.BottomGridSheetBuilder(this);
        builder.addItem(R.mipmap.icon_more_operation_share_friend, "分享到微信", TAG_SHARE_WECHAT_FRIEND, BottomMenuDialog.BottomGridSheetBuilder.FIRST_LINE)
                .addItem(R.mipmap.icon_more_operation_share_moment, "分享到朋友圈", TAG_SHARE_WECHAT_MOMENT, BottomMenuDialog.BottomGridSheetBuilder.FIRST_LINE)
                .addItem(R.mipmap.icon_more_operation_share_weibo, "分享到微博", TAG_SHARE_WEIBO, BottomMenuDialog.BottomGridSheetBuilder.FIRST_LINE)
                .addItem(R.mipmap.icon_more_operation_share_chat, "分享到私信", TAG_SHARE_CHAT, BottomMenuDialog.BottomGridSheetBuilder.FIRST_LINE)
                .addItem(R.mipmap.icon_more_operation_save, "保存到本地", TAG_SHARE_LOCAL, BottomMenuDialog.BottomGridSheetBuilder.SECOND_LINE)

                .addItem(R.mipmap.icon_more_operation_share_moment, "分享到朋友圈", TAG_SHARE_WECHAT_MOMENT, BottomMenuDialog.BottomGridSheetBuilder.SECOND_LINE)
                .addItem(R.mipmap.icon_more_operation_share_weibo, "分享到微博", TAG_SHARE_WEIBO, BottomMenuDialog.BottomGridSheetBuilder.SECOND_LINE)
                .addItem(R.mipmap.icon_more_operation_share_chat, "分享到私信", TAG_SHARE_CHAT, BottomMenuDialog.BottomGridSheetBuilder.SECOND_LINE)
                .setOnSheetItemClickListener(new BottomMenuDialog.BottomGridSheetBuilder.OnSheetItemClickListener() {
                    @Override
                    public void onClick(BottomMenuDialog dialog, View itemView) {
                        dialog.dismiss();
                        int tag = (int) itemView.getTag();
                        switch (tag) {
                            case TAG_SHARE_WECHAT_FRIEND:
                                Toast.makeText(DialogMenuActivity.this, "分享到微信", Toast.LENGTH_SHORT).show();
                                break;
                            case TAG_SHARE_WECHAT_MOMENT:
                                Toast.makeText(DialogMenuActivity.this, "分享到朋友圈", Toast.LENGTH_SHORT).show();
                                break;
                            case TAG_SHARE_WEIBO:
                                Toast.makeText(DialogMenuActivity.this, "分享到微博", Toast.LENGTH_SHORT).show();
                                break;
                            case TAG_SHARE_CHAT:
                                Toast.makeText(DialogMenuActivity.this, "分享到私信", Toast.LENGTH_SHORT).show();
                                break;
                            case TAG_SHARE_LOCAL:
                                Toast.makeText(DialogMenuActivity.this, "保存到本地", Toast.LENGTH_SHORT).show();
                                break;
                        }
                    }
                }).build().show();

    }
}
