package com.example.administrator.everread;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.administrator.everread.Activity.BookClassActivity;

/**
 * Created by Administrator on 2017/4/10.
 */
public class MyAdapterFind extends RecyclerView.Adapter<MyAdapterFind.ViewHolder> {

    String[] name_class={"语文","数学","英语","地理","政治","历史","物理","化学","生物","体育"};

    Context mcontext;

    public MyAdapterFind(Context mcontext) {
        this.mcontext = mcontext;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {


        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_find, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {

        holder.textview.setText(name_class[position]);
        holder.imageview.setImageResource(R.drawable.four);
        holder.imageview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(mcontext,BookClassActivity.class);
                intent.putExtra("name",name_class[position]);
                mcontext.startActivity(intent);
            }
        });
        if(holder.mRecyclerView.getAdapter()==null) {

            holder.mRecyclerView.setAdapter(new MyAdapterBook(mcontext));
        }else {
            holder.mRecyclerView.getAdapter().notifyDataSetChanged();
        }

    }

    @Override
    public int getItemCount() {
        return 10;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public final TextView textview;
        public final ImageView imageview;
        public final RecyclerView mRecyclerView;

        public ViewHolder(View itemView) {
            super(itemView);
            textview= (TextView) itemView.findViewById(R.id.item);
            imageview= (ImageView) itemView.findViewById(R.id.buttonclass_extend);
            mRecyclerView = (RecyclerView) itemView.findViewById(R.id.recyclerView);
            RecyclerView.LayoutManager manager = new GridLayoutManager(itemView.getContext(), 3);
            manager.setAutoMeasureEnabled(true);
            mRecyclerView.setLayoutManager(manager);
        }
    }


}
