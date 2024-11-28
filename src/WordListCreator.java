import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class WordListCreator implements ListGenerator<String> {

    public List<String> generateList(String filePath) throws IOException {
        List<String> wordList = new ArrayList<>();
        
        BufferedReader br = new BufferedReader(new FileReader(filePath));
        String line;
        while ((line = br.readLine()) != null) {
            String[] parts = line.split(",");
            if (parts.length >= 1) {
                wordList.add(parts[0].trim());
            }
        }
        br.close();

        return wordList;  
    }

}
