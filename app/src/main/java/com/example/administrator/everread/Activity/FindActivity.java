package com.example.administrator.everread.Activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
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
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

import static com.example.administrator.everread.R.id.header_find;
import static com.example.administrator.everread.R.id.news_group;

/**
 * Created by Administrator on 2017/4/9.
 */
public class FindActivity extends AppCompatActivity {

    //类别
    private String initurl = "https://book.douban.com/tag/";

    private RecyclerView mRecyclerView;
    private MyAdapterFind mMyAdapter;
    private EditText editText;
    private ImageView search_btn;
    private ProgressDialog dialog;
    private String[] nameOfMoudle={"热门","小说","名著","童话"};
    private int loadCount = 0;
   // public static  ArrayList<Net_Book> net_books= null;
    public static HashMap<String,ArrayList<Net_Book>> map_book = new HashMap<String,ArrayList<Net_Book>>();
  //  private ImageView header= (ImageView) findViewById(R.id.header_find);




    //  private int imageId=R.mipmap.img1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.page_01);

        editText= (EditText) findViewById(R.id.search2);
        search_btn = (ImageView) findViewById(R.id.search_btn);
//        editText.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent =new Intent(FindActivity.this,SearchActivity.class);
//            }
//        });
        search_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(FindActivity.this,SearchBookActivity.class);
                String search_str = editText.getText().toString().trim();
                intent.putExtra("search_name",search_str);
                startActivity(intent);
            }
        });

        mRecyclerView= (RecyclerView) findViewById(R.id.recyclerView);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        Log.d("DEBUG","下面开始跑spider\n");
       // mRecyclerView.setAdapter(new MyAdapterFind(FindActivity.this,net_books));



        showinit();
        if (map_book.size()<1){
            new Thread(runnable).start();
            new Thread(runnable2).start();
            new Thread(runnable3).start();
            new Thread(runnable4).start();
        }
//        if(net_books  == null){
//            new Thread(runnable).start();
//        }


    }



    private void showinit(){
        mRecyclerView.setAdapter(new MyAdapterFind(FindActivity.this,map_book));
    }
    private void show(){
        mRecyclerView.setAdapter(new MyAdapterFind(FindActivity.this,map_book));
    }
    Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if(loadCount == 4){
                new Thread(runnableLoadImg).start();
            }
            show();
        }
    };
 //线程

    Runnable runnable = new Runnable() {
        @Override
        public void run() {

            ArrayList<Net_Book> books = initNetInfo();
            map_book.put("热门",books);
            loadCount++;
            handler.sendEmptyMessage(0);

        }
    };
    Runnable runnable2 = new Runnable() {
        @Override
        public void run() {

            ArrayList<Net_Book> books = initDetialInfo(initurl,"小说");
            map_book.put("小说",books);
            loadCount++;
            handler.sendEmptyMessage(0);

        }
    };
    Runnable runnable3 = new Runnable() {
        @Override
        public void run() {

            ArrayList<Net_Book> books = initDetialInfo(initurl,"名著");
            map_book.put("名著",books);
            loadCount++;
            handler.sendEmptyMessage(0);

        }
    };

    Runnable runnable4 = new Runnable() {
        @Override
        public void run() {

            ArrayList<Net_Book> books = initDetialInfo(initurl,"童话");
            map_book.put("童话",books);
            loadCount++;
            handler.sendEmptyMessage(0);

        }
    };

    Runnable runnableLoadImg = new Runnable() {
        @Override
        public void run() {
            Set<String> keys = map_book.keySet();
            loadCount++;
            for (String key: keys){
                ArrayList<Net_Book> books = map_book.get(key);
                for (int i=0;i<books.size();i++){
                    books.get(i).setImgface(getPIC2Bitmap(books.get(i).getCoverImgUrl()));
                }
                handler.sendEmptyMessage(0);
            }
        }
    };
//    Runnable runnable3 = new Runnable() {
//        @Override
//        public void run() {
//
//            net_books = initNetInfo();
//            handler.sendEmptyMessage(0);
//
//        }
//    };
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
//                Bitmap bookbitmap;
//                URL url = new URL(coverImg);
//                URLConnection conn = url.openConnection();
//                conn.connect();
//                InputStream is = conn.getInputStream();
//                bookbitmap = BitmapFactory.decodeStream(is);
//                Log.d("DEBUG",authorName+"\n");
//                Log.d("DEBUG",bookInfoUrl+"\n");
//                Log.d("DEBUG",coverImg+"\n");
                Net_Book book = new Net_Book();
                book.setAuthorName(authorName);
                book.setBookInfoURL(bookInfoUrl);
                book.setCoverImgUrl(coverImg);
                book.setBookName(bookName);
//                book.setImgface(bookbitmap);
                net_books.add(book);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return net_books;
    }

    public ArrayList<Net_Book> initDetialInfo(String url,String booktype){

        url = url + booktype;


        ArrayList<Net_Book> books = new ArrayList<Net_Book>();
        try {
            Document doc = Jsoup.connect(url).get();
            Elements items = doc.getElementsByClass("subject-item");
            for (Element item : items){
                String imgsource = item.select("img[width]").attr("src");
                String bookName = item.select("a[title]").attr("title");
                String bookAuthor = item.select(".pub").get(0).text();
                String bookInfoUrl = item.select("a[class=nbg]").attr("href");
                Net_Book book = new Net_Book();
                book.setCoverImgUrl(imgsource);
                book.setBookName(bookName);
                book.setAuthorName(bookAuthor);
                book.setBookInfoURL(bookInfoUrl);
                books.add(book);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return books;
    }


    public Bitmap getPIC2Bitmap(String picurl){
        Bitmap bookbitmap = null;
        URL url = null;
        try {
            url = new URL(picurl);
            URLConnection conn = url.openConnection();
            conn.connect();
            InputStream is = conn.getInputStream();
            bookbitmap = BitmapFactory.decodeStream(is);
            is.close();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return bookbitmap;

    }


    // 刷新
//    public void refresh() {
//        if(isNetworkAvailable(FindActivity.this)) {
//            // 显示“正在刷新”窗口
//            dialog = new ProgressDialog(this);
//            dialog.setMessage("正在刷新...");
//            dialog.setCancelable(false);
//            dialog.show();
//            // 重新抓取
//            net_books.removeAll(net_books);
//            new Thread(runnable).start();  // 子线程
//        } else {
//            // 弹出提示框
//            new AlertDialog.Builder(this)
//                    .setTitle("刷新")
//                    .setMessage("当前没有网络连接！")
//                    .setPositiveButton("重试",new DialogInterface.OnClickListener() {
//                        @Override
//                        public void onClick(DialogInterface dialog, int which) {
//                            refresh();
//                        }
//                    }).setNegativeButton("退出",new DialogInterface.OnClickListener() {
//                @Override
//                public void onClick(DialogInterface dialog, int which) {
//                    System.exit(0);  // 退出程序
//                }
//            }).show();
//        }
//    }

    // 判断是否有可用的网络连接
    public boolean isNetworkAvailable(Activity activity)
    {
        Context context = activity.getApplicationContext();
        ConnectivityManager cm = (ConnectivityManager)context.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (cm == null)
            return false;
        else
        {   // 获取所有NetworkInfo对象
            NetworkInfo[] networkInfo = cm.getAllNetworkInfo();
            if (networkInfo != null && networkInfo.length > 0)
            {
                for (int i = 0; i < networkInfo.length; i++)
                    if (networkInfo[i].getState() == NetworkInfo.State.CONNECTED)
                        return true;  // 存在可用的网络连接
            }
        }
        return false;
    }



}
