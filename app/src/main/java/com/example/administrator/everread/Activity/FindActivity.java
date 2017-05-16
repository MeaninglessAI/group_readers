package com.example.administrator.everread.Activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.administrator.everread.MyAdapterFind;
import com.example.administrator.everread.R;
import com.example.administrator.everread.bean.Net_Book;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;

import static com.example.administrator.everread.R.id.header_find;
import static com.example.administrator.everread.R.id.news_group;

/**
 * Created by Administrator on 2017/4/9.
 */
public class FindActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private MyAdapterFind mMyAdapter;
    private EditText editText;
    private String[] nameOfMoudle={"历史","文学","小说","经济","地理","天文"};
    private ArrayList<Net_Book> net_books=null;
  //  private ImageView header= (ImageView) findViewById(R.id.header_find);




    //  private int imageId=R.mipmap.img1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.page_01);

        editText= (EditText) findViewById(R.id.search2);

        editText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(FindActivity.this,SearchActivity.class);
            }
        });

        mRecyclerView= (RecyclerView) findViewById(R.id.recyclerView);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        Log.d("DEBUG","下面开始跑spider\n");
       // mRecyclerView.setAdapter(new MyAdapterFind(FindActivity.this,net_books));


        new Thread(new Runnable() {
            @Override
            public void run() {
                net_books = initNetInfo();
                handler.sendEmptyMessage(0);
            }
        }).start();

    }
    private void show(){
        mRecyclerView.setAdapter(new MyAdapterFind(FindActivity.this,net_books));
    }
    Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            show();
        }
    };

//获取网络资源
    public ArrayList<Net_Book> initNetInfo(){
        String doupanUrl = "https://book.douban.com/";
        ArrayList<Net_Book> net_books = new ArrayList<Net_Book>();
        try {
            Document doc = Jsoup.connect(doupanUrl).get();
            Elements items = doc.getElementsByClass("list-col list-col5 list-express slide-item").get(0).getElementsByTag("li");
            for (Element item: items){
                //获取封面
                String coverImg = item.getElementsByClass("cover").get(0).select("img[src]").attr("src");
                //获取书本详细信息url
                String bookInfoUrl = item.getElementsByClass("cover").get(0).select("a[href]").attr("href");
                //获取作者的名字
                String authorName = item.getElementsByClass("info").get(0).getElementsByClass("author").get(0).text();
                //获取书名
                String bookName = item.getElementsByClass("info").get(0).getElementsByClass("title").get(0).text();
                //书的封面图片
                Bitmap bookbitmap;
                URL url = new URL(coverImg);
                URLConnection conn = url.openConnection();
                conn.connect();
                InputStream is = conn.getInputStream();
                bookbitmap = BitmapFactory.decodeStream(is);
                Log.d("DEBUG",authorName+"\n");
                Log.d("DEBUG",bookInfoUrl+"\n");
                Log.d("DEBUG",coverImg+"\n");
                Net_Book book = new Net_Book();
                book.setAuthorName(authorName);
                book.setBookInfoURL(bookInfoUrl);
                book.setCoverImgUrl(coverImg);
                book.setBookName(bookName);
                book.setImgface(bookbitmap);
                net_books.add(book);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return net_books;
    }





}
