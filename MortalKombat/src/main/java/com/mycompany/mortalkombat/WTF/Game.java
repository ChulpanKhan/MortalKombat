
package com.mycompany.mortalkombat.WTF;

import com.mycompany.mortalkombat.WTF.Players.Player;
import com.mycompany.mortalkombat.WTF.Players.Human;
import java.io.IOException;
import java.util.Comparator;
import java.util.List;

/**
 *
 * @author Мария
 */
//public class Game {
//
//    private final CharacterAction action = new CharacterAction();
//    private final LocationManager locationManager = new LocationManager(action);
//    private final Fight fight = new Fight(locationManager, action);
//    
//    private final List<Result> results = ResultManager.getResults();
//    
//    
//    public Player createNewEnemy() {
//        action.initializeEnemies();
//        locationManager.startNextLocation();
//        Player currentEnemy = locationManager.getNextOpponent();
//        return currentEnemy;
//    }
//    
//    public Human createNewHuman() {
//        Human human = new Human(0, 80, 16, 1); //начальные параметры
//        locationManager.reset();
//        locationManager.setPlayer(human);
//        return human;
//    }
//
//    public boolean isLocationComplete() {
//        return locationManager.isLocationComplete();
//    }
//
//    public void startNextLocation() {
//        locationManager.startNextLocation();
//    }
//
//    public void finishGameAndUpdateTop(Human human, String nameInput){
//        results.add(new Result(nameInput, human.getPoints()));
//        results.sort(Comparator.comparing(Result::getPoints).reversed());
//        ResultManager.writeResultsToExcel();
//    }
//
// 
//    public List<Result> getResults() {
//        return results;
//    }
//
//    public Fight getFight() {
//        return fight;
//    }
//
//    public CharacterAction getAction() {
//        return action;
//    }
//    
//    public void setTotalLocations(int count) {
//        locationManager.setTotalLocations(count);
//    }
//    
//    public int getCurrentLocation() {
//        return locationManager.getCurrentLocation();
//    }
//    
//    public LocationManager getLocation() {
//        return locationManager;
//    }
//    
//    public boolean isOver() {
//        return locationManager.isGameOver();
//    }
//    
//}
