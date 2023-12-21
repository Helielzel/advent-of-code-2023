import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Cube {

    public static void main(String[] args) {
        try {    
            List<String> gameInput = Files.readAllLines(Paths.get("D:\\Projects\\Advent_of_Code2023\\Day02\\input.txt"));
            partTwo(gameInput);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void partTwo(List<String> gameInput) {
        int powerOfAllCubes = 0;
        for (int i = 0; i < gameInput.size(); i++) {
            String game = gameInput.get(i).substring(gameInput.get(i).indexOf(":") + 1).trim();
            powerOfAllCubes += getMinimumCubes(game);
        }
        System.out.println(powerOfAllCubes);
    }

    public static int getMinimumCubes(String game) {
        var minimumCubes = new HashMap<>(Map.of("red", 0, "green", 0, "blue", 0));
        game = game.replace(";", ",");

        for (String element : game.split(", ")) {
            String[] colorPairs = element.split(" ");   
            if (minimumCubes.get(colorPairs[1]) < Integer.parseInt(colorPairs[0])) {
                minimumCubes.put(colorPairs[1], Integer.parseInt(colorPairs[0]));
            }  
        }
        return (minimumCubes.get("red") * minimumCubes.get("green") * minimumCubes.get("blue"));
    }
    
    public static void partOne(List<String> gameInput) {
        int gameSum = 0;
    
        for (int i = 0; i < gameInput.size(); i++) {
            String game = gameInput.get(i).substring(gameInput.get(i).indexOf(":") + 1).trim();
            if (colorCounter(game)) {
                gameSum += i + 1;
            }
        }
        System.out.println(gameSum);
    }

    public static boolean colorCounter(String game) {
        var bag = Map.of("red", 12, "green", 13, "blue", 14);
        
        for (String set:game.split("; ")) { 
            for (String parts : set.split(", ")) {
                String[] colorPairs = parts.split(" ");
                if (bag.get(colorPairs[1]) < Integer.parseInt(colorPairs[0])) {
                    return false;
                }
            }
        }
        return true;
    }
}
