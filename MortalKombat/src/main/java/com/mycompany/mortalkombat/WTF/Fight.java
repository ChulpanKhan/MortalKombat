/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.mortalkombat.WTF;

import com.mycompany.mortalkombat.WTF.Players.*;
import java.util.Comparator;
import java.util.List;

/**
 * Класс, реализующий логику боя между игроком и врагом.
 */
public class Fight {

    private final CharacterAction action = new CharacterAction();
    private final LocationManager locationManager = new LocationManager(action);

    private int[] currentPattern = {0};
    private int patternIndex = -1;
    private int roundCounter = 1;
    private int stunned = 0;
    private final Items[] items = ItemsStorage.getItems();
    private final List<Result> results = ResultManager.getResults();
    
    private String actionStatus;
    private String stunStatus;
    private String debufStatus;
    private String fightResult; 
    
    
    /**
     * Создаёт нового врага и начинает новую локацию.
     *
     * @return новый враг
     */
    public Player createNewEnemy() {
        action.initializeEnemies();
        locationManager.startNextLocation();
        Player currentEnemy = locationManager.getNextOpponent();
        return currentEnemy;
    }
    
    /**
     * Создаёт нового игрока с начальными параметрами.
     *
     * @return новый игрок
     */
    public Human createNewHuman() {
        Human human = new Human(0, 80, 16, 1); //начальные параметры
        locationManager.reset();
        locationManager.setPlayer(human);
        return human;
    }
    
    
    /**
     * Обрабатывает ход одного из участников боя.
     *
     * @param attacker атакующий
     * @param defender защищающийся
     */
    public void processTurn(Player attacker, Player defender) {
        if (stunned == 1) {
            attacker.setAttack(-1); // атакующий оглушён
        }

        //attacker.decrementWeakened();
        defender.decrementWeakened();

        int attackerAction = attacker.getAttack();
        int defenderAction = defender.getAttack();

        boolean attackerAttacks = (attackerAction == 1);
        boolean defenderAttacks = (defenderAction == 1);
        boolean attackerDefends = (attackerAction == 0);
        boolean defenderDefends = (defenderAction == 0);
        boolean attackerStunned = (attackerAction == -1);
        boolean attackerWeakens = (attackerAction == 2);

        double random = Math.random();

        // 1. Оба защищаются
        if (attackerDefends && defenderDefends) {
            if (random <= 0.5) {
                stunned = 1;
            }
            actionStatus = "Both defended themselves";
            return;
        }

        // 2. Оглушение
        if (attackerStunned) {
            stunStatus = attacker.getName() + " was stunned";
            if (defenderAttacks) {
                int damage = defender.getDamage();
                if (attacker.isWeakened()) damage *= 1.25;
                attacker.setHealth(-damage);
                actionStatus = defender.getName() + " attacked";
            } else {
                actionStatus = defender.getName() + " didn't attack";
            }
            stunned = 0;
            return;
        } else  stunStatus = "";

        // 3. Ослабление
        if (attackerWeakens) {
            if (defenderDefends) {
                if (Math.random() < 0.75) {
                    int weakenTurns = (attacker.getLevel() == 0) ? 2 : attacker.getLevel()+1;
                    defender.setWeakened(weakenTurns);
                    debufStatus = defender.getName() + " was weakened for " + weakenTurns + " turns!";
                    actionStatus = defender.getName() + " was weakened";
                } else {
                    debufStatus = "Resisted weakening!";
                    actionStatus = debufStatus;
                }
            } else if (defenderAttacks) {
                int bonus = (int) (attacker.getDamage() * 0.15);
                defender.setHealth(-bonus);
                actionStatus = attacker.getName() + " failed to weaken and counterattacked!";
                debufStatus = "Oops, weakening failed";
            } else {
                actionStatus = attacker.getName() + " tried to weaken, but nothing happened";
            }
            return;
        } else debufStatus = "";

        // 4. Атака против защиты
        if (attackerAttacks && defenderDefends) {
            if (attacker instanceof ShaoKahn && random < 0.15) {
                int damage = (int) (attacker.getDamage() * 0.5);
                if (defender.isWeakened()) {
                    damage *= 1.25;
                }
                defender.setHealth(-damage);
                actionStatus = "Your block is broken";
            } else {
                int damage = (int) (defender.getDamage() * 0.5);
                if (attacker.isWeakened()) {
                    damage *= 1.25;
                }
                attacker.setHealth(-damage);
                actionStatus = defender.getName() + " counterattacked";
            }
            return;
        }

        // 5. Оба атакуют
        if (attackerAttacks && defenderAttacks) {
            int damage = attacker.getDamage();
            if (defender.isWeakened()) {
                damage *= 1.25;
            }
            if (attacker.isWeakened()) {
                damage *= 0.5;
            }
            defender.setHealth(-damage);
            actionStatus = attacker.getName() + " attacked";
            return;
        }

        // 6. Защита против атаки
        if (attackerDefends && defenderAttacks) {
            actionStatus = attacker.getName() + " didn't attack";
            return;
        }

        actionStatus = "Action not recognized";
    }

