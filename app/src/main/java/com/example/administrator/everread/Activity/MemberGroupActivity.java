package com.example.administrator.everread.Activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.administrator.everread.R;
import com.example.administrator.everread.bean.Friend;

import java.util.ArrayList;
import java.util.List;

import static com.example.administrator.everread.R.id.tag_member;

/**
 * Created by Administrator on 2017/5/8.
 */

public class MemberGroupActivity extends Activity {

    //TODO 群组成员列表
    private RecyclerView mRecyclerView;
    private List<Friend> mDatas;
    private MemberAdapter mAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_membergroup);

        mRecyclerView= (RecyclerView) findViewById(R.id.member_group);

        mRecyclerView = (RecyclerView) findViewById(R.id.id_recyclerview);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mAdapter = new MemberAdapter();
        mRecyclerView.setAdapter(mAdapter);
    }




    class MemberAdapter extends RecyclerView.Adapter<MemberAdapter.ListHolder>{
        @Override
        public MemberAdapter.ListHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View layout = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_friend, parent, false);
            return new ListHolder(layout);
        }

        @Override
        public void onBindViewHolder(MemberAdapter.ListHolder holder, int position) {
            holder.imageview.setImageResource(R.drawable.b);
            holder.member_tag.setText("群组");
            holder.member_name.setText("小明");
        }

        @Override
        public int getItemCount() {
            return 10;
        }

        public class ListHolder extends RecyclerView.ViewHolder {

            ImageView imageview;
            TextView member_tag;
            TextView member_name;
            public ListHolder(View itemView) {
                super(itemView);
                imageview= (ImageView) itemView.findViewById(R.id.header);
                member_tag= (TextView) itemView.findViewById(tag_member);
                member_name= (TextView) itemView.findViewById(R.id.name);
            }
        }
    }
}
