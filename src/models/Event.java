package models;

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
