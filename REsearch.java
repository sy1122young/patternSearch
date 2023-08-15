
import java.util.Iterator;
import java.util.LinkedList;
import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.File; // Import the File class
import java.io.FileNotFoundException; // Import this class to handle errors
import java.io.InputStreamReader;
import java.util.Scanner; // Import the Scanner class to read text files

public class REsearch {
    // static Deque<DequeStructure> deque = new LinkedList<>();
    static Deque deque = new Deque();
    static String inputSequence;
    static DequeStructure current;
    static DequeStructure possiblleNext;
    static int startState = 0;
    // possition in searched string
    static int pos = 0;
    // amount moving scan in deque
    static int moveScan = 2;
    // marker
    static int marker = 0;
    static ArrayList<TableStructure> Arrayvalues = new ArrayList<TableStructure>();

    public static void main(String[] args) {
        // get input sequence
        String textFile;
        textFile = args[0];
        // System.out.println("This is text file"+ textFile);
        // gets table
        GetTable(args);
        initialSetup();
        // String textFile = "MobyDick.txt";
        String textline;
        ReadFile(textFile);
    }

    public static void initialSetup() {
        // add scan
        deque.addFirst(new DequeStructure(0, true));
        // add initial start state
        deque.addFirst(new DequeStructure(Arrayvalues.get(startState).getcurrState(), false));
    }
    static boolean matchFound = false;

    public static void ReadFile(String textFile) {
        int lineNum = 0;

        // loop through text file
        int total = 0;
        try {
            File text = new File(textFile);
            Scanner myReader = new Scanner(text);
            while (myReader.hasNextLine()) {
                while (deque.Getlength() != 0) {
                    deque.removeFirst();
                }
                initialSetup();
                lineNum++;
                String textline = myReader.nextLine();
                // set everything to lower case
                inputSequence = textline;
                search();
                if (matchFound == true) {
                    matchFound = false;
                    total++;
                    System.out.println(inputSequence);
                }
                // possition in searched string
                pos = 0;
                // amount moving scan in deque
                moveScan = 2;
                // marker
                marker = 0;
            }
            System.out.println("this is total: " + total);
        } catch (FileNotFoundException e) {
            System.out.println("could not read file");
            e.printStackTrace();
        }

    }

    public static void reset() {
        // reset to start state
        deque.addFirst(new DequeStructure(Arrayvalues.get(startState).getcurrState(), false));
        current = deque.peekFirst();
    }

    public static void search() {
        // set current to the current state
        current = deque.peekFirst();
        // check for fin
        if (Arrayvalues.get(current.getcurrState()).getInput().equals("FN")) {
            // System.out.println("we have a match and will end");
            matchFound = true;
            return;
        }
        // check for scan
        if (checkScan()) {
            return;
        }

        // loop through sequence
        if (pos < inputSequence.length()) {
            char val = inputSequence.charAt(pos);
            // get possible next states
            getPossibleNextStates();
            // set possible next state
            possiblleNext = deque.peekLast();
            String input = Arrayvalues.get(current.getcurrState()).getInput();

            if (input.equals("BR")) {
                // pop of current
                deque.removeFirst();
                // move scan by 1
                DequeStructure scan = new DequeStructure(0, true);
                moveScan++;
                deque.shift(scan, moveScan);
                // add next state
                current = possiblleNext;
                search();
            } else if (input.equals("FN")) {
                System.out.println("not an accepted string");
                return;
            } else {
                if (val == input.charAt(0)) {

                    // pop of current
                    deque.removeFirst();
                    // move scan by 1
                    DequeStructure scan = new DequeStructure(0, true);
                    deque.shift(scan, moveScan);
                    // move forward in the sequence
                    pos++;
                    search();
                }
                if (val != input.charAt(0)) {
                    if (deque.removeFirst().getScan() == true) {
                        // System.out.println("invalid string");
                    } else {
                        deque.removeLast();
                        if (moveScan != 2)
                            moveScan--;
                        search();
                    }
                }
            }
        } else {
            // loop through possible current states see if you can find FN
            if (current.getScan() != true) {
                while (current.getScan() != true) {
                    if (Arrayvalues.get(current.getcurrState()).getInput().equals("FN")) {
                        // System.out.println("we have a match");
                        matchFound = true;
                        return;
                    }
                    current = deque.removeFirst();
                }
            }
            // System.out.println("this is not an accepting string");
            return;
        }
        return;

    }

    public static void getPossibleNextStates() {
        String nextState = Arrayvalues.get(current.getcurrState()).getnextState();
        String[] nextStates = nextState.split(",");
        // loop through all states
        for (int x = 0; x < nextStates.length; x++) {
            deque.addLast(new DequeStructure(Integer.parseInt(nextStates[x]), false));
        }
    }

    public static boolean checkScan() {
        if (current.getScan() == true) {
            DequeStructure remove = deque.peekLast();
            while (remove != null && remove.getScan() != true) {
                // remove possible next states and start clean
                remove = deque.removeLast();
            }
            // set i to previos
            marker++;
            pos = marker;
            if (pos == inputSequence.length()) {
                // System.out.println("This is not an accepting string");
                return true;
            }
            // set state to zero
            reset();
        }
        return false;
    }

    // read from output
    public static void GetTable(String[] args) {
        InputStreamReader isr = new InputStreamReader(System.in);
        BufferedReader reader = new BufferedReader(isr);
        String line;
        try {
            startState = Integer.parseInt(reader.readLine());
            while ((line = reader.readLine()) != null) {
                // System.out.println(line);
                String[] values = line.split(" ");
                // System.out.println(values[0]+ values[1]+values[2]);
                Arrayvalues.add(new TableStructure(Integer.parseInt(values[0]), values[1], values[2]));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
