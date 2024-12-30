import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Set;

public class OutputFileBuilder {

    public void createOutputFile(List<String> changedWords, List<String> other, Set<Integer> indexes, String outputPath) {
        File file = new File(outputPath);

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {

            for (int i = 0; i < changedWords.size() + other.size(); i++) {
                if(indexes.contains(i)) {
                    //write punctuation into file
                    //Make sure to remove first element after adding it
                    writer.write(other.remove(0));
                } else {
                    //write word into file
                    //Make sure to remove first element after adding it
                    writer.write(changedWords.remove(0));
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}