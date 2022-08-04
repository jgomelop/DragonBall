package models;


public class Record {
    private String movement;
    private Position playerPosition;
    private Event event;
    private int earnedPoints;

    public Record(String movement, Position playerPosition, Event event, int earnedPoints) {
        this.movement = movement;
        this.playerPosition = playerPosition;
        this.event = event;
        this.earnedPoints = earnedPoints;
    }

    public String getMovement() {
        return movement;
    }

    public Position getPlayerPosition() {
        return playerPosition;
    }

    public Event getEvent() {
        return event;
    }

    public int getEarnedPoints() {
        return earnedPoints;
    }

}
