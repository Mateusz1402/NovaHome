package com.novahome.functionalities.core;

public interface Connectable {
    void turnOn();
    void turnOff();
    void connectToWifi();
    void disconnectFromWifi();
    boolean isRunning();
    boolean isConnectedToWifi();


}
