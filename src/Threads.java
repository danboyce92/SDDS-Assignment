import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;
import java.util.stream.IntStream;

public class Threads {
    List<Future<String>> futures = new ArrayList<>();  // List to store Future<String> objects


    //Stick actual swap() method in to see if it works
    public String swap(String word) {
        return word;
    }

    public void go(List<String> words) {


        try (var pool = Executors.newVirtualThreadPerTaskExecutor()) {
            IntStream.range(0, words.size()).forEach(i -> {
                String word = words.get(i);

                Callable<String> task = new Callable<String>() {
                    @Override
                    public String call() throws Exception {
                        return swap(word);
                    }
                };

                // Submit a virtual thread for each word and store the Future<String> in the list
                Future<String> future = pool.submit(task);
                futures.add(future);
            });
        }


        // futures.forEach(future -> {
        //     try {
        //         String result = future.get();
        //         // Do something with the result if needed
        //         System.out.println(result);  // Example: printing the result
        //     } catch (Exception e) {
        //         // Handle exceptions (e.g., interrupted or execution errors)
        //         e.printStackTrace();
        //     }
        // });

        //What should I do with the results? Return them?

    }
}