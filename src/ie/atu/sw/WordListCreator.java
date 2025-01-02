package ie.atu.sw;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class WordListCreator implements ListGenerator<String> {
    /**
     * Generate List runs at a time complexity of O(n)
     *The larger or smaller the file provided gets, the workload of the method increases or decreases linearly or at the same rate.
     * 
     * @param filePath
     * @return wordList List<String>
     * @throws IOException
     */
    @Override
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
