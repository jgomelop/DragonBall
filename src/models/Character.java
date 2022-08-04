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
public abstract class Character {
    protected String name;
    protected int hp;
    protected int def;

    public Character(String name, int hp, int def) {
        this.name = name;
        this.hp = hp;
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

    public int getDef() {
        return def;
    }

    public void setDef(int def) {
        this.def = def;
    }
    
    @Override
    public String toString() {
        return "Character{" + "name=" + name + ", hp=" + hp + ", def=" + def + '}';
    }
    
}
