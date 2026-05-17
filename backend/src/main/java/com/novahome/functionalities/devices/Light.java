package com.novahome.functionalities.devices;

import com.novahome.functionalities.core.SmartDevice;

public class Light extends SmartDevice{
    private int brightness;

    public Light(){
        //super() method copy the constructor of parent class
        super();
        this.brightness = 0;
    }

    public void setBrightness(int brightnessLevel){
        if (this.isRunning() && this.isConnectedToWifi()){
            if(brightnessLevel >= 0 && brightnessLevel <= 100) {
                this.brightness = brightnessLevel;
            }
        }else{
            if(!this.isRunning()){
                throw new IllegalStateException("Cannot adjust the brightness: Device is turned off!");
            }else if(!this.isConnectedToWifi()){
                throw new IllegalStateException("Cannot adjust the brightness: Device is not connected to Wifi!");
            }
        }

    }

    public int getBrightness(){
        return this.brightness;
    }

    public boolean getIsRunning(){
        return this.isRunning();
    }

    public boolean getIsConnectedToWifi(){
        return this.isConnectedToWifi();
    }



}
