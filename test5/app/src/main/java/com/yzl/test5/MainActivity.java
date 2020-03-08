package com.yzl.test5;


import android.text.TextUtils;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

import java.util.Map;

public class MainActivity extends AppCompatActivity {

    private EditText et_name;
    private EditText et_password;
    private CheckBox cb_isCheck;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //1.找到我们关心的控件
        et_name = findViewById(R.id.et_userName);
        et_password = findViewById(R.id.et_userPassword);
        cb_isCheck = findViewById(R.id.is_check);
        ///读取data/data/com.yzl.test5/info.txt里面的信息
        Map<String, String> maps = UserInfoUtils.readInfo();
        if(maps!=null){
            //把name和pwd取出来
            String name = maps.get("name");
            String pwd = maps.get("pwd");
            //把name和pwd显示到editText控件上
            et_name.setText(name);
            et_password.setText(pwd);

        }
    }
    //2.写按钮的点击事件
    public void login(View view) {
        String name=et_name.getText().toString().trim();
        String pwd=et_password.getText().toString().trim();
        //判断name和pwd是否为空
        if (TextUtils.isEmpty(name)||TextUtils.isEmpty(pwd)) {
            Toast.makeText(MainActivity.this, "账号密码不能为空！", Toast.LENGTH_SHORT).show();
        }else {
            //进行登陆的逻辑
            System.out.println("连接服务器");
            if(cb_isCheck.isChecked()){
                //把用户名和密码存起来
                boolean result = UserInfoUtils.saveInfo(name, pwd);
                if(result){
                    Toast.makeText(MainActivity.this, "保存成功", Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(MainActivity.this, "保存失败", Toast.LENGTH_SHORT).show();
                }
            }else {
                Toast.makeText(MainActivity.this, "请勾选", Toast.LENGTH_SHORT).show();
            }
        }

    }
}
