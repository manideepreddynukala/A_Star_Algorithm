import java.util.ArrayList;
import java.util.Objects;
import java.util.*;

public class BoardGame {
    ArrayList<ArrayList<Tile>> exploredNodes = new ArrayList<ArrayList<Tile>>();
    ArrayList<ArrayList<Tile>> generatedBoardsList = new ArrayList<ArrayList<Tile>>();
    static Map<Integer,ArrayList<Tile>> generatedBoards = new HashMap<Integer, ArrayList<Tile>>();
    int gValue = 0;
    void search(ArrayList<Tile> input123, ArrayList<Tile> goal,String heuristic)
    {

        gValue = gValue+1;
        printBoard(input123);
        if(heuristic.equals("manhattan Distance Heuristic"))
            System.out.println("manhattan distance of above board is" + manhattanHeuristic(input123,goal));
        else
            System.out.println("Misplaced tiles of above board is" + misplacedHeuristic(input123,goal));
        System.out.println();
        try {
            int counter = 0;
            ArrayList<Tile> input = new ArrayList<>(input123);
            // if the heuristic value of the input is zero, then terminate the recursion.
            if(heuristic.equals("manhattan Distance Heuristic")) {
                if (manhattanHeuristic(input, goal) == 0)
                    counter++;
            }
            else if(misplacedHeuristic(input,goal) == 0)
                counter++;
            if (counter > 0) {

                System.out.println("Number of steps it took to reach the solution is" + gValue);
                System.out.println("Total number of generated nodes" + exploredNodes.size());

                return;
            }
            int index;
            ArrayList<Tile> neighbourTiles = null;
            Tile tileZero = new Tile();
            tileZero = findTile(0, input);

            // Get the neighbouring tiles of the given tile
            neighbourTiles = neighbourTiles1(tileZero, input);

            // swap the tiles with zero values and add it to the List

            for (Tile t : neighbourTiles) {
                swapTiles(t, input,goal,heuristic);
            }
            Map.Entry<Integer,ArrayList<Tile>> entry = generatedBoards.entrySet().iterator().next();
            int temp = entry.getKey();
            // Get the least heuristic board from the List
            sortbykey();

            // modify the input with the board having least heuristic
            int arrayListIndex = 0;
            for (Tile p : generatedBoards.get(temp)) {
                input.set(arrayListIndex, p.clone());
                arrayListIndex++;
            }
            // Remove the explored Board from the list.
            generatedBoards.remove(temp);
            // Recursion is applied until goal is reached
            search(input, goal,heuristic);
        }
        catch (Exception e){}
    }

    // It is used to sort the HashMap which consists of tile values(key) and tiles(Colletion of tiles) of fringe.
    public void sortbykey()
    {
        TreeMap<Integer,ArrayList<Tile>> sorted = new TreeMap<>();
        sorted.putAll(generatedBoards);
    }

    // It is used to get the Tile with number and input Arraylist (Collection of tiles)
    public Tile findTile(int number,ArrayList<Tile> input){

        for(Tile t: input) {
            if(t.getValue() == number)
                return t;
        }

        return null;
    }

    //misplaced heuristics Implementation
    int misplacedHeuristic(ArrayList<Tile> generatedTiles,ArrayList<Tile> goal)
    {
        int numberOfMisplacedTiles=0;
        for(Tile t :generatedTiles){
            if(t.getPositionX() != findTile(t.getValue(),goal).getPositionX() || t.getPositionY() != findTile(t.getValue(),goal).getPositionY())
            {
                numberOfMisplacedTiles++;
            }
        }
        return numberOfMisplacedTiles;
    }

