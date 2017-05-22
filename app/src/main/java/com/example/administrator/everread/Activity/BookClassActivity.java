package com.example.administrator.everread.Activity;

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

import com.example.administrator.everread.R;
import com.example.administrator.everread.bean.Net_Book;
import com.example.administrator.everread.bean.SerializableHashMap;

import java.io.Serializable;
import java.util.ArrayList;

import static com.example.administrator.everread.Activity.FindActivity.map_book;

/**
 * Created by Administrator on 2017/4/15.
 */
public class BookClassActivity extends Activity {

    TextView textView_header;
    ImageView imageView;
    RecyclerView recyclerView;
    public static ArrayList<Net_Book> net_books;
    public  static Net_Book infobookactivity_book;
    SerializableHashMap serializableMap;
    String nameString;
    //public static Net_Book book_details;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bookclass);

        textView_header= (TextView) findViewById(R.id.book_class);
        imageView= (ImageView) findViewById(R.id.back);

        recyclerView= (RecyclerView) findViewById(R.id.booktodisplay);

        RecyclerView.LayoutManager layoutManager=new GridLayoutManager(BookClassActivity.this,1);

        recyclerView.setLayoutManager(layoutManager);


        Intent intent = getIntent();
        nameString = (String) intent.getStringExtra("name");
        //int position = (int) intent.getIntExtra("position");
        net_books = (ArrayList<Net_Book>)map_book.get(nameString);


        recyclerView.setAdapter(new ClassAdapter(BookClassActivity.this,nameString,net_books));
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
        ArrayList<Net_Book> net_books;
        String nameString;
        public ClassAdapter(Context mcontext,String nameString,ArrayList<Net_Book> net_books) {
            this.mcontext = mcontext;
            this.nameString = nameString;
            this.net_books = net_books;
        }

        @Override
        public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_book_inclass, parent, false);
            return new MyViewHolder(view);
        }

        @Override
        public void onBindViewHolder(MyViewHolder holder, final int position) {
            holder.imageView.setImageBitmap(net_books.get(position).getImgface());
            holder.imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent=new Intent(mcontext,InfoBookActivity.class);

                    intent.putExtra("name",nameString);
                    //book_details = net_books.get(position);
                    infobookactivity_book =  net_books.get(position);
                    //int p = position;
                    //intent.putExtra("position",p);
                    //intent.putExtra("book", net_books.get(position));
                    mcontext.startActivity(intent);
                }
            });
            holder.textViewname.setText(net_books.get(position).getBookName());
            holder.textViewintro.setText("这是一本好书");
        }



        @Override
        public int getItemCount() {
            if(net_books !=null )
                return net_books.size();
            else
                return 5;
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
