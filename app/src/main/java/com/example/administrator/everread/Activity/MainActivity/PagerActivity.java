package com.example.administrator.everread.Activity.MainActivity;

import android.app.ActivityGroup;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.administrator.everread.Activity.find.FindActivity;
import com.example.administrator.everread.Activity.friend.FriendActivity;
import com.example.administrator.everread.Activity.group.contentOfInfo.ActicityGroupActivity;
import com.example.administrator.everread.Activity.plan.PlanActivity;
import com.example.administrator.everread.ImageAdapter;
import com.example.administrator.everread.R;

/**
 * Created by Administrator on 2017/4/9.
 */
public class PagerActivity extends ActivityGroup
        implements NavigationView.OnNavigationItemSelectedListener{


   BottomNavigationView bottomNavigationView;
    private ImageAdapter topImgAdapter;
    public LinearLayout container;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.app_bar_personal_center);



        final DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);


        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);



        ImageView imageView= (ImageView) findViewById(R.id.header_find);

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawer.openDrawer(Gravity.LEFT);
            }
        });

        bottomNavigationView = (BottomNavigationView) findViewById(R.id.navigation);

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
                            case R.id.navigation_notifications1:
                                SwitchActivity(3);
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
            intent = new Intent(PagerActivity.this, FindActivity.class);
        } else if (id == 1) {
            intent = new Intent(PagerActivity.this, FriendActivity.class);
        }else if (id == 2) {
            intent = new Intent(PagerActivity.this, PlanActivity.class);
        }else if (id == 3) {
            intent = new Intent(PagerActivity.this, ActicityGroupActivity.GroupActivity.class);
        }
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        //Activity 转为 View
        Window subActivity = getLocalActivityManager().startActivity(
                "subActivity", intent);
        //容器添加View
        container.addView(subActivity.getDecorView(),
                ViewGroup.LayoutParams.FILL_PARENT, ViewGroup.LayoutParams.FILL_PARENT);
    }


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
