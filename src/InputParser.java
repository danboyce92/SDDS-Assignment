import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class InputParser {
    List<String> wordList = new ArrayList<>();
    List<String> otherList = new ArrayList<>();
    Set<Integer> indexes = new HashSet<Integer>();

    //processFile should retrieve and organise the words and punctuation into separate Lists
    //Keep track of the indexes for punctuation in a Set so the order can be maintained

    public void processFile(String filepath) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(filepath));
        String line;
        int index = 0;

        Pattern pattern = Pattern.compile("[a-zA-Z]+|[^a-zA-Z]+");


        while ((line = reader.readLine()) != null) {
            Matcher matcher = pattern.matcher(line);
            while (matcher.find()) {
                String match = matcher.group();

                // If the match is a word, add it to wordList
                if (match.matches("[a-zA-Z]+")) {
                    wordList.add(match);
                } else {
                    //Otherwise add to punctuation list and take note of index
                    otherList.add(match);
                    indexes.add(index);
                }
                index++;
            }
        }
        reader.close();
    }

    public List<String> getWords() {
        return wordList;
    };

    public List<String> getOther() {
        return otherList;
    }
    public Set<Integer> getSet() {
        return indexes;
    }
}
