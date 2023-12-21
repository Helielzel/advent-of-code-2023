import java.io.IOException;
import java.lang.reflect.Array;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

public class Trebuchet {
    public static void main(String[] args) {
		
        /* PART 1 */
        List<String> allLines = Arrays.asList("1abc2", "pqr3stu8vwx", "a1b2c3d4e5f", "treb7uchet");
        System.out.println(allLines.stream()
            .map(line -> line.chars().filter(c -> c >= '0' && c <= '9').mapToObj(c -> Character.getNumericValue(c)).toList())
            .mapToInt(list -> 10 * list.get(0) + list.get(list.size() - 1))
            .sum());


        /* PART 2 */
        // -> Test with input file : List<String> allLines = Files.readAllLines(Paths.get("D:\\Projects\\Advent_of_Code2023\\Day01\\input.txt"));
        List<String> allLines = Arrays.asList("two1nine","eightwothree","abcone2threexyz","xtwone3four","4nineeightseven2","zoneight234","7pqrstsixteen");    
        var alphabeticalNumbers = List.of("one", "two", "three", "four", "five", "six", "seven", "eight", "nine");
        int sum = 0;
        
        for (String line:allLines) {
            List<Integer> numbers = new ArrayList<>();
            for (int i = 0; i != line.length(); i++) {
                int index = i;
                Stream.of(line.charAt(i)).filter(c -> Character.isDigit(c)).forEach(c -> numbers.add(Character.getNumericValue(c)));
                alphabeticalNumbers.stream().filter(e -> line.startsWith(e, index)).forEach(e -> numbers.add(alphabeticalNumbers.indexOf(e) + 1));
            }
            sum += numbers.getFirst() * 10 + numbers.getLast();
        }
        System.out.println(sum);
    }
}
