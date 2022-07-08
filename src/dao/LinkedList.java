package dao;

public class LinkedList {

    private final Nodo head;
    private Nodo x ;
    private Nodo tail;

    public LinkedList() {
        head = new Nodo("/");
        tail = head;
        x = head;

    }

    public void add(String gameLog) {
        Nodo p = new Nodo(gameLog);
        tail.setLiga(p);
        tail = p;
    }

    public String output() {
        x = x.getLiga();
        if (x == null)
        {
            x = head;
        }
        return x.getGameLog();
    }
}
