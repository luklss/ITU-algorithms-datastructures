import java.util.HashMap;

/**
 * Created by lucas on 3/16/2017.
 */
public class HashPipe {

    private Node[] rootPipe;
    private int higestHeight = 0;
    private int size = 0;


    // create an empty symbol table
    public HashPipe() {
        rootPipe = new Node[32];
        for (int i = 0; i < rootPipe.length; i++) {
            rootPipe[i] = new Node(null, null, rootPipe);
        }
    }

    // return the number of element s
    public int size() {
        return size;
    }

    // put key-value pair into the table
    public void put(String key, Integer val) {
        // initialize new pipe
        Node[] newPipe = new Node[getPipeHeight(key)]; // initialize a new pipe
        for (int i = 0; i < newPipe.length; i ++) {
            newPipe[i] = new Node(key, val, newPipe);
        }

        if (newPipe.length > higestHeight) higestHeight = newPipe.length; // update hiestHeight
        int currentRootPosition = higestHeight - 1;
        Node[] leftPipe = rootPipe;

        if (this.size() == 0) { // if it is the first being inserted
            while (currentRootPosition >= 0) {
                leftPipe[currentRootPosition].pointer = newPipe[currentRootPosition--];
            }
        }
        else {
            addPipe(leftPipe, newPipe, currentRootPosition);
        }

        size++;
    }

    private void addPipe(Node[] leftPipe, Node[] newPipe, int currentPipeposition) {

        if (currentPipeposition < 0) return;
        while (currentPipeposition >= 0 && leftPipe[currentPipeposition].pointer == null ) { // while the pointers of the left pipe are null
            if (newPipe.length > currentPipeposition) { // if the height of the right is bigger than the left, we update the pointers
                leftPipe[currentPipeposition].pointer = newPipe[currentPipeposition];
            }
            currentPipeposition--;
        }

        if (currentPipeposition < 0) return; // if it is out of bounds it means we are done

        if (leftPipe[currentPipeposition].pointer.key.equals(newPipe[0].key)) { // if we found the same key, just update it
            Node[] pipeToUpdate = leftPipe[currentPipeposition].pointer.parent;
            for (int i = 0; i < pipeToUpdate.length; i ++) {
                pipeToUpdate[i].value = newPipe[0].value;
            }
            size--; // means that a current pipe was found, so since the size was already increased, we have to decrease it
            return;
        }


        // if the right pipe key is bigger than the new pipe, we stay in this range
        if (leftPipe[currentPipeposition].pointer.key.compareTo(newPipe[0].key) > 0) {
            // if the new pipe is bigger or equal than the current position when we find a pointer to a bigger key,
            // we have to update both the pointer to the new pipe and from the new pipe.
            if (newPipe.length - 1 >= currentPipeposition) {
                newPipe[currentPipeposition].pointer = leftPipe[currentPipeposition].pointer; // makes new point to right
                leftPipe[currentPipeposition].pointer =newPipe[currentPipeposition]; // makes left point to new
            }
            addPipe(leftPipe, newPipe, currentPipeposition - 1);
        }
        else { //otherwise we set the right pipe to be the left one and proced
            addPipe(leftPipe[currentPipeposition].pointer.parent, newPipe, currentPipeposition);
        }

    }

    public String control(String key, int height) {
        Node node = search(key, false);
        if (height >= node.parent.length ) return null;
        if (node.parent[height].pointer == null) return null;
        return node.parent[height].pointer.key;
    }

    // value associated with key
    public Integer get(String key) {
        return search(key, false).value;
    }

    private Node search(String key, boolean floor) {
        if (size() <= 0) return null;
        Node[] currentPipe = rootPipe;
        int currentPosition = higestHeight - 1;
        int comparison =currentPipe[currentPosition].pointer.key.compareTo(key); // starts at the top of the root
        if (comparison == 0) return currentPipe[currentPosition].pointer;

        while (true) {

             if (comparison > 0) {
                 currentPosition--;
                 if (currentPosition < 0){
                     if (floor) return currentPipe[currentPosition + 1];
                     return null;
                 }
                 comparison = currentPipe[currentPosition].pointer.key.compareTo(key); // if the right is bigger, go down one position in the pipe
                if (comparison == 0) return currentPipe[currentPosition].pointer;
            }
            else if (comparison < 0) {
                currentPipe = currentPipe[currentPosition].pointer.parent; // go to next pipe
                while (currentPipe[currentPosition].pointer == null) { // when changing the pipe, have to go down until the pointer is not null anymore
                    currentPosition--;// if the pointer is null go down
                    if (currentPosition < 0){
                        if (floor) return currentPipe[currentPosition + 1];
                        return null; // if it reach the bottom and didnt find return null;
                    }
                }
                comparison = currentPipe[currentPosition].pointer.key.compareTo(key);
                if (comparison == 0) return currentPipe[currentPosition].pointer;
            }
        }

    }


    private int getPipeHeight(String key) {
        return 1 + Long.numberOfTrailingZeros(key.hashCode());
    }

    //largest key less than or equal to key
    public String floor(String key) {
        return search(key, true).key;
    }

    private class Node {
        String key;
        Integer value;
        Node pointer;
        Node[] parent;

        public Node (String key, Integer value, Node[] parent) {
            this.parent = parent;
            this.key = key;
            this.value = value;
        }
    }

    public static void main(String[] args) {
        HashPipe myPipe = new HashPipe();
        myPipe.put("s", 0);
        myPipe.put("e", 1);
        myPipe.put("a", 2);
        myPipe.put("r", 3);
        myPipe.put("c", 4);
        myPipe.put("h", 5);
        myPipe.put("e", 6);
        myPipe.put("x", 7);
        myPipe.put("a", 8);
        myPipe.put("m", 9);
        myPipe.put("p", 10);
        myPipe.put("l", 11);
        myPipe.put("e", 12);
        System.out.println("value for s is " + myPipe.get("s"));
        System.out.println("value for e is " + myPipe.get("e"));
        System.out.println("value for a is " + myPipe.get("a"));
        System.out.println("value for r is " + myPipe.get("r"));
        System.out.println("value for c is " + myPipe.get("c"));
        System.out.println("value for h is " + myPipe.get("h"));
        System.out.println("value for x is " + myPipe.get("x"));
        System.out.println("value for m is " + myPipe.get("m"));
        System.out.println("value for p is " + myPipe.get("p"));
        System.out.println("value for l is " + myPipe.get("l"));
        System.out.println("value for l is " + myPipe.get("l"));
        System.out.println("floor for k is " + myPipe.floor("k"));
        System.out.println("floor for a is " + myPipe.floor("a"));
        System.out.println("floor for q is " + myPipe.floor("q"));
        System.out.println("control for H,3 is " + myPipe.control("h", 3));
        System.out.println("control for H,2 is " + myPipe.control("h", 2));
        System.out.println("control for P,4 is " + myPipe.control("p", 4));
    }
}
