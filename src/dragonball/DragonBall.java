package dragonball;

import java.util.Random;
import dao.LinkedList;
import models.Player;
import models.Enemy;
import java.util.Scanner;
import models.GameMap;
import models.Position;

/**
 *
 * @author Juan Pablo Gómez López Santiago Bañol villa
 *
 */
public class DragonBall {

    public static String movementMenu() {
        String seleccion;
        String msg;
        String separador = new String(new char[20]).replace("\0", "-");
        Scanner entradaUsuario = new Scanner(System.in);

        msg = String.join(System.getProperty("line.separator"),
                separador,
                "SELECCION DE MOVIMIENTO",
                "Ingrese un valor para mover personaje. ",
                "w: Mover arriba",
                "a: Mover a la izquierda",
                "s: Mover abajo",
                "d: Mover a la derecha",
                "p: Mostrar mapa",
                "e: salir",
                separador
        );

        System.out.println(msg);

        seleccion = entradaUsuario.next();

        //entradaUsuario.close();
        return seleccion;
    }

    public static int movementHandler(String selection, GameMap mapa) {
        Position pos = mapa.getplayerPos();
        String old_i = String.valueOf(pos.getI());
        String old_j = String.valueOf(pos.getJ());

        int currentPositionValue;

        switch (selection) {
            case "w":

                if (pos.getI() > 0) {
                    mapa.hideEventsAuxFunction(pos);
                    pos.setI(pos.getI() - 1);
                    mapa.showEventsAuxFunction(pos);
                }
                break;

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

        return mapa.getEventValueInPos(pos);
    }

    public static String eventHandler(int value, Player player, Enemy enemy) {
        String msg;
        if (value == 1) {
            int eventSw = (int) Math.round(Math.random()*101); // random [0,100)
            if (eventSw <= 25) {
                
                int healedValue = (player.getHp() == player.getMaxHealth()) ? 0 : player.heal();
                
                msg = player.getName() + " recupera " 
                      + String.valueOf(healedValue) 
                      + " HP";
                return msg;
                
            } else if (eventSw > 25 && eventSw <= 65) {
                int enemyDmgValue = enemy.attack(player);
                
                msg = player.getName() + " pierde " 
                      + String.valueOf(enemyDmgValue) 
                      + " HP";
                return msg;
                
            } else {
                int playerDmgValue = player.attack(enemy);
                 msg = enemy.getName() + " pierde " 
                      + String.valueOf(playerDmgValue) 
                      + " HP";
                return msg;
            }
        } else {
            msg = "No sucede nada.";
            return msg;
        }
    }

    public static void main(String[] args) {

        //creacion de personajes 
        Player goku = new Player("Goku", 120, 22, 10);
        Player vegetta = new Player("Vegetta", 150, 15, 20);
        Player kirllin = new Player("Kirllin", 90, 8, 8);
        Enemy freezer = new Enemy("Freezer", 300, 5);

        GameMap map = new GameMap();
        map.printPlayerMap();

        String sel;
        while (true) {
            System.out.println("");
            sel = movementMenu();
            if (sel.equals("e")) {
                break;
            } else if (sel.equals("p")) {
                map.printPlayerMap();
            } else {
                int eventValue = movementHandler(sel, map);
                String eventMsg = eventHandler(eventValue, goku, freezer);
                System.out.println(eventMsg);

            }
        }

    }
}
