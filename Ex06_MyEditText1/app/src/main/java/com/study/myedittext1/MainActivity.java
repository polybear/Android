package com.study.myedittext1;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.io.UnsupportedEncodingException;

public class MainActivity extends Activity {

    TextView textView1;

    EditText etID;
    EditText etPW;
    EditText etYY;
    EditText etMM;
    EditText etDD;

    String sID;
    String sPW;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);

        textView1 = (TextView)findViewById(R.id.textView1);

        etID = (EditText)findViewById(R.id.etID);
        etPW = (EditText)findViewById(R.id.etPW);
        etYY = (EditText)findViewById(R.id.etYY);
        etMM = (EditText)findViewById(R.id.etMM);
        etDD = (EditText)findViewById(R.id.etDD);

        etPW.addTextChangedListener(watcher);

        Button btnKeyDown = (Button)findViewById(R.id.btnKeyDown);
        btnKeyDown.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                InputMethodManager mgr = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
                mgr.hideSoftInputFromWindow(v.getWindowToken(), 0);
            }
        });

        Button btnLogin = (Button)findViewById(R.id.btnLogin);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sID = etID.getText().toString();
                sPW = etPW.getText().toString();

                if (sID.length() < 3){
                    new AlertDialog.Builder(MainActivity.this)
                            .setTitle("알림")
                            .setMessage("아이디를 입력하세요")
                            .setCancelable(false)
                            .setIcon(android.R.drawable.ic_dialog_alert)
                            .setPositiveButton("닫기", null)
                            .show();
                    etID.requestFocus();
                    return;
                }
                else if (sPW.length() < 5) {
                    new AlertDialog.Builder(MainActivity.this)
                            .setTitle("알림")
                            .setMessage("비밀번호가 틀립니다")
                            .setCancelable(false)
                            .setIcon(android.R.drawable.ic_dialog_alert)
                            .setPositiveButton("닫기", null)
                            .show();
                    etPW.requestFocus();
                    return;
                }
            }
        });
    }

    TextWatcher watcher = new TextWatcher() {
        String beforeText;
        @Override
        public void beforeTextChanged(CharSequence str, int start, int count, int after) {
            beforeText = str.toString();
            Log.d("study", str.toString());
        }

        @Override
        public void onTextChanged(CharSequence str, int start, int before, int count) {
            byte[] bytes = null;
            try {
                bytes = str.toString().getBytes("KSC5601");
                int strCount = bytes.length;
                textView1.setText(strCount + " / 8 바이트");
            }
            catch (UnsupportedEncodingException ex) {
                ex.printStackTrace();
            }
        }

        @Override
        public void afterTextChanged(Editable strEditable) {
            String str = strEditable.toString();
            Log.d("study", str);
            try {
                byte[] strBytes = str.getBytes("KSC5601");
                if (strBytes.length > 8) {
                    etPW.setText(beforeText);
                    etPW.setSelection(beforeText.length() - 1, beforeText.length() - 1);
                }
            }
            catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    };
}
