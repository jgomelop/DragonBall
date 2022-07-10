package dragonball;
import dao.LinkedList;
/**
 *
 * @author Juan Pablo Gómez López
 */
public class DragonBall {
    public static int generateValue(float prob) {
        float random_int = (float) Math.random();
        if (random_int <= prob) {
            return 1;
        }
        return 0;
    }

    public static String[][] intMapToStringMap(int[][] map) {
        String[][] stringMap = new String[map.length][map[0].length];

        for (int i = 0; i <= map.length - 1; i++) {
            for (int j = 0; j <= map[0].length - 1; j++) {
                stringMap[i][j] = String.valueOf(map[i][j]);
//                System.out.print(stringMap[i][j] + " ");
            }
//            System.out.println();

        }
        return stringMap;
    }
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        LinkedList registro = new LinkedList();
        registro.add("HP 100");
        registro.add("Recivió 2 ataques");
        registro.add("Realizó 5 movimientos");
        String historial = registro.output();

        while(!historial.equals("/")) {
            System.out.println(historial); //Prueba del objeto lista
            historial = registro.output();
        }

        int[][] map = new int[5][5];
        for (int i = 0; i <= 4; i++) {
            for (int j = 0; j <= 4; j++) {
                if ((i == 0 && j == 0) || (i == 4 && j == 4)) {
                    map[i][j] = 0;
                } else {
                    map[i][j] = generateValue((float) 0.65);
                }
                /*System.out.print(map[i][j] + " ");*/
            }
            /*System.out.println();*/
        }

        /* # Representa la posicion del jugador*/
        String[][] mapString = intMapToStringMap(map);
        mapString[0][0] = "#";

        for (int i = 0; i <= mapString.length - 1; i++) {
            for (int j = 0; j <= mapString[0].length - 1; j++) {
                System.out.print(mapString[i][j] + " ");
            }
            /*System.out.println();*/

        }
    }
}
