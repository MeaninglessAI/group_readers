package com.example.administrator.everread.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import com.example.administrator.everread.MyAdapterFind;
import com.example.administrator.everread.R;

import static com.example.administrator.everread.R.id.header_find;

/**
 * Created by Administrator on 2017/4/9.
 */
public class FindActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private MyAdapterFind mMyAdapter;
    private EditText editText;
    private String[] nameOfMoudle={"历史","文学","小说","经济","地理","天文"};
  //  private ImageView header= (ImageView) findViewById(R.id.header_find);




    //  private int imageId=R.mipmap.img1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.page_01);

        editText= (EditText) findViewById(R.id.search2);

        editText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(FindActivity.this,SearchActivity.class);
            }
        });

        mRecyclerView= (RecyclerView) findViewById(R.id.recyclerView);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setAdapter(new MyAdapterFind(FindActivity.this));
    }





}
