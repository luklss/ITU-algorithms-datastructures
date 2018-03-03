import edu.princeton.cs.algs4.*;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class spanningUSA {
    private Map<String, Integer> st = new HashMap<>();
    private EdgeWeightedGraph graph;
    private String[] keys;

    public spanningUSA (In input) {
        int i = 0; // index increment
        boolean mapInitialized = false; // keep track if we initialized the graph
        while (!input.isEmpty()){ // put every city name in the map and assign an integer to it
            String line = input.readLine();
            if (!line.contains("--") & !mapInitialized){
                st.put(line.trim(), i); // remove trailing spaces
                i++;
            }
            else {
                if (!mapInitialized){ // checks if the map was init already, if not do it
                    graph = new EdgeWeightedGraph(st.size());
                    mapInitialized = true;
                }
                // add edges to the graph
                String [] vertices = getVertices(line);
                int weight = getWeight(line);
                Edge e = new Edge(st.get(vertices[0]), st.get(vertices[1]), weight);
                graph.addEdge(e);
            }
        }

        keys = new String[st.size()]; // initialize an string array that will map the values of the map back to the keys
        for (String key: st.keySet()){
            keys[st.get(key)] = key;
        }

    }


    public EdgeWeightedGraph getGraph() {
        return graph;
    }

    public String[] getKeys() {
        return keys;
    }

    /**
     * This function returns the weight within the string we are parsing
     * @param s
     * @return
     */
    private static int getWeight(String s) {
        Pattern p = Pattern.compile(".*\\[ *(.*) *\\].*");
        Matcher m = p.matcher(s);
        m.find();
        return Integer.parseInt(m.group(1));
    }

    private static String[] getVertices(String s) {
        String[] split = s.split("--");
        String v1 = split[0];
        String v2 = split[1].substring(0, split[1].lastIndexOf(" "));
        return new String[]{v1, v2};
    }

    public static void main(String[] args) {
        File inputFile = new File(args[0]);
        spanningUSA USAHighways = new spanningUSA(new In(inputFile));
        PrimMST myPrim = new PrimMST(USAHighways.getGraph());
        System.out.println(myPrim.weight());
        for (Edge e : myPrim.edges()){
            System.out.println(EdgeComponents(e, USAHighways.getKeys()));
        }
    }

    private static String EdgeComponents(Edge e, String[] keys) {
        String[] split = e.toString().split(" ");
        String vertices = split[0];
        String weight = split[1];
        String[] verticesSplit = vertices.split("-");
        int vertex1 = Integer.parseInt(verticesSplit[0]);
        int vertex2 = Integer.parseInt(verticesSplit[1]);
        return (keys[vertex1] + "-" + keys[vertex2] + " " + weight).replaceAll("\"", "");
    }


}