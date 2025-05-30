/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.mortalkombat.WTF.Players;

/**
 * Представляет конкретного персонажа (Sub-Zero) с уникальными характеристиками.
 */
public class SubZero extends Player{
    
    private static final int LEVEL = 1;
    private static final int HEALTH = 60;
    private static final int DAMAGE = 16;
    private static final int ATTACK = 1;

    public SubZero() {
        super(LEVEL, HEALTH, DAMAGE, ATTACK);
    }
    
    @Override
    public String getName(){
        return "Sub-Zero";
    }

}
