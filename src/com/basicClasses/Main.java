package com.basicClasses;

import javafx.util.Pair;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        Scanner scanner = new Scanner(System.in);
        Game g = new Game(7,6);

        //Fill the grid
        g.get_the_grid(read_grid(7 , 6));
        g.fill_the_grid();
        System.out.print("Starting Grid: ");
        g.print_grid();
        System.out.println('\n');
        //Play Algorithms
        System.out.print("The Solution! :");
        g.aStarSolution(g.get_bridges_parts());

        // Play Zone .
        /*int x_coord, y_coord ;
        char dir= ' ';
        System.out.println();
        while(dir !='e'){
            System.out.println("Enter the Coordinates as: ");
            x_coord = scanner.nextInt();
            y_coord = scanner.nextInt();
            dir = scanner.next().charAt(0);
            //graph.add_grid(g.grid_clone());
            char u = g.get_cell(x_coord,y_coord);
            g.make_complete_move(x_coord,y_coord,dir);
            g.call_dfs();
            g.print_grid();
        }*/
    }

    static  public int[][] read_grid(int x , int y ) throws FileNotFoundException {
        Scanner sc = new Scanner(new BufferedReader(new FileReader("D:\\grid.txt")));
        int [][] color_grid = new int[x][y];
        while(sc.hasNextLine()) {
            for (int i=0; i<color_grid.length; i++) {
                String[] line = sc.nextLine().trim().split(" ");
                for (int j=0; j<line.length; j++) {
                    color_grid[i][j] =  Integer.parseInt(line[j]);
                }
            }
        }
        return color_grid;
    }
}
