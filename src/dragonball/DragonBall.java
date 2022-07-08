package dragonball;
import dao.LinkedList;
import models.GameMap;
/**
 *
 * @author Juan Pablo Gómez López
 */
public class DragonBall {
    

   
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        LinkedList registro = new LinkedList();
        registro.add("HP 100");
        registro.add("Recibió 2 ataques");
        registro.add("Realizó 5 movimientos");
        String historial = registro.output();

        while(!historial.equals("/")) {
            System.out.println(historial); //Prueba del objeto lista
            historial = registro.output();
        }

        
        GameMap mapa = new GameMap();
        mapa.printMap();
       
    }
}
