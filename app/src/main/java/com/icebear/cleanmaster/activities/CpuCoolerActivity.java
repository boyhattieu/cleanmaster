package com.icebear.cleanmaster.activities;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.github.florent37.viewanimator.ViewAnimator;
import com.icebear.cleanmaster.R;

public class CpuCoolerActivity extends AppCompatActivity {

    private ImageView imgSnow;
    private Thread loadingThread;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cpu_cooler);
        initView();
        animation();
        turnBackToMain();
    }

    private void animation() {
        loadingThread = new Thread(new Runnable() {
            @Override
            public void run() {
                while(true){
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            ViewAnimator.animate(imgSnow).rotation(1080).duration(3000).start();
                        }
                    });

                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        loadingThread.start();
    }

    private void turnBackToMain() {
        new Handler().postDelayed(() -> {
            loadingThread.interrupt();
            startActivity(new Intent(this, MainActivity.class));
            Toast.makeText(this, "CPU đã được giảm nhiệt độ", Toast.LENGTH_SHORT).show();
            finish();
        }, 3000);
    }



    private void initView() {
        imgSnow = findViewById(R.id.img_snow);
        Glide.with(this).load(R.drawable.img_snow_flower).into(imgSnow);
    }
}
