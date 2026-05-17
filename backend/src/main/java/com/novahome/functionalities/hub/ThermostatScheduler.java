package com.novahome.functionalities.hub;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import jakarta.annotation.PostConstruct;

@Component
public class ThermostatScheduler {
    private final CentralSystem centralSystem;

    public ThermostatScheduler(CentralSystem centralSystem){
        this.centralSystem = centralSystem;
    }

    @PostConstruct
    public void initiate(){
        centralSystem.setStep(0.1);
    }

    @Scheduled(fixedRate = 100)
    public void runTemperatureUpdateLoop(){
        int targetThermostatInterval = 5;

        for(int i=0; i < centralSystem.getThermostatsSize(); i++){
            if(centralSystem.isRunningThermostat(i)){
                centralSystem.updateTemperature(i, targetThermostatInterval);
            }
        }
    }


}
