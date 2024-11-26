import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.HashSet;
import java.util.HashMap;
import java.util.Set;
import java.util.Map;

public class GoogleWordProcessor {
    List<String> wordsList = new ArrayList<>();
    public Set<String> wordSet = new HashSet<>();
    public Map<String, double[]> wordMap = new HashMap<>();


    public List<String> processWords(String filePath) throws IOException {
        createSet(filePath);

        return wordsList;
    }

    private void createSet(String filePath) throws IOException {
        //Using filePath create a Set that holds all words in file
        //Each word is placed on an individual line
        BufferedReader br = new BufferedReader(new FileReader(filePath));
        String line;
        while ((line = br.readLine()) != null) {
            wordsList.add(line);
        }
        br.close();
        //Now iterate through array and add each element to a Set.
        for (String word : wordsList) {
            wordSet.add(word);
        }
    }

    private void createMap(String filePath) throws IOException {
        //This function needs to iterate through each element in the google list
        //Then find the word embedding for that word
        //Add the word itself to the map as the key, the embedding as the value
        
    }   

}
