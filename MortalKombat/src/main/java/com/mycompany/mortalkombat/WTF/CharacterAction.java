/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.mortalkombat.WTF;

import com.mycompany.mortalkombat.WTF.Players.EnemyFactory;
import com.mycompany.mortalkombat.WTF.Players.Player;
import com.mycompany.mortalkombat.WTF.Players.Human;

/**
 * Класс, отвечающий за действия персонажей в игре:
 * выбор врагов, поведение, развитие персонажа, применение предметов.
 */
public class CharacterAction {

    private final int[] EXPERIENCE_LEVELS = {40, 90, 180, 260, 410, 1000};

    private static final int[][] ATTACK_PATTERNS = {
        {1, 0},
        {1, 1, 0},
        {0, 1, 0},
        {1, 1, 1, 1}
    };
    private final EnemyFactory enemyFactory = new EnemyFactory();
    private final Player[] enemies = new Player[5];
    private Player currentEnemy;
 
    /**
     * Инициализирует массив врагов с предустановленными персонажами.
     */
    public void initializeEnemies() {
        enemies[0] = enemyFactory.createEnemy("Baraka");
        enemies[1] = enemyFactory.createEnemy("SubZero");
        enemies[2] = enemyFactory.createEnemy("LiuKang");
        enemies[3] = enemyFactory.createEnemy("SonyaBlade");
        enemies[4] = enemyFactory.createEnemy("ShaoKahn");
    }

    /**
     * Возвращает массив всех доступных врагов.
     *
     * @return массив игроков-врагов
     */   
    public Player[] getEnemies() {
        return this.enemies;
    }
    
    /**
     * Случайным образом выбирает обычного врага из списка.
     *
     * @return случайный враг
     */
    public Player chooseEnemy() {
        int index = (int) (Math.random() * 4);
        currentEnemy = enemies[index];
        return currentEnemy;
    }

    
    /**
     * Выбирает босса (последнего врага в массиве).
     *
     * @return босс
     */
    public Player chooseBoss() {
        currentEnemy = enemies[4];
        return currentEnemy;
    }

    
    /**
     * Определяет поведение врага на основе его типа и случайного значения.
     *
     * @param enemy текущий враг
     * @return массив действий
     */
    public int[] chooseBehavior(Player enemy) {
        double r = Math.random();
        return switch (enemy.getName()) {
            case "Baraka" -> selectEnemyBehavior(30, 60, 10, 0, r); // танк
            case "Sub-Zero" -> selectEnemyBehavior(50, 0, 0, 50, r); // маг
            case "Liu Kang" -> selectEnemyBehavior(25, 10, 65, 0, r); // боец
            case "Sonya Blade" -> selectEnemyBehavior(50, 50, 0, 0, r); // солдат
            case "Shao Kahn" -> selectEnemyBehavior(10, 45, 0, 45, r); // босс
            default -> ATTACK_PATTERNS[0];
        };
    }

    
    /**
     * Выбирает паттерн поведения врага по заданным шансам.
     *
     * @param chancePattern1 шанс первого поведения
     * @param chancePattern2 шанс второго поведения
     * @param chancePattern3 шанс третьего поведения
     * @param chancePattern4 шанс четвертого поведения
     * @param randomValue случайное число от 0 до 1
     * @return выбранный паттерн поведения
     */
    public int[] selectEnemyBehavior(
            int chancePattern1,
            int chancePattern2,
            int chancePattern3,
            int chancePattern4,
            double randomValue
    ) {
        double threshold1 = chancePattern1 * 0.01;
        double threshold2 = (chancePattern1 + chancePattern2) * 0.01;
        double threshold3 = (chancePattern1 + chancePattern2 + chancePattern3) * 0.01;

        if (randomValue < threshold1) {
            return ATTACK_PATTERNS[0]; // 1-й тип поведения
        } else if (randomValue >= threshold1 && randomValue < threshold2) {
            return ATTACK_PATTERNS[1]; // 2-й тип поведения
        } else if (randomValue >= threshold2 && randomValue < threshold3) {
            return ATTACK_PATTERNS[2]; // 3-й тип поведения
        } else {
            return ATTACK_PATTERNS[3]; // 4-й тип поведения
        }
    }
    
