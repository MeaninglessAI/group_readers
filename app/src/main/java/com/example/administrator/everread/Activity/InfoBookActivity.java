package com.example.administrator.everread.Activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.test.suitebuilder.TestMethod;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.administrator.everread.MyAdapterBookInfo;
import com.example.administrator.everread.R;
import com.example.administrator.everread.bean.Comment;
import com.example.administrator.everread.bean.Net_Book;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;
import org.jsoup.nodes.TextNode;
import org.jsoup.select.Elements;
import org.w3c.dom.Text;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


import static com.example.administrator.everread.Activity.BookClassActivity.infobookactivity_book;
import static com.example.administrator.everread.Activity.BookClassActivity.net_books;
import static com.example.administrator.everread.Activity.FindActivity.map_book;

/**
 * Created by Administrator on 2017/4/15.
 */
public class InfoBookActivity extends Activity {

    private ImageView bookface;
    private TextView bookname;
    private TextView bookauthor;
    private TextView bookpress ;
    private TextView bookpresstime;
    private TextView pages ;
    private TextView ISBN ;

    private RecyclerView mRecyclerView;
    private TextView book_class;
    private HomeAdapter mAdapter;
    private Net_Book book;
    private String type_name;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        initViewElement();
        setContentView(R.layout.book);

        Intent getintent = getIntent();

        //book = (Net_Book) getintent.getSerializableExtra("book");
        type_name = getintent.getStringExtra("name");
        //int position = (int)getintent.getIntExtra("position",0);
        //System.out.print(type_name+"  "+position);
       // book = net_books.get(position);
        book = infobookactivity_book;
        mRecyclerView = (RecyclerView) findViewById(R.id.info_book);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        book_class = (TextView) findViewById(R.id.book_class);
        book_class.setText(type_name);
        showinit();
        show();
//        initViewElement();
//        show();


