package com.basicClasses;

import javafx.util.Pair;
import java.util.*;

public class Game {
    private char [][] grid ;
    private int [][] color_grid;
    private int x_rang , y_rang;
    private boolean vis[][];
    List<Pair<Integer , Integer>> list ;
    List<Pair<Integer , Integer>> optimalRes ;
    int[] ar= {1 ,0 ,-1 ,0 };
    int[] ac= {0 ,1 ,0 ,-1 };

    Game(int x, int y){
        this.x_rang = x;
        this.y_rang = y;
        grid = new char[x][y];
        color_grid = new int[x][y];
        for(int i =0;i<x_rang;i++){
            for(int j =0;j<y_rang;j++){
                grid[i][j]='_';
                color_grid[i][j]= 0;
            }
        }
        list = new ArrayList<>();
        vis = new boolean[x_rang][y_rang];
    }

    public void clean_grid(){
        for(int i=0;i<x_rang;i++){
            for(int j=0;j<y_rang;j++){
                grid[i][j]=0;
            }
        }
    }
    public char get_cell(int x,int y){
        return grid[x][y];
    }
    public void get_the_grid(int[][] gr){
        this.color_grid = gr;
    }
    public int[][] grid_clone(){
        return color_grid;
    }

    public List<Pair<Integer,Integer>> get_bridges_parts(){
        Set<Integer> set = new HashSet<>();
        List<Pair<Integer,Integer>> listOfParts=new ArrayList<>();
        for(int i=0;i<x_rang;i++) {
            for (int j = 0; j < y_rang; j++) {
                if(check_Bridge(color_grid[i][j] ) || check_Grass(color_grid[i][j])){
                    set.add(color_grid[i][j]);
                }
            }
        }
        for(int i=0;i<x_rang;i++) {
            for (int j = 0; j < y_rang; j++) {
                if((check_Bridge(color_grid[i][j] ) || check_Grass(color_grid[i][j]))&&set.contains(color_grid[i][j] )){
                    listOfParts.add(new Pair(i,j));
                    set.remove(color_grid[i][j]);
                }
            }
        }
        return listOfParts;
        }
    public List<Pair<Integer,Integer>> initCostsBegin(List<Pair<Integer,Integer>> list){
        List<Pair<Integer,Integer>> costs = new ArrayList<>();
        int cost=0;
        for(int c=0;c<list.size();c++){
            int curValue = color_grid[list.get(c).getKey()][list.get(c).getValue()];
            for(int i=0;i<x_rang;i++){
                for(int j=0;j<y_rang;j++){
                    if(curValue==color_grid[i][j]){
                        cost++;
                    }
                }
            }
            costs.add(new Pair(curValue,1000-cost));
            cost=0;
        }
        return costs;
    }
    public int getNewCost(List<Pair<Integer,Integer>> list1 , List<Pair<Integer,Integer>> list2 , List<Pair<Integer,Integer>>costs){
        int helpValue=0;
        for(int i =0;i<list1.size();i++){
            if((list1.get(i).getKey() == list2.get(i).getKey()) || (list1.get(i).getValue() != list2.get(i).getValue())){
                helpValue= color_grid[list2.get(i).getKey()][list2.get(i).getValue()];
            }
        }
        return addCost(helpValue,costs);
     }
     public int addCost(int value,List<Pair<Integer,Integer>> list){
        for(int i=0;i<list.size();i++){
            if(value==list.get(i).getKey()){
                return list.get(i).getValue();
            }
        }
        return 0;
     }
     public int getStateCost(List<Pair<Integer,Integer>> list){
        int ret=0;
        for(int i=0;i<list.size();i++){
            ret+=list.get(i).getValue();
        }
        return ret;
     }
    public List<Pair<Pair<Integer,Integer>,Character>> findAllMoves(List<Pair<Integer,Integer>> list){
        List<Pair<Pair<Integer,Integer>,Character>>moves = new ArrayList<>();
        for(int i=0;i<list.size();i++){
            if(make_check(list.get(i).getKey(),list.get(i).getValue(),'r')){
                moves.add(new Pair(new Pair(list.get(i).getKey(),list.get(i).getValue()),'r'));
            }
            if(make_check(list.get(i).getKey(),list.get(i).getValue(),'l')){
                moves.add(new Pair(new Pair(list.get(i).getKey(),list.get(i).getValue()),'l'));
            }
            if(make_check(list.get(i).getKey(),list.get(i).getValue(),'u')){
                moves.add(new Pair(new Pair(list.get(i).getKey(),list.get(i).getValue()),'u'));
            }
            if(make_check(list.get(i).getKey(),list.get(i).getValue(),'d')){
                moves.add(new Pair(new Pair(list.get(i).getKey(),list.get(i).getValue()),'d'));
            }
        }
        return moves;
    }
    public boolean check_Bridge(int val){
        if(val>0)
            return true;
        return false;
    }
    public boolean check_Grass(int val){
        if(val<0)
            return true;
        return false;
    }
    public void fill_the_grid(){
        for(int i=0;i<x_rang;i++){
            for(int j=0;j<y_rang;j++){
                if(color_grid[i][j]== -9)
                    grid[i][j]='R';
                else if(check_Bridge(color_grid[i][j]))
                    grid[i][j]='B';
                else if(check_Grass(color_grid[i][j]))
                    grid[i][j]='G';
                else
                    grid[i][j]='_';
            }
        }
    }
    public void print_grid(){
        System.out.println("\n");
        for(int i =0;i<y_rang;i++)
            System.out.print(i + " ");

        for(int i =0;i<x_rang;i++){
            System.out.println();
            for(int j =0;j<y_rang;j++){
                /*if(grid[i][j]=='R')
                    System.out.print("\033[47m");
                else if(grid[i][j]=='_')
                    System.out.print("\033[46m");
                else if(grid[i][j]=='B')
                    System.out.print("\033[41m");
                else if(grid[i][j]=='G')
                    System.out.print("\033[42m");
*/
                System.out.print(grid[i][j] + " ");

            }
        }
    }
    public void print_back_grid(){
        for(int i =0;i<y_rang;i++)
            System.out.print(i + " ");

        for(int i =0;i<x_rang;i++){
            System.out.println(": " + (i-1) );
            for(int j =0;j<y_rang;j++){
                System.out.print(color_grid[i][j] + " ");
            }
        }
    }
    public List<Pair<Integer,Integer>> block(int x , int y){
        int current = color_grid[x][y];
        List<Pair<Integer,Integer>> cells = new ArrayList<>();
        for(int i=0;i<x_rang;i++){
            for(int j=0;j<y_rang;j++){
                if(current == color_grid[i][j]){
                    cells.add(new Pair(i,j));
                }
            }
        }
        return cells;
    }
    public void make_move(int x ,int y , char d){
        int current = color_grid[x][y];
        List<Pair<Integer,Integer>> cells = new ArrayList<>();
        List<Pair<Integer,Integer>> e_cells = new ArrayList<>();
        cells.addAll(block(x,y));
        boolean con = false;
        con =make_check(x,y,d);
        if(con== false)
            return;
        if(d=='r'){
            for(int i=0;i< cells.size();i++){
                e_cells.add(new Pair(cells.get(i).getKey(),cells.get(i).getValue()+1));
            }
            for(int i=0;i<x_rang;i++){
                for(int j=0;j<y_rang;j++){
                    if(current == color_grid[i][j] ){
                        color_grid[i][j]=0;
                    }
                }
            }
            for(int i=0;i<e_cells.size();i++){
                color_grid[e_cells.get(i).getKey()][e_cells.get(i).getValue()]=current;
            }
        }
        else if(d=='l'){
            for(int i=0;i< cells.size();i++){
                e_cells.add(new Pair(cells.get(i).getKey(),cells.get(i).getValue()-1));
            }
            for(int i=0;i<x_rang;i++){
                for(int j=0;j<y_rang;j++){
                    if(current == color_grid[i][j] ){
                        color_grid[i][j]=0;
                    }
                }
            }
            for(int i=0;i<e_cells.size();i++){
                color_grid[e_cells.get(i).getKey()][e_cells.get(i).getValue()]=current;
            }
        }
        else if(d=='u'){
            for(int i=0;i< cells.size();i++){
                e_cells.add(new Pair(cells.get(i).getKey()-1,cells.get(i).getValue()));
            }
            for(int i=0;i<x_rang;i++){
                for(int j=0;j<y_rang;j++){
                    if(current == color_grid[i][j] ){
                        color_grid[i][j]=0;
                    }
                }
            }
            for(int i=0;i<e_cells.size();i++){
                color_grid[e_cells.get(i).getKey()][e_cells.get(i).getValue()]=current;
            }
        }
        else if(d=='d'){
            for(int i=0;i< cells.size();i++){
                e_cells.add(new Pair(cells.get(i).getKey()+1,cells.get(i).getValue()));
            }
            for(int i=0;i<x_rang;i++){
                for(int j=0;j<y_rang;j++){
                    if(current == color_grid[i][j] ){
                        color_grid[i][j]=0;
                    }
                }
            }
            for(int i=0;i<e_cells.size();i++){
                color_grid[e_cells.get(i).getKey()][e_cells.get(i).getValue()]=current;
            }
        }
        clean_grid();
        fill_the_grid();

    }
    public boolean make_check(int x , int y , char d){
        if(color_grid[x][y]==-9)
            return false;
        List<Pair<Integer,Integer>> cells = new ArrayList<>();
        List<Pair<Integer,Integer>> e_cells = new ArrayList<>();
        cells.addAll((block(x,y)));
        int current = color_grid[x][y];
        if(d=='r'){
            for(int i=0;i< cells.size();i++){
                e_cells.add(new Pair(cells.get(i).getKey(),cells.get(i).getValue()+1));
                if(cells.get(i).getValue()+1 >= y_rang)
                    return false;
            }
            for(int i=0;i<e_cells.size();i++){
                if((color_grid[e_cells.get(i).getKey()][e_cells.get(i).getValue()]!=0) ){
                    if((color_grid[e_cells.get(i).getKey()][e_cells.get(i).getValue()]!=current))
                        return false;
                }
            }
        }
        else if(d=='l'){
            for(int i=0;i< cells.size();i++){
                e_cells.add(new Pair(cells.get(i).getKey(),cells.get(i).getValue()-1));
                if(cells.get(i).getValue()-1 <0 )
                    return false;
            }
            for(int i=0;i<e_cells.size();i++){
                if((color_grid[e_cells.get(i).getKey()][e_cells.get(i).getValue()]!=0) ){
                    if((color_grid[e_cells.get(i).getKey()][e_cells.get(i).getValue()]!=current))
                        return false;
                }
            }
        }
        else if(d=='u'){
            for(int i=0;i< cells.size();i++){
                e_cells.add(new Pair(cells.get(i).getKey()-1,cells.get(i).getValue()));
                if(cells.get(i).getKey()-1< 0)
                    return false;
            }
            for(int i=0;i<e_cells.size();i++){
                if((color_grid[e_cells.get(i).getKey()][e_cells.get(i).getValue()]!=0) ){
                    if((color_grid[e_cells.get(i).getKey()][e_cells.get(i).getValue()]!=current))
                        return false;
                }
            }
        }
        else if(d=='d'){
            for(int i=0;i< cells.size();i++){
                e_cells.add(new Pair(cells.get(i).getKey()+1,cells.get(i).getValue()));
                if(cells.get(i).getKey()+1>= x_rang)
                    return false;
            }
            for(int i=0;i<e_cells.size();i++){
                if((color_grid[e_cells.get(i).getKey()][e_cells.get(i).getValue()]!=0) ){
                    if((color_grid[e_cells.get(i).getKey()][e_cells.get(i).getValue()]!=current))
                        return false;
                }
            }
        }
        return true;
    }
    public void make_complete_move(int x , int y ,char d){
        make_move(x,y,d);
        boolean make_another_move = true;
        if (d == 'r') {
            while (make_another_move) {
                y++;
                boolean test = make_check(x, y, 'r');
                if (test == true)
                    make_move(x, y, 'r');
                make_another_move = test;
            }
        } else if (d == 'l') {
            while (make_another_move) {
                y--;
                boolean test = make_check(x, y, 'l');
                if (test == true)
                    make_move(x, y, 'l');
                make_another_move = test;
            }
        } else if (d == 'u') {
            while (make_another_move) {
                x--;
                boolean test = make_check(x, y, 'u');
                if (test == true)
                    make_move(x, y, 'u');
                make_another_move = test;
            }
        } else if (d == 'd') {
            while (make_another_move) {
                x++;
                boolean test = make_check(x, y, 'd');
                if (test == true)
                    make_move(x, y, 'd');
                make_another_move = test;
            }
        }
    }

