import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Arrays;

public class Runner {
    private Scanner s = new Scanner(System.in);
    private boolean isRunning = false;
    //Default program settings
    private String embeddingFilePath = "./word-embeddings.txt";
    private String outputFilePath = "./searchResults.txt";
    private String pathToInput = "../text-files/sample.txt";


    public static void main(String[] args) throws IOException {
        Runner runner = new Runner();
        // runner.startInterface();



        WordListCreator wlc = new WordListCreator();
        EmbeddingListCreator elc = new EmbeddingListCreator();
        GoogleEmbeddingListCreator gelc = new GoogleEmbeddingListCreator();

        List<String> wordList = new ArrayList<>(wlc.generateList("../text-files/amend-total.txt"));
        List<String> googleList = new ArrayList<>(wlc.generateList("../text-files/google-1000.txt"));
        List<double[]> embeddingList = new ArrayList<>(elc.generateList("../text-files/amend-total.txt")); 

        TotalMapCreator tmc = new TotalMapCreator();
        HashMap<String, double[]> totalHashMap = new HashMap<>(tmc.generateMap(wordList, embeddingList));

        GoogleMapCreator gmc = new GoogleMapCreator();
        HashMap<String, double[]> googleHashMap = new HashMap<>(gmc.generateMap(googleList, totalHashMap));
        List<double[]> googleEmbeddings = new ArrayList<>(gelc.createEmbeddingList(totalHashMap, googleList));
       
        //^^^ The above is necessary at program launch
        //Next should take care of everything that needs to be in place for swap to run
        //Path variables etc that are dynamic make sure they are set.




        //^^^ The above is necessary to run swap with no errors
        //Next you should run the swap ( Virtual Threads )
        //And return results to a new file specified in the previous section


        InputParser ip = new InputParser();
        //System.out.println(ip.processFile("../text-files/sample.txt"));



        WordSwapper ws = new WordSwapper();





    }

        public void startInterface() throws IOException {
        //Initialization of program's other classes
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
            System.out.println("(3) Specify path to Input File (currently: " + pathToInput + ")");
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
                    System.out.print("Enter path to file to be processed: ");
                    pathToInput = s.nextLine();
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
