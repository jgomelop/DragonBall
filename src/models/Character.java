/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

/**
 *
 * @author Juan Pablo
 */
public class Character {

    private String name;
    private int hp;
    private int atk;
    private int def;

    public Character(String name, int hp, int atk, int def) {
        this.name = name;
        this.hp = hp;
        this.atk = atk;
        this.def = def;
    }

    public Character() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getHp() {
        return hp;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }

    public int getAtk() {
        return atk;
    }

    public void setAtk(int atk) {
        this.atk = atk;
    }

    public int getDef() {
        return def;
    }

    public void setDef(int def) {
        this.def = def;
    }

    @Override
    public String toString() {
        return "Character{" + "name=" + name + ", hp=" + hp + ", atk=" + atk + ", def=" + def + '}';
    }

    public void curar() {
        int vidaRegenerada = (int) (Math.random() * 10 + 1);
        this.hp += vidaRegenerada;
        System.out.println("El personaje se ha curado" + vidaRegenerada + ",su nueva vida es:" + this.hp);
    }

}
