import java.util.ArrayList;
import java.util.List;

/**
 * Created by Kristin K on 02/05/2017.
 */
public class SuperMario {
    public static char [][] racetrack;
    public static int rows;
    public static int columns;

    public static void makeVertices() {
        List<Vertex> vertices = new ArrayList<>();
        List<Vertex> auxVertices = new ArrayList<>();

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                if (racetrack[i][j] == 'S') { //what if there are more than one starting point?
                    Vertex startVertex = new Vertex(i, j, 0, 0);
                    vertices.add(startVertex);
                    //auxVertices.addAll(startVertex.buildAdjVertices());
                }
                if (racetrack[i][j] == 'F') { // what if there are more than one finish point?
                    Vertex finishVertex = new Vertex(i, j, 0, 0);
                    vertices.add(finishVertex);
                }
            }
        }

        // builds the graph or at least the first vertices, but how to continue and when to stop?
        for( Vertex vertex : auxVertices) {
            vertex.buildAdjVertices();
        }

    }

    public static void main(String[] args) {

        rows = StdIn.readInt();
        columns = StdIn.readInt();
        char[][] racetrack = new char[rows][columns];

        String noLine = StdIn.readLine();
        for (int i = 0; i < rows; i++) {
            String line = StdIn.readLine();
            //System.out.println();
            for (int j = 0; j < line.length(); j++) {
                racetrack[i][j] = line.charAt(j);
                //System.out.print(racetrack[i][j]);
            }
        }
    }
}
