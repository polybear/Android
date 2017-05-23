package exam01.study.com.hello;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class ActivityMain extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 버튼1 :
        // 클릭 이벤트 추가
        // 토스트 생성
        Button btn1 = (Button)findViewById(R.id.button1);
        btn1.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Toast.makeText(getApplicationContext(), "긴 토스트", Toast.LENGTH_LONG).show();
                Log.d("study", "Hello");
            }
        });

        // 버튼2 :
        //인텐트 만들어 웹브라우저 띄우기
        Button btn2 = (Button)findViewById(R.id.button2);
        btn2.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://m.naver.com"));
                startActivity(intent);
            }
        });

        // 버튼3 :
        //인텐트 만들어 전화 띄우기
        Button btn3 = (Button)findViewById(R.id.button3);
        btn3.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("tel:01040197521"));
                startActivity(intent);
            }
        });

        // 버튼4 :
        //인텐트 만들어 전화 띄우기
        Button btn4 = (Button)findViewById(R.id.button4);
        btn4.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                EditText editText1 = (EditText)findViewById(R.id.editText1);

                String str = editText1.getText().toString();

                TextView textView1 = (TextView)findViewById(R.id.textView1);
                textView1.setText(str);

                Toast.makeText(getApplicationContext(), "EditText : " + str, Toast.LENGTH_SHORT).show();
            }
        });

        // 버튼5 :
        //인텐트 만들어 전화 띄우기
        Button btn5 = (Button)findViewById(R.id.button5);
        btn5.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(getApplicationContext(), NewActivity.class);
                intent.putExtra("CustomerName", "홍길동");
                //startActivity(intent);
                startActivityForResult(intent, 1);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        String sData = "";
        String str = "OnActivityResult() called : " + requestCode + " : " + resultCode;

        if (requestCode == 1)
        {
            sData = data.getStringExtra("BackData");
            str = str + " : " + sData;
        }

        Toast.makeText(getApplicationContext(), str, Toast.LENGTH_SHORT).show();
    }
}