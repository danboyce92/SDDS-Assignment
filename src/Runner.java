import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;
import java.util.Set;
import java.util.ArrayList;

public class Runner {
    private Scanner s = new Scanner(System.in);
    private boolean isRunning = false;
    //Default program settings
    private String embeddingFilePath = "../text-files/amend-total.txt";
    private String commonWordsPath = "../text-files/google-1000.txt";
    private String outputFilePath = "./searchResults.txt";
    private String pathToInput = "../text-files/sample.txt";


    public static void main(String[] args) throws IOException {
        Runner runner = new Runner();
        runner.startInterface();

        // InputParser ip = new InputParser();
        // //System.out.println(ip.processFile("../text-files/sample.txt"));

        // Threads t = new Threads();

        // List<String> testList = new ArrayList<>();
        // testList.add("hello");
        // testList.add("chocolate");
        // testList.add("rich");
        // testList.add("business");
        // testList.add("sequence");

        // System.out.println(t.go(testList, googleList, googleEmbeddings, googleHashMap, totalHashMap));
    }

        public void startInterface() throws IOException {
        //Instantiate List Creator classes
        WordListCreator wlc = new WordListCreator();
        EmbeddingListCreator elc = new EmbeddingListCreator();
        GoogleEmbeddingListCreator gelc = new GoogleEmbeddingListCreator();

        //Generate Lists
        List<String> wordList = new ArrayList<>(wlc.generateList(embeddingFilePath));
        List<double[]> embeddingList = new ArrayList<>(elc.generateList(embeddingFilePath)); 
        List<String> googleList = new ArrayList<>(wlc.generateList(commonWordsPath));

        //Instantiate Map Creators and generate them
        TotalMapCreator tmc = new TotalMapCreator();
        HashMap<String, double[]> totalHashMap = new HashMap<>(tmc.generateMap(wordList, embeddingList));
        GoogleMapCreator gmc = new GoogleMapCreator();
        HashMap<String, double[]> googleHashMap = new HashMap<>(gmc.generateMap(googleList, totalHashMap));

        //Get commonEmbeddings from HashMap
        List<double[]> googleEmbeddings = new ArrayList<>(gelc.createEmbeddingList(totalHashMap, googleList));
        
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
            System.out.println("(2) Specify an Common Words File (currently: " + commonWordsPath + ")");
            System.out.println("(3) Specify path to Input File (currently: " + pathToInput + ")");
            System.out.println("(4) Specify path to Output File (currently: " + outputFilePath + ")");
            System.out.println("(5) Run Program ");
            System.out.println("(6)");
            System.out.println("(7)");
            System.out.println("(8)");
            System.out.println("(0) Quit");
            System.out.println("Select Option [0-8]>");

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
                    System.out.print("Enter the path for the common words file: ");
                    outputFilePath = s.nextLine();
                    System.out.println("The path you have chosen is " + commonWordsPath);
                    break;
                case 3:
                    //Allows user to type the sentence they want altered
                    System.out.print("Enter path to input file to be processed: ");
                    pathToInput = s.nextLine();
                    System.out.println("The path you have chosen is " + pathToInput);
                    break;
                case 4:
                    //Allows user to specify output file path
                    System.out.print("Enter the output file path: ");
                    outputFilePath = s.nextLine();
                    System.out.println("The path you have chosen is " + outputFilePath);
                    break;
                case 5:
                    //Run Program
                    //Remember to put a check in here to verify all necessary paths have been provided.

                    if (pathCheck(embeddingFilePath, commonWordsPath, pathToInput, outputFilePath)) {
                        //Run
                        InputParser ip = new InputParser();
                        ip.processFile(pathToInput);
                        List<String> wordsToProcess = ip.getWords();
                        List<String> otherList = ip.getOther();
                        Set<Integer> indexes = ip.getPuncSet();
                        Set<Integer> lineSet = ip.getLineSet();
                        Set<Integer> capCheck = ip.getCapsSet();

                        Threads t = new Threads();
                        List<String> changedWords = t.go(wordsToProcess, googleList, googleEmbeddings, googleHashMap, totalHashMap);

                        OutputFileBuilder ofb = new OutputFileBuilder();
                        ofb.createOutputFile(changedWords, otherList, indexes, lineSet, capCheck, outputFilePath);

                    } else {
                        System.out.print("Please ensure you have set a path for all required..");
                    }
                    System.out.print("File processed, please examine file at the output path provided");
                    System.out.println("");
                    break;
                case 6:
                    //Currently not in use
                    System.out.println("Currently empty...");
                    break;
                case 7:
                    //Currently not in use
                    System.out.println("Currently empty...");
                    break;
                case 8: 
                    //Currently not in use
                    System.out.println("Currently empty...");
                    break;
                case 0:
                    //Allows user to quit program
                    System.out.println("Now closing..");
                    isRunning = false;
                    break;
                default:
                    //In case a user enters something other than a number between 0-8
                    System.out.println("Please select a number between 0-8");
                    break;
            }
        }
    }

    private boolean pathCheck(String embeddingPath, String commonEmbeddingPath, String inputPath, String outputPath) {
        if (embeddingPath == "empty" || commonEmbeddingPath == "empty" || inputPath == "empty" || outputPath == "empty") {
            return false;
        } else return true;
    }
    
}
