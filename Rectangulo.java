package Bloxorz;

public class Rectangulo {
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
