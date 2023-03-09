package Bloxorz;

public class Main {
    public static void main(String[] args) {
        Bloxorz bloxorz = new Bloxorz();

        int[][] mapa = {{1,0,0,1},
                        {1,1,1,1},
                        {1,1,1,1},
                        {0,1,0,1}};

        int[][] mapa2 = {{1,1,1,0},
                        {0,1,1,0},
                        {1,1,1,1},
                        {0,1,1,1}};

        int[][] mapa3 = {{1,1,1,1}};

        int[][] mapa4 = {{0,1,1,1},
                         {1,1,1,1},
                         {0,1,1,1},
                         {0,1,1,1}};
        int[][] mapa5 = {{0,0,1,1,0},
                         {1,1,1,1,1},
                        {0,1,1,1,0},
                        {0,1,1,1,0}};

        int[][] mapa6 = {{1,0,1,1,0},
                        {1,1,1,1,1},
                        {1,1,1,1,1},
                        {0,1,1,1,0}};

        int[][] mapa7 = {{1,1,1,1,0},
                        {1,1,1,1,1},
                        {1,0,1,1,1},
                        {0,0,0,1,0}};

        int[][] mapa8 = {{1,1,1,0},
                        {1,1,1,1},
                        {1,0,0,0},
                        {1,1,1,1},
                        {1,1,1,0},
                        {1,0,0,0}};

        int[][] mapa9 = {{1,1,1,1,1},
                        {1,1,0,0,0},
                        {1,1,1,1,1},
                        {0,0,0,1,1},
                        {0,0,1,1,1}};

        int[][] mapa10 = {{1,1,1,0,0,0,0},
                          {1,1,1,1,0,0,0},
                          {1,1,1,1,0,0,1},
                          {0,0,0,1,0,0,1},
                          {0,0,0,1,0,1,1},
                          {0,1,1,1,1,1,1}};

        bloxorz.encontrarSolucion(mapa10, 2,6, 0, 0);
        System.out.println(bloxorz.getCombinaciones());
        bloxorz.encontrarSolucion(mapa9, 4,2, 0, 2);
        System.out.println(bloxorz.getCombinaciones());
        bloxorz.encontrarSolucion(mapa8, 3,3, 1, 3);
        System.out.println(bloxorz.getCombinaciones());
        bloxorz.encontrarSolucion(mapa7, 2,0, 2, 3);
        System.out.println(bloxorz.getCombinaciones());
        bloxorz.encontrarSolucion(mapa, 0,0, 3, 3);
        System.out.println(bloxorz.getCombinaciones());
        bloxorz.encontrarSolucion(mapa6, 1,0, 2, 0);
        System.out.println(bloxorz.getCombinaciones());
        bloxorz.encontrarSolucion(mapa6, 0,0, 3, 1);
        System.out.println(bloxorz.getCombinaciones());
        bloxorz.encontrarSolucion(mapa5, 1,0, 1, 4);
        System.out.println(bloxorz.getCombinaciones());
        bloxorz.encontrarSolucion(mapa4, 1,0, 0, 3);
        System.out.println(bloxorz.getCombinaciones());
        bloxorz.encontrarSolucion(mapa2, 0,0, 3, 3);
        System.out.println(bloxorz.getCombinaciones());
        bloxorz.encontrarSolucion(mapa3, 0,0, 0, 3);
        System.out.println(bloxorz.getCombinaciones());

    }
}
