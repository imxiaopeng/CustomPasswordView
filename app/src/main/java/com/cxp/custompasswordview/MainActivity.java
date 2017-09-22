package com.cxp.custompasswordview;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        PassWordView passWordView = (PassWordView) findViewById(R.id.passView);
        passWordView.setmListener(new PassWordView.OnCompleteInputListener() {
            @Override
            public void onComplete(String passw) {
                Toast.makeText(MainActivity.this,"输入了"+passw, Toast.LENGTH_LONG).show();
            }
        });
    }
}
