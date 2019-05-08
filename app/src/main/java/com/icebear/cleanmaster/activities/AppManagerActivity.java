package com.icebear.cleanmaster.activities;

import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.icebear.cleanmaster.R;
import com.icebear.cleanmaster.adapter.InstalledAppRecycler;
import com.icebear.cleanmaster.item.InstalledApp;

import java.util.ArrayList;
import java.util.List;

public class AppManagerActivity extends AppCompatActivity {

    private List<InstalledApp> installedApps;
    private RecyclerView rcvAppList;
    private TextView btnRemove;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_app_manager);
        initView();
        initData();
    }

    private void initData() {
        installedApps = new ArrayList<>();
        final PackageManager packageManager = getPackageManager();
        List<PackageInfo> packageInfos = packageManager.getInstalledPackages(0);
        for (PackageInfo packageInfo : packageInfos){
            installedApps.add(new InstalledApp(packageInfo.applicationInfo.loadIcon(packageManager), packageInfo.applicationInfo.loadLabel(packageManager).toString()));
        }

        InstalledAppRecycler adapter = new InstalledAppRecycler(installedApps);
        rcvAppList.setAdapter(adapter);
        rcvAppList.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
    }

    private void initView() {
        rcvAppList = findViewById(R.id.rcv_app);
        btnRemove = findViewById(R.id.btn_remove);
    }
}
