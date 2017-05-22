package com.example.administrator.everread;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.administrator.everread.Activity.BookClassActivity;
import com.example.administrator.everread.bean.Net_Book;
import com.example.administrator.everread.bean.SerializableHashMap;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

import static com.example.administrator.everread.Activity.FindActivity.map_book;

/**
 * Created by Administrator on 2017/4/10.
 */
public class MyAdapterFind extends RecyclerView.Adapter<MyAdapterFind.ViewHolder> {

    String[] name_class={"热门","小说","名著","童话"};

    Context mcontext;
    //ArrayList<Net_Book> net_books;
    HashMap<String,ArrayList<Net_Book>> map_books;
    public MyAdapterFind(Context mcontext,HashMap<String,ArrayList<Net_Book>> map_books) {
        this.mcontext = mcontext;
        this.map_books = map_book;
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
               // intent.putExtra("position",position);
                //Bundle bundle = new Bundle();
                //bundle.putString("name",name_class[position]);

                //intent.putExtras(bundle);

                mcontext.startActivity(intent);
            }
        });
        if(holder.mRecyclerView.getAdapter()==null) {

            holder.mRecyclerView.setAdapter(new MyAdapterBook(mcontext,map_book.get(name_class[position])));
        }else {
            holder.mRecyclerView.getAdapter().notifyDataSetChanged();
        }

    }

    @Override
    public int getItemCount() {
        return name_class.length;
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
