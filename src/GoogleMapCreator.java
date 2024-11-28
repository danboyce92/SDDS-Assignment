import java.io.IOException;
import java.util.Map;
import java.util.HashMap;
import java.util.List;

public class GoogleMapCreator implements MapGenerator<HashMap<String, double[]>> {
    private HashMap<String, double[]> map = new HashMap<>();


    public Map<String, double[]> generateMap(List<String> googleWords, HashMap<String, double[]> hashMap) throws IOException {
        for (int i = 0; i < googleWords.size(); i++) {
            map.put(googleWords.get(i), hashMap.get(googleWords.get(i)));
        }

        return map;
    }

}
