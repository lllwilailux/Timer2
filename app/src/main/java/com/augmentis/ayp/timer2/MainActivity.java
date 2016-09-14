package com.augmentis.ayp.timer2;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.sql.Time;
import java.util.concurrent.TimeUnit;

public class MainActivity extends AppCompatActivity {

    EditText editText;
    TextView textView;
    Button btn;
    String str;
    int num,i;
    protected Handler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editText = (EditText) findViewById(R.id.edt_text);
        textView = (TextView) findViewById(R.id.txt_view);


        handler = new Handler() {

            @Override
            public void handleMessage(Message msg) {
                textView.setText(String.valueOf(msg.what));
            }
        };


        btn = (Button) findViewById(R.id.btn);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                str = editText.getText().toString();
                num = Integer.parseInt(str);

                new Thread(new Runnable() {

                    @Override
                    public void run() {

                        for (i = num; i >= 0; i--) {
                            try {
                                handler.sendEmptyMessage(i);
                                Thread.sleep(1000);
                                //TimeUnit.SECONDS.sleep(1);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }).start();
            }
        });

    }

}
