import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Set;

public class OutputFileBuilder {

    /**
     * CreateOutputFile uses time complexity of O(n)
     * It has a while loop that runs n times where n = the size of the words + punctuation list
     * Every other operation inside the loop appears to be O(1) Constant
     *
     * @param changedWords
     * @param other
     * @param indexes
     * @param lineSet
     * @param capCheck
     * @param outputPath
     * @return void
     */
    public void createOutputFile(List<String> changedWords, List<String> other, Set<Integer> indexes, Set<Integer> lineSet, Set<Integer> capCheck, String outputPath) {
        File file = new File(outputPath);

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
            int intialSize = changedWords.size() + other.size();
            int totalIndex = 0;
            int wordIndex = 0;
            int puncIndex = 0;

            while (totalIndex <= intialSize) {
                if (lineSet.contains(totalIndex)) {
                    writer.newLine();
                }
                else if (indexes.contains(totalIndex)) {
                    writer.write(other.get(puncIndex));
                    puncIndex++;
                }
                else {
                    if(capCheck.contains(totalIndex)) {
                        String word = changedWords.get(wordIndex);
                        word = word.substring(0, 1).toUpperCase() + word.substring(1);
                        writer.write(word);
                    } else {
                        writer.write(changedWords.get(wordIndex));
                    }
                    wordIndex++;
                }
                totalIndex++;
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}