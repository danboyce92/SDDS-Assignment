import java.io.IOException;
import java.util.List;
import java.util.Map;

public interface MapGenerator<T> {
    Map<String, double[]> generateMap(List<String> words, T embeddings) throws IOException;
}
