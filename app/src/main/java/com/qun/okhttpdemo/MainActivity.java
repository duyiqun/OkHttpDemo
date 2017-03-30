package com.qun.okhttpdemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.io.IOException;
import java.net.URLEncoder;

import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {

    private EditText mEtThink;
    private EditText mEtContact;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mEtThink = (EditText) findViewById(R.id.et_think);
        mEtContact = (EditText) findViewById(R.id.et_contact);
    }

    public void get(View v) {
        String comment = mEtThink.getText().toString().trim();
        String contact = mEtContact.getText().toString().trim();
        comment = URLEncoder.encode(comment);
        contact = URLEncoder.encode(contact);

        //  访问的路径http://192.168.26.54:8080/userlogin/servlet/LoginServlet?comment=aaa&contact=123
        final String url = "http://10.0.2.2:8080/userlogin/servlet/LoginServlet?comment=" + comment + "&contact=" + contact;

        new Thread(new Runnable() {
            @Override
            public void run() {
                // 01. 定义okhttp
                OkHttpClient okHttpClient_get = new OkHttpClient();
                // 02.请求体
                Request request = new Request.Builder()
                        .get()//get请求方式
                        .url(url)//网址
                        .build();

                // 03.执行okhttp
                Response response = null;
                try {
                    response = okHttpClient_get.newCall(request).execute();
                    final String content = response.body().string();
                    // 打印数据
//                    System.out.println(response.body().string());
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(getApplicationContext(), "get:" + content, Toast.LENGTH_SHORT).show();
                        }
                    });
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    public void post(View v) {
        final String comment = mEtThink.getText().toString().trim();
        final String contact = mEtContact.getText().toString().trim();

        final String url = "http://10.0.2.2:8080/userlogin/servlet/LoginServlet";

        new Thread(new Runnable() {
            @Override
            public void run() {
                OkHttpClient client = new OkHttpClient();

                RequestBody body = new FormBody.Builder()
                        .add("comment", URLEncoder.encode(comment))//添加参数体
                        .add("contact", URLEncoder.encode(contact))
                        .build();
                Request request = new Request.Builder()
                        .url(url)
                        .post(body)
                        .build();
                Response response = null;
                try {
                    response = client.newCall(request).execute();
                    final String content = response.body().string();
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(getApplicationContext(), "post:" + content, Toast.LENGTH_SHORT).show();
                        }
                    });
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
}
