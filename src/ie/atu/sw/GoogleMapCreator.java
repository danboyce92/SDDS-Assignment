package ie.atu.sw;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

public class GoogleMapCreator implements MapGenerator<HashMap<String, double[]>> {
    private HashMap<String, double[]> map = new HashMap<>();

    /**
     * GenerateMap runs at the time complexity of O(n)
     * The amount of loop iterations depends on the size of the googleWords list passed in.
     *
     * @param googleWords
     * @param hashMap
     * @return map Map<String, double[]>
     * @throws IOException
     */
    @Override
     public HashMap<String, double[]> generateMap(List<String> googleWords, HashMap<String, double[]> hashMap) throws IOException {
        for (int i = 0; i < googleWords.size(); i++) {
            map.put(googleWords.get(i), hashMap.get(googleWords.get(i)));
        }
        return map;
    }
}
