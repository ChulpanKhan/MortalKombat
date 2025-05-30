/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.mortalkombat.WTF.Players;

/**
 * Представляет игрока с дополнительными полями: очки, опыт, победы и т. д.
 */
public class Human extends Player{

    private int points = 0;
    private int experience;
    private int win;
    private int nextexperience;
    private boolean leveledUp = false;
    private boolean inTop10 = false; 
    
    public Human(int level, int health, int  damage, int attack){
        super (level, health, damage, attack);
        this.points=0;
        this.experience=0;
        this.nextexperience=40;
        this.win=0;
    }
    

    public int getPoints(){
        return this.points;
    }
    public int getExperience(){
        return this.experience;
    }
    public int getNextExperience(){
        return this.nextexperience;
    }
    public int getWin(){
        return this.win;
    }

    public void setPoints(int p){
        this.points+=p;
    }
    public void setExperience(int e){
        this.experience+=e;
    }
    public void setNextExperience(int e){
        this.nextexperience=e;
    }
    public void setWin(){
        this.win++;
    }
    
    @Override
    public String getName(){
        return "You";
    }

    public void markLevelUp() {
        this.leveledUp = true;
    }

    public boolean justLeveledUp() {
        boolean was = leveledUp;
        leveledUp = false;
        return was;
    }
    
    public void setInTop10(boolean inTop10){
        this.inTop10 = inTop10;
    }
    
    public boolean inTop10(){
        return inTop10;
    }

}
