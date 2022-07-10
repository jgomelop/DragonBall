package models;

/**
 *
 * @author Juan Pablo
 */
public class GameMap {

    private String[][] playerMatrix = new String[5][5];
    private int[][] eventMatrix = new int[5][5];
    private Position lastPlayerPos;

    public GameMap(Position initialPlayerPosition) {

        this.lastPlayerPos = initialPlayerPosition;

        generateEventMatrix();
        generateInitialPlayerMatrix();

    }

    /**
     * Retorna "0" o "1" dependiendo del valor de probabilidad prob de acuerdo a
     * la función de probabilidad uniforme de Math.random
     *
     * @param prob float. Probabilidad. Valor entre 0 y 1.
     * @return "1" si random_int es menor o igual a prob, "0" en lo contrario.
     */
    private int generateValue(float prob) {
        float random_int = (float) Math.random();
        if (random_int <= prob) {
            return 1;
        }
        return 0;
    }

    private void generateEventMatrix() {
        for (int i = 0; i <= 4; i++) {
            for (int j = 0; j <= 4; j++) {
                if ((i == this.lastPlayerPos.getI()
                        && j == this.lastPlayerPos.getJ())
                        || (i == 4 && j == 4)) { // Posición inicial del jugador

                    this.eventMatrix[i][j] = 0;
                } else {
                    // Genera valores "0" o "1" dependiendo de probabilidad
                    // en este caso, 65%
                    this.eventMatrix[i][j] = generateValue((float) 0.65);
                }
            }
        }
    }

    private void generateInitialPlayerMatrix() {
        for (int i = 0; i <= 4; i++) {
            for (int j = 0; j <= 4; j++) {
                if (i == this.lastPlayerPos.getI() && j == this.lastPlayerPos.getJ()) {
                    this.playerMatrix[i][j] = "#";
                } else if (i == 4 && j == 4) {
                    this.playerMatrix[i][j] = "-";
                } else {
                    // Puntos (0,1) y (1,0) muestran valores de evento
                    if ((i == 0 && j == 1) || (i == 1 && j == 0)) {
                        this.playerMatrix[i][j] = String.valueOf(this.eventMatrix[i][j]);
                    } else {
                        this.playerMatrix[i][j] = "?";
                    }
                }
            }
        }

    }

    public void hideEventsAuxFunction(Position pos) {
        int i = pos.getI();
        int j = pos.getJ();
        try {
            this.playerMatrix[i][j - 1] = "?";
        } catch (IndexOutOfBoundsException e) {
        }

        try {
            this.playerMatrix[i][j + 1] = "?";
        } catch (IndexOutOfBoundsException e) {
        }

        try {
            this.playerMatrix[i - 1][j] = "?";
        } catch (IndexOutOfBoundsException e) {
        }

        try {
            this.playerMatrix[i + 1][j] = "?";
        } catch (IndexOutOfBoundsException e) {
        }

    }
    
    public void showEventsAuxFunction(Position pos) {
        int i = pos.getI();
        int j = pos.getJ();
        
        this.playerMatrix[i][j] = "#";
        
        try {
            this.playerMatrix[i][j-1] = String.valueOf(this.eventMatrix[i][j-1]);
        } catch (IndexOutOfBoundsException e) {
        }

        try {
            this.playerMatrix[i][j+1] = String.valueOf(this.eventMatrix[i][j+1]);
        } catch (IndexOutOfBoundsException e) {
        }

        try {
            this.playerMatrix[i-1][j] = String.valueOf(this.eventMatrix[i-1][j]);

        } catch (IndexOutOfBoundsException e) {
        }

        try {
            this.playerMatrix[i+1][j] = String.valueOf(this.eventMatrix[i+1][j]);
        } catch (IndexOutOfBoundsException e) {
        } 
        
        this.setLastPlayerPos(pos);

    }
    
    

    public void updatePlayerMatrix(Position pos) {

        hideEventsAuxFunction(this.lastPlayerPos);
        showEventsAuxFunction(pos);
        
        this.setLastPlayerPos(pos);

    }

    /* Imprime el matriz de String de jugador en pantalla */
    public void printPlayerMap() {
        for (int i = 0; i <= 4; i++) {
            for (int j = 0; j <= 4; j++) {
                System.out.print(this.playerMatrix[i][j] + " ");
            }
            System.out.println();
        }
    }

    public void printEventMap() {
        for (int i = 0; i <= 4; i++) {
            for (int j = 0; j <= 4; j++) {
                System.out.print(this.eventMatrix[i][j] + " ");
            }
            System.out.println();
        }
    }

    /**
     *
     * @param i Valor de la i-ésima fila a consultar
     * @param j Valor de la j-ésima columna a consultar
     * @return Valor String de la posición (i,j) de la matriz
     */
    public String getValue(int i, int j) {
        return this.playerMatrix[i][j];
    }

    /**
     *
     * @param s String. Valor a insertar en la matriz.
     * @param i Valor de la i-ésima fila a insertar.
     * @param j Valor de la j-ésima columna a insertar.
     */
    public void setValue(String s, int i, int j) {
        this.playerMatrix[i][j] = s;
    }

    public Position getLastPlayerPos() {
        return lastPlayerPos;
    }

    public void setLastPlayerPos(Position lastPlayerPos) {
        this.lastPlayerPos = lastPlayerPos;
    }

    public String[][] getPlayerMatrix() {
        return playerMatrix;
    }

}
