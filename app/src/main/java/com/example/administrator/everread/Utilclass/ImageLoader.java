package com.example.administrator.everread.Utilclass;

/**
 * Created by Lenvov on 2017/5/14.
 */

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Handler;
import android.os.Message;
import android.widget.ImageView;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * 异步加载网络图片
 */
public class ImageLoader {

    private ImageView iv;
    private String urlAddress;

    public ImageLoader(ImageView imageView, String url) {
        iv = imageView;
        urlAddress = url;
    }

    //region 多线程

    /**
     * 接收异步线程的消息，更新主线程的ui
     */
    private Handler handler = new Handler() {

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            iv.setImageBitmap((Bitmap) msg.obj);
        }
    };

    /**
     * 使用多线程异步加载图片
     * 异步线程不能直接更新ui，需要利用Message
     */
    public void showImageByThread() {
        new Thread(){

            @Override
            public void run() {
                super.run();

                //获取网络图片
                Bitmap bitmap = getBitmapFromURL(urlAddress);

                //通知主线程更新图片
                Message message = Message.obtain();//获取现有的以及回收的message，提高message的使用效率
                message.obj = bitmap;
                handler.sendMessage(message);
            }
        }.start();
    }

    //endregion

    //region AsyncTask方式

    /**
     * AsyncTask是在多线程的基础上进行了封装，可利用onPostExecute方法直接更新ui
     */
    public void showImageByAsyncTask() {
        new LoadImageAsyncTask().execute(urlAddress);
    }

    class LoadImageAsyncTask extends AsyncTask<String, Void, Bitmap> {

        @Override
        protected Bitmap doInBackground(String... strings) {
            return getBitmapFromURL(strings[0]);
        }

        @Override
        protected void onPostExecute(Bitmap bitmap) {
            super.onPostExecute(bitmap);

            iv.setImageBitmap(bitmap);
        }
    }

    //endregion

    /**
     * 加载网络图片
     * @param urlString
     * @return
     */
    private Bitmap getBitmapFromURL(String urlString) {
        Bitmap bitmap = null;
        InputStream is = null;
        try {
            URL url = new URL(urlString);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            is = new BufferedInputStream(connection.getInputStream());
            bitmap = BitmapFactory.decodeStream(is);

            connection.disconnect();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return bitmap;
    }
}