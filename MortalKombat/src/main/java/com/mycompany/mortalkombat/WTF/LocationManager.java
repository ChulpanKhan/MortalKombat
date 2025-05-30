/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.mortalkombat.WTF;

import com.mycompany.mortalkombat.WTF.Players.Human;
import com.mycompany.mortalkombat.WTF.Players.Player;
import java.util.Random;

/**
 * Управляет локациями и противниками в них.
 */
public class LocationManager {

    private int totalLocations;
    private int currentLocation = 1;
    private int enemiesLeftInCurrentLocation = 0;
    private boolean bossFoughtInCurrentLocation = false;
    private final Random random = new Random();
    private final CharacterAction action;
    private Human player;

    public LocationManager(CharacterAction action) {
        this.action = action;
    }
    
     public void setTotalLocations(int count) {
        this.totalLocations = count;
    }

    public int getCurrentLocation() {
        return currentLocation;
    }

    public int getEnemiesLeftInCurrentLocation() {
        return enemiesLeftInCurrentLocation;
    }
    
    public boolean isGameOver() {
        return currentLocation >= totalLocations && isLocationComplete();
    }

    public void startNextLocation() {
        currentLocation++;
        enemiesLeftInCurrentLocation = 2 + player.getLevel() + random.nextInt(2 + player.getLevel()); // от 1 до (2 + уровень) врагов
        bossFoughtInCurrentLocation = false;
    }

    public boolean hasEnemies() {
        return enemiesLeftInCurrentLocation > 0;
    }

    public boolean isBossFought() {
        return bossFoughtInCurrentLocation;
    }

    public boolean isLocationComplete() {
        return !hasEnemies() && bossFoughtInCurrentLocation;
    }

    public Player getNextOpponent() {

        enemiesLeftInCurrentLocation--;
        if (hasEnemies()) {
            return action.chooseEnemy();
        }

        if (!bossFoughtInCurrentLocation) {
            bossFoughtInCurrentLocation = true;
            return action.chooseBoss();
        }

        return null; //локация пройдена
    }
    
    public void setPlayer(Human player) {
        this.player = player;
    }
    
    public void reset() {
        currentLocation = 0;
        enemiesLeftInCurrentLocation = 0;
        bossFoughtInCurrentLocation = false;
    }
}
