package Bloxorz;

public class Rectangulo {
    /*
     * En el juego el rectángulo puede estar en varias posiciones, de pie, tumbado horizontalmete o tumbado verticalmente
     * Esta clase sirve para almacenar el estado del rectángulo
     * Los puntos que se guardan son la base del rectángulo, pueden haber tres tipos de base distintos:
     * Si el rectángulo está de pie, su base será su posición actual
     * Si el rectángulo está tumbado horizontalmente, su base será el lado derecho del rectángulo
     * Si el rectángulo está tumbado verticalmente, su base será el lado de arriba del rectángulo
     *
     * Fijamos una base para luego poder realizar los movimientos de forma más cómoda.
     ! El rectángulo tiene otra posición más que no hemos añadido a esta clase porque al fijar la base podemos
     ! calcular cuál es su posición restando o sumando las coordenadas X e Y de la base
     */
    private boolean dePie;
    private boolean horizontal;
    private Punto punto1;

    public Rectangulo(int posX, int posY){
        punto1 = new Punto(posX, posY);
        dePie = true;
        horizontal = false;
    }

    public void setPunto1(Punto punto1) {
        this.punto1 = punto1;
    }

    public Punto getPunto1() {
        return punto1;
    }

    public boolean isDePie() {
        return dePie;
    }

    public boolean isHorizontal() {
        return horizontal;
    }

    public void setDePie(boolean dePie) {
        this.dePie = dePie;
    }

    public void setHorizontal(boolean horizontal) {
        this.horizontal = horizontal;
    }
}
