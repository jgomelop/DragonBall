/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import java.util.Random;

/**
 *
 * @author Carito
 */
public class Player extends Character {

    private int atk;
    private final int MAX_HEALTH;

    public Player(String name, int hp, int atk, int def) {
        super(name, hp, def);
        this.atk = atk;
        this.MAX_HEALTH = hp;
    }

    public int attack(Enemy e) {
        int std = 2; // Standard Deviation
        Random gauss = new Random();

        int playerAtkValue = (int) Math.round(this.atk + gauss.nextGaussian() * std);

        int effectiveDmgValue = playerAtkValue - e.getDef();

        if (effectiveDmgValue > 0) {
            e.setHp(e.getHp() - effectiveDmgValue);
            return effectiveDmgValue;
        }
        return 0;
    }

    public int heal() {

        int vidaRegenerada = (int) Math.round(Math.random()*(10 - 1 + 1) + 1);
        this.hp += vidaRegenerada;
        return vidaRegenerada;
    }

    public int getMaxHealth() {
        return this.MAX_HEALTH;
    }

}
