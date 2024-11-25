import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class EmbeddingExtractor {

    private String[] wordArray;
    private double[][] embeddingArray;

    //This method splits up and organizes the words and each word's embeddings into separate arrays
    public void extractEmbeddings(String providedPath) throws IOException {
        List<String> wordsList = new ArrayList<>();
        List<double[]> embeddingsList = new ArrayList<>();
        
        // BufferedReader is used to interpret the lines of text in the provided .txt file
        BufferedReader br = new BufferedReader(new FileReader(providedPath));
        String line;
        while ((line = br.readLine()) != null) {
            String[] parts = line.split(",");
            if (parts.length > 1) {
                wordsList.add(parts[0].trim());
                double[] embeddings = new double[parts.length - 1];
                for (int i = 1; i < parts.length; i++) {
                    embeddings[i - 1] = Double.parseDouble(parts[i].trim());
                }
                embeddingsList.add(embeddings);
            }
        }
        br.close();

        // Convert lists to arrays
        wordArray = wordsList.toArray(new String[0]);
        embeddingArray = embeddingsList.toArray(new double[0][]);
    }

    public String[] getWords() {
        return wordArray;
    }

    public double[][] getAllEmbedding() {
        return embeddingArray;
    }

    // This method takes a word as input and finds its index in the word array
    // Or returns -1 if it does not exist
    public int findWord(String chosenWord) {
        for (int i = 0; i < wordArray.length; i++) {
            if (chosenWord.equals(wordArray[i])) {
                return i;
            }
        }
        return -1;
    }

    // This method should take in the index of the chosen word
    // Then return the appropriate embedding
    public double[] getRelevantEmbed(int index) {
        return embeddingArray[index];
    }
}
