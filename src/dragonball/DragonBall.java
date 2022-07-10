package dragonball;

import com.sun.javafx.collections.MapAdapterChange;
import dao.LinkedList;
import java.util.Scanner;
import models.GameMap;
import models.Position;

/**
 *
 * @author Juan Pablo Gómez López
 */
public class DragonBall {

    public static String StringRepeat(int n, String str) {
        return new String(new char[n]).replace("\0", str);
    }

    /**
     * Salto de línea. Funcion auxiliar para String join
     *
     * @return str
     */
    public static String NewLineDelimiter() {
        String newLine = System.getProperty("line.separator");
        return newLine;
    }

    public static String movementMenu() {
        String seleccion;
        String msg;
        Scanner entradaUsuario = new Scanner(System.in);

        msg = String.join(NewLineDelimiter(),
                StringRepeat(20, "-"),
                "SELECCION DE MOVIMIENTO",
                "Ingrese un valor para mover personaje. ",
                "w: Mover arriba",
                "a: Mover a la izquierda",
                "s: Mover abajo",
                "d: Mover a la derecha",
                "p: Mostrar mapa",
                "e: salir",
                StringRepeat(20, "-")
        );

        System.out.println(msg);

        seleccion = entradaUsuario.next();

        //entradaUsuario.close();
        return seleccion;
    }

    public static void movementHandler(String selection, Position pos, GameMap mapa) {
        String old_i = String.valueOf(pos.getI());
        String old_j = String.valueOf(pos.getJ());

        switch (selection) {
            case "w":

                if (pos.getI() > 0) {
                    mapa.hideEventsAuxFunction(pos);
                    pos.setI(pos.getI() - 1);
                    mapa.showEventsAuxFunction(pos);
                    break;
                }

            case "a":

                if (pos.getJ() > 0) {
                    mapa.hideEventsAuxFunction(pos);
                    pos.setJ(pos.getJ() - 1);
                    mapa.showEventsAuxFunction(pos);
                }

                break;

            case "s":

                if (pos.getI() < mapa.getPlayerMatrix()[0].length - 1) {
                    mapa.hideEventsAuxFunction(pos);
                    pos.setI(pos.getI() + 1);
                    mapa.showEventsAuxFunction(pos);
                }

                break;

            case "d":
                if (pos.getJ() < mapa.getPlayerMatrix().length - 1) {
                    mapa.hideEventsAuxFunction(pos);
                    pos.setJ(pos.getJ() + 1);
                    mapa.showEventsAuxFunction(pos);
                }
                break;
        }

        String new_i = String.valueOf(pos.getI());
        String new_j = String.valueOf(pos.getJ());

        String msg = String.format("(%s,%s) -> (%s,%s)", old_i, old_j, new_i, new_j);

        System.out.println(msg);
        mapa.printPlayerMap();

    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        LinkedList registro = new LinkedList();
        registro.add("HP 100");
        registro.add("Recibió 2 ataques");
        registro.add("Realizó 5 movimientos");
        String historial = registro.output();

        while (!historial.equals("/")) {
            //System.out.println(historial); //Prueba del objeto lista
            historial = registro.output();
        }

        Position playerPos = new Position(0, 0);
        GameMap map = new GameMap(playerPos);

        String sel;
        while (true) {
            System.out.println("");
            sel = movementMenu();
            if (sel.equals("e")) {
                break;
            } else if (sel.equals("p")) {
                map.printPlayerMap();
            } else {
                movementHandler(sel, playerPos, map);
            }
        }

    }

}