    public boolean valid(int x , int y ){
        return x < x_rang && y< y_rang && y>=0 &&x>=0;
    }
    public boolean dfs(int x , int y ){
        if(x == x_rang -1)
            return true;
        vis[x][y]= true;
        for(int i =0;i< 4;i++){
            int nx = x+ ar[i];
            int ny = y + ac[i];
            if(valid(nx,ny))
                if(!vis[nx][ny])
                    if(grid[nx][ny]== 'B')
                        if(dfs(nx,ny))
                            return true;
        }
        return false;
    }

    HashSet<List<Pair<Integer,Integer>>> hSet = new HashSet<>();
    public void dfsSolution(List <Pair<Integer,Integer>>listOfBridegs){
        if(hSet.contains(listOfBridegs))
            return;
        hSet.add(listOfBridegs);
        if(isFinal()){
            storeLastRes(listOfBridegs);
            return;
        }
        List<Pair<Pair<Integer,Integer>,Character>>newMove = new ArrayList<>();
        newMove = findAllMoves(listOfBridegs);
        for(int i=0;i<newMove.size();i++){
            make_complete_move(newMove.get(i).getKey().getKey(),newMove.get(i).getKey().getValue(),newMove.get(i).getValue());
            dfsSolution(get_bridges_parts());
        }
    }
    class GridComparator implements Comparator<Grid>{

