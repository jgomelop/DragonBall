/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

/**
 *
 * @author Carito
 */
public class Enemy extends Character {

    private final int min_atk;
    private final int max_atk;

    public Enemy(String name, int hp, int def) {
        super(name, hp, def);
        this.min_atk = 15;
        this.max_atk = 30;
    }

    public Enemy(int min_atk, int max_atk, String name, int hp, int def) {
        super(name, hp, def);
        this.min_atk = min_atk;
        this.max_atk = max_atk;

    }

    public int attack(Player p) {
        int playerHp = p.getHp();
        int playerDef = p.getDef();
        //generador  del numero aleatorio en el rango
        //int daño = (int) Math.floor(this.min_atk + Math.random() * 16); // [0,1)
        int daño = (int) (Math.random() * (this.max_atk - this.min_atk) + 1) + this.min_atk;
        //daño reducido por la defensa
        if (daño >= playerDef) {
            return playerHp - daño + playerDef;
        } else {
            return playerHp;
        }
    }

}