    // manhattan heuristic Implementation
    int manhattanHeuristic(ArrayList<Tile> generatedTiles,ArrayList<Tile> goal)
    {
        int GoalTileX;
        int GoalTileY;
        int differenceOfX;
        int differenceofY;
        int sum=0;
        for(Tile t : generatedTiles)
        {
            if(t.getValue() != 0) {
                Tile goalTile = findTile(t.getValue(), goal);
                GoalTileX = goalTile.getPositionX();
                GoalTileY = goalTile.getPositionY();
                differenceOfX = GoalTileX - t.getPositionX();
                differenceofY = GoalTileY - t.getPositionY();
                sum+= Math.abs(differenceOfX) + Math.abs(differenceofY);
            }
        }
        return sum;
    }
    void swapTiles(Tile t, ArrayList<Tile> input, ArrayList<Tile> goal, String heuristic){
        //int index = input.indexOf(t);
        int x = t.positionX;
        int y = t.positionY;
        int t66 = 0;
        Tile emptyTile=null;
        emptyTile = findTile(0,input);
        int zerox = emptyTile.positionX;
        int zeroy = emptyTile.positionY;
        ArrayList<Tile> cloneTiles = new ArrayList<>();
        for(Tile p : input)
            cloneTiles.add(p.clone());
        Tile zeroTile = findTile(0,cloneTiles);
        zeroTile.setPosition(x,y);

        Tile swapTile = findTile(t.getValue(),cloneTiles);
        swapTile.setPosition(zerox,zeroy);
        int bb =0;
        for(ArrayList<Tile> t3 : generatedBoardsList)
        {

            if((
                    t3.get(0).getPositionX() == cloneTiles.get(0).getPositionX() && t3.get(0).getPositionY() == cloneTiles.get(0).getPositionY() && t3.get(0).getValue() == cloneTiles.get(0).getValue()
                            && t3.get(1).getPositionX() == cloneTiles.get(1).getPositionX() && t3.get(1).getPositionY() == cloneTiles.get(1).getPositionY() && t3.get(1).getValue() == cloneTiles.get(1).getValue()
                            && t3.get(2).getPositionX() == cloneTiles.get(2).getPositionX() && t3.get(2).getPositionY() == cloneTiles.get(2).getPositionY() && t3.get(2).getValue() == cloneTiles.get(2).getValue()
                            && t3.get(3).getPositionX() == cloneTiles.get(3).getPositionX() && t3.get(3).getPositionY() == cloneTiles.get(3).getPositionY() && t3.get(3).getValue() == cloneTiles.get(3).getValue()
                            && t3.get(4).getPositionX() == cloneTiles.get(4).getPositionX() && t3.get(4).getPositionY() == cloneTiles.get(4).getPositionY() && t3.get(4).getValue() == cloneTiles.get(4).getValue()
                            && t3.get(5).getPositionX() == cloneTiles.get(5).getPositionX() && t3.get(5).getPositionY() == cloneTiles.get(5).getPositionY() && t3.get(5).getValue() == cloneTiles.get(5).getValue()
                            && t3.get(6).getPositionX() == cloneTiles.get(6).getPositionX() && t3.get(6).getPositionY() == cloneTiles.get(6).getPositionY() && t3.get(6).getValue() == cloneTiles.get(6).getValue()
                            && t3.get(7).getPositionX() == cloneTiles.get(7).getPositionX() && t3.get(7).getPositionY() == cloneTiles.get(7).getPositionY() && t3.get(7).getValue() == cloneTiles.get(7).getValue()
                            && t3.get(8).getPositionX() == cloneTiles.get(8).getPositionX() && t3.get(8).getPositionY() == cloneTiles.get(8).getPositionY() && t3.get(8).getValue() == cloneTiles.get(8).getValue()

            )){
                bb = bb+1;
                exploredNodes.add(t3);
            }
        }
        if(bb==0) {
            if(heuristic.equals("manhattan Distance Heuristic"))
                t66 = gValue + manhattanHeuristic(cloneTiles,goal);
            else
                t66 = gValue + misplacedHeuristic(cloneTiles,goal);
            //System.out.println("man" + manhattanHeuristic(cloneTiles,goal) + " gvalue" + gValue);
            exploredNodes.add(cloneTiles);
            generatedBoardsList.add(cloneTiles);
            generatedBoards.put(t66,cloneTiles);
        }
    }
    // returns the neighbour tiles
    ArrayList<Tile> neighbourTiles1(Tile tile,ArrayList<Tile> input){
        ArrayList<Tile> neighbourTiles = new ArrayList<>();
        int i=0;
        int x=tile.getPositionX();
        int y=tile.getPositionY();
        int top_X_Position=x-1;
        int top_Y_Position=y;
        int down_X_Position=x+1;
        int down_Y_position=y;
        int right_X_position=x;
        int right_Y_position=y+1;
        int left_X_position=x;
        int left_Y_position=y-1;

        for(Tile t :input){
            if(t.getPositionX() == left_X_position && t.getPositionY() == left_Y_position)
            {
                neighbourTiles.add(t);
            }
            else if(t.getPositionX() == right_X_position && t.getPositionY() == right_Y_position)
            {
                neighbourTiles.add(t);
            }
            else  if(t.getPositionX() == top_X_Position && t.getPositionY() == top_Y_Position)
            {
                neighbourTiles.add(t);
            }
            else if(t.getPositionX() == down_X_Position && t.getPositionY() == down_Y_position)
            {
                neighbourTiles.add(t);
            }

        }
        //for(Tile t : neighbourTiles)
        // System.out.print(t.getValue());
        return neighbourTiles;
    }
    public Tile findTilewithXY(int x, int y, ArrayList<Tile> input){

        for(Tile t: input) {
            if(t.getPositionX() == x && t.getPositionY() == y)
                return t;
        }

        return null;
    }
    void printBoard(ArrayList<Tile> tiles){
        System.out.println("+++++");
        for(int i=0;i<3;i++)
        {
            System.out.print("+");
            for(int j=0;j<3;j++)
            {
                System.out.print(findTilewithXY(i,j,tiles).getValue());
            }
            System.out.print("+");
            System.out.println("");
        }
        System.out.println("+++++");
        //System.out.println();
    }
}


