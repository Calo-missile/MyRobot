package com.hsc.myrobot;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Window;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.hsc.myrobot.MessageControl.Data;
import com.hsc.myrobot.MessageControl.Result;
import com.hsc.myrobot.MessageControl.TextAdapter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private String url = "http://www.tuling123.com/openapi/api?key=a269b344966244a0b581724b3d910458&info=";
    private String content;
    private ListView listView;
    private Button send;
    private EditText inputText;
    private TextAdapter adapter;
    private List<Data> mDatas;
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.main);
        initView();
        Data data = new Data("主人你好，小千为你服务！", Data.RECEIVE);
        mDatas.add(data);
        adapter.notifyDataSetChanged();
    }

    private void initView(){
        listView = (ListView) findViewById(R.id.list_view);
        send = (Button) findViewById(R.id.send_msg);
        inputText = (EditText) findViewById(R.id.input_text);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        mDatas = new ArrayList<>();
        adapter = new TextAdapter(this, R.layout.msg_item, mDatas);
        listView.setAdapter(adapter);
    }

    public void showResponse(final String respose) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Data data = new Data(respose, Data.RECEIVE);
                mDatas.add(data);
                adapter.notifyDataSetChanged();

            }
        });
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.send_msg) {
            sendMessage();
            sendRequestWithHttpURLConnection();
        }
    }
    
    private void sendMessage(){
        content = inputText.getText().toString();
        Data data = new Data(content, Data.SEND);
        mDatas.add(data);
        adapter.notifyDataSetChanged();
        inputText.setText("");
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        // 得到InputMethodManager的实例
        if (imm.isActive())
        {
            // 如果开启
            imm.toggleSoftInput(InputMethodManager.SHOW_IMPLICIT,
                    InputMethodManager.HIDE_NOT_ALWAYS);
            // 关闭软键盘，开启方法相同，这个方法是切换开启与关闭状态的
        }
    }

    private void sendRequestWithHttpURLConnection() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    OkHttpClient mHttpClient = new OkHttpClient();
                    RequestBody requestBody = new FormBody.Builder()
                            .build();
                    Request request = new Request.Builder()
                            .url(url+content)
                            .post(requestBody)
                            .build();
                    Response response = mHttpClient.newCall(request).execute();
                    String responseData = response.body().string();
                    Gson gson = new Gson();
                    Result result = gson.fromJson(responseData, Result.class);
                    showResponse(result.getText());
                } catch (IOException e) {
                    Toast.makeText(MainActivity.this,"最遥远的距离就是没网...",Toast.LENGTH_SHORT).show();
                    e.printStackTrace();
                }
            }
        }).start();
    }
}
