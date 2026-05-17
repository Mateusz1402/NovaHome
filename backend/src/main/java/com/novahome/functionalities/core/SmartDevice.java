package com.novahome.functionalities.core;

public abstract class SmartDevice implements Connectable{
    private boolean runningState;
    private boolean connectionToWifi;

    public SmartDevice(){
        this.runningState = false;
        this.connectionToWifi = false;
    }

    @Override
    public void turnOn(){
        this.runningState = true;
    }

    @Override
    public void turnOff(){
        this.runningState = false;
    }

    @Override
    public boolean isRunning(){
        return this.runningState;
    }

    @Override
    public void connectToWifi(){
        this.connectionToWifi = true;
    }

    @Override
    public void disconnectFromWifi(){this.connectionToWifi = false;}

    @Override
    public boolean isConnectedToWifi(){
        return this.connectionToWifi;
    }
}
