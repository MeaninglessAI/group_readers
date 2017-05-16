package com.example.administrator.everread.Activity.group.contentOfInfo;

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
import android.widget.TextView;

import com.example.administrator.everread.Activity.group.GroupTalkActivity;
import com.example.administrator.everread.R;
import com.example.administrator.everread.bean.Friend;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/5/8.
 */

public class ActicityGroupActivity extends Activity {
    //TODO 群组的活动

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_acticitygroup);

    }

    /**
     * Created by Administrator on 2017/4/9.
     */
    public static class GroupActivity extends AppCompatActivity {

        private RecyclerView mRecyclerView;
        private List<Friend> mDatas;
        private HomeAdapter mAdapter;
        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.page_04);

            initData();
            mRecyclerView = (RecyclerView) findViewById(R.id.id_recyclerview1);
            mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
            mAdapter = new HomeAdapter();
            mRecyclerView.setAdapter(mAdapter);

            //为RecyclerView添加HeaderView和FooterView
            setHeaderView(mRecyclerView);
            setFooterView(mRecyclerView);
        }

        private void setHeaderView(RecyclerView view){
            View header = LayoutInflater.from(this).inflate(R.layout.header, view, false);
            mAdapter.setHeaderView(header);
        }

        private void setFooterView(RecyclerView view){
            View footer = LayoutInflater.from(this).inflate(R.layout.footer, view, false);
            mAdapter.setFooterView(footer);
        }

        protected void initData()
        {
            mDatas = new ArrayList<Friend>();
            String[] name ={"武侠爱好者","我们都是诗人","射雕大侠","美国汇","英国通","历史家们","成长","我们一起度过的人生"};
            String[] idea={"我想笑傲江湖","我才是天下最帅的","我才是天下最美的","我才是最厉害的","我拥有世界","我是坚强的","谢谢","我能编制我的梦想"};
            int[] image={R.drawable.anastasia,R.drawable.andriy,R.drawable.dmitriy,R.drawable.dmitry,R.drawable.illya,R.drawable.konstantin,R.drawable.oleksii,
                    R.drawable.pavel};
            for (int i =0; i < name.length; i++)
            {
                mDatas.add(new Friend(name[i],idea[i],image[i]));
            }
        }

        class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.ListHolder>
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
            public ListHolder onCreateViewHolder(ViewGroup parent, int viewType)
            {
                if(mHeaderView != null && viewType == TYPE_HEADER) {
                    return new ListHolder(mHeaderView);
                }
                if(mFooterView != null && viewType == TYPE_FOOTER){
                    return new ListHolder(mFooterView);
                }
                View layout = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_group, parent, false);
                return new ListHolder(layout);

            }

            @Override
            public void onBindViewHolder(ListHolder holder, int position)
            {
                if(getItemViewType(position) == TYPE_NORMAL){
                    if(holder instanceof ListHolder) {
                        holder.tv_idea.setText(mDatas.get(position-1).getIdea_friend());
                        holder.tv_name.setText(mDatas.get(position-1).getName_friend());
                        holder.imageView.setImageResource(mDatas.get(position-1).getImage());
                        holder.imageView.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Intent intent=new Intent(GroupActivity.this,GroupTalkActivity.class);
                                startActivity(intent);
                            }
                        });
                        // return;
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

            class ListHolder extends RecyclerView.ViewHolder
            {

                TextView tv_idea;
                TextView tv_name;
                ImageView imageView;

                public ListHolder(View view)
                {
                    super(view);
                    //如果是headerview或者是footerview,直接返回
                    if (itemView == mHeaderView){
                        return;
                    }
                    if (itemView == mFooterView){
                        return;
                    }
                    tv_idea = (TextView) view.findViewById(R.id.ideaOffriend1);
                    tv_name= (TextView) view.findViewById(R.id.name_friend1);
                    imageView= (ImageView) view.findViewById(R.id.header_friend1);
                }
            }
        }

    }
}
