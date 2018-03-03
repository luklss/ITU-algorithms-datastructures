import edu.princeton.cs.algs4.StdIn;

import java.util.ArrayList;
import java.util.List;

public class GorillaHash {

  public static void main (String args[]) {

    // Assign length of k-gram "k" and number of hash-buckets "d"
    int k = 3;
    int d = 10000;

    // Initiate species name and sequence variables, mySpecies constructor and an ArrayList of Species
    String name = null;
    StringBuilder sequence = new StringBuilder();
    Species mySpecies;
    List<Species> speciesList = new ArrayList<>();

    // Reading in species names and sequences from a FASTA file:
    while (!StdIn.isEmpty()) {

      String line = StdIn.readLine();

      // Check if a new species starts in line
      if (line.startsWith(">")) {

        if (name != null && sequence != null) {
          // Create new Species with the current name, sequence from StringBuilder, k and d
          mySpecies = new Species(name, sequence.toString(), d, k);

          // Add the new Species to the ArrayList
          speciesList.add(mySpecies);

          // Create an empty Stringbuilder
          sequence = new StringBuilder();
        }
        // Assign name to the current line
        name = line;
      } else {
        // If the current line is not a name (= doesn't start with ">"), append the sequence String to the StringBuilder
        sequence.append(line);
      }
    }

    // Create a species from the last name and sequence read form the input file and add it to the ArrayList
    mySpecies = new Species(name, sequence.toString(), d, k);
    speciesList.add(mySpecies);

    // Print the similarity between each two species in a matrix
    System.out.print(",");
    for (Species s : speciesList) {
    System.out.print(s.name + ",");
    }
    System.out.println();
    for (Species s : speciesList) {
      System.out.print(s.name);
      for (Species t : speciesList) {
        System.out.print("," + s.similarity(t));
      }
      System.out.println();
    }
  }
}
