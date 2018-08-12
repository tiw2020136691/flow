package com.bawei.mr.zhoukao2lxapp;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.uuzuche.lib_zxing.activity.CaptureActivity;
import com.uuzuche.lib_zxing.activity.CodeUtils;

public class QRCodeActivity extends AppCompatActivity {

    private EditText inputEt;
    private ImageView scanIv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qrcode);
        initView();
    }

    private void initView() {
        inputEt = findViewById(R.id.scan_tv);
        scanIv = findViewById(R.id.scanIv);
    }

    /**
     * 扫描二维码
     *
     * @param view
     */
    public void scanBtn(View view) {

        startActivity(new Intent(this, CaptureActivity.class));

    }

    /**
     * 生成二维码
     *
     * @param view
     */
    public void generateBtn(View view) {

        String content = inputEt.getText().toString();

        if (TextUtils.isEmpty(content)) {
            Toast.makeText(this, "请输入关键字", Toast.LENGTH_SHORT).show();
            return;
        }

        Bitmap mBitmap = CodeUtils.createImage(content, 400, 400, BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher));
        scanIv.setImageBitmap(mBitmap);
    }
}
