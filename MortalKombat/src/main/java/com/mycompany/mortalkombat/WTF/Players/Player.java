/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.mortalkombat.WTF.Players;

/**
 * Абстрактный класс, представляющий базового персонажа в игре.
 */
public abstract class Player {
    
    private int level;
    private int health;
    private int maxhealth;
    private int damage;
    private int attack;
    private int weakenedTurns = 0;
    private int baseMaxHealth; //базовое здоровье
    private int baseDamage;    //базовый урон

    public Player(int level, int health, int damage, int attack) {
        this.level = level;
        this.health = health;
        this.damage = damage;
        this.attack = attack;
        this.maxhealth = health;
        this.baseMaxHealth = health;
        this.baseDamage = damage;
    }

    public void levelUp() {
        this.level++;
    }
    public void setHealth(int h){
        this.health+=h;
    }
    public void setNewHealth(int h){
        this.health=h;
    }
    public void setDamage(int d){
        this.damage+=d;
    }
    public void setAttack(int a){
        this.attack=a;
    }
    public void setMaxHealth(int h){
        this.maxhealth+=h;
    }
    
    public int getLevel(){
        return this.level;
    }
    public int getHealth(){
        return this.health;
    }
    public int getDamage(){
        return this.damage;
    }
    public int getAttack(){
        return this.attack;
    }
    public int getMaxHealth() {
        return this.maxhealth;
    }
    
    public int getBaseMaxHealth() {
        return baseMaxHealth;
    }
    public int getBaseDamage() {
        return baseDamage;
    }

    public void setWeakened(int turns) {
        this.weakenedTurns = turns;
    }

    public boolean isWeakened() {
        return weakenedTurns > 0;
    }

    public void decrementWeakened() {
        if (weakenedTurns > 0) {
            weakenedTurns--;
        }
    }

    public abstract String getName();
    
}
