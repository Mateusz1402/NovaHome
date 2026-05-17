package com.novahome.functionalities.hub;
import java.util.List;
import java.util.ArrayList;
import com.novahome.functionalities.devices.*;
public class CentralSystem {
    User user;
    private final List<Light> lights = new ArrayList<>();
    private final List<Thermostat> thermostats = new ArrayList<>();

    public CentralSystem(User u, int lightsAmount, int thermostatsAmount){
        this.user = u;
        for(int i=0; i < lightsAmount; i++){
            lights.add(new Light());
        }
        for(int i=0; i < thermostatsAmount; i++){
            thermostats.add(new Thermostat());
        }
    }

    public void turnOnLight(int deviceNumber){
        if(deviceNumber >= 0 && deviceNumber < lights.size()) {
            if(!lights.get(deviceNumber).getIsRunning()) {
                lights.get(deviceNumber).turnOn();
            }
        }
    }

    public void turnOffLight(int deviceNumber){
        if(deviceNumber >= 0 && deviceNumber < lights.size()){
            if(lights.get(deviceNumber).getIsRunning()){
                lights.get(deviceNumber).disconnectFromWifi();
                lights.get(deviceNumber).turnOff();
            }
        }
    }

    public void connectToWifiLight(int deviceNumber){
        if(deviceNumber >= 0 && deviceNumber < lights.size()){
            if(lights.get(deviceNumber).getIsRunning()){
                if(!lights.get(deviceNumber).getIsConnectedToWifi()){
                    lights.get(deviceNumber).connectToWifi();
                }
            }
        }
    }

    public void disconnectFromWifiLight(int deviceNumber){
        if(deviceNumber >= 0 && deviceNumber < lights.size()){
            if(lights.get(deviceNumber).getIsConnectedToWifi()){
                lights.get(deviceNumber).disconnectFromWifi();
            }
        }
    }

    public void setBrightness(int lightNumber, int brightness){
        lights.get(lightNumber).setBrightness(brightness);
    }


    public List<Integer> getAllLightBrighnessLevels(){
        List<Integer> brightnessLevels= new ArrayList<>();
        for(int i=0; i < lights.size(); i++){
            brightnessLevels.add(lights.get(i).getBrightness());
        }
        return brightnessLevels;
    }

    public boolean getIsLightRunning(int id){ return lights.get(id).getIsRunning();}

    public boolean getIsLightConnected(int id){return lights.get(id).isConnectedToWifi();}



    public int getLigthsSize(){
        return lights.size();
    }

    public void turnOnThermostat(int id){
        if(id >= 0 && id < thermostats.size()){
            thermostats.get(id).turnOn();
        }
    }

    public void turnOffThermostat(int id){
        if(id >= 0 && id < thermostats.size()){
            thermostats.get(id).turnOff();
        }
    }

    public void connectThermostat(int id){
        if(id >= 0 && id < thermostats.size()){
            thermostats.get(id).connectToWifi();
        }
    }

    public void disconnectThermostat(int id){
        if(id >= 0 && id < thermostats.size()){
            thermostats.get(id).disconnectFromWifi();
        }
    }

    public void updateTemperature(int id, int singlePeriodInSec){
        if(id >= 0 && id < thermostats.size()){
           thermostats.get(id).updateTemperature(singlePeriodInSec);
        }
    }

    public void setStep(double step){
        for(int i=0; i < thermostats.size(); i++){
            thermostats.get(i).setStep(step);
        }
    }

    public void setTemperature(int id, double temperature){
        if(id >= 0 && id < thermostats.size()){
            thermostats.get(id).setTemperature(temperature);
        }
    }

    public double getActualTemperature(int id){
        if(id >= 0 && id < thermostats.size()){
           return thermostats.get(id).getActualTemperature();
        }
        return 0.0;
    }

    public double getSettedTemperature(int id){
        if(id >= 0 && id < thermostats.size()){
            return thermostats.get(id).getSettedTemperature();
        }
        return 0.0;
    }

    public double getStep(int id){
        if(id >= 0 && id < thermostats.size()){
            return thermostats.get(id).getStep();
        }
        return 0.0;
    }

    public boolean isRunningThermostat(int id){
        if(id >= 0 && id < thermostats.size()){
            return thermostats.get(id).isRunning();
        }
        return false;
    }

    public boolean isConnectedThermostat(int id){
        if(id >= 0 && id < thermostats.size()){
            return thermostats.get(id).isConnectedToWifi();
        }
        return false;
    }

    public List<Double> getAllActualTemperature(){
        List<Double> arr = new ArrayList<>();
        for(int i=0; i < thermostats.size(); i++){
            arr.add(thermostats.get(i).getActualTemperature());
        }
        return arr;
    }

    public int getThermostatsSize(){
        return thermostats.size();
    }

    public void login(String name, String pass){
        this.user.login(name, pass);
    }

    public void logout(){
        this.user.logout();
    }

    public boolean getLoginStatus(){
        return this.user.getLogged();
    }

    public void register(byte type, String name, String pass){
        this.user.register(type, name, pass);
    }

}
