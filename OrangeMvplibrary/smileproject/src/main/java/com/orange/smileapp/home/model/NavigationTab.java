package com.orange.smileapp.home.model;

/**
 * GridView导航项
 */
public class NavigationTab {
    private String name;
    private int icon;

    public NavigationTab(String name, int icon) {
        this.name = name;
        this.icon = icon;
    }

    public int getIcon() {
        return icon;
    }

    public void setIcon(int icon) {
        this.icon = icon;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
