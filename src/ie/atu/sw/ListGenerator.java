package ie.atu.sw;
import java.io.IOException;
import java.util.List;

public interface ListGenerator<T>  {
    List<T> generateList(String path) throws IOException;
}
