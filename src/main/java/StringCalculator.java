import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StringCalculator {

    public static int add(String numbers) {
        if (numbers.isEmpty()) {
            return 0;
        }

        String delimiter = ",";
        if (numbers.startsWith("//")) {
            delimiter = numbers.substring(2, numbers.indexOf("\n"));
            numbers = numbers.substring(numbers.indexOf("\n") +1);

            // if multiple delims we need to separate them within one set of [] separated by '|'
            // remove repeating delims as we add '+' at the end of the regex which handles multiple
            if (delimiter.startsWith("[") && delimiter.endsWith("]")) {
                delimiter = Arrays.stream(delimiter.substring(1).substring(0, delimiter.length()-1)
                                .split("\\]\\["))
                        .filter(s-> !s.isEmpty())
                        .map(s->s.substring(0,1)) // remove multiple
                        .map(s-> s.replace("|", "\\|") //escape '|'
                                .replace("[","\\[") //escape '['
                                .replace("]","\\]")) //escape ']'
                        .collect(Collectors.joining("|", "[", "]+"));
            }
        }

        Map<Boolean, List<Integer>> partitioned = Arrays.stream(numbers.replace("\n", delimiter).split(delimiter))
                .map(Integer::parseInt)
                .filter(n->n<=1000)
                .collect(Collectors.partitioningBy(n-> n<0));

        if (!partitioned.get(true).isEmpty()) {
            throw new RuntimeException("Negatives not allowed: " + partitioned.get(true).stream()
                    .map(Object::toString)
                    .collect(Collectors.joining(",")));
        }

        return partitioned.get(false).stream()
                .mapToInt(Integer::intValue).sum();
    }

    public static int add_second_attempt(String numbers) {
        if (numbers.isEmpty()) {
            return 0;
        }
        String delimiter = ",";
        if (numbers.startsWith("//")) {
            delimiter = numbers.substring(2, numbers.indexOf("\n"));
            numbers = numbers.substring(numbers.indexOf("\n")+1);

            if (delimiter.startsWith("[") && delimiter.endsWith("]")) {
                //remove square brackets
                delimiter = delimiter.substring(1).substring(0, delimiter.length()-1);
            }
        }

        Map<Boolean, List<Integer>> partitioned = Arrays.stream(numbers.replace("\n", ",").split(delimiter))
                .map(Integer::parseInt)
                .filter(n-> n<=1000)
                .collect(Collectors.partitioningBy(n->n<0));

        if (!partitioned.get(true).isEmpty()){
            throw new RuntimeException("Negatives not allowed: " + partitioned.get(true).stream()
                    .map(Object::toString).collect(Collectors.joining(",")));
        }
        return partitioned.get(false).stream()
                .mapToInt(Integer::intValue)
                .sum();
    }

    public static int add_first_attempt(String numbers) {
        if (numbers.isEmpty()) {
            return 0;
        }
        String delimiter = ",";
        if (numbers.startsWith("//")) {
            delimiter = numbers.substring(2, numbers.indexOf("\n"));
            numbers = numbers.substring(numbers.indexOf("\n") + 1);
        }

        List<Integer> list = Stream.of(numbers
                        .replace("\n", delimiter).split(delimiter))
                        .map(Integer::parseInt)
                        .filter(n-> n<=1000)
                        .toList();

        if (list.stream().anyMatch(number -> number < 0)){
            throw new RuntimeException("Negatives not allowed: "+ list.stream()
                    .filter(number -> number <0)
                    .map(Object::toString)
                    .collect(Collectors.joining(",")));
        }

        return list.stream().mapToInt(Integer::intValue).sum();
    }
}
