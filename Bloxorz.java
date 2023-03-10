package Bloxorz;

import java.util.ArrayList;
import java.util.Arrays;

public class Bloxorz {
    // * Posicion fila destino
    private int endX;
    // * Posición columna destino
    private int endY;
    // * Matriz donde guardo las posiciones visitadas por el rectángulo para luego prevenir bucles
    private int[][] bucles;
    // * Lista donde guardo los movimientos realizados para llegar al destino
    private ArrayList<String> combinaciones;
    public ArrayList<String> getCombinaciones() {
        return combinaciones;
    }

    public Bloxorz(){
        combinaciones = new ArrayList<>();
    }

    /**
     *
     * @param x Posición actual de la fila de la base
     * @param y Posición actual de la columna de la base
     * @param x2 Posición actual de la fila del cubo al lado de la base
     * @param y2 Posición actual de la columna del cubo al lado de la base
     * @param rectangulo Objeto rectángulo para comprobar su estado
     * @param mapa Mapa a resolver, se usa para comprobar si podemos ir por un camino
     * @return Devuelve true si las posiciones no están fuera de rango y se encuentran dentro del camino del mapa
     */
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

    /**
     *  Si existe una solución, muestra el mapa
     * @param mapa Mapa a resolver
     */
    private void mostrarMapa(int[][] mapa){
        for(int i = 0; i < mapa.length; i++){
            System.out.println(Arrays.toString(mapa[i]));
        }
    }

