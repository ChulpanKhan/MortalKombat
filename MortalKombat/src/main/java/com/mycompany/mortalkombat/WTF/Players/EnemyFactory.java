/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.mortalkombat.WTF.Players;

/**
 * Фабричный класс, предназначенный для создания экземпляров врагов (персонажей) по их имени.
 * Использует {@link Player} как базовый тип для всех персонажей.
 */

public class EnemyFactory {

    public Player createEnemy(String characterName) {
        return switch (characterName) {
            case "Baraka" -> new Baraka();
            case "SubZero" -> new SubZero();
            case "LiuKang" -> new LiuKang();
            case "SonyaBlade" -> new SonyaBlade();
            case "ShaoKahn" -> new ShaoKahn();
            default -> throw new IllegalArgumentException("Неизвестный персонаж: " + characterName);
        };
    }
}
