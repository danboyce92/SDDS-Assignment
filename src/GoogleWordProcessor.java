import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.HashSet;
import java.util.HashMap;
import java.util.Set;
import java.util.Map;

public class GoogleWordProcessor {
    // List<String> wordsList = new ArrayList<>();
    public Set<String> wordSet = new HashSet<>();
    public Map<String, double[]> wordMap = new HashMap<>();

    public List<String> googleWordsArray = new ArrayList<>();
    public double[][] googleEmbeddingsArray = new double[1000][];

    public String pathToEmbeddingsFile = "./word-embeddings.txt";
    public List<String> totalWordArray = new ArrayList<>();

    public double[][] totalEmbeddingArray = new double[59602][];


    public List<String>  processWords(String googleFilePath, String totalEmbedPath) throws IOException {
        createWordArray(googleFilePath);
        extractEmbeddings(totalEmbedPath);



        // createMap();

        return totalWordArray;
    }

    private void createWordArray(String googleFilePath) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(googleFilePath));
        String line;
        while ((line = br.readLine()) != null) {
            googleWordsArray.add(line.trim());
        }
        br.close();
    }

    private void createEmbeddingArray() {
        //In order to use this function I need to create a Map of all word embeddings
    }

    // private void createSet(String filePath) throws IOException {
    //     //Using filePath create a Set that holds all words in file
    //     //Each word is placed on an individual line
    //     BufferedReader br = new BufferedReader(new FileReader(filePath));
    //     String line;
    //     while ((line = br.readLine()) != null) {
    //         wordsList.add(line);
    //     }
    //     br.close();
    //     //Now iterate through array and add each element to a Set.
    //     for (String word : wordsList) {
    //         wordSet.add(word);
    //     }
    // }

    private void createMap() throws IOException {
        for (String word : googleWordsArray) {
            int index = totalWordArray.indexOf(word);
            if (index == -1) {
                System.err.println("Error: Word not found in totalWordList: " + word);
                continue; // Skip this word or handle it accordingly
            }
            double[] relevantEmbedding = totalEmbeddingArray[index];
            wordMap.put(word, relevantEmbedding);
        }
    }
    
    public void extractEmbeddings(String providedPath) throws IOException {
        ArrayList<String> wordsList = new ArrayList<>();
        ArrayList<double[]> embeddingsList = new ArrayList<>();
    
        // BufferedReader is used to interpret the lines of text in the provided .txt file
        BufferedReader br = new BufferedReader(new FileReader(providedPath));
        String line;
        while ((line = br.readLine()) != null) {
            // Split the line using spaces as the delimiter
            String[] parts = line.split("\\s+");
            if (parts.length > 1) {
                wordsList.add(parts[0].trim()); // The first word
                double[] embeddings = new double[parts.length - 1];
                for (int i = 1; i < parts.length; i++) {
                    embeddings[i - 1] = Double.parseDouble(parts[i].trim()); // Parse the embeddings
                }
                embeddingsList.add(embeddings);
            }
        }
        br.close();
    
        // Convert ArrayLists to arrays if needed
        String[] finalWordsArray = wordsList.toArray(new String[0]);
        double[][] finalEmbeddingsArray = embeddingsList.toArray(new double[0][]);
    
        totalWordArray = wordsList;
        totalEmbeddingArray = finalEmbeddingsArray;
    }



}
