package com.example.test1;

import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

public class TextViewActivity1 extends AppCompatActivity {
    private TextView mTv7;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_text_view1);
        mTv7=findViewById(R.id.tv_7);
        mTv7.setSelected(true);
    }
}
