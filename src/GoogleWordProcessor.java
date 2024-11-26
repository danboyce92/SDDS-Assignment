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
    public String[] totalWordArray = new String[59602];
    public List<String> totalWordList = new ArrayList<>();
    public double[][] totalEmbeddingArray = new double[59602][];


    public double[]  processWords(String googleFilePath, String totalEmbedPath) throws IOException {
        createWordArray(googleFilePath);
        extractEmbeddings(totalEmbedPath);

        totalWordList = Arrays.asList(totalWordArray);

        // createMap();

        return totalEmbeddingArray[0];
    }

    private void createWordArray(String googleFilePath) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(googleFilePath));
        String line;
        while ((line = br.readLine()) != null) {
            googleWordsArray.add(line);
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
            //Find the word's index in the totalWordArray
            //Use that index to identify it's embedding in totalEmbeddingArray
            //Create a Map entry with word as Key and embedding as value

            int index = totalWordList.indexOf(word);
            //Need to account for if word returns -1!!!!!!!!
            double[] relevantEmbedding = totalEmbeddingArray[index];
            wordMap.put(word, relevantEmbedding);
        }
    }  
    
    public void extractEmbeddings(String providedPath) throws IOException {
        int maxLines = 59602;
        String[] wordsArray = new String[maxLines];
        double[][] embeddingsArray = new double[maxLines][];
        int count = 0;
        //BufferedReader is used to interpret the lines of text in the provided .txt file
        BufferedReader br = new BufferedReader(new FileReader(providedPath));
        String line;
        while ((line = br.readLine()) != null) {
            String[] parts = line.split(",");
            if (parts.length > 1) {
                wordsArray[count] = parts[0].trim();
                double[] embeddings = new double[parts.length - 1];
                for (int i = 1; i < parts.length; i++) {
                    embeddings[i - 1] = Double.parseDouble(parts[i].trim());
                }
                embeddingsArray[count] = embeddings;
                count++;
            }
        }
        br.close();
        
        // Resize the arrays to the actual number of lines read
        String[] finalWordsArray = new String[count];
        double[][] finalEmbeddingsArray = new double[count][50];
        
        System.arraycopy(wordsArray, 0, finalWordsArray, 0, count);
        System.arraycopy(embeddingsArray, 0, finalEmbeddingsArray, 0, count);

        totalWordArray = finalWordsArray;
        totalEmbeddingArray = finalEmbeddingsArray;
    }



}
