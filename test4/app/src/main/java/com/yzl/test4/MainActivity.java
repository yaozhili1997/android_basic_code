package com.yzl.test4;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.provider.Settings;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private static final int MY_PERMISSIONS_REQUEST_CALL_PHONE = 1;
    private Button btn_dia1;
    private Button btn_dia2;
    private Button btn_dia3;
    private Button btn_dia4;
    private EditText et_number;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // 找到控件
        et_number = findViewById(R.id.editText2);
        btn_dia1 = findViewById(R.id.button);
        btn_dia2 = findViewById(R.id.button2);
        btn_dia3 = findViewById(R.id.button3);
        btn_dia4 = findViewById(R.id.button4);

        // 给按钮设置监听（点击事件）
        btn_dia1.setOnClickListener(this);
        btn_dia2.setOnClickListener(this);
        btn_dia3.setOnClickListener(this);
        btn_dia4.setOnClickListener(this);
    }



    private void CallPhone() {
        String number = et_number.getText().toString();
        if (TextUtils.isEmpty(number)) {
            Toast.makeText(MainActivity.this, "号码不能为空！", Toast.LENGTH_SHORT).show();
        } else {
            Intent intent = new Intent(); // 意图对象：动作 + 数据
            intent.setAction(Intent.ACTION_CALL); // 设置动作
            Uri data = Uri.parse("tel:" + number); // 设置数据
            intent.setData(data);
            startActivity(intent); // 激活Activity组件
        }
    }
    // 处理权限申请的回调
    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        switch (requestCode) {
            case MY_PERMISSIONS_REQUEST_CALL_PHONE: {
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    // 授权成功，继续打电话
                    CallPhone();
                } else {
                    // 授权失败！
                    Toast.makeText(this, "授权失败！", Toast.LENGTH_LONG).show();
                }
                break;
            }
        }
    }
    public void checkPermissionAndCall(){
        if (ContextCompat.checkSelfPermission(MainActivity.this,
                Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
            // 没有获得授权，申请授权
            if (ActivityCompat.shouldShowRequestPermissionRationale(MainActivity.this,
                    Manifest.permission.CALL_PHONE)) {
                // 返回值：
//                          如果app之前请求过该权限,被用户拒绝, 这个方法就会返回true.
//                          如果用户之前拒绝权限的时候勾选了对话框中”Don’t ask again”的选项,那么这个方法会返回false.
//                          如果设备策略禁止应用拥有这条权限, 这个方法也返回false.
                // 弹窗需要解释为何需要该权限，再次请求授权
                Toast.makeText(MainActivity.this, "请授权！", Toast.LENGTH_LONG).show();
                // 帮跳转到该应用的设置界面，让用户手动授权
                Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                Uri uri = Uri.fromParts("package", getPackageName(), null);
                intent.setData(uri);
                startActivity(intent);
            } else {
                // 不需要解释为何需要该权限，直接请求授权
                ActivityCompat.requestPermissions(MainActivity.this,
                        new String[]{Manifest.permission.CALL_PHONE},
                        MY_PERMISSIONS_REQUEST_CALL_PHONE);
            }
        } else {
            // 已经获得授权，可以打电话
            CallPhone();
        }

    }
    //当点击按钮的时候执行
    @Override
    public void onClick(View v) {
       //具体判断点击的是哪个按钮
        switch (v.getId()){
            case R.id.button:
                checkPermissionAndCall();
                break;
            case R.id.button2:
                checkPermissionAndCall();
                break;
            case R.id.button3:
                checkPermissionAndCall();
                break;
            case R.id.button4:
                System.out.println("按钮4，我被点击了");
                break;
            default:
                break;
        }
    }
    //按钮的第四种点击事件，声明一个方法 方法名称和你要点击的这个按钮在布局中申明的OnClick属性一样
    public void Click(View view) {
        checkPermissionAndCall();
    }
}
