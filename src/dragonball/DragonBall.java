package dragonball;

import java.util.Random;
import dao.LinkedList;
import models.Character;

/**
 *
 * @author Juan Pablo Gómez López Santiago Bañol villa
 *
 */
public class DragonBall {

    public static int takeDamage(int characterHp, int characterDef) {
        //generador  del numero aleatorio en el rango
        int daño = (int) Math.floor(15 + Math.random() * 16); // [0,1)
        //daño reducido por la defensa
        if (daño >= characterDef) {
            return characterHp - daño + characterDef;
        } else {
            return characterHp;
        }
    }

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

    public static void hitFreezer(Character enemy, Character hero) {

        Random gauss = new Random();
        int damageForEnemy = (int) (hero.getAtk() + gauss.nextGaussian() * 3);
        System.out.println("damage de " + hero.getName() + " " + damageForEnemy); //verificacion
        enemy.setHp(enemy.getHp() + enemy.getDef() - damageForEnemy);
        System.out.println("vida de " + enemy.getName() + " " + enemy.getHp()); //mas verificaciones de que funcione
        int newDamageHero = hero.getAtk() - enemy.getDef() + damageForEnemy;
        hero.setAtk(newDamageHero);
    }

    public static void main(String[] args) {

        LinkedList registro = new LinkedList();
        registro.add("HP 100");
        registro.add("Recivió 2 ataques");
        registro.add("Realizó 5 movimientos");
        String historial = registro.output();

        while (!historial.equals("/")) {
            System.out.println(historial); //Prueba del objeto lista
            historial = registro.output();
        }


        /*
      * parte de santiago villa
         */
        //espacio en blanco para que no se superpongan los datos 
        System.out.println(" ");

        //creacion de personajes 
        Character goku = new Character("Goku", 120, 22, 10);
        Character vegetta = new Character("Vegetta", 150, 15, 20);
        Character kirllin = new Character("Kirllin", 90, 8, 8);
        Character freezer = new Character("Freezer", 300, 0, 5);

        //verifico que funcione con el print 
        System.out.println("vida de " + freezer.getName() + " " + freezer.getHp());

        hitFreezer(freezer, goku);

        goku.setHp(takeDamage(goku.getHp(), goku.getDef()));
        System.out.println(goku.getHp());

        goku.setHp(takeDamage(goku.getHp(), goku.getDef()));
        System.out.println(goku.getHp());

    }
}