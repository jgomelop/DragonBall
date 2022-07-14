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
public class Position {
    private int i; // Row position in matrix
    private int j; // Column position in matrix

    public Position(int i, int j) {
        this.i = i;
        this.j = j;
    }

    public int getI() {
        return i;
    }

    public void setI(int i) {
        this.i = i;
    }

    public int getJ() {
        return j;
    }

    public void setJ(int j) {
        this.j = j;
    }
    
    public void printPos(){
        String msg = String.format("(%s,%s)", i,j);
        System.out.println(msg);
    }

    @Override
    public String toString() {
        return "Position{" + "i=" + i + ", j=" + j + '}';
    }
}
