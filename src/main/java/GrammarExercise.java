//import sun.plugin.javascript.navig.Array;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class GrammarExercise {
    public static void main(String[] args) {
        //需要从命令行读入
        String firstWordList = "apple,juice,mother,people,beautiful,apple,dog";
        String secondWordList = "cat,baby,smile,good,apple,beautiful,Dog,nice";

        List<String> result = findCommonWordsWithSpace(firstWordList,secondWordList);
        //按要求输出到命令行
        System.out.println(result);
    }

    public static List<String> findCommonWordsWithSpace(String firstWordList, String secondWordList) {
        //在这编写实现代码
        String[] firstArr = firstWordList.split(",");
        String[] secondArr = secondWordList.split(",");
        Stream<String> allWordStream = Stream.concat(Arrays.stream(firstArr), Arrays.stream(secondArr));
        if (Arrays.asList(firstArr).contains("") || Arrays.asList(secondArr).contains("")) {
            throw new InvalidInputException();
        }
        if (!allWordStream
                .map(s -> Arrays.stream(s.split("")))
                .allMatch(stringStream -> stringStream.allMatch(s -> (s.charAt(0) <= 90 && s.charAt(0) >= 65) || (s.charAt(0) >= 97 && s.charAt(0) <= 122)))) {
            throw new InvalidInputException();
        }
        return Arrays.stream(firstArr)
                .distinct()
                .map(String::toUpperCase)
                .filter(s -> Arrays.stream(secondArr)
                            .distinct()
                            .map(String::toUpperCase)
                            .collect(Collectors.toList())
                            .contains(s)
                )
                .map(s -> String.join(" ", s.split("")))
                .collect(Collectors.toList());
    }
}
