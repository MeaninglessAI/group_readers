package com.example.administrator.everread.Activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.NumberPicker;

import com.example.administrator.everread.R;

/**
 * Created by Administrator on 2017/5/3.
 */

public class ChangePlanActivity extends Activity {
    
    NumberPicker np1,np2;
    RecyclerView recyclerView;
//    MyAdapter mAdapter;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_plan);
        
//        recyclerView= (RecyclerView) findViewById(R.id.plan_list);
//        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
//        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
//        recyclerView.setLayoutManager(linearLayoutManager);
//        mAdapter = new MyAdapter();
//        recyclerView.setAdapter(mAdapter);
//        recyclerView.setItemAnimator(new DefaultItemAnimator());

        np1= (NumberPicker) findViewById(R.id.np1);
        np2= (NumberPicker) findViewById(R.id.np2);
        np1.setMaxValue(100);
        np1.setMinValue(1);
        np1.setValue(6);
        np2.setMaxValue(100);
        np2.setMinValue(1);
        np2.setValue(6);
        
        np1.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
                //TODO 改变完成天数然后将其设置到数据库中
            }
        });
        
        np2.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
             //TODO 改变完成天数然后将其设置到数据库中
            }
        });

    }

//    class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyHolder>{
//
//        @Override
//        public MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {
//            View layout = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_plan_item_in_changeplanactivity, parent, false);
//            return new MyHolder(layout);
//        }
//
//        @Override
//        public void onBindViewHolder(MyHolder holder, int position) {
//           //TODO 计划的图片
//
//                holder.imageView.setImageResource(R.drawable.b);
//
//        }
//
//        @Override
//        public int getItemCount() {
//            //TODO 计划的数目
//            return 4;
//        }
//
//        class MyHolder extends RecyclerView.ViewHolder {
//            ImageView imageView;
//            public MyHolder(View itemView) {
//                super(itemView);
//                imageView= (ImageView) findViewById(R.id.face_plan);
//            }
//        }
//    }


}
