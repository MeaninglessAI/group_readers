package com.example.administrator.everread.Activity.book;

import android.app.Activity;
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

import com.example.administrator.everread.Activity.MainActivity.PagerActivity;
import com.example.administrator.everread.R;

/**
 * Created by Administrator on 2017/4/15.
 */
public class BookClassActivity extends Activity {

    TextView textView_header;
    ImageView imageView;
    RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bookclass);

        textView_header= (TextView) findViewById(R.id.book_class);
        imageView= (ImageView) findViewById(R.id.back);

        recyclerView= (RecyclerView) findViewById(R.id.booktodisplay);

        RecyclerView.LayoutManager layoutManager=new GridLayoutManager(BookClassActivity.this,3);

        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(new ClassAdapter(BookClassActivity.this));
        Intent intent = getIntent();
        String nameString = intent.getStringExtra("name");

        textView_header.setText(nameString);


        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(BookClassActivity.this, PagerActivity.class);

                startActivity(intent1);
            }
        });

    }

    private class ClassAdapter extends RecyclerView.Adapter<MyViewHolder> {

        Context mcontext;

        public ClassAdapter(Context mcontext) {
            this.mcontext = mcontext;
        }

        @Override
        public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_book_inclass, parent, false);
            return new MyViewHolder(view);
        }

        @Override
        public void onBindViewHolder(MyViewHolder holder, int position) {
            holder.imageView.setImageResource(R.mipmap.img2);
            holder.imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent=new Intent(mcontext,InfoBookActivity.class);
                    mcontext.startActivity(intent);

                }
            });
            holder.textViewname.setText("人民的名义");
            holder.textViewintro.setText("这是一本好书");
        }



        @Override
        public int getItemCount() {
            return 30;
        }
    }

    private class MyViewHolder extends RecyclerView.ViewHolder {
        public ImageView imageView;
        public TextView textViewname;
        public TextView textViewintro;

        public MyViewHolder(View itemView) {
            super(itemView);
            imageView= (ImageView) itemView.findViewById(R.id.image_inclass);
            textViewname= (TextView) itemView.findViewById(R.id.name_book);
            textViewintro= (TextView) itemView.findViewById(R.id.indro);
        }
    }
}
