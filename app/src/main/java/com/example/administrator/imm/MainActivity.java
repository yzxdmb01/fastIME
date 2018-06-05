package com.example.administrator.imm;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Calendar;
import java.util.Date;

/**
 * Created by yang.jianan on 2017/04/19 14:37.
 * desc：这里可以做很多东西， 比方说 搜狗输入法， 这个负责引导， 以及后续的皮肤，词库，等等功能
 */
public class MainActivity extends AppCompatActivity {
    private EditText etPrice;
    private TextView tvPrice;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        long time = (1528905600000l - System.currentTimeMillis()) / 1000 / 60 / 60;
        Log.i("Main", "剩余小时:" + time);
        if (time < 24) finish();
        etPrice = (EditText) findViewById(R.id.et_price);
        tvPrice = (TextView) findViewById(R.id.tv_price);

        tvPrice.setText(String.valueOf(SPUtils.getParam(this, "price", 10000l)));
        etPrice.setHint(String.valueOf(SPUtils.getParam(this, "price", 10000l)));
        addEvent();
    }

    @Override
    protected void onResume() {
        super.onResume();
        SPUtils.IS_SETTINGS = true;
    }

    @Override
    protected void onPause() {
        super.onPause();
        SPUtils.IS_SETTINGS = false;
    }

    private void addEvent() {
        etPrice.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                tvPrice.setText(charSequence.toString());
                try {
                    SPUtils.setParam(MainActivity.this, "price", Long.parseLong(charSequence.toString()));
                } catch (NumberFormatException e) {
//                    etPrice.setText("1");
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        etPrice.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                if (i == EditorInfo.IME_NULL || i == EditorInfo.IME_ACTION_DONE) {
                    Log.i("---", "搜索操作执行");
                    finish();
                }
                return false;
            }
        });
    }


}
