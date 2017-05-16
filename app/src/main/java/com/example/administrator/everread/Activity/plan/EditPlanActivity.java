package com.example.administrator.everread.Activity.plan;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.administrator.everread.R;
import com.example.administrator.everread.bean.Book;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/5/3.
 */

public class EditPlanActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private List<Book> mDatas;
    private EHomeAdapter mAdapter;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_plan);

        initData();
        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerView_edit_plan);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mAdapter = new EHomeAdapter();
        mRecyclerView.setAdapter(mAdapter);

        //为RecyclerView添加HeaderView和FooterView
        setHeaderView(mRecyclerView);

    }

    private void setHeaderView(RecyclerView view){
        View header = LayoutInflater.from(this).inflate(R.layout.header_edit_plan, view, false);
        mAdapter.setHeaderView(header);
    }

    private void setFooterView(RecyclerView view){
        View footer = LayoutInflater.from(this).inflate(R.layout.footer, view, false);
        mAdapter.setFooterView(footer);
    }

    protected void initData()
    {
        mDatas = new ArrayList<Book>();
        String[] name ={"我是大侠","我是帅哥","我是美女","我是英雄","我是小明","我是小强","我是你大爷","我是程序员"};
        int[] prossbar={20,50,70,80,10,90,30,30};
        //String[] idea={"我想笑傲江湖","我才是天下最帅的","我才是天下最美的","我才是最厉害的","我拥有世界","我是坚强的","谢谢","我能编制我的梦想"};
        int[] image={R.drawable.anastasia,R.drawable.andriy,R.drawable.dmitriy,R.drawable.dmitry,R.drawable.illya,R.drawable.konstantin,R.drawable.oleksii,
                R.drawable.pavel};

        for (int i=0;i<name.length;i++)
        {
            mDatas.add(new Book(image[i],name[i],prossbar[i],R.drawable.delete));
        }

    }

    class EHomeAdapter extends RecyclerView.Adapter<EHomeAdapter.EditHolder>
    {

        public static final int TYPE_HEADER = 0;  //说明是带有Header的
        public static final int TYPE_FOOTER = 1;  //说明是带有Footer的
        public static final int TYPE_NORMAL = 2;  //说明是不带有header和footer的


        private View mHeaderView;
        private View mFooterView;

        //HeaderView和FooterView的get和set函数
        public View getHeaderView() {
            return mHeaderView;
        }
        public void setHeaderView(View headerView) {
            mHeaderView = headerView;
            notifyItemInserted(0);
        }
        public View getFooterView() {
            return mFooterView;
        }
        public void setFooterView(View footerView) {
            mFooterView = footerView;
            notifyItemInserted(getItemCount()-1);
        }

        /** 重写这个方法，很重要，是加入Header和Footer的关键，我们通过判断item的类型，从而绑定不同的view    * */
        @Override
        public int getItemViewType(int position) {
            if (mHeaderView == null && mFooterView == null){
                return TYPE_NORMAL;
            }
            if (position == 0){
                //第一个item应该加载Header
                return TYPE_HEADER;
            }
            if (position == getItemCount()-1){
                //最后一个,应该加载Footer
                return TYPE_FOOTER;
            }
            return TYPE_NORMAL;
        }

        @Override
        public EditHolder onCreateViewHolder(ViewGroup parent, int viewType)
        {
            if(mHeaderView != null && viewType == TYPE_HEADER) {
                return new EditHolder(mHeaderView);
            }
            if(mFooterView != null && viewType == TYPE_FOOTER){
                return new EditHolder(mFooterView);
            }
            View layout = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_edit, parent, false);
            return new EditHolder(layout);

        }

        @Override
        public void onBindViewHolder(EditHolder holder, int position)
        {
            if(getItemViewType(position) == TYPE_NORMAL){
                if(holder instanceof EditHolder) {
                    holder.tv_idea.setText(mDatas.get(position-1).getName_book());
                    holder.progressBar.setProgress(mDatas.get(position - 1).getProgressBar());
                    holder.imageView.setImageResource(mDatas.get(position-1).getBook_face());
                    holder.planTree.setImageResource(mDatas.get(position-1).getPlan_tree());
                    return;
                }
                return;
            }else if(getItemViewType(position) == TYPE_HEADER){
                return;
            }else{
                return;
            }

        }

        @Override
        public int getItemCount()
        {
            if(mHeaderView == null && mFooterView == null){
                return mDatas.size();
            }else if(mHeaderView == null && mFooterView != null){
                return mDatas.size() + 1;
            }else if (mHeaderView != null && mFooterView == null){
                return mDatas.size() + 1;
            }else {
                return mDatas.size() + 2;
            }
        }

        class EditHolder extends RecyclerView.ViewHolder
        {
            TextView tv_idea;
            ImageView planTree;
            ImageView imageView;
            ProgressBar progressBar;

            public EditHolder(View view)
            {
                super(view);
                //如果是headerview或者是footerview,直接返回
                if (itemView == mHeaderView){

                    return;
                }
                if (itemView == mFooterView){
                    return;
                }
                tv_idea = (TextView) view.findViewById(R.id.textView);
                planTree= (ImageView) view.findViewById(R.id.delete);
                imageView= (ImageView) view.findViewById(R.id.face_book);
                progressBar= (ProgressBar) view.findViewById(R.id.progressBar);
            }
        }
    }
}
