package com.example.administrator.everread.Activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.administrator.everread.R;
import com.example.administrator.everread.bean.Friend;

/**
 * Created by Administrator on 2017/5/6.
 */
//TODO 朋友聊天界面
public class FriendTalkActivity extends Activity {

    private ImageView imageView;
    private TextView name_friend;
    private TextView more;


    //TODO 界面未实现
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_friendtalk);

       //TODO 昵称从上个界面获取，这里是随意写的
        imageView= (ImageView) findViewById(R.id.back);
        more= (TextView) findViewById(R.id.more1);
        //返回朋友列表
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               // Intent intent=new Intent(FriendTalkActivity.this,FriendInfoActivity.class);
                Intent intent=new Intent(FriendTalkActivity.this,PagerActivity.class);
                startActivity(intent);
            }
        });
        //跳到下个界面
        more.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(FriendTalkActivity.this,FriendInfoActivity.class);
                startActivity(intent);
            }
        });
    }
}
