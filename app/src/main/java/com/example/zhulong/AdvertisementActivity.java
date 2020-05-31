package com.example.zhulong;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

public class AdvertisementActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView tv_skip;
    private ImageView iv_splash_ad;
    private int num = 5;
    Handler handler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(@NonNull Message msg) {
            switch (msg.what) {
                case 0:
                    if (num > 1) {
                        num--;
                        tv_skip.setText("跳过" + num);
                        handler.sendEmptyMessageDelayed(0, 1000);
                    } else {
                        skipActivity();
                    }
                    break;
            }
            return false;
        }
    });

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_advertisement);
        initView();
    }

    private void initView() {
        tv_skip = (TextView) findViewById(R.id.tv_skip);
        iv_splash_ad = (ImageView) findViewById(R.id.iv_splash_ad);


        tv_skip.setOnClickListener(this);
        tv_skip.setText("跳过 5");
        handler.sendEmptyMessage(0);
    }

    public void skipActivity() {
        startActivity(new Intent(AdvertisementActivity.this, MainActivity.class));
        finish();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_skip:
                skipActivity();
                handler.removeMessages(0);
                break;
        }
    }
}
