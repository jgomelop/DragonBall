/**
 * Autores:
 * Juan Pablo Gómez López
 * carolina melguizo pemberty
 * Iván Daniel Mejía Gaspar
 * Santiago Bañol VIlla
 */

package dragonball;

import models.Player;
import models.Enemy;
import java.util.Scanner;
import models.GameMap;
import models.Position;
import models.Event;
import java.util.List;
import java.util.Arrays;
import java.util.LinkedList;
import models.Record;

public class DragonBall {

    /**
     * Menú para que el usuario escoja un personaje de su preferencia. Pmasados
     * un número máximo de intentos, selecciona Goku por defecto.
     *
     * @return String seleccion.
     */
    public static String playerSelectionMenu() {
        String seleccion;
        String msg;
        String separador = new String(new char[20]).replace("\0", "-");
        Scanner entradaUsuario = new Scanner(System.in);

        msg = String.join(System.getProperty("line.separator"),
                separador,
                "SELECCION DE PERSONAJE",
                "Ingrese el valor numérico correspondiente. ",
                "1: Gokú. HP: 120, ATK: 22, DEF: 10",
                "2: Vegetta. HP: 150, ATK: 15, DEF: 20",
                "3: Kirllin. HP: 90, ATK: 8, DEF: 8",
                separador
        );

        System.out.println(msg);

        seleccion = entradaUsuario.next();

        //entradaUsuario.close();
        return seleccion;
    }

