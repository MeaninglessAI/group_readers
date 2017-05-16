package com.example.administrator.everread.Activity.friend;

import android.app.ActivityGroup;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomNavigationView;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.LinearLayout;

import com.example.administrator.everread.ImageAdapter;
import com.example.administrator.everread.R;

/**
 * Created by Administrator on 2017/5/6.
 */
//TODO 朋友信息展示
public class FriendInfoActivity extends ActivityGroup {

    BottomNavigationView bottomNavigationView;
    private ImageAdapter topImgAdapter;
    public LinearLayout container;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_friendinof);

        bottomNavigationView = (BottomNavigationView) findViewById(R.id.navigation1);

        bottomNavigationView.setOnNavigationItemSelectedListener(
                new BottomNavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                        switch (item.getItemId()) {
                            case R.id.navigation_home:
                                SwitchActivity(0);
                                return true;
                            case R.id.navigation_dashboard:
                                SwitchActivity(1);;
                                return true;
                            case R.id.navigation_notifications:
                                SwitchActivity(2);
                                return true;
                        }
                        return false;
                    }
                });
        container = (LinearLayout) findViewById(R.id.Container);
        SwitchActivity(0);//默认打开第0页
    }

    void SwitchActivity(int id)
    {
        container.removeAllViews();//必须先清除容器中所有的View
        Intent intent =null;
        if (id == 0 ) {
            intent = new Intent(FriendInfoActivity.this, BookOfFriendActivity.class);//书架
        } else if (id == 1) {
            intent = new Intent(FriendInfoActivity.this, NoteOfFriendActivity.class);//发布
        }else if (id == 2) {
            intent = new Intent(FriendInfoActivity.this, PlanOfActivity.class);//朋友的计划
        }
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        //Activity 转为 View
        Window subActivity = getLocalActivityManager().startActivity(
                "subActivity", intent);
        //容器添加View
        container.addView(subActivity.getDecorView(),
                ViewGroup.LayoutParams.FILL_PARENT, ViewGroup.LayoutParams.FILL_PARENT);
    }


}
