import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

public class Runner {
    private Scanner s = new Scanner(System.in);
    private boolean isRunning = false;
    //Default program settings
    private String embeddingFilePath = "empty";
    private String commonWordsPath = "empty";
    private String outputFilePath = "empty";
    private String pathToInput = "empty";

    public static void main(String[] args) throws IOException {
        Runner runner = new Runner();
        runner.startInterface();
    }

    //StartInterface runs at an indeterminate time complexity
    //StartInterface uses a while loop that continues until it receives specific user input to tell it otherwise.
    //I think because of this the user is the determining factor between whether the method is O(1) or O(∞)
    public void startInterface() throws IOException {
        //Instantiate List Creator classes
        ListGenerator<String> wlc = new WordListCreator();
        ListGenerator<double[]> elc = new EmbeddingListCreator();
        GoogleEmbeddingListCreator gelc = new GoogleEmbeddingListCreator();

        //Generate Lists
        List<String> wordList = new ArrayList<>();
        List<double[]> embeddingList = new ArrayList<>(); 
        List<String> googleList = new ArrayList<>();

        //Instantiate Map Creators
        MapGenerator<List<double[]>> tmc = new TotalMapCreator();
        HashMap<String, double[]> totalHashMap = new HashMap<>();
        MapGenerator<HashMap<String, double[]>> gmc = new GoogleMapCreator();
        HashMap<String, double[]> googleHashMap = new HashMap<>();

        //Get commonEmbeddings from HashMap
        List<double[]> googleEmbeddings = new ArrayList<>();
        
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
            System.out.println("(1) Specify an Embedding File (currently: " + embeddingFilePath +")");
            System.out.println("(2) Specify a Common Words File (currently: " + commonWordsPath + ")");
            System.out.println("(3) Specify path to Input File (currently: " + pathToInput + ")");
            System.out.println("(4) Specify path to Output File (currently: " + outputFilePath + ")");
            System.out.println("(5) Run Program ");
            System.out.println("(0) Quit");
            System.out.println("Select Option [0-5]>");

            String input = s.nextLine();
            int userInput = Integer.parseInt(input);
            switch (userInput) {
                case 1:
                    //Allows user to specify embedding file path
                    System.out.println("Enter the path for the word embeddings file: ");
                    embeddingFilePath = s.nextLine();
                    //Populate Lists now that path has been set
                    wordList = wlc.generateList(embeddingFilePath);
                    embeddingList = elc.generateList(embeddingFilePath);
                    System.out.println("The path you have chosen is " + embeddingFilePath);
                    break;
                case 2:
                    //Allows user to specify output file path
                    System.out.print("Enter the path for the common words file: ");
                    outputFilePath = s.nextLine();
                    //Populate Lists and Maps now that commonWord File has been set
                    googleList = wlc.generateList(commonWordsPath);
                    totalHashMap = tmc.generateMap(wordList, embeddingList);
                    googleHashMap = gmc.generateMap(googleList, totalHashMap);
                    googleEmbeddings = gelc.createEmbeddingList(totalHashMap, googleList);
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

                        System.out.println("");
                        System.out.println("***");
                        System.out.println("File processed, please examine file at the output path provided");
                        System.out.println("***");
                        System.out.println("");

                    } else {
                        System.out.println("");
                        System.out.println("***");
                        System.out.println("Please ensure you have set a path for all required..");
                        System.out.println("***");
                        System.out.println("");
                    }
                    break;
                case 0:
                    //Allows user to quit program
                    System.out.println("Now closing..");
                    isRunning = false;
                    break;
                default:
                    //In case a user enters something other than a number between 0-5
                    System.out.println("Please select a number between 0-5");
                    break;
            }
        }
    }

    //PathCheck uses a time complexity of O(1)
    //It is constant because there is a fixed number of operations no matter the input size.
    private boolean pathCheck(String embeddingPath, String commonEmbeddingPath, String inputPath, String outputPath) {
        if (embeddingPath.equals("empty") 
            || commonEmbeddingPath.equals("empty") 
            || inputPath.equals("empty") 
            || outputPath.equals("empty")) {
            return false;
        } else return true;
    }
    
}
