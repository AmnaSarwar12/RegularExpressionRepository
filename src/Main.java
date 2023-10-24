import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) {
        String HelloWorld3 = format("%s %s", "Hello","World");
        System.out.printf(HelloWorld3);

        String testString = "Anyone can learn abc's , 123's and any regular expression";
        String replacement = "(-)";
        String[] pattern = {
                "[a-zA-z]*$",
                "^[a-zA-z]{3}",
                "[aA]ny\\b"
        };
        for(String Pattern: pattern){
            String output = testString.replaceFirst(Pattern, replacement);
            System.out.println("Pattern: " + Pattern + " => " +output);
        }

        String paragraph = """
                sincerely smitten, lies in hiding this fact from the woman you love,
                of feigning a casual detachment under all circumstances. What sadness there is in this simple observation!
                What an accusation against man! However, it had never occurred to me to contest this law, nor to imagine disobeying it:
                love makes you weak, and the weaker of the two is oppressed, tortured and finally killed by the other, who in his or her turn oppresses,
                tortures and kills without having evil intentions, without even getting pleasure from it, with complete indifference; that’s what men,
                normally, call love.
                """;

        String[] lines = paragraph.split("\\R");
        System.out.println("this paragraph has " +lines.length + " lines ");
        String[] words = paragraph.split("\\s");
        System.out.println("this paragraph hae " + words.length + " words ");
        System.out.println(paragraph.replaceAll("[a-zA-z]+ble", "[GRUB]"));

        Scanner scanner =  new Scanner(paragraph);
        System.out.println(scanner.delimiter());
        scanner.useDelimiter("\\R");
//        while (scanner.hasNext()){
//            String element = scanner.next();
//            System.out.println(element);
//        }
//        scanner.tokens()
//                        .map(s-> Arrays.stream(s.split("\\s+")).count())
//                        .forEach(System.out::println);
        System.out.println(scanner.findInLine("[a-zA-z]+emt"));
        scanner.close();

        String RegularText = "Hello,World!";
        boolean matches = RegularText.matches("Hello,World!");
        //System.out.println(matches);

        String RegularText2 = "[A-Z].*\\.";
        for(String s: List.of(
                "The bike is red.",
                "I am a new student.",
                "hello world.",
                "How are you?"
        )){
            boolean matches2 = s.matches(RegularText2);
            //System.out.println(matches2);
        }



        String RegularText3 = "[A-Z].+[.?!]";
        for(String s: List.of(
                "The bike is red, and has flat tires.",
                "I love being a new L.P.A student!",
                "Hello, friends and family: welcome?",
                "How are you, Mary?"
        )){
            boolean matches3 = s.matches(RegularText3);
            System.out.println(matches3);
        }

        //patterns and matcher
        String sentence = "I like motorcycles.";
        boolean matched = Pattern.matches("[A-Z].*[.]", sentence);
        System.out.println();
        System.out.println(matched);

        Pattern firstPattern = Pattern.compile("[A-Z].*[.]");
        var matcher = firstPattern.matcher(sentence);
        System.out.println(matcher.matches() + ": " + sentence);

        System.out.println(matcher.lookingAt() + ": " +sentence);
        System.out.println("Matched ending index" +matcher.end());

        matcher.reset();
        System.out.println(matcher.find() + ": " +sentence);
        System.out.println("Matched ending index" +matcher.end());
        System.out.println("Matched on : " + sentence.substring(matcher.start(), matcher.end()));

        System.out.println("Matched on : " + matcher.group());

        String htmlSnippit = """
                <h1>My Heading</h1>
                <h2>Sub-Heading</h2>
                <p>this is my paragraph</p>
                <p>this is another paragraph</p>
                <h3>summary</h3>
                
                """;

        Pattern htmlPattern =  Pattern.compile("<[hH](?<level>\\d)>(.*)</[hH]\\d");
        Matcher htmlMatcher = htmlPattern.matcher(htmlSnippit);
        while (htmlMatcher.find()){
            //System.out.println("Group : " +htmlMatcher.group());
            //System.out.println("Group0 : " +htmlMatcher.group(0));
            //System.out.println("Group1 : " +htmlMatcher.group(1));
            //System.out.println(htmlMatcher.group(1) + " " + htmlMatcher.group(2));
            System.out.println(htmlMatcher.group("level") + " " + htmlMatcher.group(2));
            System.out.println("index = " + htmlMatcher.start("level"));

            htmlMatcher.reset();
            htmlMatcher.results().forEach(mr->System.out.println(
                    mr.group(1) + " " + mr.group(2)
            ));
        }

        String TabbedText = """
                group1  group2  group3
                1   2   3
                a   b   c
                """;

        TabbedText.lines()
                .flatMap(s-> Pattern.compile("\\t").splitAsStream(s))
                .forEach(System.out::println);

        htmlMatcher.reset();
        String updatedSnippet = htmlMatcher.replaceFirst("First Header");
        System.out.println("---------------------------------");
        System.out.println(updatedSnippet);
        System.out.println(htmlMatcher.start() + ": " + htmlMatcher.end());
        System.out.println(htmlMatcher.group(2));

        htmlMatcher.usePattern(
                Pattern.compile("<([hH]\\d)>(.*)</\\1>"));

        htmlMatcher.reset();
        System.out.println("-----------------------------------");
        System.out.println("Using back refrence: \n" + htmlMatcher.replaceFirst("<em>$2</em>"));

        String replacedHtml = htmlMatcher.replaceAll((mr) ->
                "<em>" + mr.group(2) + "</em>");
        System.out.println("------------------------------------");
        System.out.println(replacedHtml);
    }

    private static String format(String regex, String... args){
        int index = 0;
        while (regex.contains("%s")){
            regex = regex.replaceAll("%s", args[index++]);
        }
        return regex;


    }
}