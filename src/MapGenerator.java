import java.io.IOException;
import java.util.Map;

public interface MapGenerator {
    Map<String, double[]> generateMap() throws IOException;
}
