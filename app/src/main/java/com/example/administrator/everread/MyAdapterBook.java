package com.example.administrator.everread;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.administrator.everread.Activity.InfoBookActivity;

/**
 * Created by Administrator on 2017/4/11.
 */
public class MyAdapterBook extends RecyclerView.Adapter<MyAdapterBook.ViewHolder> {

    String[] name_book={"平凡的世界","母亲","钢铁是怎样练成的","简爱","呼啸山庄","史记"};

    Context mcontext;

    public MyAdapterBook(Context mcontext) {
        this.mcontext = mcontext;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_book, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.imageView1.setImageResource(R.mipmap.img1);
        holder.textView2.setText("作者");
        holder.imageView1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mcontext, InfoBookActivity.class);
                mcontext.startActivity(intent);
            }
        });
//        holder.imageView2.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(mcontext, InfoBookActivity.class);
//                mcontext.startActivity(intent);
//            }
//        });
        holder.textView.setText(name_book[position]);
    }

    @Override
    public int getItemCount() {
        return 6;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public final ImageView imageView1;
        public final TextView textView;
        public final TextView textView2;

        public ViewHolder(View itemView) {
            super(itemView);
            imageView1= (ImageView) itemView.findViewById(R.id.book_header);
            textView= (TextView) itemView.findViewById(R.id.name_book);
            textView2= (TextView) itemView.findViewById(R.id.book_author);
        }
    }
}