    /**
     * Menú de interacción del usuario para moverse a través del mapa. Opción
     * "e" para salir del juego.
     *
     * @return String seleccion.
     */
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
                "e: salir",
                separador
        );

        System.out.println(msg);

        seleccion = entradaUsuario.next();

        //entradaUsuario.close();
        return seleccion;
    }

    /**
     * Clase controladora del movimiento del usuario. Verifica que los
     * movimientos queden dentro de la matriz. Además, si está en uno de los
     * bordes de la matriz, si intenta moverse en esa misma dirección (ej, si
     * estoy en la fila 0 de la matriz, y oprimió "w" para ir arriba) retorna -1
     * en la posicion del jugador que sirve para mostrar que "no hubo
     * movimiento" y así no generar un evento en el método main.
     *
     * @param selection
     * @param mapa
     * @return List<Object> : [String indicar desplazamiento, int posicion del
     * jugador]
     */
    public static List<Object> movementHandler(String selection, GameMap mapa) {
        Position pos = mapa.getplayerPos();
        String old_i = String.valueOf(pos.getI());
        String old_j = String.valueOf(pos.getJ());

        switch (selection) {
            case "w":

                if (pos.getI() > 0) {
                    mapa.hideEvents(pos);
                    pos.setI(pos.getI() - 1);
                    mapa.showEvents(pos);
                }
                break;

            case "a":

                if (pos.getJ() > 0) {
                    mapa.hideEvents(pos);
                    pos.setJ(pos.getJ() - 1);
                    mapa.showEvents(pos);
                }

                break;

            case "s":

                if (pos.getI() < mapa.getPlayerMatrix()[0].length - 1) {
                    mapa.hideEvents(pos);
                    pos.setI(pos.getI() + 1);
                    mapa.showEvents(pos);
                }

                break;

            case "d":
                if (pos.getJ() < mapa.getPlayerMatrix().length - 1) {
                    mapa.hideEvents(pos);
                    pos.setJ(pos.getJ() + 1);
                    mapa.showEvents(pos);
                }
                break;
        }

        String new_i = String.valueOf(pos.getI());
        String new_j = String.valueOf(pos.getJ());

        if (old_i.equals(new_i) && old_j.equals(new_j)) {
            mapa.printPlayerMap();
            System.out.println("");
            String msg = String.format("Sin Movimiento: (%s,%s)", new_i, new_j);
            System.out.println(msg);
            return Arrays.asList("Sin movimiento", -1);
        } else {
            mapa.printPlayerMap();
            System.out.println("");
            String msg = String.format("(%s,%s) -> (%s,%s)", old_i, old_j, new_i, new_j);
            System.out.println(msg);
            return Arrays.asList(msg, mapa.getEventValueInPos(pos));
        }
    }

    /**
     * Método controlador para los eventos. Si value == 1 es un evento, que
     * depende de un valor aleatorio en el rango entre 0 inclusivo y 100
     * inclusivo.
     *
     * @param value 1 es un evento, 0 "no pasa nada"
     * @param player
     * @param enemy
     * @return Instancia de Event.
     */
    public static Event eventHandler(int value, Player player, Enemy enemy) {
        String msg;
        Event event = new Event();
        if (value == 1) {
            int eventSw = (int) Math.round(Math.random() * 101); // valor aleatorio [0,100)

            if (eventSw <= 25) {

                int healedValue = (player.getHp() == player.getMaxHealth()) ? 0 : player.heal();

                msg = player.getName() + " recupera "
                        + String.valueOf(healedValue)
                        + " HP";

                event.setMsg(msg);
                event.setEarnedPoints(0);
                return event;

            } else if (eventSw > 25 && eventSw <= 65) {
                int enemyDmgValue = enemy.attack(player);

                msg = player.getName() + " pierde "
                        + String.valueOf(enemyDmgValue)
                        + " HP";

                event.setMsg(msg);
                event.setEarnedPoints(0);
                return event;

            } else {
                int playerDmgValue = player.attack(enemy);
                msg = enemy.getName() + " pierde "
                        + String.valueOf(playerDmgValue)
                        + " HP";
                event.setMsg(msg);
                event.setEarnedPoints(playerDmgValue);
                return event;
            }
        } else {
            msg = "No sucede nada.";
            event.setMsg(msg);
            event.setEarnedPoints(0);
            return event;
        }
    }

    public static void main(String[] args) {

        // Generamos los personajes jugables
        Player goku = new Player("Goku", 120, 22, 10);
        Player vegetta = new Player("Vegetta", 150, 15, 20);
        Player kirllin = new Player("Kirllin", 90, 8, 8);
        Enemy enemy = new Enemy("Freezer", 300, 5);

        // Ciclo para la selección de personaje. 
        Player player;
        int count = 0; // contador para máximo de intentos
        while (true) {
            String playerSelection = playerSelectionMenu();

            if (1 == Integer.valueOf(playerSelection) || count >= 10) {
                player = goku;
                break;
            } else if (2 == Integer.valueOf(playerSelection)) {
                player = vegetta;
                break;
            } else if (3 == Integer.valueOf(playerSelection)) {
                player = kirllin;
                break;
            }
            count += 1;
            System.out.println("Parámentro no válido.");
        }

        int gamePoints = 0; // puntos de juego, que suman al hacerle daño al enemigo
        List<Record> gameRecords = new LinkedList<Record>(); // Lista ligada para alamcenar registros de eventos.

        GameMap map = new GameMap();
        map.printPlayerMap();

        // Ciclo principal de juego. 
        String sel; // Variable que contiene la selección del usuario del movementMenu.
        while (true) {
            System.out.println(""); // Salto de línea
            sel = movementMenu(); // Selecciona una opción para moverse

            if (sel.equals("e")) {  // Opción para salir del juego
                break;
            }

            // [desplazamiento (String), valor del evento (0 o 1)]
            List<Object> movement = movementHandler(sel, map);
            String movementMsg = (String) movement.get(0);
            int eventValue = (int) movement.get(1);

            // Si el valor del evento es -1, significa que no hubo movimiento.
            if (eventValue != -1) {
                Event event = eventHandler(eventValue, player, enemy); //obtenemos el evento asociado
                String eventMsg = event.getMsg();
                
                // si jugador no hizo daño a enemigo, esta variable es cero.
                int eventEarnedPoints = event.getEarnedPoints(); 
                gamePoints += eventEarnedPoints;

                // Guardamos los registros a la lsita ligada.
                gameRecords.add(new Record(movementMsg,
                        map.getplayerPos(),
                        event,
                        gamePoints));

                // imprimimos los resultados leugo del movimiento.
                System.out.println(eventMsg);
                System.out.printf("PV de %s: %d\n", player.getName(), player.getHp());
                System.out.printf("PV de %s: %d\n", enemy.getName(), enemy.getHp());
                System.out.printf("Puntos: %d\n", gamePoints);

                // Condiciones de finalización del juego.
                if (player.getHp() <= 0) { // Si el jugador ya no tiene HP
                    System.out.println("Has perdido!\n");
                    System.out.printf("ültima Posición: (%d,%d)\n",
                            map.getplayerPos().getI(),
                            map.getplayerPos().getJ());

                    break;
                } 
                // Si jugador llega a casilla  (4,4) o enemigo queda con 0 HP, victoria.
                else if (enemy.getHp() <= 0
                        || (map.getplayerPos().getI() == 4
                        && map.getplayerPos().getJ() == 4)) { 

                    System.out.println("Has Ganado!\n");
                    break;
                }

            }

        }

    }
}