        @Override
        public int compare(Grid o1, Grid o2) {
            if(o1.getCost()<o2.getCost())
                return 1;
            else if(o1.getCost()>o2.getCost())
                return -1;
            return 0;
        }
    }
    class GridComparatorWithH implements Comparator<Grid>{

        @Override
        public int compare(Grid o1, Grid o2) {
            if(o1.getF()<o2.getF())
                return 1;
            else if(o1.getF()>o2.getF())
                return -1;
            return 0;
        }
    }
    public void ucsS(List<Pair<Integer,Integer>> listOfBridges,PriorityQueue<Grid> pq,List<Grid> solutions){
        List<Pair<Integer,Integer>> initCosts,nListOfBirdges;
        int nCost=0,stateCounter=1;
        initCosts = initCostsBegin(listOfBridges);
        nCost += getStateCost(initCosts);
        if(hSet.contains((listOfBridges)))
            return;
        hSet.add(listOfBridges);
        pq.add(new Grid(listOfBridges,nCost));
        while(!pq.isEmpty()){
            nListOfBirdges = pq.peek().getGrid();
            pq.poll();
            List<Pair<Pair<Integer,Integer>,Character>>newMove = findAllMoves( nListOfBirdges);
            for(int i=0;i<newMove.size();i++){
                if(grid[newMove.get(i).getKey().getKey()][newMove.get(i).getKey().getValue()]=='B'||grid[newMove.get(i).getKey().getKey()][newMove.get(i).getKey().getValue()]=='G'){
                    make_complete_move(newMove.get(i).getKey().getKey(),newMove.get(i).getKey().getValue(),newMove.get(i).getValue());
                    nListOfBirdges =get_bridges_parts();
                    if(!hSet.contains(nListOfBirdges)){
                        hSet.add(nListOfBirdges);
                        stateCounter++;
                        if(!pq.isEmpty())
                            nCost += getNewCost(nListOfBirdges,pq.peek().getGrid(),initCosts);
                        pq.add(new Grid(nListOfBirdges,nCost));
                    }
                }
                else
                    newMove = findAllMoves(nListOfBirdges);
                if(isFinal() ){
                    if(!solutions.isEmpty()){
                        for(int k=0;k<solutions.size();k++)
                            if(nCost < solutions.get(k).getCost())
                                solutions.add(new Grid(nListOfBirdges,nCost));
                    }
                    else{
                        solutions.add(new Grid(nListOfBirdges,nCost));
                        print_grid();
                    }
                }
                nListOfBirdges = get_bridges_parts();
            }
        }
        System.out.println("Number of States: " + stateCounter);
    }

