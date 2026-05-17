package com.novahome.functionalities.devices;

import com.novahome.functionalities.core.SmartDevice;
import lombok.Getter;

import java.util.Random;


public class Thermostat extends SmartDevice {
    @Getter
    private double settedTemperature;
    @Getter
    private double actualTemperature;
    @Getter
    private double step;    //single step of increase/decrease temp per cycle
    private long timerStart;
    Random random = new Random();

    public Thermostat(){
        super();
        this.settedTemperature = 0.0;
        this.actualTemperature = random.nextDouble(40) - 10.0;
        this.step = 0.0;
        this.timerStart = 0;

    }

    public void setTemperature(double temperature){
        if(this.isRunning() && this.isConnectedToWifi()){
            if(temperature >= -10.0 && temperature <= 30.0){
                this.settedTemperature = temperature;
            }
        }
    }

    public void setStep(double stepValue){
        if(stepValue <= 2.0 && stepValue >= 0.01) {
            this.step = stepValue;
        }
    }

    private boolean timer(long seconds){
        long currentTime = System.currentTimeMillis();
        long stopTime = seconds * 1000;
        if(this.timerStart == 0){
            this.timerStart = currentTime;
        }else if(stopTime <= (currentTime - this.timerStart)){
            this.timerStart = 0;
            return true;
        }
        return false;
    }

    public void updateTemperature(int singlePeriodInSec){
        while(this.actualTemperature != this.settedTemperature){
            boolean timer = timer(singlePeriodInSec);

            if(this.actualTemperature > this.settedTemperature){
                if(timer){
                    this.actualTemperature -= this.step;
                }
            }else if(this.actualTemperature < this.settedTemperature){
                if(timer){
                    this.actualTemperature += this.step;
                }
            }
        }
    }
}
