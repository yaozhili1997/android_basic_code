package com.yzl.test2;


import android.content.Intent;
import android.net.Uri;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    private Button button;
    private EditText et_text;//变成全局变量

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //加载一个布局
        setContentView(R.layout.activity_main);
        //找到我们关心的控件
        et_text = findViewById(R.id.editText2);
        //找到按钮
        button = findViewById(R.id.button);
        //给按钮设置一个点击事件
        button.setOnClickListener(new MyClickListener());//这叫做内部类
    }

    private class MyClickListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            String number = et_text.getText().toString().trim();//获取输入的值
            if ("".equals(number)) {
                //context 上下文
                Toast.makeText(MainActivity.this, "输入值不能为空", Toast.LENGTH_LONG).show();
                return;
            }
            Intent intent = new Intent();//创建一个意图
            intent.setAction(Intent.ACTION_CALL);
            //设置要拨打的数据  uri:统一资源标识符 url:统一资源定位符
            intent.setData(Uri.parse("tel:"+number));
            //开启意图
            startActivity(intent);
        }

    }
}