        new Thread(runnable).start();
    }
    private void showinit(){

        mAdapter = new HomeAdapter(book);
        mRecyclerView.setAdapter(mAdapter);
        //为RecyclerView添加HeaderView和FooterView
        setHeaderView(mRecyclerView);
        setFooterView(mRecyclerView);
    }
    private void show(){


        mAdapter = new HomeAdapter(book);
        mRecyclerView.setAdapter(mAdapter);
        //为RecyclerView添加HeaderView和FooterView
        setHeaderView(mRecyclerView);
        setFooterView(mRecyclerView);
    }
    private void initViewElement(){
        //bookname
        bookface = (ImageView) findViewById(R.id.boo_face);
        bookname = (TextView) findViewById(R.id.book_name);
        bookauthor = (TextView) findViewById(R.id.author);
        bookpress = (TextView) findViewById(R.id.chubanshe);
        bookpresstime = (TextView) findViewById(R.id.time_chuban);
        pages = (TextView) findViewById(R.id.pagers);
        ISBN = (TextView) findViewById(R.id.isbn);
    }


    Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            show();
        }
    };

    Runnable runnable = new Runnable() {
        @Override
        public void run() {
            getDetial(book.getBookInfoURL());
        }
    };

    private void getDetial(String durl){

        try {
            Document doc = Jsoup.connect(durl).get();
            Element info = doc.getElementById("info");
            List<Node> childs = info.childNodes();
            for (Node child : childs) {

                if (child instanceof Element) {
                    Element t = (Element) child;
                    if (t.hasText()) {

                        String text = t.text().trim();
                        if (text.contains("出版社")) {
                            book.setPress(t.nextSibling().toString().trim());

                        } else if (text.contains("出版年")) {
                            book.setPresstime(t.nextSibling().toString().trim());
                        } else if (text.contains("页数")) {
                            book.setPages(t.nextSibling().toString().trim());
                        } else if (text.contains("ISBN")) {
                            book.setISBN(t.nextSibling().toString().trim());
                        }
                    }
                }
            }

            handler.sendEmptyMessage(0);

            Elements items = doc.getElementsByClass("intro").get(0).getElementsByTag("p");
            String book_intro = "";
            for (Element item:items){
                book_intro = book_intro + item.text().toString().trim()+'\n';
            }

            book.setBook_intro(book_intro);

            handler.sendEmptyMessage(0);

            items = doc.getElementsByClass("intro").get(0).getElementsByTag("p");
            String author_intro = "";
            for (Element item:items){
                author_intro= author_intro + item.text().toString().trim()+'\n';
            }

            book.setAuthor_intro(author_intro);

//            Element item_ = doc.getElementsByClass("related_info").get(0).select("div[id$=_short]").select("div[id^=dir_]").get(0);
//            List<TextNode> nodes = item_.textNodes();
//            String book_menu = "";
//            for (TextNode node:nodes){
//                book_menu = book_menu + node.toString().trim()+'\n';
//            }
//            book.setBook_menu(book_menu);

            items = doc.getElementsByClass("comment-list hot show").get(0).getElementsByClass("comment-item");
            ArrayList<Comment> comments = new ArrayList<Comment>();
            for (Element item1:items){
                String commentName = item1.getElementsByClass("comment-info").get(0).select("a[href]").text().trim();
                String commentTime = item1.getElementsByClass("comment-info").get(0).children().select("span").text().trim();
                String commentCon = item1.getElementsByClass("comment-content").text().trim();
                Comment comment = new Comment();
                comment.setCom_name(commentName);
                comment.setCom_time(commentTime);
                comment.setCom_content(commentCon);
                comments.add(comment);
            }
            book.setComments(comments);

            handler.sendEmptyMessage(0);

        } catch (IOException e) {
            e.printStackTrace();
            Log.d("woarning","InfoBookActivity si get a wrong!\n");
        }

    }

    private void setHeaderView(RecyclerView view){
        View header = LayoutInflater.from(this).inflate(R.layout.header_info, view, false);
        mAdapter.setHeaderView(header);
    }

    private void setFooterView(RecyclerView view){
        View footer = LayoutInflater.from(this).inflate(R.layout.footer_info, view, false);
        mAdapter.setFooterView(footer);
    }



    class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.ListHolder>
    {

        public static final int TYPE_HEADER = 0;  //说明是带有Header的
        public static final int TYPE_FOOTER = 1;  //说明是带有Footer的
        public static final int TYPE_NORMAL = 2;  //说明是不带有header和footer的


        private View mHeaderView;
        private View mFooterView;
        private Net_Book book;
        public HomeAdapter(Net_Book book){
            this.book = book;
            if(book!=null){
                if(book.getBook_intro()!=null)
                infoBook[0] = book.getBook_intro();
                if(book.getAuthor_intro()!=null)
                infoBook[1] = book.getAuthor_intro();
                if(book.getBook_menu()!=null)
                infoBook[2] = book.getBook_menu();
            }
        }
        private String[] infoBook={"从印染厂的实习生到纽约时装皇后\n" +
                "\n" +
                "从嫁给王子到独自创建时尚品牌\n" +
                "\n" +
                "她用一条裹身裙成为了她想成为的女人\n" +
                "\n" +
                "“继可可•香奈儿后时尚界最具市场号召力的女性”\n" +
                "\n" +
                "《福布斯》杂志2012全球时尚界最有影响力女性\n" +
                "\n" +
                "Diane von Furstenberg品牌创始人\n" +
                "\n" +
                "美国时装设计师协会（ CFDA）主席首部亲笔完整自传\n" +
                "\n" +
                "近百幅珍贵照片首次公开\n" +
                "\n" +
                "FaceBook首席运营官 雪莉•桑德伯格\n" +
                "\n" +
                "《Vogue》杂志美国版主编 安娜•温图尔\n" +
                "\n" +
                "《欲望都市》主演 莎拉•杰西卡•帕克"
                ,"巴金（1904年11月25日－2005年10月17日）之一，"
                ,"1.《激流》总序\n" +
                "2.家\n" +
                "3.附录"};

        private String[] intro={"简介","作者简介","目录"};

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
            View layout = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_book_info, parent, false);
            return new ListHolder(layout);

        }

        @Override
        public void onBindViewHolder(ListHolder holder, int position)
        {
            if(getItemViewType(position) == TYPE_NORMAL){
                if(holder instanceof ListHolder) {
                    holder.tv_idea.setText(intro[position-1]);

                    holder.tv_name.setText(infoBook[position-1]);
                   // holder.imageView.setImageResource(mDatas.get(position-1).getImage());
                    // return;
                }
                return;
            }else if(getItemViewType(position) == TYPE_HEADER){
                bookface.setImageBitmap(book.getImgface());
                bookname.setText(book.getBookName());
                bookauthor.setText(book.getAuthorName());

                if(book.getPress()!=null)
                    bookpress.setText(book.getPress());
                if(book.getPresstime()!=null)
                    bookpresstime.setText(book.getPresstime());
                if(book.getPages()!=null)
                    pages.setText(book.getPages());
                if(book.getISBN()!=null)
                    ISBN.setText(book.getISBN());
                return;
            }else{
                if(holder.recyclerView.getAdapter()==null) {

                    holder.recyclerView.setAdapter(new MyAdapterBookInfo(book.getComments()));
                }else {
                    holder.recyclerView.getAdapter().notifyDataSetChanged();
                }
                return;
            }

        }

        @Override
        public int getItemCount()
        {
            if(mHeaderView == null && mFooterView == null){
                return 3;
            }else if(mHeaderView == null && mFooterView != null){
                return 3 + 1;
            }else if (mHeaderView != null && mFooterView == null){
                return 3 + 1;
            }else {
                return 3 + 2;
            }
        }

        class ListHolder extends RecyclerView.ViewHolder
        {

            TextView tv_idea;
            TextView tv_name;
           RecyclerView recyclerView;

            public ListHolder(View view)
            {
                super(view);
                //如果是headerview或者是footerview,直接返回
                if (itemView == mHeaderView){
                    bookface = (ImageView) view.findViewById(R.id.boo_face);
                    bookname = (TextView) view.findViewById(R.id.book_name);
                    bookauthor = (TextView) view.findViewById(R.id.author);
                    bookpress = (TextView) view.findViewById(R.id.chubanshe);
                    bookpresstime = (TextView) view.findViewById(R.id.time_chuban);
                    pages = (TextView) view.findViewById(R.id.pagers);
                    ISBN = (TextView) view.findViewById(R.id.isbn);
                    return;
                }
                if (itemView == mFooterView){
                    recyclerView= (RecyclerView) view.findViewById(R.id.footer_info);
                    RecyclerView.LayoutManager layoutManager=new LinearLayoutManager(view.getContext());
                    layoutManager.canScrollHorizontally();
                    recyclerView.setLayoutManager(layoutManager);
                    return;
                }
                tv_idea = (TextView) view.findViewById(R.id.intro);
                tv_name= (TextView) view.findViewById(R.id.content_intro);

            }
        }
    }
}