    /**
     *
     * @param x Posición x de la base
     * @param y Posición y de la base
     * @param mapa Mapa a resolver
     * @param rectangulo Objeto rectángulo para comprobar su estado
     * @return Devuelve true si ha encontrado el camino a la solución, false en caso contrario
     */
    private boolean encontrarCaminoAbajo(int x, int y, int[][] mapa, Rectangulo rectangulo){
        // * Si está de pie y sus coordenadas de la base coinciden con las posiciones destino, ha encontrado la solución y devuelve true
        if(rectangulo.isDePie() && x == endX && y == endY)
            return true;

        // * A la hora de buscar la solución pueden ocurrir "bucles", es decir, que el rectángulo se encuentre en una posición en la cual ya a estado
        // * Por ello, aquí comprobamos si se encuentra en una posición ya visitada
        if(rectangulo.isDePie() && bucles[x][y] == 1) {
            return false;
        }
        // * Marcamos las posiciones donde se encuentra de pie para comprobar si luego surgen bucles
        if(rectangulo.isDePie()){
            bucles[x][y] = 1;
        }
        // * Comprueba que haya camino en todas las direcciones menos por arriba que es por donde ha venido
        if(comprobacionesAbajo(rectangulo, mapa))
            return true;

        if(comprobacionesDerecha(rectangulo, mapa))
            return true;

        if(comprobacionesIzquierda(rectangulo, mapa))
            return true;


        return false;
    }
    // * Las siguientes funciones encontrarCaminoIzquierda, encontrarCaminoArriba y encontrarCaminoDerecha son iguales que la anterior,
    // * lo único que cambia son las comprobaciones que hace al final
    private boolean encontrarCaminoIzquierda(int x, int y, int[][] mapa, Rectangulo rectangulo){
        if(rectangulo.isDePie() && x == endX && y == endY)
            return true;

        if(rectangulo.isDePie() && bucles[x][y] == 1) {
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

    private boolean encontrarCaminoArriba(int x, int y, int[][] mapa, Rectangulo rectangulo){
        if(x == endX && y == endY && rectangulo.isDePie())
            return true;

        if(rectangulo.isDePie() && bucles[x][y] == 1) {
            return false;
        }
        if(rectangulo.isDePie()){
            bucles[x][y] = 1;
        }

        if(comprobacionesArriba(rectangulo, mapa)){
            return true;
        }

        if(comprobacionesDerecha(rectangulo, mapa)){
            return true;
        }

        if(comprobacionesIzquierda(rectangulo, mapa))
            return true;

        return false;
    }

    private boolean encontrarCaminoDerecha(int x, int y, int[][] mapa, Rectangulo rectangulo){
        if(x == endX && y == endY && rectangulo.isDePie())
            return true;

        if(rectangulo.isDePie() && bucles[x][y] == 1) {
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

    /**
     *  Este método llama recursivamente a encontrarCaminoAbajo, primero comprueba que el rectángulo se pueda mover hacia abajo
     *  dependiendo de su posición (de pie, tumbado horizontalmente o tumbado verticalmente)
     *  si puede moverse actualizamos su coordenada base y volvemos a llamar encontrarCaminoAbajo.
     *  En el caso de que la llamada recursiva devuelva false (No ha encontrado ningún camino) colocamos el rectángulo
     *  en su posición original y eliminamos de la lista comprobaciones el movimiento realizado (Backtracking) y devolvemos false.
     * @param rectangulo Objeto rectángulo para saber en qué posición se encuentra el rectángulo
     * @param mapa Mapa a resolver
     * @return Devuelve true si ha encontrado un camino, false en caso contrario
     */
    private boolean comprobacionesAbajo(Rectangulo rectangulo, int[][] mapa){
        int getX = rectangulo.getPunto1().getX();
        int getY = rectangulo.getPunto1().getY();

        if(rectangulo.isDePie()){
            rectangulo.setDePie(false);
            rectangulo.setHorizontal(false);
            if(comprobarRango(getX + 1, getY, getX + 2, getY, rectangulo, mapa)){
                rectangulo.setPunto1(new Punto(getX + 1, getY));
                combinaciones.add("abajo");
                if(encontrarCaminoAbajo(getX + 1, getY, mapa, rectangulo))
                    return true;

                rectangulo.setPunto1(new Punto(getX, getY));
                combinaciones.remove(combinaciones.size() - 1);
            }
            rectangulo.setDePie(true);
        } else {
            if(rectangulo.isHorizontal()){
                if(comprobarRango(getX + 1, getY, getX + 1, getY - 1, rectangulo, mapa)){
                    rectangulo.setPunto1(new Punto(getX + 1, getY));
                    combinaciones.add("abajo");
                    if(encontrarCaminoAbajo(getX + 1, getY, mapa, rectangulo))
                        return true;
                    rectangulo.setPunto1(new Punto(getX, getY));
                    combinaciones.remove(combinaciones.size() - 1);
                }
            } else {
                rectangulo.setDePie(true);
                if(comprobarRango(getX + 2, getY, getX + 2, getY, rectangulo, mapa)){
                    rectangulo.setPunto1(new Punto(getX + 2, getY));
                    combinaciones.add("abajo");
                    if(encontrarCaminoAbajo(getX + 2, getY, mapa, rectangulo))
                        return true;
                    rectangulo.setPunto1(new Punto(getX, getY));
                    combinaciones.remove(combinaciones.size() - 1);
                }
                rectangulo.setDePie(false);
            }
        }

        return false;
    }

    /**
     *  Este método llama recursivamente a encontrarCaminoIzquierda, primero comprueba que el rectángulo se pueda mover hacia la izquierda
     *  dependiendo de su posición, si puede moverse actualizamos su coordenada base y volvemos a llamar encontrarCaminoIzquierda.
     *  En el caso de que la llamada recursiva devuelva false (No ha encontrado ningún camino) colocamos el rectángulo
     *  en su posición original y eliminamos de la lista comprobaciones el movimiento realizado (Backtracking) y devolvemos false.
     * @param rectangulo Objeto rectángulo para saber en qué posición se encuentra el rectángulo
     * @param mapa Mapa a devolver
     * @return Devuelve true si ha encontrado un camino, false en caso contrario
     */
    private boolean comprobacionesIzquierda(Rectangulo rectangulo, int[][] mapa){
        int getX = rectangulo.getPunto1().getX();
        int getY = rectangulo.getPunto1().getY();

        if(rectangulo.isDePie()){
            rectangulo.setDePie(false);
            rectangulo.setHorizontal(true);
            if(comprobarRango(getX, getY - 1, getX, getY - 2, rectangulo, mapa)){
                rectangulo.setPunto1(new Punto(getX, getY - 1));
                combinaciones.add("izquierda");
                if(encontrarCaminoIzquierda(getX, getY - 1, mapa, rectangulo))
                    return true;
                rectangulo.setPunto1(new Punto(getX, getY));
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
                    combinaciones.add("izquierda");
                    if(encontrarCaminoIzquierda(getX, getY - 2, mapa, rectangulo))
                        return true;
                    rectangulo.setPunto1(new Punto(getX, getY));
                    combinaciones.remove(combinaciones.size() - 1);
                }
                rectangulo.setDePie(false);
                rectangulo.setHorizontal(true);
            } else {
                if(comprobarRango(getX, getY - 1, getX + 1, getY - 1, rectangulo, mapa)){
                    rectangulo.setPunto1(new Punto(getX, getY - 1));
                    combinaciones.add("izquierda");
                    if(encontrarCaminoIzquierda(getX, getY - 1, mapa, rectangulo))
                        return true;
                    rectangulo.setPunto1(new Punto(getX, getY));
                    combinaciones.remove(combinaciones.size() - 1);
                }
            }
        }
        return false;
    }
    /**
     *  Este método llama recursivamente a encontrarCaminoDerecha, primero comprueba que el rectángulo se pueda mover hacia la derecha
     *  dependiendo de su posición, si puede moverse actualizamos su coordenada base y volvemos a llamar encontrarCaminoDerecha.
     *  En el caso de que la llamada recursiva devuelva false (No ha encontrado ningún camino) colocamos el rectángulo
     *  en su posición original y eliminamos de la lista comprobaciones el movimiento realizado (Backtracking) y devolvemos false.
     * @param rectangulo Objeto rectángulo para saber en qué posición se encuentra el rectángulo
     * @param mapa Mapa a devolver
     * @return Devuelve true si ha encontrado un camino, false en caso contrario
     */
    private boolean comprobacionesDerecha(Rectangulo rectangulo, int[][] mapa){
        int getX = rectangulo.getPunto1().getX();
        int getY = rectangulo.getPunto1().getY();

        if(rectangulo.isDePie()){
            rectangulo.setDePie(false);
            rectangulo.setHorizontal(true);
            if(comprobarRango(getX, getY + 2, getX, getY + 1, rectangulo, mapa)){
                rectangulo.setPunto1(new Punto(getX, getY + 2));
                combinaciones.add("derecha");
                if(encontrarCaminoDerecha(getX, getY + 2, mapa, rectangulo)){
                    return true;
                }
                rectangulo.setPunto1(new Punto(getX, getY));
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
                    combinaciones.add("derecha");
                    if(encontrarCaminoDerecha(getX, getY + 1, mapa, rectangulo))
                        return true;
                    rectangulo.setPunto1(new Punto(getX, getY));
                    combinaciones.remove(combinaciones.size() - 1);
                }
                rectangulo.setDePie(false);
                rectangulo.setHorizontal(true);
            } else {
                if(comprobarRango(getX, getY + 1, getX + 1, getY + 1, rectangulo, mapa)){
                    rectangulo.setPunto1(new Punto(getX, getY + 1));
                    combinaciones.add("derecha");
                    if(encontrarCaminoDerecha(getX, getY + 1, mapa, rectangulo))
                        return true;
                    rectangulo.setPunto1(new Punto(getX, getY));
                    combinaciones.remove(combinaciones.size() - 1);
                }
            }
        }
        return false;
    }
    /**
     *  Este método llama recursivamente a encontrarCaminoArriba, primero comprueba que el rectángulo se pueda mover hacia arriba
     *  dependiendo de su posición, si puede moverse actualizamos su coordenada base y volvemos a llamar encontrarCaminoArriba.
     *  En el caso de que la llamada recursiva devuelva false (No ha encontrado ningún camino) colocamos el rectángulo
     *  en su posición original y eliminamos de la lista comprobaciones el movimiento realizado (Backtracking) y devolvemos false.
     * @param rectangulo Objeto rectángulo para saber en qué posición se encuentra el rectángulo
     * @param mapa Mapa a devolver
     * @return Devuelve true si ha encontrado un camino, false en caso contrario
     */
    private boolean comprobacionesArriba(Rectangulo rectangulo, int[][] mapa){
        int getX = rectangulo.getPunto1().getX();
        int getY = rectangulo.getPunto1().getY();

        if(rectangulo.isDePie()){
            rectangulo.setDePie(false);
            rectangulo.setHorizontal(false);
            if(comprobarRango(getX - 2, getY, getX - 1, getY, rectangulo, mapa)){
                rectangulo.setPunto1(new Punto(getX - 2, getY));
                rectangulo.setDePie(false);
                rectangulo.setHorizontal(false);
                combinaciones.add("Arriba");
                if(encontrarCaminoArriba(getX - 2, getY, mapa, rectangulo))
                    return true;
                rectangulo.setPunto1(new Punto(getX, getY));
                combinaciones.remove(combinaciones.size() - 1);
            }
            rectangulo.setDePie(true);
        } else {
            if(rectangulo.isHorizontal()){
                if(comprobarRango(getX - 1, getY, getX - 1, getY - 1, rectangulo, mapa)){
                    rectangulo.setPunto1(new Punto(getX - 1, getY));
                    combinaciones.add("Arriba");
                    if(encontrarCaminoArriba(getX - 1, getY,mapa, rectangulo)){
                        return true;
                    } else {
                        rectangulo.setPunto1(new Punto(getX, getY));
                        combinaciones.remove(combinaciones.size() - 1);
                    }
                }
            } else {
                rectangulo.setDePie(true);
                rectangulo.setHorizontal(false);
                // * Caso si el rectangulo se encuentra tumbado verticalmente
                if(comprobarRango(getX - 1, getY, getX - 1, getY, rectangulo, mapa)){
                    rectangulo.setPunto1(new Punto(getX - 1, getY));
                    combinaciones.add("Arriba");
                    if(encontrarCaminoArriba(getX - 1, getY, mapa, rectangulo)){
                        return true;
                    } else {
                        rectangulo.setPunto1(new Punto(getX, getY));
                        combinaciones.remove(combinaciones.size() - 1);
                    }
                }
                rectangulo.setDePie(false);
            }
        }
        return false;
    }

    /**
     * * Este es el inicio de la búsqueda, como no se ha hecho ningún movimiento previo, comprobamos todos los
     * * posibles movimientos
     * @param x Fila donde se encuentra la base
     * @param y Columna donde se encuentra la base
     * @param mapa Mapa a resolver
     * @param rectangulo Objeto rectángulo para comprobar su posición
     * @return Devuelve true si ha conseguido encontrar la solución, false en caso contrario
     */
    private boolean encontrarCamino(int x, int y, int[][] mapa, Rectangulo rectangulo){
        if(x == endX && y == endY && rectangulo.isDePie())
            return true;

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

    public boolean encontrarSolucion(int[][] mapa, int x, int y, int endX, int endY){
        this.endX = endX;
        this.endY = endY;
        bucles = new int[mapa.length][mapa[0].length];
        combinaciones = new ArrayList<>();
        Rectangulo rectangulo = new Rectangulo(x, y);

        if(!encontrarCamino(x, y, mapa, rectangulo)){
            System.out.println("No existe ninguna solucion");
            return false;
        } else {
            mostrarMapa(mapa);
            return true;
        }
    }
}