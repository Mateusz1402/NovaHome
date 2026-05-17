package com.novahome.controllers;


import com.novahome.functionalities.hub.CentralSystem;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.novahome.dto.BrightnessRequest;
import java.util.List;
import java.util.ArrayList;


@RestController
@RequestMapping("/api/v1/lights")
public class LightController {
    //This is a REST API for Light class.

    private final CentralSystem centralSystem;

    public LightController(CentralSystem centralSystem){
        this.centralSystem = centralSystem;
    }

    @GetMapping
    public ResponseEntity<List<Integer>> getallLightStates(){
        return ResponseEntity.ok(centralSystem.getAllLightBrighnessLevels());
    }

    @GetMapping("/{id}/is-running")
    public ResponseEntity<Boolean> isRunning(
            @PathVariable int id){
        return ResponseEntity.ok(centralSystem.getIsLightRunning(id));
    }

    @GetMapping("/{id}/is-connected")
    public ResponseEntity<Boolean> isConnected(
            @PathVariable int id){
        return ResponseEntity.ok(centralSystem.getIsLightConnected(id));
    }


    @PatchMapping("/{id}/brightness")
    public ResponseEntity<String> setBrightness(
            @PathVariable int id,
            @RequestBody BrightnessRequest request) {
        if(id < 0 || id >= centralSystem.getLigthsSize()){
            return ResponseEntity.badRequest().body("Light ID not found.");
        }else{
            centralSystem.setBrightness(id, request.getLevel());
            return ResponseEntity.ok("Light " + id + " has been adjusted to " + request.getLevel() + " %.");
        }
    }

    @PostMapping("/{id}/turn-on")
    public ResponseEntity<Void> turnOn(@PathVariable int id){
        centralSystem.turnOnLight(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{id}/turn-off")
    public ResponseEntity<Void> turnOff(@PathVariable int id){
        centralSystem.turnOffLight(id);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping ("/{id}/connect")
    public ResponseEntity<String> connectToWifi(
            @PathVariable int id){
        if(id < 0 || id >= centralSystem.getLigthsSize()){
            return ResponseEntity.badRequest().body("Id not found.");
        }else{
            centralSystem.connectToWifiLight(id);
            return ResponseEntity.ok("Light " + id + " has been connected to Wifi");
        }
    }

    @PatchMapping("/{id}/disconnect")
    public ResponseEntity<String> disconnectFromWifi(
            @PathVariable int id){
        if(id < 0 || id >= centralSystem.getLigthsSize()){
            return ResponseEntity.badRequest().body("Id not found");
        }else{
            centralSystem.disconnectFromWifiLight(id);
            return ResponseEntity.ok("Light " + id + " has been disconnected from Wifi");
        }
    }


}
