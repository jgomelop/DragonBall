package models;

/**
 *
 * @author Juan Pablo
 */
public class GameMap {

    private String[][] matrix = new String[5][5];
    private Position posJugador = new Position(0,0);

    public GameMap() {
        
        /**
         * Rellena la matriz matrix con "0" si 
         */
        for (int i = 0; i <= 4; i++) {
            for (int j = 0; j <= 4; j++) {
                if (i == 0 && j == 0) { // Posición inical del jugador
                    this.matrix[i][j] = "#";
                } else if (i == 4 && j == 4){ //Pos. Final. Meta del juego
                    this.matrix[i][j] = "-";
                }
                else {
                    // Genera valores "0" o "1" dependiendo de probabilidad
                    // en este caso, 65%
                    this.matrix[i][j] = generateValue((float) 0.65);
                }
            }
        }
    }

    /**
     * Retorna "0" o "1" dependiendo del valor de probabilidad prob
     * de acuerdo a la función de probabilidad uniforme de Math.random
     * @param prob float. Probabilidad. Valor entre 0 y 1.
     * @return  "1" si random_int es menor o igual a prob, "0" en lo contrario.
     */
    private String generateValue(float prob) {
        float random_int = (float) Math.random();
        if (random_int <= prob) {
            return String.valueOf(1);
        }
        return String.valueOf(0);
    }
    
    /* Imprime el matrixa en pantalla */
    public void printMap() {
        for (int i = 0; i <= 4; i++) {
            for (int j = 0; j <= 4; j++) {
                 System.out.print(this.matrix[i][j] + " ");
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
    public String getValue(int i, int j){
        return this.matrix[i][j];
    }
    
    /**
     * 
     * @param s String. Valor a insertar en la matriz.
     * @param i Valor de la i-ésima fila a insertar.
     * @param j Valor de la j-ésima columna a insertar.
     */
    public void setValue(String s, int i, int j){
        this.matrix[i][j] = s;
    }
    
}
