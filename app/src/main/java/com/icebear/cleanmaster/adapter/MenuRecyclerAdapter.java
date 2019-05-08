package com.icebear.cleanmaster.adapter;

import android.support.annotation.DrawableRes;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.icebear.cleanmaster.R;
import com.icebear.cleanmaster.item.MenuItem;

import java.util.List;

public class MenuRecyclerAdapter extends RecyclerView.Adapter<MenuRecyclerAdapter.ItemHolder> {

    private List<MenuItem> menuItems;
    private IOnClickItem onClickItem;

    public MenuRecyclerAdapter(List<MenuItem> menuItems) {
        this.menuItems = menuItems;
    }

    public void setOnClickItem(IOnClickItem onClickItem) {
        this.onClickItem = onClickItem;
    }

    @NonNull
    @Override
    public MenuRecyclerAdapter.ItemHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new ItemHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_menu_recycler, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull MenuRecyclerAdapter.ItemHolder itemHolder, final int i) {
        int imgMenu = menuItems.get(i).getImgMenu();
        String txtMenu = menuItems.get(i).getTxtMenu();
        itemHolder.setData(imgMenu, txtMenu);
        itemHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickItem.onClickItem(i);
            }
        });
    }

    @Override
    public int getItemCount() {
        return menuItems.size();
    }

    public class ItemHolder extends RecyclerView.ViewHolder {

        private ImageView imgMenu;
        private TextView txtMenu;

        public ItemHolder(@NonNull View itemView) {
            super(itemView);

            imgMenu = itemView.findViewById(R.id.img_menu);
            txtMenu = itemView.findViewById(R.id.txt_menu);
        }

        public void setData(@DrawableRes int imgMenu, String txtMenu){
            Glide.with(itemView).load(imgMenu).into(this.imgMenu);
            this.txtMenu.setText(txtMenu);
        }
    }

    public interface IOnClickItem{
        void onClickItem(int position);
    }
}
