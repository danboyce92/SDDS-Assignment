import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class GoogleEmbeddingListCreator {
    private List<double[]> finishedListOfEmbeddings = new ArrayList<>();
    
    //CreateEmbeddingList runs at a time complexity of O(n)
    //The amount of work it needs to do is based on the size of googleWords argument passed in.
    public List<double[]> createEmbeddingList(HashMap<String, double[]> allWordsMap, List<String> googleWords) {
        for (String word : googleWords) {
            double[] embedding = allWordsMap.get(word);
            finishedListOfEmbeddings.add(embedding);
        }
        return finishedListOfEmbeddings;
    }
}
