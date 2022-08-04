/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models;

/**
 *
 * @author jpgl2
 */
public class Event {
    private String msg;
    private int earnedPoints;

    public Event(String msg, int earnedPoints) {
        this.msg = msg;
        this.earnedPoints = earnedPoints;
    }

    public String getMsg() {
        return msg;
    }

    public int getEarnedPoints() {
        return earnedPoints;
    }

    public Event() {
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public void setEarnedPoints(int earnedPoints) {
        this.earnedPoints = earnedPoints;
    }
    
    
}
