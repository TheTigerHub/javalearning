import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class Main {

    public static void printIslandMap(int [][] array) {
        for (int i = 0; i<array.length; i++){
            System.out.println(Arrays.toString(array[i]));
        }
        System.out.println("---------------------------------");
    }
    public static void printIslandMap(boolean [][] array) {
        for (int i = 0; i<array.length; i++){
            System.out.println(Arrays.toString(array[i]));
        }
        System.out.println("---------------------------------");
    }

    public static void debugPrintMap (Land[][] array) {
        for (int i = 0; i<array.length; i++){
            for (int j = 0; j<array[0].length; j++){
                System.out.println(array[i][j].adjland);
            }
            System.out.println("---------------------------------");
        }
        System.out.println("---------------------------------");
    }

    public static void findIslands(int [][] array, boolean[][] visited, Land[][] landArray) {

        int islandsize = 0;

        for (int y = 0; y<array.length; y++){
            for (int x = 0; x<array[0].length; x++){
                landArray[y][x] = new Land();
                if (array[y][x] == 1){

                    //System.out.println("result of detectLandDirection(array, visited, x, y, islandsize).adjland: " + (detectLandDirection(array, visited, x, y, islandsize).adjland));
                    Land land = (detectLandDirection(array, visited, x, y, islandsize));
                    System.out.println("land.adjland: " + land.adjland);
                    landArray[y][x] = land;
                    visited[y][x] = true;
                    System.out.println("---------------------------------");
                }

            }
        }
    }

    public static void findTotalSize(boolean[][] landvisited, Land[][] landArray, ArrayList<Integer> islandSizeList) {

        int totalislandsize = 0;
        System.out.println(landArray.length + " heigh");
        System.out.println(landArray[0].length + " width");

        for (int y = 0; y<landArray.length; y++){
            for (int x = 0; x<landArray[0].length; x++){
                System.out.println("(" + x + ", " + y + ") " + "value: " + landArray[y][x].adjland);
                if (landArray[y][x].adjland != 0){
                    System.out.println(areaAdder(landArray, landvisited, x, y, totalislandsize) + " total size");
                    System.out.println("---------------------------------");
                }
                islandSizeList.add(areaAdder(landArray, landvisited, x, y, totalislandsize));
            }
        }
    }

    public static Land detectLandDirection (int [][] array, boolean[][] visited, int x, int y, int islandsize) {
        if (visited[y][x] == false){
            islandsize = 1;
            System.out.println(islandsize);
        }
        if (y != 0) {
            if (x != 0 && array[y - 1][x - 1] == 1) {
                System.out.println("island goes up to the left");
                if (!visited[y - 1][x - 1]) {
                    islandsize = islandsize+1;
                    System.out.println("island size = " + islandsize);
                }
                visited[y - 1][x - 1] = true;

            }
            if (array[y - 1][x] == 1) {
                System.out.println("island goes straight up");

                if (!visited[y - 1][x]) {
                    islandsize = islandsize+1;
                    System.out.println("island size = " + islandsize);
                }
                visited[y - 1][x]  = true;

            }
            if (x != 9 && array[y - 1][x + 1] == 1) {
                System.out.println("island goes up to the right");

                if (!visited[y - 1][x + 1]) {
                    islandsize = islandsize+1;
                    System.out.println("island size = " + islandsize);
                }
                visited[y - 1][x + 1] = true;

            }
        }
        if (y != 9) {
            if (x != 0 && array[y + 1][x - 1] == 1) {
                System.out.println("island goes to bottom left");

                if (!visited[y + 1][x - 1]) {
                    islandsize = islandsize+1;
                    System.out.println("island size = " + islandsize);
                }
                visited[y + 1][x - 1] = true;

            }
            if (array[y + 1][x] == 1) {
                System.out.println("island goes straight down");

                if (!visited[y + 1][x]) {
                    islandsize = islandsize+1;
                    System.out.println("island size = " + islandsize);
                }
                visited[y + 1][x] = true;

            }
            if (x != 9 && array[y + 1][x + 1] == 1) {
                System.out.println("island goes to bottom right");

                if (!visited[y + 1][x + 1]) {
                    islandsize = islandsize+1;
                    System.out.println("island size = " + islandsize);
                }
                visited[y + 1][x + 1] = true;

            }
        }
        if (x != 0) {
            if (array[y][x - 1] == 1) {
                System.out.println("island goes straight left");

                if (!visited[y][x - 1]) {
                    islandsize = islandsize+1;
                    System.out.println("island size = " + islandsize);
                }
                visited[y][x - 1] = true;

            }
        }
        if (x != 9) {
            if (array[y][x + 1] == 1) {
                System.out.println("island goes straight right");
                if (!visited[y][x + 1]) {
                    islandsize = islandsize+1;
                    System.out.println("island size = " + islandsize);
                }
                visited[y][x + 1] = true;

            }
        }

        Land land = new Land();
        land.value = 1;
        land.xcord = x;
        land.ycord = y;
        land.adjland = islandsize;
        return land;
    }

    public static int areaAdder (Land [][] landArray, boolean[][] landvisited, int x, int y, int totalIslandSize) {
        if (landvisited[y][x] == false){
            totalIslandSize = landArray[y][x].adjland;
            System.out.println("landvisited[y][x] == false --- size: " + totalIslandSize);
        }
        if (y != 0) {
            if (x != 0 && landArray[y - 1][x - 1].adjland != 0) {
                System.out.println("island goes to top left");
                if (!landvisited[y - 1][x - 1]) {
                    totalIslandSize = totalIslandSize + landArray[y - 1][x - 1].adjland;
                    System.out.println("total island size = " + totalIslandSize);
                }
                landvisited[y - 1][x - 1] = true;

            }
            if (landArray[y - 1][x].adjland != 0) {
                System.out.println("island goes to straight up");
                if (!landvisited[y - 1][x]) {
                    totalIslandSize = totalIslandSize+landArray[y - 1][x].adjland;
                    System.out.println("island size = " + totalIslandSize);
                }
                landvisited[y - 1][x]  = true;

            }
            if (x != 9 && landArray[y - 1][x + 1].adjland != 0) {
                System.out.println("island goes to top right");
                if (!landvisited[y - 1][x + 1]) {
                    totalIslandSize = totalIslandSize+landArray[y - 1][x + 1].adjland;
                    System.out.println("island size = " + totalIslandSize);
                }
                landvisited[y - 1][x + 1] = true;

            }
        }
        if (y != 9) {
            if (x != 0 && landArray[y + 1][x - 1].adjland != 0) {
                System.out.println("island goes to bottom left");
                if (!landvisited[y + 1][x - 1]) {
                    totalIslandSize = totalIslandSize+landArray[y + 1][x - 1].adjland;
                    System.out.println("island size = " + totalIslandSize);
                }
                landvisited[y + 1][x - 1] = true;

            }
            if (landArray[y + 1][x].adjland != 0) {
                System.out.println("island goes straight down");
                if (!landvisited[y + 1][x]) {
                    totalIslandSize = totalIslandSize+landArray[y + 1][x].adjland;
                    System.out.println("island size = " + totalIslandSize);
                }
                landvisited[y + 1][x] = true;

            }
            if (x != 9 && landArray[y + 1][x + 1].adjland != 0) {
                System.out.println("island goes to bottom right");

                if (!landvisited[y + 1][x + 1]) {
                    totalIslandSize = totalIslandSize+landArray[y + 1][x + 1].adjland;
                    System.out.println("island size = " + totalIslandSize);
                }
                landvisited[y + 1][x + 1] = true;

            }
        }
        if (x != 0) {
            if (landArray[y][x - 1].adjland != 0) {
                System.out.println("island goes straight left");

                if (!landvisited[y][x - 1]) {
                    totalIslandSize = totalIslandSize+landArray[y][x - 1].adjland;
                    System.out.println("island size = " + totalIslandSize);
                }
                landvisited[y][x - 1] = true;

            }
        }
        if (x != 9) {
            if (landArray[y][x + 1].adjland != 0) {
                System.out.println("island goes straight right");
                if (!landvisited[y][x + 1]) {
                    totalIslandSize = totalIslandSize+landArray[y][x + 1].adjland;
                    System.out.println("island size = " + totalIslandSize);
                }
                landvisited[y][x + 1] = true;

            }
        }

        return totalIslandSize;
    }

    public static void main(String[] args) {

        ArrayList<Integer> islandSizeList = new ArrayList<>();
        Land [][] landArray = new Land[10][10];
        boolean [][] visited = new boolean[10][10];
        boolean [][] landvisited = new boolean[10][10];
        //int [][] islandsv1 = new int[10][10];
        int [][] islandsv1 = {
                {1,0,1,0,0,0,0,1,0,1},
                {0,0,1,1,1,0,0,0,1,1},
                {0,0,1,1,0,0,0,0,1,0},
                {1,0,0,0,0,0,0,0,0,0},
                {0,0,0,1,1,0,0,1,0,0},
                {0,0,0,0,0,0,0,0,0,1},
                {0,0,0,0,1,0,0,0,0,1},
                {0,0,1,1,1,1,0,0,0,0},
                {0,0,0,0,0,0,0,1,1,0},
                {0,0,1,0,0,0,1,1,1,0}};
        int[][] islandsv2 = {
                {1,0,0,1,0,0,1,0,0,1},
                {0,1,1,1,1,1,1,1,1,0},
                {1,0,1,0,1,0,0,1,0,1},
                {0,0,0,0,0,0,0,0,0,0},
                {0,1,1,1,0,0,0,0,0,0},
                {0,1,1,1,0,1,1,1,1,1},
                {0,0,0,0,0,0,0,0,0,0},
                {1,0,1,1,1,1,1,0,0,0},
                {1,0,1,0,0,0,1,0,1,0},
                {1,0,1,0,0,0,1,0,0,1}};
        printIslandMap(islandsv2);
        findIslands(islandsv2, visited, landArray);
        System.out.println("****************************************************************************************************");
        System.out.println("start of total size");
        System.out.println("****************************************************************************************************");
        debugPrintMap(landArray);
        findTotalSize(landvisited, landArray,islandSizeList);
        System.out.println(islandSizeList + " island size list");
        Collections.sort(islandSizeList);
        Collections.reverse(islandSizeList);
        System.out.println("The biggest island has a size of: " + islandSizeList.get(0));


    }
}
