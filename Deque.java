import java.util.Currency;

public class Deque {
    DequeStructure tp;
    protected Node head;

    public Deque() {
        head = null;
    }

    // peek first
    public DequeStructure peekFirst() {
        return head.value;
    }

    // peak last
    public DequeStructure peekLast() {
        // traverse list
        Node current = head;
        while (current.next != null) {
            current = current.next;
        }
        return current.value;
    }

    // add at strt of the list
    public void addFirst(DequeStructure c) {
        if(c ==null){
            System.out.println("c is equal to null ADD FIRST");
        }
        // create node
        Node newABCNode = new Node(c);
        // check if head is null
        if (head == null) {
            head = newABCNode;
        } else {
            // make node point to same place as head
            newABCNode.next = head;
            head = newABCNode;
        }
    }

    public void addLast(DequeStructure c) {
        if(c ==null){
            System.out.println("c is equal to null ADD LAST");
        }
        Node newAbcNode = new Node(c);
        // traverse list
        Node current = head;
        while (current.next != null) {
            current = current.next;
        }
        current.next = newAbcNode;
    }
    public void shift(DequeStructure c, int pos){
        if(c == null){
            System.out.println("shift is starting null");
        }
        c = removeFirstOccurrence(c);
        if(c == null){
            System.out.println("shift is add null");
        }
        insert(c, pos);
    }
    

    public void insert(DequeStructure c, int pos) {
        if(c ==null){
            System.out.println("c is equal to null this is INSERT");
        }
        if (isEmpty()) {
            return;
        }
        if(pos -1 == 0){
            addFirst(c);
            return;
        }
        if(pos > Getlength()){
            addLast(c);
            return;
        }
        
        // remove middle
        Node previous = head;
        Node current = head.next;

        int i = 0;
        while (current != null) {
            i++;
            if (i == pos - 1) {//might be error here
                Node toinsert = new Node(c);
                previous.next = toinsert;
                toinsert.next = current;
                return;
            } else {
                previous = previous.next;
                current = current.next;
            }
        }
    }

    /**
     * HAS VALUE IN LIST
     * 
     * @param c Character
     * @return A boolean that tells you the thing
     */
    public boolean has(DequeStructure c) {
        boolean scan = true;
        Node current = head;
        // iterate through list
        while (current != null) {
            if (current.value.getScan() == scan) {
                return true;
            }
            current = current.next;
        }
        return false;
    }
   

    // remove Head
    public DequeStructure removeFirst() {
        if (isEmpty()) {
            return null;
        }
        DequeStructure removed;
        removed = head.value;
        head = head.next;
        return removed;
    }

    // remove tail
    public DequeStructure removeLast() {
        if (isEmpty()) {
            return null;
        }
        DequeStructure removed;
        // travers
        int len = Getlength();
        Node previous = head;
        Node current = head.next;
        int i = 0;
        while (current != null) {
            i++;
            if (i == len - 1) {
                Node toRemove = current;
                removed = toRemove.value;
                previous.next = toRemove.next;
                current = toRemove.next;
                return removed;
            } else {
                previous = previous.next;
                current = current.next;
            }
        }
        return null;

    }

    // REMOVEVALUE
    public DequeStructure removeFirstOccurrence(DequeStructure c) {
        boolean scan = c.getScan();
        if (isEmpty()) {
            System.out.println("scan not found");
            return null;
        }
        // check for value
        if (!has(c)) {
            System.out.println("scan not found");
            return null;
        }

        // Remove head
        if (head.value.getScan() == scan) {
            Node node = head;
            head = head.next;
            return node.value;
        }
        // remove middle
        Node previous = head;
        Node current = head.next;
        Node removed;
        if(current == null){
            DequeStructure value = removeFirst();//might be return lasty
            return value;
        }
        
        while (current != null) {
            if (current.value.getScan() == true) {
                removed = current;
                Node toRemove = current;
                previous.next = toRemove.next;
                current = toRemove.next;
                return removed.value;
            } else {
                previous = previous.next;
                current = current.next;
            }
        }
        System.out.println("we went in here");
        return null;
    }

    // GET LENGTH
    public int Getlength() {
        int counter = 0;
        Node current = head;
        // iterate over list
        while (current != null) {
            counter++;
            current = current.next;
        }
        return counter;
    }

    // IS LIST EMPTY
    public boolean isEmpty() {
        if (head == null) {
            return true;
        }
        return false;
    }

    // DUMP
    // iterate through list
    public void dump() {
        System.out.println("DequeList:");
        Node current = head;
        // iterate list
        while (current != null) {
            System.out.println(current.value.getcurrState()+ " "+ current.value.getScan());
                current = current.next;
        }
        // System.out.println();
    }
}
