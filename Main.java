package Bloxorz;

import java.util.ArrayList;

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

        int[][] mapa8= {{1,1,1,0},
                        {1,1,1,1},
                        {1,0,0,0},
                        {1,1,1,1},
                        {1,1,1,0},
                        {1,0,0,0}};

        long start = System.nanoTime();
        bloxorz.findSolution(mapa8, 3,3, 1, 3);
        long time = System.nanoTime() - start;
        System.out.println(time);
        System.out.println(bloxorz.getCombinaciones());
        bloxorz.findSolution(mapa7, 2,0, 2, 3);
        System.out.println(bloxorz.getCombinaciones());
        bloxorz.findSolution(mapa, 0,0, 3, 3);
        System.out.println(bloxorz.getCombinaciones());
        bloxorz.findSolution(mapa6, 1,0, 2, 0);
        System.out.println(bloxorz.getCombinaciones());
        bloxorz.findSolution(mapa6, 0,0, 3, 1);
        System.out.println(bloxorz.getCombinaciones());
        bloxorz.findSolution(mapa5, 1,0, 1, 4);
        System.out.println(bloxorz.getCombinaciones());
        bloxorz.findSolution(mapa4, 1,0, 0, 3);
        System.out.println(bloxorz.getCombinaciones());
        bloxorz.findSolution(mapa2, 0,0, 3, 3);
        System.out.println(bloxorz.getCombinaciones());
        bloxorz.findSolution(mapa3, 0,0, 0, 3);
        System.out.println(bloxorz.getCombinaciones());

    }
}