    public void aStar(List<Pair<Integer,Integer>> listOfBridges,PriorityQueue<Grid> pq, List<Pair<Grid,Double>> solutions){
        List<Pair<Integer,Integer>> initCosts,nListOfBirdges;
        int nCost=0,stateCounter=1;
        initCosts = initCostsBegin(listOfBridges);
        nCost += getStateCost(initCosts);
        if(hSet.contains((listOfBridges)))
            return;
        hSet.add(listOfBridges);
        pq.add(new Grid(listOfBridges,nCost));
        while(!pq.isEmpty()){
            nListOfBirdges = pq.peek().getGrid();
            pq.poll();
            List<Pair<Pair<Integer,Integer>,Character>>newMove = findAllMoves( nListOfBirdges);
            for(int i=0;i<newMove.size();i++){
                if(grid[newMove.get(i).getKey().getKey()][newMove.get(i).getKey().getValue()]=='B'||grid[newMove.get(i).getKey().getKey()][newMove.get(i).getKey().getValue()]=='G'){
                    make_complete_move(newMove.get(i).getKey().getKey(),newMove.get(i).getKey().getValue(),newMove.get(i).getValue());
                    nListOfBirdges =get_bridges_parts();
                    if(!hSet.contains(nListOfBirdges)){
                        hSet.add(nListOfBirdges);
                        stateCounter++;
                        if(!pq.isEmpty())
                            nCost += getNewCost(nListOfBirdges,pq.peek().getGrid(),initCosts);
                        pq.add(new Grid(nListOfBirdges,nCost,getRatioH()));
                    }
                }
                else
                    newMove = findAllMoves(nListOfBirdges);
                if(isFinal() ){
                    print_grid();
                    System.out.println("Number of States: " + stateCounter);
                    return;
                }
                nListOfBirdges = get_bridges_parts();

            }
        }
    }

