import java.io.IOException;
import java.util.Map;
import java.util.HashMap;
import java.util.List;

public class TotalMapCreator implements MapGenerator<List<double[]>> {
    private HashMap<String, double[]> map = new HashMap<>();

    //GenerateMap runs at the time complexity of O(n)
    //The amount of loop iterations depends on the size of the words list passed in.
    public Map<String, double[]> generateMap(List<String> words, List<double[]> embeddings) throws IOException {
        for (int i = 0; i < words.size(); i++) {
            map.put(words.get(i), embeddings.get(i));
        }
        return map;
    }
}
