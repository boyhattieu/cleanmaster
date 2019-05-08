package com.icebear.cleanmaster.activities;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Environment;
import android.os.StatFs;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.icebear.cleanmaster.R;
import com.icebear.cleanmaster.adapter.MenuRecyclerAdapter;
import com.icebear.cleanmaster.constant.Key;
import com.icebear.cleanmaster.item.MenuItem;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    private List<MenuItem> menuItems;
    private RecyclerView rcvMenu;
    private ImageView imgClean;
    private ImageView imgRamUsage;
    private ImageView imgDiskUsage;
    private TextView txtDiskUsage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (!isGranted(Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
            permissionRequest(Manifest.permission.WRITE_EXTERNAL_STORAGE);
        } else {
            loadData();
        }
    }

    private void loadData() {
        initView();
        initData();
    }

    private boolean isGranted(String permission) {
        if (isMashMarllow()) {
            return ContextCompat.checkSelfPermission(this, permission) == PackageManager.PERMISSION_GRANTED;
        } else {
            return true;
        }
    }

    private boolean isMashMarllow() {
        return Build.VERSION.SDK_INT > Build.VERSION_CODES.LOLLIPOP_MR1;
    }

    private void permissionRequest(String... strings) {
        ActivityCompat.requestPermissions(
                this,
                strings,
                Key.REQUEST_CODE_PERMISSION);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case Key.REQUEST_CODE_PERMISSION:
                for (int i = 0; i < permissions.length; i++) {
                    if (grantResults[i] == PackageManager.PERMISSION_GRANTED
                            && permissions[i].equals(Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
                        loadData();
                    } else {
                        this.finish();
                    }
                }
                break;

            default:
                break;
        }
    }

    public static boolean externalMemoryAvailable() {
        return android.os.Environment.getExternalStorageState().equals(
                android.os.Environment.MEDIA_MOUNTED);
    }

    public static String formatSize(long size) {
        String suffix = null;

        if (size >= 1024) {
            suffix = "KB";
            size /= 1024;
            if (size >= 1024) {
                suffix = "MB";
                size /= 1024;
            }
        }

        StringBuilder resultBuffer = new StringBuilder(Long.toString(size));

        int commaOffset = resultBuffer.length() - 3;
        while (commaOffset > 0) {
            resultBuffer.insert(commaOffset, ',');
            commaOffset -= 3;
        }

        if (suffix != null) resultBuffer.append(suffix);
        return resultBuffer.toString();
    }

    public static String getAvailableExternalMemorySize() {
        if (externalMemoryAvailable()) {
            File path = Environment.getExternalStorageDirectory();
            StatFs stat = new StatFs(path.getPath());
            long blockSize = stat.getBlockSizeLong();
            long availableBlocks = stat.getAvailableBlocksLong();
            return formatSize(availableBlocks * blockSize);
        } else {
            return "0";
        }
    }

    public static String getTotalExternalMemorySize() {
        if (externalMemoryAvailable()) {
            File path = Environment.getExternalStorageDirectory();
            StatFs stat = new StatFs(path.getPath());
            long blockSize = stat.getBlockSizeLong();
            long totalBlocks = stat.getBlockCountLong();
            return formatSize(totalBlocks * blockSize);
        } else {
            return "0";
        }
    }

    private void initData() {
        menuItems = new ArrayList<>();
        menuItems.add(new MenuItem(R.drawable.img_clean, "Tăng tốc điện thoại"));
        menuItems.add(new MenuItem(R.drawable.ic_trash, "Tập tin rác"));
        menuItems.add(new MenuItem(R.drawable.ic_lock, "Khóa ứng dụng"));
        menuItems.add(new MenuItem(R.drawable.ic_cooler, "CPU Cooler"));
        menuItems.add(new MenuItem(R.drawable.ic_battery, "Tiết kiệm pin"));
        menuItems.add(new MenuItem(R.drawable.ic_app, "Quản lý ứng dụng"));
        menuItems.add(new MenuItem(R.drawable.ic_rate, "Đánh giá ứng dụng này"));
        menuItems.add(new MenuItem(R.drawable.ic_share, "Chia sẻ"));

        MenuRecyclerAdapter adapter = new MenuRecyclerAdapter(menuItems);
        rcvMenu.setAdapter(adapter);
        rcvMenu.setLayoutManager(new GridLayoutManager(this, 2));

        adapter.setOnClickItem(new MenuRecyclerAdapter.IOnClickItem() {
            @Override
            public void onClickItem(int position) {
                switch (position) {
                    case 0:
                        Toast.makeText(MainActivity.this, "Coming soon", Toast.LENGTH_SHORT).show();
                        break;

                    case 1:
                        // TODO: 08/05/2019 Clean trash files
                        startActivity(new Intent(MainActivity.this, TrashFilesActivity.class));
//                        Toast.makeText(MainActivity.this, "Coming soon", Toast.LENGTH_SHORT).show();
                        break;

                    case 2:
                        Toast.makeText(MainActivity.this, "Coming soon", Toast.LENGTH_SHORT).show();
                        break;

                    case 3:
                        // TODO: 08/05/2019 CPU Cooler
                        startActivity(new Intent(MainActivity.this, CpuCoolerActivity.class));
//                        Toast.makeText(MainActivity.this, "Coming soon", Toast.LENGTH_SHORT).show();
                        break;

                    case 4:
                        Toast.makeText(MainActivity.this, "Coming soon", Toast.LENGTH_SHORT).show();
                        break;

                    case 5:
                        startActivity(new Intent(MainActivity.this, LoadingAppActivity.class));
                        break;

                    case 6:
                        Toast.makeText(MainActivity.this, "Coming soon", Toast.LENGTH_SHORT).show();
                        break;

                    case 7:
                        // TODO: 08/05/2019 Share
                        Intent shareIntent = new Intent(Intent.ACTION_SEND);
                        shareIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        shareIntent.setType("text/plain");

                        shareIntent.putExtra(Intent.EXTRA_SUBJECT, "This app is the best");
                        shareIntent.putExtra(Intent.EXTRA_TEXT, "This is the app link");

                        startActivity(Intent.createChooser(shareIntent, "Sharing App..."));
                        Log.d(TAG, "onClickItem: " + "Clicked");
//                        Toast.makeText(MainActivity.this, "Coming soon", Toast.LENGTH_SHORT).show();
                        break;
                }
            }
        });
    }

    private float convertedAvailableMemory() {
        return Float.valueOf(getAvailableExternalMemorySize().substring(0, getAvailableExternalMemorySize().indexOf(",")) + getAvailableExternalMemorySize().substring(getAvailableExternalMemorySize().indexOf(",") + 1, getAvailableExternalMemorySize().indexOf("M")));
    }

    private float convertedTotalMemory() {
        return Float.valueOf(getTotalExternalMemorySize().substring(0, getTotalExternalMemorySize().indexOf(",")) + getTotalExternalMemorySize().substring(getTotalExternalMemorySize().indexOf(",") + 1, getTotalExternalMemorySize().indexOf("M")));
    }

    private void initView() {
        rcvMenu = findViewById(R.id.rcv_menu);
        imgClean = findViewById(R.id.img_clean);
        imgRamUsage = findViewById(R.id.img_ram_usage);
        imgDiskUsage = findViewById(R.id.img_disk_usage);
        txtDiskUsage = findViewById(R.id.txt_disk_usage);

        Glide.with(this).load(R.drawable.img_clean).into(imgClean);
        Glide.with(this).load(R.drawable.img_circle).into(imgRamUsage);
        Glide.with(this).load(R.drawable.img_circle).into(imgDiskUsage);

        txtDiskUsage.setText(
                String.valueOf((int) (convertedAvailableMemory() /
                        convertedTotalMemory() *
                        100))
        );
    }
}
