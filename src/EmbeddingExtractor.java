import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class EmbeddingExtractor {

    private static String[] wordArray = new String[59602];
    private static double[][] embeddingArray = new double[59602][50];
    
    //This method splits up and organises the words and each word's embeddings into separate arrays
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

        wordArray = finalWordsArray;
        embeddingArray = finalEmbeddingsArray;
    }

    public String[] getWords() {
        return wordArray;
    }

    public double[][] getAllEmbedding() {
        return embeddingArray;
    }

    //This method takes a word as input and finds it's index in the word array
    //Or returns -1 if it does not exist
    public int findWord(String chosenWord) {
        for (int i = 0; i < wordArray.length; i++) {
            if (chosenWord.equals(wordArray[i])) {
                return i;
            }
        }
        return -1;
    }

    //This method should take in the index of the chosen word
    //Then return the appropriate embedding
    public double[] getRelevantEmbed(int index) {
        return embeddingArray[index];  
    }
}
