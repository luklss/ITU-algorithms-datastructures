import edu.princeton.cs.algs4.Vector;

public class Species {
    String name;
    String sequence;
    int d;
    int k;

    // Initiate an array of doubles for counting of k-grams
    double[] vector;

    // Initiate vector for similarity calculation
    // We use the Vector class from Sedgewick and Wayne, StdLib
    Vector v;

    // Constructor
    public Species(String name, String sequence, int d, int k) {
        this.name = name;
        this.sequence = sequence;
        this.d = d;
        this.k = k;
        vector = new double[d];
        populateVector();
    }

    // Return a String of length k at char i within the sequence String
    private String kgram(int position) {
        return sequence.substring(position, position+k);
    }

    // Return the hashValue for a kgram modulo d
    private int ourHashCode(String kgram) {
        //System.out.println("Hashcode for " + kgram + " is " + kgram.hashCode());
        return Math.abs(kgram.hashCode()) % d ;
    }

    //
    private void populateVector () {
        for (int i = 0; i < sequence.length()-k; i++) {
            int iToIncrement = ourHashCode(kgram(i));
            // Increment the size of the hash-bucket corresponding to the current kgram
            // Parsed to a double to fulfil requirements of Vector()
            vector[iToIncrement]++;
        }
        // Create a Vector from the array of doubles. (Vectors are immutable)
        v = new Vector(vector);
    }

    // Return the similarity value for the current species and the species from the argument
    public double similarity(Species species) {
        // Calls the dot product function from Sedgewick and Wayne, StdLib
        double dotProduct = v.dot(species.v);
        // Multiplies the Euclidean lengths of the vectors of each species
        // by calling the magnitude function from Sedgewick and Wayne, StdLib
        double lengthProduct = v.magnitude()*species.v.magnitude();

        return dotProduct/lengthProduct;
    }
}
