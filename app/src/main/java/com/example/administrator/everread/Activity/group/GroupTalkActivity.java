package com.example.administrator.everread.Activity.group;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.administrator.everread.Activity.MainActivity.PagerActivity;
import com.example.administrator.everread.R;

/**
 * Created by Administrator on 2017/5/6.
 */

public class GroupTalkActivity extends Activity {

    ImageView imageView;
    TextView textView1;
    //TODO 群组的名字 TextView textView1;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grouptalk);

        imageView= (ImageView) findViewById(R.id.back);
        textView1= (TextView) findViewById(R.id.more);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(GroupTalkActivity.this,PagerActivity.class);
                startActivity(intent);
            }
        });

        textView1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(GroupTalkActivity.this,GroupInfoActivity.class);
                startActivity(intent);
            }
        });

    }
}
