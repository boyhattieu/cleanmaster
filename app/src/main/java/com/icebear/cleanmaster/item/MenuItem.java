package com.icebear.cleanmaster.item;

public class MenuItem {
    private int imgMenu;
    private String txtMenu;

    public MenuItem(int imgMenu, String txtMenu) {
        this.imgMenu = imgMenu;
        this.txtMenu = txtMenu;
    }

    public int getImgMenu() {
        return imgMenu;
    }

    public String getTxtMenu() {
        return txtMenu;
    }
}
