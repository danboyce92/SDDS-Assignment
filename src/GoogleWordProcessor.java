import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
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

    public ArrayList<String> missingWords = new ArrayList<>();
    public double[][] missingWordEmbeddings = new double[498][];

    public List<String> googleWordsArray = new ArrayList<>();
    public double[][] googleEmbeddingsArray = new double[1000][];


    public String pathToEmbeddingsFile = "./word-embeddings.txt";
    public List<String> totalWordArray = new ArrayList<>();
    public double[][] totalEmbeddingArray = new double[400000][];

    private List<String> wordList;
    private List<double[]> embeddingList;

    public List<String>  processWords(String googleFilePath, String totalEmbedPath) throws IOException {
        createWordArray(googleFilePath);
        createMissingWordArray("./missingWords.txt");
        extractEmbeddings(totalEmbedPath);

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

    private void createMissingWordArray(String missingFilePath) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(missingFilePath));
        String line;
        while ((line = br.readLine()) != null) {
            missingWords.add(line.trim());
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
        // Initialize missingWords as an empty list first
        missingWords.clear(); // clear any previous values
    
        //CreateMap uses the smallerTotal list to identify which words are not present.
        //I need to change this function 

        for (String word : googleWordsArray) {
            int index = totalWordArray.indexOf(word);
            if (index == -1) {
                // If the word is missing in totalWordArray, add it to missingWords
                missingWords.add(word);
                System.err.println("Error: Word not found in totalWordList: " + word);
                continue; // Skip adding to the map if the word is missing
            }
            // If the word is found, add it to wordMap with its corresponding embedding
            double[] relevantEmbedding = totalEmbeddingArray[index];
            wordMap.put(word, relevantEmbedding);
        }
    }
    
    public void extractEmbeddings(String providedPath) throws IOException {
        wordList = new ArrayList<>();
        embeddingList = new ArrayList<>();

        // BufferedReader is used to interpret the lines of text in the provided .txt file
        BufferedReader br = new BufferedReader(new FileReader(providedPath));
        String line;
        while ((line = br.readLine()) != null) {
            String[] parts = line.split(",");
            if (parts.length > 1) {
                wordList.add(parts[0].trim());
                double[] embeddings = new double[parts.length - 1];
                for (int i = 1; i < parts.length; i++) {
                    embeddings[i - 1] = Double.parseDouble(parts[i].trim());
                }
                embeddingList.add(embeddings);
            }
        }
        br.close();
    }
    


    // public void findMissingWordEmbeddings(List<String> words) {
    //     int counter = 0;
    //     for (String word : words) {
    //         //Get index of totalWordArray use index to take from totalEmbeddingArray
    //         int index = totalWordArray.indexOf(word);
    //         missingWordEmbeddings[counter] = totalEmbeddingArray[index];
    //         counter++;
    //     }
    // }

    // public static void writeSolution(ArrayList<String> list, double[][] array) {
    //     // Validate inputs
    //     if (list == null || array == null || list.size() != array.length) {
    //         throw new IllegalArgumentException("ArrayList and double[][] must be non-null and have matching sizes.");
    //     }
        
    //     try (BufferedWriter writer = new BufferedWriter(new FileWriter("Solution.txt"))) {
    //         System.out.println("Starting to write to Solution.txt...");
    //         for (int i = 0; i < list.size(); i++) {
    //             System.out.println("Writing row " + i);
    //             // Write the string from the ArrayList
    //             writer.write(list.get(i));

    //             // Append the elements of the corresponding double[] separated by commas
    //             double[] innerArray = array[i];
    //             for (double value : innerArray) {
    //                 writer.write(", " + value);
    //             }

    //             // Move to the next line
    //             writer.newLine();
    //         }
    //         writer.flush(); // Explicit flush
    //         System.out.println("Finished writing to Solution.txt.");
    //     } catch (IOException e) {
    //         System.err.println("Error writing to Solution.txt: " + e.getMessage());
    //     }
    // }




}
