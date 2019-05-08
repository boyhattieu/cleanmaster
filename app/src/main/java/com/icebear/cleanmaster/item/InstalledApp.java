package com.icebear.cleanmaster.item;

import android.graphics.drawable.Drawable;

public class InstalledApp {
    private Drawable imgAppLogo;
    private String txtAppName;

    public InstalledApp(Drawable imgAppLogo, String txtAppName) {
        this.imgAppLogo = imgAppLogo;
        this.txtAppName = txtAppName;
    }

    public Drawable getImgAppLogo() {
        return imgAppLogo;
    }

    public String getTxtAppName() {
        return txtAppName;
    }
}
