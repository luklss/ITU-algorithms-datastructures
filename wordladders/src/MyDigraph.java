import edu.princeton.cs.algs4.BreadthFirstDirectedPaths;
import edu.princeton.cs.algs4.Digraph;
import edu.princeton.cs.algs4.In;

import java.io.File;
import java.util.*;

/**
 * Created by lucas on 4/19/2017.
 */
public class MyDigraph {
    private Digraph G;
    private Map<String, Integer> st = new HashMap<>();
    private String[] keys;

    public MyDigraph(In in) {
        int i = 0; // index increment
        while (!in.isEmpty()){ // put every word in the map and assign a integer to it
            st.put(in.readString(), i);
            i++;
        }
        keys = new String[st.size()]; // initialize an string array that will map the values of the map back to the keys
        for (String key: st.keySet()){
            keys[st.get(key)] = key;
        }
        G = new Digraph(st.size());
        buildGraph();
    }

    public String key(int v) { return keys[v]; }

    private void buildGraph() {
        for (String word1: st.keySet()) { // for every word
            String fourGram = word1.substring(1,5); // get the 4 last letters
            for (String word2: st.keySet()) { // for every other word
                if (word1.equals(word2)) continue; // if word is the same, do nothing
                if (matchVertices(fourGram, word2)) { // otherwise, check if there is an arc
                    G.addEdge(st.get(word1), st.get(word2)); // add arc on the graph
                }

            }
        }
    }

    private boolean matchVertices(String fourGram, String word2) {
        int counter = 0;
        char[] word2CharArray = word2.toCharArray();
        for (char i : fourGram.toCharArray()){ // for every char in the word
            for (int j = 0; j < word2CharArray.length; j ++) { // for every char in the other word
                if (word2CharArray[j] == i){ // if there is a match, assign that place to ' ' not to count it again, increase counter and break
                    word2CharArray[j] = ' ';
                    counter++;
                    break;
                }
            }
        }

        if (counter >= 4) return true;
        return false;


    }

    public Digraph getG(){
        return G;
    }

    public Map<String, Integer> getSt() {
        return st;
    }

    public static void main(String[] args) {
        File graphFile = new File(args[0]);
        File pathFile = new File(args[1]);
        In pathContent = new In(pathFile);

        MyDigraph wordLadders = new MyDigraph(new In(graphFile));

        while (!pathContent.isEmpty()){
            String word1 = pathContent.readString();
            String word2 = pathContent.readString();
            BreadthFirstDirectedPaths BFS = new BreadthFirstDirectedPaths(wordLadders.getG(), wordLadders.getSt().get(word1));
            int disttance = BFS.distTo(wordLadders.getSt().get(word2));
            // given that their implementation assigned infinity if there is no path and we have to output -1
            if (disttance == Integer.MAX_VALUE) System.out.println("distance is: "+ -1);
            // otherwise prints the path and the distance to
            else {
                    System.out.print("distance is: " + disttance + " path is: ");
                    for (Integer i: BFS.pathTo(wordLadders.getSt().get(word2))){
                        System.out.print( wordLadders.key(i) + " - ");
                    }
                System.out.println();
                }

        }


    }
}
