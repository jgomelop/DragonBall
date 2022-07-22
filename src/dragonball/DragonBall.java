package dragonball;

import java.util.Random;
import dao.LinkedList;
import models.Character;
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

    public static void movementHandler(String selection, GameMap mapa) {
        Position pos = mapa.getplayerPos();
        String old_i = String.valueOf(pos.getI());
        String old_j = String.valueOf(pos.getJ());

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

    }

    public static void hitFreezer(Character enemy, Character hero) {

        Random gauss = new Random();
        int damageForEnemy = (int) (hero.getAtk() + gauss.nextGaussian() * 3);
        System.out.println("damage de " + hero.getName() + " " + damageForEnemy); //verificacion
        enemy.setHp(enemy.getHp() + enemy.getDef() - damageForEnemy);
        System.out.println("vida de " + enemy.getName() + " " + enemy.getHp()); //mas verificaciones de que funcione
        int newDamageHero = hero.getAtk() - enemy.getDef() + damageForEnemy;
        hero.setAtk(newDamageHero);
    }

    public static int takeDamage(Character character) {
        
        int characterHp = character.getHp();
        int characterDef = character.getDef();
        //generador  del numero aleatorio en el rango
        int daño = (int) Math.floor(15 + Math.random() * 16); // [0,1)
        //daño reducido por la defensa
        if (daño >= characterDef) {
            return characterHp - daño + characterDef;
        } else {
            return characterHp;
        }
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        //creacion de personajes 
        Character goku = new Character("Goku", 120, 22, 10);
        Character vegetta = new Character("Vegetta", 150, 15, 20);
        Character kirllin = new Character("Kirllin", 90, 8, 8);
        Character freezer = new Character("Freezer", 300, 0, 5);


        GameMap map = new GameMap();

        String sel;
        while (true) {
            System.out.println("");
            sel = movementMenu();
            if (sel.equals("e")) {
                break;
            } else if (sel.equals("p")) {
                map.printPlayerMap();
            } else {
                movementHandler(sel, map);
            }
        }

    }
}
