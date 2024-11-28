import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class Runner {
    private Scanner s = new Scanner(System.in);
    private boolean isRunning = false;
    //Default program settings
    private String embeddingFilePath = "./word-embeddings.txt";
    private String outputFilePath = "./searchResults.txt";
    private String wordToSearch = "";

    public static void main(String[] args) throws IOException {
        Runner runner = new Runner();
        // runner.startInterface();

        WordListCreator wlc = new WordListCreator();
        EmbeddingListCreator elc = new EmbeddingListCreator();
        List<String> wordList = new ArrayList<>(wlc.generateList("./amend-total.txt"));
        List<double[]> embeddingList = new ArrayList<>(elc.generateList("./amend-total.txt")); 

        TotalMapCreator tmc = new TotalMapCreator();
        HashMap<String, double[]> hashMap = new HashMap<>(tmc.generateMap(wordList, embeddingList));
        System.out.println(Arrays.toString(hashMap.get("has")));





        // GoogleWordProcessor gwp = new GoogleWordProcessor();
        // // gwp.processWords("./google-1000.txt");
        // // System.out.println(gwp.wordSet.contains("yes"));
        // System.out.println(gwp.processWords("./google-1000.txt", "./total-list.txt"));




    }

        public void startInterface() throws IOException {
        //Initialization of program's other classes
        EmbeddingExtractor ee = new EmbeddingExtractor();
        GoogleWordProcessor gwp = new GoogleWordProcessor();
        // Comparator c = new Comparator(searchSize);
        // EmbeddingConverter ec = new EmbeddingConverter(searchSize);
        // OutputManager om = new OutputManager();
        
        isRunning = true;
        while(isRunning) {
            System.out.println("************************************************************");
            System.out.println("*     ATU - Dept. of Computer Science & Applied Physics    *");
            System.out.println("*                                                          *");
            System.out.println("*          Similarity Search with Word Embeddings          *");
            System.out.println("*                                                          *");
            System.out.println("************************************************************");
            System.out.println("PLEASE MOVE THROUGH THESE SEQUENTIALLY TO AVOID POTENTIAL ERRORS");
            System.out.println("");
            System.out.println("(1) Specify Embedding File (currently: " + embeddingFilePath +")");
            System.out.println("(2) Specify an Output File (currently: " + outputFilePath + ")");
            System.out.println("(3) Enter a Text Passage");
            System.out.println("(4)");
            System.out.println("(5)");
            System.out.println("(6)");
            System.out.println("(7) Run Modification");
            System.out.println("(0) Quit");
            System.out.println("Select Option [0-7]>");

            String input = s.nextLine();
            int userInput = Integer.parseInt(input);
            switch (userInput) {
                case 1:
                    //Allows user to specify embedding file path
                    System.out.println("Enter the path for the word embeddings file: ");
                    embeddingFilePath = s.nextLine();
                    System.out.println("The path you have chosen is " + embeddingFilePath);
                    break;
                case 2:
                    //Allows user to specify output file path
                    System.out.print("Enter the output file path: ");
                    outputFilePath = s.nextLine();
                    System.out.println("The path you have chosen is " + outputFilePath);
                    break;
                case 3:
                    //Allows user to type the sentence they want altered
                    System.out.print("Enter the text passage to modify: ");
                    wordToSearch = s.nextLine();
                    System.out.println("");
                    break;
                case 4:
                    //Currently not in use
                    System.out.print("Currently empty...");
                    break;
                case 5:
                    //Currently not in use
                    System.out.println("Currently empty...");
                    break;
                case 6:
                    //Currently not in use
                    System.out.println("Currently empty...");
                    break;
                case 7: 
                    //Run program
                    System.out.println("Where all components come together and execute program");
                    break;
                case 0:
                    //Allows user to quit program
                    System.out.println("Now closing..");
                    isRunning = false;
                    break;
                default:
                    //In case a user enters something other than a number between 0-7
                    System.out.println("Please select a number between 0-7");
                    break;
            }
        }
    }

    
}
