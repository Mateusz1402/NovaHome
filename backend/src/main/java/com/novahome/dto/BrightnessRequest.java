package com.novahome.dto;

public class BrightnessRequest {
    private int level;

    public BrightnessRequest() {
        this.level = 0;
    }

    public int getLevel(){
        return level;
    }

    public void setLevel(int level){
        this.level = level;
    }
}
