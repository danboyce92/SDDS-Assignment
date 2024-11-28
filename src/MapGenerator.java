import java.io.IOException;
import java.util.List;
import java.util.Map;

public interface MapGenerator {
    Map<String, double[]> generateMap(List<String> words, List<double[]> embeddings) throws IOException;
}
