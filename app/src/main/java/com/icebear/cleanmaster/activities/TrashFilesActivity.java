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

public class TrashFilesActivity extends AppCompatActivity {

    private ImageView imgTrash;
    private Thread loadingThread;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trash_files);
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
                            ViewAnimator.animate(imgTrash).swing().duration(500).start();
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
            Toast.makeText(this, "Tệp tin rác đã được loại bỏ", Toast.LENGTH_SHORT).show();
            finish();
        }, 3000);
    }



    private void initView() {
        imgTrash = findViewById(R.id.img_trash);
        Glide.with(this).load(R.drawable.ic_trash).into(imgTrash);
    }
}
