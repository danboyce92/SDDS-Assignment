import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class InputParser {
    public static String processFile(String filepath) throws IOException {
        StringBuilder result = new StringBuilder();
        BufferedReader reader = new BufferedReader(new FileReader(filepath));
        String line;

        // Pattern to match words and punctuation separately
        // [a-zA-Z]+ matches words, and [^a-zA-Z]+ matches everything else (punctuation, spaces, etc.)
        Pattern pattern = Pattern.compile("[a-zA-Z]+|[^a-zA-Z]+");

        // Read file line by line
        while ((line = reader.readLine()) != null) {
            // Find all words and punctuation in the line
            Matcher matcher = pattern.matcher(line);
            while (matcher.find()) {
                String match = matcher.group();

                // If the match is a word, convert it to uppercase
                if (match.matches("[a-zA-Z]+")) {
                    result.append(match.toUpperCase());
                } else {
                    // If it's punctuation, append it as is
                    result.append(match);
                }
            }
            result.append("\n"); // Maintain the line breaks
        }
        reader.close();
        return result.toString().trim(); // Return the result
    }
}
