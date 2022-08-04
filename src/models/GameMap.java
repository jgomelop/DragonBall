package models;

public class GameMap {

    private String[][] playerMatrix = new String[5][5];
    private int[][] eventMatrix = new int[5][5];
    private Position playerPos;

    public GameMap() {
        this.playerPos = new Position(0, 0);

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
                if ((i == this.playerPos.getI()
                        && j == this.playerPos.getJ())
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
                if (i == this.playerPos.getI() && j == this.playerPos.getJ()) {
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

    public void hideEvents(Position pos) {
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

    public void showEvents(Position pos) {
        int i = pos.getI();
        int j = pos.getJ();

        this.playerMatrix[i][j] = "#";

        try {
            this.playerMatrix[i][j - 1] = String.valueOf(this.eventMatrix[i][j - 1]);
        } catch (IndexOutOfBoundsException e) {
        }

        try {
            this.playerMatrix[i][j + 1] = String.valueOf(this.eventMatrix[i][j + 1]);
        } catch (IndexOutOfBoundsException e) {
        }

        try {
            this.playerMatrix[i - 1][j] = String.valueOf(this.eventMatrix[i - 1][j]);

        } catch (IndexOutOfBoundsException e) {
        }

        try {
            this.playerMatrix[i + 1][j] = String.valueOf(this.eventMatrix[i + 1][j]);
        } catch (IndexOutOfBoundsException e) {
        }

        this.playerPos = pos;

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
     * @return Valor String de la posición (i,j) de la matriz
     */
    public Position getplayerPos() {
        return playerPos;
    }

    public String[][] getPlayerMatrix() {
        return playerMatrix;
    }
    
    public int getEventValueInPos(Position pos){
        return eventMatrix[pos.getI()][pos.getJ()];
    }

}
