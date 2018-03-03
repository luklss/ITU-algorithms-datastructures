import edu.princeton.cs.algs4.*;

public class GiantBook {

    public static void main(String[] args) {
        StdOut.println("please enter a size N and number of iterations T");
        while (!StdIn.isEmpty()) {
            int size = StdIn.readInt();
            int iterations = StdIn.readInt();
            calculateAllConnected(size, iterations);
            calculateAllNonIsolated(size, iterations);
            calculateGiantComponenet(size, iterations);
        }
    }

    public static void calculateGiantComponenet(int size, int iterations) {
        int[] results = new int[iterations];
        for (int i = 0; i < iterations; i++) {
            MyUnionFind myUF = new MyUnionFind(size);
            int connections = 0;
            while (myUF.maxComponentSize() < size / 2.0) {
                connections = getConnections(size, myUF, connections);
            }
            results[i] = connections;
        }
        double std = StdStats.stddev(results);
        double average = StdStats.mean(results);
        StdOut.println("Giant Component: " + "average - " + average + " std - " + std);
    }


    public static void calculateAllNonIsolated(int size, int iterations) {
        int[] results = new int[iterations];
        for (int i = 0; i < iterations; i++) {
            MyUnionFind myUF = new MyUnionFind(size);
            int connections = 0;
            while (myUF.hasIsolatedNode()) {
                connections = getConnections(size, myUF, connections);
            }
            results[i] = connections;
        }
        double std = StdStats.stddev(results);
        double average = StdStats.mean(results);
        StdOut.println("Non Isolated Components: " + "average - " + average + " std - " + std);
    }


    public static void calculateAllConnected(int size, int iterations) {
        int[] results = new int[iterations];
        for (int i = 0; i < iterations; i++) {
            MyUnionFind myUF = new MyUnionFind(size);
            int connections = 0;
            while (!myUF.isAllConnected()) {
                connections = getConnections(size, myUF, connections);
            }
            results[i] = connections;
        }
        double std = StdStats.stddev(results);
        double average = StdStats.mean(results);
        StdOut.println("All Components Connected: " + "average - " + average + " std - " + std);
    }

    private static int getConnections(int size, MyUnionFind myUF, int connections) {
        int p;
        int q;
        p = StdRandom.uniform(0, size); // size is exclusive
        q = StdRandom.uniform(0, size);
        if (myUF.connected(p, q)) {
            connections++;
            return connections;
        }
        myUF.union(p, q);
        connections++;
        return connections;
    }
}
