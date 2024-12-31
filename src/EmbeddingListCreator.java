import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class EmbeddingListCreator implements ListGenerator<double[]> {
    private List<double[]> embeddingList = new ArrayList<>();

    //Generate List runs at a time complexity of O(n)
    //The larger or smaller the file provided gets, the workload of the method increases or decreases linearly or at the same rate.
    public List<double[]> generateList(String filePath) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(filePath));
        String line;
        while ((line = br.readLine()) != null) {
            String[] parts = line.split(",");
            if (parts.length > 1) {
                double[] embeddings = new double[parts.length - 1];
                for (int i = 1; i < parts.length; i++) {
                    embeddings[i - 1] = Double.parseDouble(parts[i].trim());
                }
                embeddingList.add(embeddings);
            }
        }
        br.close();

        return embeddingList;  
    }
}
