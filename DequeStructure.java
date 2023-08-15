public class DequeStructure {
    private int currentState;
    private boolean scan;

    public DequeStructure(int currentState, boolean scan){
        this.currentState = currentState;
        this.scan = scan;
    }
    public int getcurrState(){
        return currentState;
    }
    public boolean getScan(){
        return scan;
    }
}
