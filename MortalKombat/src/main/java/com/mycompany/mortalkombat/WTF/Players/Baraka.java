/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.mortalkombat.WTF.Players;

/**
 * Представляет конкретного персонажа (Baraka) с уникальными характеристиками.
 */
public class Baraka extends Player{
    
    private static final int LEVEL = 1;
    private static final int HEALTH = 100;
    private static final int DAMAGE = 12;
    private static final int ATTACK = 1;
    
    public Baraka(){
        super (LEVEL, HEALTH, DAMAGE, ATTACK);
    }
    
    @Override
    public String getName(){
        return "Baraka";
    }
    
}
