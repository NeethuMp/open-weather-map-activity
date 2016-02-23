package com.example.neethu.openweathermapactivity;

/**
 * Created by neethu on 16/2/16.
 */
public class Weather {
    public String main;
    public  String description;
    public String icon;

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public Weather(String description) {
        this.description = description;
    }

    public String getMain() {
        return main;
    }

    public void setMain(String main) {
        this.main = main;
    }
}
