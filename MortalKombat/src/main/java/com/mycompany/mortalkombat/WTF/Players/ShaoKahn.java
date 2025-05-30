/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.mortalkombat.WTF.Players;

/**
 * Представляет конкретного персонажа (Shao Kahn) с уникальными характеристиками.
 */
public class ShaoKahn extends Player{

    private static final int LEVEL = 3;
    private static final int HEALTH = 100;
    private static final int DAMAGE = 30;
    private static final int ATTACK = 1;
    
    private boolean isRegenerating = false;
    private boolean hasRegenerated = false;
    private int totalDamageReceived = 0;

    public ShaoKahn() {
        super(LEVEL, HEALTH, DAMAGE, ATTACK);
    }

    @Override
    public String getName(){
        return "Shao Kahn";
    }
    
    public boolean isRegenerating() {
        return isRegenerating;
    }
    
    public boolean hasRegenerated() {
        return hasRegenerated;
    }

    public void setRegenerating(boolean regen) {
        hasRegenerated = true;
        this.isRegenerating = regen;
    }

    public void addDamageReceived(int dmg) {
        this.totalDamageReceived += dmg;
    }

    public int getTotalDamageReceived() {
        return totalDamageReceived;
    }

    public void resetTotalDamageReceived() {
        this.totalDamageReceived = 0;
    }

}
