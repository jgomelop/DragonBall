package dao;

public class Nodo {

   private String gameLog;
   private Nodo liga;

    public Nodo(String gameLog) {
        this.liga = null;
        this.gameLog = gameLog;
    }

    public String getGameLog() {
        return gameLog;
    }

    public void setGameLog(String gameLog) {
        this.gameLog = gameLog;
    }

    public Nodo getLiga() {
        return liga;
    }

    public void setLiga(Nodo liga) {
        this.liga = liga;
    }
}
