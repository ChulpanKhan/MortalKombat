/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.mortalkombat.WTF.Players;

/**
 * Представляет конкретного персонажа (Liu Kang) с уникальными характеристиками.
 */
public class LiuKang extends Player{
    
    private static final int LEVEL = 1;
    private static final int HEALTH = 70;
    private static final int DAMAGE = 20;
    private static final int ATTACK = 1;

    public LiuKang() {
        super(LEVEL, HEALTH, DAMAGE, ATTACK);
    }
    
    @Override
    public String getName(){
        return "Liu Kang";
    }
}
