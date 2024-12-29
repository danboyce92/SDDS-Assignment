import java.util.List;

public class WordSwapper {
    
    public String swapWord(double[] wordToChange,List<String> googleWords, List<double[]> googleEmbeddings) {
        double[] closest = null;
        double highest = -1;
        String replacement = null;

        for (double[] word : googleEmbeddings) {
            double current = cosSimilarity(wordToChange, word);
            if (current > highest) {
                highest = current;
                closest = word;
            }
        }
        //Now using closest, find it's index in the embedding list and take the word from that index in the word list
        if (closest == null) {
            return "closest embedding not set..";
        } else {
            int index = googleEmbeddings.indexOf(closest);
            replacement = googleWords.get(index);
        }
        return replacement;
    }

    private double cosSimilarity(double[] v1, double[] v2) {
        double dotProduct = 0.0;
        double normV1 = 0.0;
        double normV2 = 0.0;
        for (int i = 0; i < v1.length; i++) {
            dotProduct += v1[i] * v2[i];
            normV1 += Math.pow(v1[i], 2);
            normV2 += Math.pow(v2[i], 2);
        }
        return dotProduct / (Math.sqrt(normV1) * Math.sqrt(normV2));
    }

}
