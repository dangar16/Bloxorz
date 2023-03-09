package Bloxorz;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Bloxorz {
    private int endX;
    private int endY;
    private int[][] bucles;
    private int bucleX;
    private int bucleY;
    private ArrayList<String> combinaciones;
    public ArrayList<String> getCombinaciones() {
        return combinaciones;
    }

    public Bloxorz(){
        combinaciones = new ArrayList<>();
    }

    public boolean comprobarRango(int x, int y, int x2, int y2, Rectangulo rectangulo, int[][] mapa){
        if(rectangulo.isDePie()){
            if(x < 0 || x >= mapa.length || y < 0 || y >= mapa[0].length || mapa[x][y] == 0)
                return false;
            return true;
        } else {
            if(x < 0 || x >= mapa.length || y < 0 || y >= mapa[0].length || x2 < 0 || x2 >= mapa.length || y2 < 0 || y2 >= mapa[0].length || mapa[x][y] == 0 || mapa[x2][y2] == 0)
                return false;
            return true;
        }
    }

    private void printSolution(int[][] sol){
        for(int i = 0; i < sol.length; i++){
            System.out.println(Arrays.toString(sol[i]));
        }
    }

    private boolean findPathDown(int x, int y, int[][] mapa, Rectangulo rectangulo){
        if(rectangulo.isDePie() && x == endX && y == endY)
            return true;

        if(rectangulo.isDePie() && bucles[x][y] == 1) {
            bucleX = x;
            bucleY = y;
            return false;
        }
        if(rectangulo.isDePie()){
            bucles[x][y] = 1;
        }

        if(comprobacionesAbajo(rectangulo, mapa))
            return true;

        if(comprobacionesDerecha(rectangulo, mapa))
            return true;

        if(comprobacionesIzquierda(rectangulo, mapa))
            return true;


        return false;
    }

    private boolean findPathLeft(int x, int y, int[][] mapa, Rectangulo rectangulo){
        if(rectangulo.isDePie() && x == endX && y == endY)
            return true;

        if(rectangulo.isDePie() && bucles[x][y] == 1) {
            bucleX = x;
            bucleY = y;
            return false;
        }
        if(rectangulo.isDePie()){
            bucles[x][y] = 1;
        }

        if(comprobacionesIzquierda(rectangulo, mapa))
            return true;
        if(comprobacionesArriba(rectangulo, mapa))
            return true;
        if(comprobacionesAbajo(rectangulo, mapa))
            return true;

        return false;
    }

    private boolean findPathUp(int x, int y, int[][] mapa, Rectangulo rectangulo){
        if(x == endX && y == endY && rectangulo.isDePie())
            return true;

        if(rectangulo.isDePie() && bucles[x][y] == 1) {
            bucleX = x;
            bucleY = y;
            return false;
        }
        if(rectangulo.isDePie()){
            bucles[x][y] = 1;
        }

        // * Vamos hacia arriba
        // * Comprobacion si vamos arriba, multiples casos

        if(comprobacionesArriba(rectangulo, mapa)){
            return true;
        }

        // * Comprobación si vamos hacia la derecha

        if(comprobacionesDerecha(rectangulo, mapa)){
            return true;
        }

        if(comprobacionesIzquierda(rectangulo, mapa))
            return true;

        return false;
    }

    private boolean findPathRight(int x, int y, int[][] mapa, Rectangulo rectangulo){
        if(x == endX && y == endY && rectangulo.isDePie())
            return true;

        if(rectangulo.isDePie() && bucles[x][y] == 1) {
            bucleX = x;
            bucleY = y;
            return false;
        }
        if(rectangulo.isDePie()){
            bucles[x][y] = 1;
        }
        if(comprobacionesDerecha(rectangulo, mapa))
            return true;
        if(comprobacionesArriba(rectangulo, mapa))
            return true;
        if(comprobacionesAbajo(rectangulo, mapa))
            return true;


        return false;
    }

    private boolean comprobacionesAbajo(Rectangulo rectangulo, int[][] mapa){
        int getX = rectangulo.getPunto1().getX();
        int getY = rectangulo.getPunto1().getY();

        if(rectangulo.isDePie()){
            rectangulo.setDePie(false);
            rectangulo.setHorizontal(false);
            if(comprobarRango(getX + 1, getY, getX + 2, getY, rectangulo, mapa)){
                rectangulo.setPunto1(new Punto(getX + 1, getY));
                //rectangulo.setPunto2(new Punto(getX + 2, getY));
                combinaciones.add("abajo");
                if(findPathDown(getX + 1, getY, mapa, rectangulo))
                    return true;

                rectangulo.setPunto1(new Punto(getX, getY));
                //rectangulo.setPunto2(new Punto(getX, getY));
                combinaciones.remove(combinaciones.size() - 1);
            }
            rectangulo.setDePie(true);
        } else {
            if(rectangulo.isHorizontal()){
                if(comprobarRango(getX + 1, getY, getX + 1, getY - 1, rectangulo, mapa)){
                    rectangulo.setPunto1(new Punto(getX + 1, getY));
                    //rectangulo.setPunto2(new Punto(getX + 1, getY - 1));
                    combinaciones.add("abajo");
                    if(findPathDown(getX + 1, getY, mapa, rectangulo))
                        return true;
                    rectangulo.setPunto1(new Punto(getX, getY));
                    //rectangulo.setPunto2(new Punto(getX, getY - 1));
                    combinaciones.remove(combinaciones.size() - 1);
                }
            } else {
                rectangulo.setDePie(true);
                if(comprobarRango(getX + 2, getY, getX + 2, getY, rectangulo, mapa)){
                    rectangulo.setPunto1(new Punto(getX + 2, getY));
                    //rectangulo.setPunto2(new Punto(getX + 2, getY));
                    combinaciones.add("abajo");
                    if(findPathDown(getX + 2, getY, mapa, rectangulo))
                        return true;
                    rectangulo.setPunto1(new Punto(getX, getY));
                    //rectangulo.setPunto2(new Punto(getX + 1, getY));
                    combinaciones.remove(combinaciones.size() - 1);
                }
                rectangulo.setDePie(false);
            }
        }

        return false;
    }

    private boolean comprobacionesIzquierda(Rectangulo rectangulo, int[][] mapa){
        int getX = rectangulo.getPunto1().getX();
        int getY = rectangulo.getPunto1().getY();

        if(rectangulo.isDePie()){
            rectangulo.setDePie(false);
            rectangulo.setHorizontal(true);
            if(comprobarRango(getX, getY - 1, getX, getY - 2, rectangulo, mapa)){
                rectangulo.setPunto1(new Punto(getX, getY - 1));
                //rectangulo.setPunto2(new Punto(getX, getY - 2));
                combinaciones.add("izquierda");
                if(findPathLeft(getX, getY - 1, mapa, rectangulo))
                    return true;
                rectangulo.setPunto1(new Punto(getX, getY));
                //rectangulo.setPunto2(new Punto(getX, getY));
                combinaciones.remove(combinaciones.size() - 1);
            }
            rectangulo.setDePie(true);
            rectangulo.setHorizontal(false);
        } else {
            if(rectangulo.isHorizontal()){
                rectangulo.setDePie(true);
                rectangulo.setHorizontal(false);
                if(comprobarRango(getX, getY - 2, getX, getY - 2, rectangulo, mapa)){
                    rectangulo.setPunto1(new Punto(getX, getY - 2));
                    //rectangulo.setPunto2(new Punto(getX, getY - 2));
                    combinaciones.add("izquierda");
                    if(findPathLeft(getX, getY - 2, mapa, rectangulo))
                        return true;
                    rectangulo.setPunto1(new Punto(getX, getY));
                    ///rectangulo.setPunto2(new Punto(getX, getY - 1));
                    combinaciones.remove(combinaciones.size() - 1);
                }
                rectangulo.setDePie(false);
                rectangulo.setHorizontal(true);
            } else {
                if(comprobarRango(getX, getY - 1, getX + 1, getY - 1, rectangulo, mapa)){
                    rectangulo.setPunto1(new Punto(getX, getY - 1));
                    //rectangulo.setPunto2(new Punto(getX + 1, getY - 1));
                    combinaciones.add("izquierda");
                    if(findPathLeft(getX, getY - 1, mapa, rectangulo))
                        return true;
                    rectangulo.setPunto1(new Punto(getX, getY));
                    //rectangulo.setPunto2(new Punto(getX + 1, getY));
                    combinaciones.remove(combinaciones.size() - 1);
                }
            }
        }
        return false;
    }

    private boolean comprobacionesDerecha(Rectangulo rectangulo, int[][] mapa){
        int getX = rectangulo.getPunto1().getX();
        int getY = rectangulo.getPunto1().getY();

        if(rectangulo.isDePie()){
            rectangulo.setDePie(false);
            rectangulo.setHorizontal(true);
            if(comprobarRango(getX, getY + 2, getX, getY + 1, rectangulo, mapa)){
                rectangulo.setPunto1(new Punto(getX, getY + 2));
                //rectangulo.setPunto2(new Punto(getX, getY + 1));
                combinaciones.add("derecha");
                if(findPathRight(getX, getY + 2, mapa, rectangulo)){
                    return true;
                }
                rectangulo.setPunto1(new Punto(getX, getY));
                //rectangulo.setPunto2(new Punto(getX, getY));
                combinaciones.remove(combinaciones.size() - 1);
            }
            rectangulo.setDePie(true);
            rectangulo.setHorizontal(false);
        } else {
            if(rectangulo.isHorizontal()){
                rectangulo.setDePie(true);
                rectangulo.setHorizontal(false);
                if(comprobarRango(getX, getY + 1, getX, getY + 1, rectangulo, mapa)){
                    rectangulo.setPunto1(new Punto(getX, getY + 1));
                    //rectangulo.setPunto2(new Punto(getX, getY + 1));
                    combinaciones.add("derecha");
                    if(findPathRight(getX, getY + 1, mapa, rectangulo))
                        return true;
                    rectangulo.setPunto1(new Punto(getX, getY));
                    //rectangulo.setPunto2(new Punto(getX, getY-1));
                    combinaciones.remove(combinaciones.size() - 1);
                }
                rectangulo.setDePie(false);
                rectangulo.setHorizontal(true);
            } else {
                if(comprobarRango(getX, getY + 1, getX + 1, getY + 1, rectangulo, mapa)){
                    rectangulo.setPunto1(new Punto(getX, getY + 1));
                    //rectangulo.setPunto2(new Punto(getX + 1, getY + 1));
                    combinaciones.add("derecha");
                    if(findPathRight(getX, getY + 1, mapa, rectangulo))
                        return true;
                    rectangulo.setPunto1(new Punto(getX, getY));
                    //rectangulo.setPunto2(new Punto(getX + 1, getY));
                    combinaciones.remove(combinaciones.size() - 1);
                }
            }
        }
        return false;
    }

    private boolean comprobacionesArriba(Rectangulo rectangulo, int[][] mapa){
        int getX = rectangulo.getPunto1().getX();
        int getY = rectangulo.getPunto1().getY();

        if(rectangulo.isDePie()){
            rectangulo.setDePie(false);
            rectangulo.setHorizontal(false);
            if(comprobarRango(getX - 2, getY, getX - 1, getY, rectangulo, mapa)){
                rectangulo.setPunto1(new Punto(getX - 2, getY));
                //rectangulo.setPunto2(new Punto(getX - 1, getY));
                rectangulo.setDePie(false);
                rectangulo.setHorizontal(false);
                combinaciones.add("Arriba");
                if(findPathUp(getX - 2, getY, mapa, rectangulo)){
                    return true;
                } else {
                    rectangulo.setPunto1(new Punto(getX, getY));
                    //rectangulo.setPunto2(new Punto(getX, getY));
                    combinaciones.remove(combinaciones.size() - 1);
                }
            }
            rectangulo.setDePie(true);
        } else {
            // * Caso si el rectangulo se encuentra tumbado horizontalmente
            if(rectangulo.isHorizontal()){
                if(comprobarRango(getX - 1, getY, getX - 1, getY - 1, rectangulo, mapa)){
                    rectangulo.setPunto1(new Punto(getX - 1, getY));
                    //rectangulo.setPunto2(new Punto(getX - 1, getY - 1));
                    combinaciones.add("Arriba");
                    if(findPathUp(getX - 1, getY,mapa, rectangulo)){
                        return true;
                    } else {
                        rectangulo.setPunto1(new Punto(getX, getY));
                        //rectangulo.setPunto2(new Punto(getX, getY - 1));
                        combinaciones.remove(combinaciones.size() - 1);
                    }
                }
            } else {
                rectangulo.setDePie(true);
                rectangulo.setHorizontal(false);
                // * Caso si el rectangulo se encuentra tumbado verticalmente
                if(comprobarRango(getX - 1, getY, getX - 1, getY, rectangulo, mapa)){
                    rectangulo.setPunto1(new Punto(getX - 1, getY));
                    //rectangulo.setPunto2(new Punto(getX - 1, getY));
                    combinaciones.add("Arriba");
                    if(findPathUp(getX - 1, getY, mapa, rectangulo)){
                        return true;
                    } else {
                        rectangulo.setPunto1(new Punto(getX, getY));
                        //rectangulo.setPunto2(new Punto(getX + 1, getY));
                        combinaciones.remove(combinaciones.size() - 1);
                    }
                }
                rectangulo.setDePie(false);
            }
        }
        return false;
    }

    private boolean findPath(int x, int y, int[][] mapa, Rectangulo rectangulo){
        if(x == endX && y == endY && rectangulo.isDePie())
            return true;

        // * Ahora tenemos que ir hacia todos los caminos posibles

        // * Camino hacia arriba
        if(comprobacionesArriba(rectangulo, mapa))
            return true;

        if(comprobacionesDerecha(rectangulo, mapa))
            return true;

        if(comprobacionesIzquierda(rectangulo, mapa))
            return true;

        if(comprobacionesAbajo(rectangulo, mapa))
            return true;

        return false;
    }

    public boolean findSolution(int[][] mapa, int x, int y, int endX, int endY){
        this.endX = endX;
        this.endY = endY;
        bucles = new int[mapa.length][mapa[0].length];
        combinaciones = new ArrayList<>();
        Rectangulo rectangulo = new Rectangulo(x, y);

        if(!findPath(x, y, mapa, rectangulo)){
            System.out.println("No existe ninguna solucion");
            return false;
        } else {
            printSolution(mapa);
            return true;
        }
    }
}
