package com.basicClasses;

import javafx.util.Pair;

import java.util.ArrayList;
import java.util.List;

public class Grid {
    private List<Pair<Integer,Integer>> grid;
    private int cost;
    private double h;
    private double f;
    Grid(List<Pair<Integer,Integer>> g , int c, double hill){
        grid = new ArrayList<>();
        this.cost=c;
        this.grid = g;
        this.h=hill;
        f= (double)cost +h;
    }
    Grid(List<Pair<Integer,Integer>> g , int c){
        grid = new ArrayList<>();
        this.cost=c;
        this.grid = g;
        this.h=0;
        f= (double)cost +h;
    }
    public List<Pair<Integer,Integer>> getGrid(){
        return grid;
    }
    public int getCost(){
        return cost;
    }
    public double getH(){
        return h;
    }
    public double getF(){
        return f;
    }
}
