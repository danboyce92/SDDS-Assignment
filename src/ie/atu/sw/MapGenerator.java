package ie.atu.sw;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

public interface MapGenerator<T> {
    HashMap<String, double[]> generateMap(List<String> words, T embeddings) throws IOException;
}
