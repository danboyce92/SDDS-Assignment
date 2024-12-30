import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.*;
import java.util.stream.IntStream;

public class Threads {
    private WordSwapper ws = new WordSwapper();
    private final List<Future<String>> futures = new ArrayList<>();

    public String swap(double[] word, List<String> googleWords, List<double[]> googleEmbeddings) {
        return ws.swapWord(word, googleWords , googleEmbeddings);
    }

    // Process the list using virtual threads
    public List<String> go(List<String> wordsToChange, List<String> googleWords, List<double[]> googleEmbeddings, HashMap<String, double[]>googleMap, HashMap<String, double[]> totalMap) {
        List<String> results = new ArrayList<>(); // List to store final results

        try (var pool = Executors.newVirtualThreadPerTaskExecutor()) {
            IntStream.range(0, wordsToChange.size()).forEach(i -> {
                String word = wordsToChange.get(i);

                if (googleMap.containsKey(word) || !totalMap.containsKey(word)) {
                    //send as is
                    Future<String> future = CompletableFuture.completedFuture(word);
                    futures.add(future);
                } else {
                    //Otherwise create a thread to process it.
                    double[] currentWordEmbed = totalMap.get(word);
                    Callable<String> task = () -> swap(currentWordEmbed, googleWords, googleEmbeddings);
                    Future<String> future = pool.submit(task);
                    futures.add(future);
                    
                }
            });
        }

        //Return results from method
        futures.forEach(future -> {
            try {
                String result = future.get();
                results.add(result);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        return results;
    }
}