    /**
     * Совершает действие игрока (атака, защита и т. д.) и обрабатывает ответ
     * врага.
     *
     * @param human игрок
     * @param enemy враг
     * @param attackChoice выбор действия
     */
    public void hit(Player human, Player enemy, int attackChoice) {

        human.setAttack(attackChoice);
        //Регенерация босса
        if (handleShaoKahnRegeneration(human, enemy, attackChoice)) {
            return;
        }
        //Загрузка поведения противника
        if (patternIndex < currentPattern.length - 1) {
            patternIndex++;
        } else {
            currentPattern = action.chooseBehavior(enemy);
            patternIndex = 0;
        }

        enemy.setAttack(currentPattern[patternIndex]);
        
        //Ход игры: поочередно игрок и противник
        if (roundCounter % 2 == 1) {           
            processTurn(human, enemy);
        } else {
            processTurn(enemy, human);
        }

        roundCounter++;
        
        // Воскрешение
        if (human.getHealth() <= 0 && items[2].getCount() > 0) {
            human.setNewHealth((int) (human.getMaxHealth() * 0.05));
            items[2].setCount(-1);
            actionStatus = "Вы воскресли";
        }

        //Проверка на завершение раунда
        if (human.getHealth() <= 0 || enemy.getHealth() <= 0) {
            actionStatus = "";
            stunStatus = "";
            debufStatus = "";
            if (locationManager.isGameOver()) {
                endFinalRound((Human) human);
            } else {
                endRound(human, enemy);
            }
        }
    }

    
    /**
     * Обрабатывает механику регенерации Shao Kahn.
     *
     * @param human Игрок
     * @param enemy Враг 
     * @param attackChoice Действие игрока (1 - атака, 0 - защита, другое -
     * событие или пропуск)
     * @return true если обработка завершена (нужно выйти из hit), false если
     * продолжаем обычную логику боя.
     */
    private boolean handleShaoKahnRegeneration(Player human, Player enemy, int attackChoice) {
        if (enemy instanceof ShaoKahn) {
            ShaoKahn boss = (ShaoKahn) enemy;

            // Если сейчас активна регенерация
            if (boss.isRegenerating()) {
                if (attackChoice == 0) { //Защита
                    int regen = human.getDamage() / 2;
                    boss.setNewHealth(Math.min(boss.getHealth() + regen, boss.getMaxHealth()));
                    actionStatus = "Босс восстановил " + regen + " HP!";
                } else if (attackChoice == 1) {// Атака
                    int doubleDmg = human.getDamage() * 2;
                    boss.setHealth(- doubleDmg);
                    actionStatus = "You interrupted the regeneration!";
                    debufStatus = "The boss took " + doubleDmg + " damage!";
                }
                boss.setRegenerating(false);
                return true;
            }

            // Иначе: шанс начать регенерацию
            if ((Math.random() < 0.15  || boss.getHealth() <= human.getDamage())&& !boss.hasRegenerated()) {
                boss.setRegenerating(true);
                actionStatus = "The boss is trying to regenerate!";
                return true;
            }
        }
        return false;
    }

    /**
     * Завершает раунд боя, начисляет награды или фиксирует поражение.
     *
     * @param human игрок
     * @param enemy враг
     */
    public void endRound(Player human, Player enemy) {

        if (human.getHealth() > 0) {
            fightResult = "You win";
            ((Human) human).setWin();

            if (enemy instanceof ShaoKahn) {
                action.addItems(38, 23, 8, items);
                action.applyExperience((Human) human,  true);
            } else {
                action.addItems(25, 15, 5, items);
                action.applyExperience((Human) human,  false);
            }

        } else {
            fightResult = enemy.getName() + " wins";
        }

        //сброс боя
        roundCounter = 1;
        patternIndex = -1;
        currentPattern = new int[]{0};
    }
    /**
     * Завершает финальный раунд игры.
     *
     * @param human игрок
     */
    public void endFinalRound(Human human) {
        if (human.getHealth() > 0) {
            fightResult = "You win";
            human.setWin();
            action.applyExperience(human, true);
        } else {
            fightResult = "Shao Kahn wins";
        }
        boolean isTop = isTop10(human);
        human.setInTop10(isTop);
    }

    /**
     * Проверяет, попал ли игрок в ТОП-10.
     *
     * @param player игрок
     * @return true если входит в ТОП-10
     */
    private boolean isTop10(Human player) {
        long betterScores = results.stream().filter(r -> player.getPoints() < r.getPoints()).count();
        return betterScores < 10;
    }
    
    /**
     * Сохраняет результат игрока и обновляет таблицу рекордов.
     *
     * @param human игрок
     * @param nameInput имя игрока
     */
    public void finishGameAndUpdateTop(Human human, String nameInput){
        results.add(new Result(nameInput, human.getPoints()));
        results.sort(Comparator.comparing(Result::getPoints).reversed());
        ResultManager.writeResultsToExcel();
    }
    
    /**
     * Начинает новый раунд, восстанавливает здоровье и готовит следующего
     * врага.
     *
     * @param human игрок
     * @return новый враг
     */
    public Player newRound(Player human) {
        if (locationManager.isLocationComplete()) {
            locationManager.startNextLocation();
        }
        Player newEnemy = locationManager.getNextOpponent();
        //настройка интерфейса
        human.setNewHealth(human.getMaxHealth());
        newEnemy.setNewHealth(newEnemy.getMaxHealth());
        return newEnemy;
    }

    public int getRoundCounter() {
        return roundCounter;
    }
    
    public String getActionStatus() {
        return this.actionStatus;
    }

    public String getStunStatus() {
        return this.stunStatus;
    }
    
    public String getDebufStatus() {
        return this.debufStatus;
    }
    
    public String getFightResult() {
        return this.fightResult;
    }
    
    public void setTotalLocations(int count) {
        locationManager.setTotalLocations(count);
    }
    
    public int getCurrentLocation() {
        return locationManager.getCurrentLocation();
    }
    
    public LocationManager getLocation() {
        return locationManager;
    }
    
    public boolean isOver() {
        return locationManager.isGameOver();
    }
    
    public CharacterAction getAction() {
        return action;
    }
}
