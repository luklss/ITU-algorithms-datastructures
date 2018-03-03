import edu.princeton.cs.algs4.MaxPQ;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

/**
 * Congress
 */
public class Congress {

    public Congress() {

    }

    public static void main(String[] args) {
        int totalSeats;
        int states;
        states = StdIn.readInt();

        totalSeats = StdIn.readInt();

        totalSeats = totalSeats - states;
        StdIn.readLine();
        MaxPQ<State> maxPQ = new MaxPQ<State>(states);

        while (StdIn.hasNextLine()) {
            String name = StdIn.readLine();
            int pop = Integer.parseInt(StdIn.readLine());
            State state = new State(pop, name);
            maxPQ.insert(state);
        }

        while (totalSeats > 0) {
            State temp = maxPQ.delMax();
            temp.setSeat();
            temp.setDivisor();
            maxPQ.insert(temp);
            totalSeats--;
        }

        while(!maxPQ.isEmpty()){
            State temp = maxPQ.delMax();
            StdOut.println(temp.getName() + " " + temp.getSeat());

    }
    }
}
