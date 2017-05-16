package com.example.administrator.everread.Activity.group.contentOfInfo;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.example.administrator.everread.R;

/**
 * Created by Administrator on 2017/5/8.
 */

public class NewsGroupActivity extends Activity {
    //TODO 群组的动态

   private RecyclerView mRecyclerView;
    private NewsAdapter mAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.newsgroup_activity);

        mRecyclerView= (RecyclerView) findViewById(R.id.member_group);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mAdapter = new NewsAdapter();
        mRecyclerView.setAdapter(mAdapter);
    }

    private class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.NewsHolder> {
        @Override
        public NewsAdapter.NewsHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return null;
        }

        @Override
        public void onBindViewHolder(NewsHolder holder, int position) {

        }


        @Override
        public int getItemCount() {
            return 0;
        }

        public class NewsHolder extends RecyclerView.ViewHolder {
            public NewsHolder(View itemView) {
                super(itemView);
            }
        }
    }
}