    public void ucsSolution(List<Pair<Integer,Integer>> listOfBridges){
        PriorityQueue<Grid> pq = new PriorityQueue<Grid>(new GridComparator());
        List<Grid> solutions = new ArrayList<>();
        ucsS(listOfBridges,pq,solutions);
    }
    public void aStarSolution(List<Pair<Integer,Integer>> listOfBridges){
        PriorityQueue<Grid>pq = new PriorityQueue<>(new GridComparatorWithH());
        List<Pair<Grid,Double>> solutions = new ArrayList<>();
        aStar(listOfBridges,pq,solutions);
    }
    public void bfsSolution(List<Pair<Integer,Integer>> listOfBridges){
        LinkedList<List<Pair<Integer,Integer>>> queue = new LinkedList<List<Pair<Integer,Integer>>>();
        List<Pair<Integer,Integer>>nListOfBirdges = new ArrayList<>();
        if(hSet.contains((listOfBridges)))
            return;
        queue.add(listOfBridges);
        List<Pair<Pair<Integer,Integer>,Character>>newMove = new ArrayList<>();
        int stateCounter=1;
        while(!queue.isEmpty()){
            stateCounter++;
           nListOfBirdges = queue.pop();
            hSet.add(new ArrayList<Pair<Integer,Integer>>(nListOfBirdges));
            newMove = findAllMoves(nListOfBirdges);
            for(int i=0;i<newMove.size();i++){
                if(grid[newMove.get(i).getKey().getKey()][newMove.get(i).getKey().getValue()]=='B'||grid[newMove.get(i).getKey().getKey()][newMove.get(i).getKey().getValue()]=='G'){
                    make_complete_move(newMove.get(i).getKey().getKey(),newMove.get(i).getKey().getValue(),newMove.get(i).getValue());
                    nListOfBirdges =get_bridges_parts();
                    if(!hSet.contains(nListOfBirdges)){
                        hSet.add(new ArrayList<Pair<Integer,Integer>>(nListOfBirdges));
                        queue.add(nListOfBirdges);
                    }
                }
                else{
                    newMove = findAllMoves(nListOfBirdges);
                }
                if(isFinal()){
                    print_grid();
                    System.out.println("Number of Used States: " + stateCounter);
                    return;
                }
            }
        }

    }
    public void storeLastRes(List<Pair<Integer,Integer>>list){
        this.optimalRes= list;
    }
    public double getRatioH(){
        int counter=0,numberOfPieces=0;
        for(int i =0;i<x_rang;i++)
            for(int j=0;j<y_rang;j++)
                vis[i][j]=false;
        for(int i =0;i<y_rang ;i++)
            if(grid[0][i] == 'B')
                dfs(0,i);
        for(int i=0;i<x_rang;i++)
            for(int j=0;j<y_rang;j++)
                if(vis[i][j])
                    counter++;
        for(int i=0;i<x_rang;i++)
            for(int j=0;j<y_rang;j++)
                if(grid[i][j]=='B')
                    numberOfPieces++;
        if(counter==0)
            return 0;
        return numberOfPieces/counter;
    }
    public double getRatioC(int cost){
        return (double)cost/10000;
    }
    public boolean isFinal(){
        boolean d = false;
        for(int i =0;i<x_rang;i++)
            for(int j=0;j<y_rang;j++)
                vis[i][j]=false;
        for(int i =0;i<y_rang ;i++)
            if(grid[0][i] == 'B')
                d= dfs(0,i);
        if(d) {
            return true;
        }
        return false;
    }
}
