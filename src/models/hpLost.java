import java.util.Random;

public class hpLost {
    public static int takeDamage(int characterHp, int characterDef){
        //generador del numero aleatorio en el rango
        int daño = (int) Math.floor(15+Math.random()*16);
        //daño reducido por la defensa
        if (daño>=characterDef){
            return characterHp-daño+characterDef;
        } else{
            return characterHp;
        }
    }
}
