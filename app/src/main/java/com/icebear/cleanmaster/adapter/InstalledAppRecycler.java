package com.icebear.cleanmaster.adapter;

import android.graphics.drawable.Drawable;
import android.support.annotation.DrawableRes;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.icebear.cleanmaster.R;
import com.icebear.cleanmaster.item.InstalledApp;

import java.io.File;
import java.util.List;

public class InstalledAppRecycler extends RecyclerView.Adapter<InstalledAppRecycler.ItemHolder> {

    private List<InstalledApp> installedApps;

    public InstalledAppRecycler(List<InstalledApp> installedApps) {
        this.installedApps = installedApps;
    }

    @NonNull
    @Override
    public InstalledAppRecycler.ItemHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new ItemHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_app_recycler, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull InstalledAppRecycler.ItemHolder itemHolder, int i) {
        Drawable imgAppLogo = installedApps.get(i).getImgAppLogo();
        String txtAppName = installedApps.get(i).getTxtAppName();
        itemHolder.setData(imgAppLogo, txtAppName);
        itemHolder.btnRemove.setOnClickListener((v) ->  {
            Toast.makeText(itemHolder.itemView.getContext(), "Uninstalled " + installedApps.get(i).getTxtAppName(), Toast.LENGTH_SHORT).show();
        });
    }

    @Override
    public int getItemCount() {
        return installedApps.size();
    }

    public class ItemHolder extends RecyclerView.ViewHolder {

        private ImageView imgAppLogo;
        private TextView txtAppName;
        private TextView btnRemove;

        public ItemHolder(@NonNull View itemView) {
            super(itemView);

            imgAppLogo = itemView.findViewById(R.id.img_app_logo);
            txtAppName = itemView.findViewById(R.id.txt_app_name);
            btnRemove = itemView.findViewById(R.id.btn_remove);
        }

        private void setData(Drawable imgAppLogo, String txtAppName){
            Glide.with(itemView).load(imgAppLogo).into(this.imgAppLogo);
            this.txtAppName.setText(txtAppName);
        }
    }
}
