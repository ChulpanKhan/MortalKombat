/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.mortalkombat.WTF;

/**
 * Хранилище игровых предметов.
 */
public class ItemsStorage {
    private static final Items[] items = {
        new Items("Малое зелье лечение", 0),
        new Items("Большое зелье лечение", 0),
        new Items("Крест возрождения", 0)
    };

    public static Items[] getItems() {
        return items;
    }

    public static Items getItem(int index) {
        return items[index];
    }
}
