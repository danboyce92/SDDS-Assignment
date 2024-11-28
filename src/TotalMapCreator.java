import java.io.IOException;
import java.util.Map;
import java.util.HashMap;
import java.util.List;

public class TotalMapCreator implements MapGenerator {
    private HashMap<String, double[]> map = new HashMap<>();

    public Map<String, double[]> generateMap(List<String> words, List<double[]> embeddings) throws IOException {
        for (int i = 0; i < words.size(); i++) {
            map.put(words.get(i), embeddings.get(i));
        }

        return map;
    }

}
