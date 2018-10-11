import java.util.ArrayList;
import java.util.Scanner;
public class A_Star_Alorithm {
        public static void main(String[] args){
            BoardGame board = new BoardGame();
            ArrayList<Tile> inputTiles = new ArrayList<Tile>();
            ArrayList<Tile> goalTiles = new ArrayList<Tile>();
            Scanner sc = new Scanner(System.in);
            int[] inputArray = new int[9];
            int[] goalArray = new int[9];
            String heuristic;
            // Takes the Input
            System.out.println("Please enter the initial Board configuration");
            for(int i=0;i<9;i++)
            {
                inputArray[i] = sc.nextInt();
            }
            // Takes the  goal
            System.out.println("Please enter the Goal Board configuration");
            for(int i=0;i<9;i++)
            {
                goalArray[i] = sc.nextInt();
            }
            // Selects the heuristic
            System.out.println("Please enter the heuristic you wish to apply");
            System.out.println("Please enter 1 for manhattan Distance and 2 for Misplaced Tiles heuristic");
            int heuristicInt = sc.nextInt();
            if(heuristicInt ==1)
                heuristic = "manhattan Distance Heuristic";
            else
                heuristic = "Misplaced Tiles Heuristic";
            int arrayCounter=0;
            Tile tile,goalTile;
            // Places the input and goal array into ArrayList
            for(int i=0;i<3;i++)
                {
                    for(int j=0;j<3;j++)
                        {
                            tile = new Tile(i,j,inputArray[arrayCounter]);
                            goalTile = new Tile(i,j,goalArray[arrayCounter]);
                            arrayCounter++;
                            inputTiles.add(tile);
                            goalTiles.add(goalTile);
                        }
                }
                board.generatedBoardsList.add(inputTiles);
            System.out.println("Solution for the above configuration");
            // Calls the search function
            board.search(inputTiles,goalTiles,heuristic);
        }
}
