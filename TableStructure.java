import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Iterator;
public class TableStructure {
    private int currState;
    private String input;
    private String nextState;
    private boolean scan;
    public TableStructure(int currState, String input, String nextState){
        this.currState =  currState;
        this.input = input;
        this.nextState = nextState;
        this.scan = scan;
    }
    public int getcurrState(){
        return currState;
    }
    public String getInput(){
        return input;
    }
    public String getnextState(){
        return nextState;
    }
    public Boolean getScan(){
        return scan;
    }
}
