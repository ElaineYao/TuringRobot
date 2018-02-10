package com.elainedv.turingrobot;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import org.json.JSONObject;

import java.net.URL;
import java.util.ArrayList;

import Utils.Constants;
import Utils.HttpCallbackListener;

public class MainActivity extends AppCompatActivity   {


    ArrayList<Message> messages=new ArrayList<Message>();
    RecyclerView rv;
    Button bt;
    EditText et;
    MyAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.i("-----onClick-----","onClick");
               String content=et.getText().toString();
               Message message=new Message();
               message.setType(Constants.TYPE_RIGHT);
               message.setContent(content);
               messages.add(message);
               adapter.notifyItemInserted(messages.size()-1);
               rv.scrollToPosition(messages.size()-1);
               et.setText("");
                String url="http://www.tuling123.com/openapi/api?key=2df4795a65c1455794661d0049486332&info="+content+"&userid=123";
                new HttpTask(url, new HttpCallbackListener() {
                    @Override
                    public void onFinish(String response) {
                        parseJSON(response);
                        adapter.notifyItemInserted(messages.size()-1);
                        rv.scrollToPosition(messages.size()-1);
                        Log.i("-----thread-----",Thread.currentThread().getName());
                        Log.i("----msg0--------", messages.get(0).getContent());
                    }
                }).execute();
            }
        });

    }

    public void initView() {
        bt=(Button)findViewById(R.id.send);
        rv=(RecyclerView)findViewById(R.id.msg_recycler_view);
        et=(EditText)findViewById(R.id.input_text);
        rv.setLayoutManager(new LinearLayoutManager(MainActivity.this));
        Message message1=new Message();
        message1.setType(Constants.TYPE_LEFT);
        message1.setContent(getRandomWelcomeTips());
        messages.add(message1);
        adapter=new MyAdapter(MainActivity.this,messages);
        rv.setAdapter(adapter);
        Log.i("----initview-----","here");
    }

    public void parseJSON(String jsonData) {
        try {
            Message msg = new Message();
            JSONObject jsonObject = new JSONObject(jsonData);
            msg.setType(Constants.TYPE_LEFT);
            msg.setContent(jsonObject.getString("text"));
            messages.add(msg);
            Log.i("----msg1--------", messages.get(0).getContent());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String getRandomWelcomeTips(){
        String welcome_tips;
        String[] welcome_array;
        int index;
        welcome_array=this.getResources().getStringArray(R.array.WelcomeTips);
        index=(int)(Math.random()*(welcome_array.length-1));
        welcome_tips=welcome_array[index];
        return welcome_tips;
    }


}
