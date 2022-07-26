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

    public Player(int atk) {
        this.atk = atk;

    }

    public Player(String name, int atk, int hp, int def) {
        super(name, hp, def);
        this.atk = atk;
    }

    public void attack(Enemy e) {
        Random gauss = new Random();
        int damageForEnemy = (int) (this.atk + gauss.nextGaussian() * 3);
        System.out.println("damage de " + super.name + " " + damageForEnemy); //verificacion
        e.setHp(e.getHp() + e.getDef() - damageForEnemy);
        System.out.println("vida de " + e.getName() + " " + e.getHp()); //mas verificaciones de que funcione
        int newDamageHero = this.atk - e.getDef() + damageForEnemy;
        this.atk = newDamageHero;
    }

    public void heal() {
        int vidaRegenerada = (int) (Math.random() * 10 + 1);
        this.hp += vidaRegenerada;
        System.out.println("El personaje se ha curado" + vidaRegenerada + ",su nueva vida es:" + this.hp);
    }

}