    /**
     * Применяет опыт и бонусные очки к игроку после победы над врагом.
     *
     * @param human игрок
     * @param isBoss был ли враг боссом
     */
    public void applyExperience(Human human, boolean isBoss) {
        int level = human.getLevel();
        int gainedXP = isBoss 
        ? (level == 2 ? 30 : 50) 
        : switch (level) {
            case 0 -> 20;
            case 1 -> 25;
            case 2 -> 30;
            case 3 -> 40;
            case 4 -> 50;
            default -> 0;
        };

        int gainedPoints = isBoss
                ? (level == 2 ? 45 + human.getHealth() / 2 : 65 + human.getHealth() / 2)
                : switch (level) {
                    case 0 -> 25 + human.getHealth() / 4;
                    case 1 -> 30 + human.getHealth() / 4;
                    case 2 -> 35 + human.getHealth() / 4;
                    case 3 -> 45 + human.getHealth() / 4;
                    case 4 -> 55 + human.getHealth() / 4;
                    default -> 0;
                };

        human.setExperience(gainedXP);
        human.setPoints(gainedPoints);

        checkLevelUp(human);
    }

    /**
     * Проверяет, достиг ли игрок нужного количества опыта для повышения уровня.
     *
     * @param human игрок
     */
    private void checkLevelUp(Human human) {
        int currentLevel = human.getLevel();
        int currentXP = human.getExperience();

        if (currentXP >= EXPERIENCE_LEVELS[currentLevel]) {
            human.levelUp();
            human.markLevelUp();
            human.setNextExperience(EXPERIENCE_LEVELS[currentLevel + 1]);
            //scaleHuman(human);
            for (int i = 0; i <= 4; i++) {
                scaleEnemy(enemies[i], human.getLevel());
            }
        }   
    }
    
    /**
     * Увеличивает параметры игрока при повышении уровня.
     *
     * @param human игрок
     * @param isIncreaseHealth true — увеличить здоровье, false — увеличить урон
     */
    public void scaleHuman(Human human, boolean isIncreaseHealth) {
        int level = human.getLevel()+1;     

        if (isIncreaseHealth) {
            int hp = switch (level) {
                case 1 -> 25;
                case 2 -> 30;
                case 3 -> 40;
                case 4 -> 50;
                default -> 0;
            };
            human.setMaxHealth(hp);
        } else {
            int dmg = switch (level) {
                case 1, 2 -> 3;
                case 3 -> 4;
                case 4 -> 6;
                default -> 0;
            };                
            human.setDamage(dmg);
        }        
    }

    /**
     * Увеличивает параметры врага в зависимости от уровня игрока.
     *
     * @param enemy враг
     * @param playerLevel уровень игрока
     */
    private void scaleEnemy(Player enemy, int playerLevel) {
        int hpScale = switch (playerLevel) {
            case 1 -> 32;
            case 2 -> 30;
            case 3 -> 23;
            case 4 -> 25;
            default -> 100;
        };
        int dmgScale = switch (playerLevel) {
            case 1 -> 25;
            case 2 -> 20;
            case 3 -> 24;
            case 4 -> 26;
            default -> 100;
        };

        enemy.setMaxHealth((int)enemy.getMaxHealth() * hpScale / 100);
        enemy.setDamage((int)enemy.getDamage() * dmgScale / 100);
        enemy.levelUp();
    }
    
    /**
     * Добавляет случайный предмет игроку по заданным шансам.
     *
     * @param smallChance шанс малого зелья
     * @param bigChance шанс большого зелья
     * @param crossChance шанс креста
     * @param items массив предметов
     */
    public void addItems(int smallChance, int bigChance, int crossChance, Items[] items) {
        double roll = Math.random();
        if (roll < smallChance * 0.01) {
            items[0].setCount(1); // Малое зелье
        } else if (roll < (smallChance + bigChance) * 0.01) {
            items[1].setCount(1); // Большое зелье
        } else if (roll < (smallChance + bigChance + crossChance) * 0.01) {
            items[2].setCount(1); // Крест
        }
    }
    
    /**
     * Использует выбранный предмет на игроке.
     *
     * @param human игрок
     * @param items массив предметов
     * @param index индекс предмета
     */
    public void useItem(Player human, Items[] items, int index) {
        int heal = switch (index) {
            case 0 -> (int) (human.getMaxHealth() * 0.25);
            case 1 -> (int) (human.getMaxHealth() * 0.50);
            default -> 0;
        };

        if (heal > 0) {
            human.setHealth(heal);
            items[index].setCount(-1);
        } 
    }
    
}
