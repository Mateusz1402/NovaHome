package com.novahome.controllers;


import com.novahome.functionalities.hub.CentralSystem;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.ArrayList;

@RestController
@RequestMapping("/api/v1/thermostats")
@CrossOrigin(origins = "http://localhost:5173")
public class ThermostatController {
    private final CentralSystem centralSystem;

    public ThermostatController(CentralSystem centralSystem){ this.centralSystem = centralSystem;}

    @GetMapping
    public ResponseEntity<List<Double>> getAllActualTemperature(){
        return ResponseEntity.ok(centralSystem.getAllActualTemperature());
    }

    @PostMapping("/{id}/turn-on")
    public ResponseEntity<Void> turnOn(@PathVariable int id){
        centralSystem.turnOnThermostat(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{id}/turn-off")
    public ResponseEntity<Void> turnOff(@PathVariable int id){
        centralSystem.turnOffThermostat(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}/is-running")
    public ResponseEntity<Boolean> isRunning(@PathVariable int id){
        return ResponseEntity.ok(centralSystem.isRunningThermostat(id));
    }

    @PatchMapping("/{id}/connect")
    public ResponseEntity<Void> connect(@PathVariable int id){
        centralSystem.connectThermostat(id);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{id}/disconnect")
    public ResponseEntity<Void> disconnect(@PathVariable int id){
        centralSystem.disconnectThermostat(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}/is-connect")
    public ResponseEntity<Boolean> isConnect(@PathVariable int id){
        return ResponseEntity.ok(centralSystem.isConnectedThermostat(id));
    }

    @PatchMapping("/{id}/set-temp")
    public ResponseEntity<Void> setTemperature(@PathVariable int id, @RequestBody double temperature){
        centralSystem.setTemperature(id, temperature);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}/get-temp")
    public ResponseEntity<Double> getActualTemperature(@PathVariable int id){
        return ResponseEntity.ok(centralSystem.getActualTemperature(id));
    }

    @GetMapping("/{id}/get-setted-temp")
    public ResponseEntity<Double> getSettedTemperature(@PathVariable int id){
        return ResponseEntity.ok(centralSystem.getSettedTemperature(id));
    }


}
