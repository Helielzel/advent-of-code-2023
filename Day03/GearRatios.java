import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class GearRatios {
    public static void main(String[] args) {
        try {    
            List<String> input = Files.readAllLines(Paths.get("D:\\Projects\\Advent_of_Code2023\\Day03\\input.txt"));
            partOne(input);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void partOne(List<String> input) {
        int toIgnore = 0;
        int input_line_len = input.get(0).length();
        int[] numberAndNumberIndex = new int[2];
        int result = 0;
        
        for (int x = 0; x != input_line_len; x++) {
            for (int y = 0; y != input_line_len; y++) {
                if (toIgnore > 0) {
                    toIgnore--;
                    continue;
                }
                if (!Character.isDigit(input.get(x).charAt(y))) {
                    toIgnore = 0;
                    continue;
                }
                if (isAdjacentToSymbol(input, x, y)) {
                    numberAndNumberIndex = getWholeNumberAndItsIndex(input.get(x), y);
                    toIgnore = numberAndNumberIndex[1] - y;
                    result += numberAndNumberIndex[0];
                }
            }
        }
        System.out.println("Result: " + result);
    }

    public static boolean isAdjacentToSymbol(List<String> input, int x, int y) {
        if (
            (x > 0 && input.get(x - 1).charAt(y) != '.' && !Character.isDigit(input.get(x - 1).charAt(y))) ||
            (x < input.size() - 1 && input.get(x + 1).charAt(y) != '.' && !Character.isDigit(input.get(x + 1).charAt(y))) ||
            (y > 0 && input.get(x).charAt(y - 1) != '.' && !Character.isDigit(input.get(x).charAt(y - 1))) ||
            (y < input.get(x).length() - 1 && input.get(x).charAt(y + 1) != '.' && !Character.isDigit(input.get(x).charAt(y + 1))) ||
            (x > 0 && y > 0 && input.get(x - 1).charAt(y - 1) != '.' && !Character.isDigit(input.get(x - 1).charAt(y - 1))) ||
            (x > 0 && y < input.get(x).length() - 1 && input.get(x - 1).charAt(y + 1) != '.' && !Character.isDigit(input.get(x - 1).charAt(y + 1))) ||
            (x < input.size() - 1 && y > 0 && input.get(x + 1).charAt(y - 1) != '.' && !Character.isDigit(input.get(x + 1).charAt(y - 1))) ||
            (x < input.size() - 1 && y < input.get(x).length() - 1 && input.get(x + 1).charAt(y + 1) != '.' && !Character.isDigit(input.get(x + 1).charAt(y + 1)))) {
                return true;
            }
        return false;
    }

    public static int[] getWholeNumberAndItsIndex(String line, int coordinatesY) {
        String number = "";
        int[] numberAndNumberIndex = new int[2];
        numberAndNumberIndex[0] = -1;
        boolean isCoordinateYIncluded = false;
    
        for (int i = coordinatesY; i >= 0; i--) {
            if (Character.isDigit(line.charAt(i))) {
                number = line.charAt(i) + number;
                isCoordinateYIncluded = true;
            } else { break; }
        }
        if (isCoordinateYIncluded) { coordinatesY++; }
        for (int i = coordinatesY; i < line.length(); i++) {
            if (Character.isDigit(line.charAt(i))) {
                number += line.charAt(i);
            } else {
                numberAndNumberIndex[1] = i;
                break;
            }
        }
        numberAndNumberIndex[0] = Integer.parseInt(number);    
        return numberAndNumberIndex;
    }
}
