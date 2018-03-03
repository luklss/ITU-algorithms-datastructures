import edu.princeton.cs.algs4.BreadthFirstDirectedPaths;
import edu.princeton.cs.algs4.Digraph;
import edu.princeton.cs.algs4.StdIn;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Sune on 06-05-2017.
 */
public class Mario {

    private char [][] racetrack;
    private int rows;
    private int columns;
    private static List<Vertex> StartPos = new ArrayList<>();
    private static List<Vertex> endPos = new ArrayList<>();

    private int count = 0;
    private static Map<Vertex, Integer> vertices = new HashMap();
    private Map<Vertex, Integer> auxVertices = new HashMap();
    private Map<Vertex, List<Vertex>> adj = new HashMap();
    private Digraph di;

    private int findShortestPath(){
        int shortest = -1;
        for (Vertex v : StartPos){
            BreadthFirstDirectedPaths bfs = new BreadthFirstDirectedPaths(di,v.count);
            for (Vertex ver : endPos){
            int dist = bfs.distTo(ver.count) + 1;
            if (shortest == -1 ){
                shortest = dist;
            }else if(shortest > dist){
                shortest = dist;
            }
            }

        }
        return shortest;
    }

    private void createDigraph(){
        di = new Digraph(count + 1);
        for (Map.Entry<Vertex,List<Vertex>> entry: adj.entrySet()){
            for (Vertex v : entry.getValue()){
                di.addEdge(entry.getKey().count,v.count);
            }
        }
    }

    private List<Vertex> newVertex(Vertex v, List<Vertex> list, int x, int y,char trackId){
        Vertex ver = new Vertex(trackId,v.x + x,v.y + y,x,y, count);

        if (!adj.containsKey(ver)) {
           auxVertices.put(ver, count++);
            if (ver.trackIdentifier == 'F'){
                if (!endPos.contains(ver))
                    endPos.add(ver);
            }
        } else{
            for (Vertex vertex : adj.keySet()){
                if (vertex.equals(ver)){
                    ver = vertex;
                    break;
                }
            }
        }
        list.add(ver);
        return list;
    }

    private void findAndMapVertices(){
        for (Map.Entry<Vertex,Integer> entry : vertices.entrySet()){
            Vertex v = entry.getKey();

            List<Vertex> listOfAdj = new ArrayList<>();

            int row1 = v.x + v.speedX;
            int row2 = v.x + v.speedX + 1;
            int row3 = v.x + v.speedX - 1;

            int column1 = v.y + v.speedY;
            int column2 = v.y + v.speedY + 1;
            int column3 = v.y + v.speedY - 1;
            if (!(row1 < 0) && !(row1 >= rows )) {
                if (!(column1 < 0) && !(column1 >= columns )) {
                    if (racetrack[row1][column1] != 'O'){
                    listOfAdj = newVertex(v, listOfAdj, v.speedX, v.speedY,racetrack[row1][column1]);
                }}
                if (!(column2 < 0) && !(column2 >= columns )) {
                    if (racetrack[row1][column2] != 'O'){
                    listOfAdj = newVertex(v, listOfAdj, v.speedX, v.speedY + 1,racetrack[row1][column2]);
                }}
                if (!(column3 < 0) && !(column3 >= columns )) {
                    if (racetrack[row1][column3] != 'O'){
                    listOfAdj = newVertex(v, listOfAdj, v.speedX, v.speedY - 1,racetrack[row1][column3]);
                }}
            }
            if (!(row2 < 0) && !(row2 >= rows )) {
                if (!(column1 < 0) && !(column1 >= columns )) {
                    if (racetrack[row2][column1] != 'O'){
                        listOfAdj = newVertex(v, listOfAdj, v.speedX + 1, v.speedY,racetrack[row2][column1]);
                }}
                if (!(column2 < 0) && !(column2 >= columns )) {
                    if (racetrack[row2][column2] != 'O'){
                        listOfAdj = newVertex(v, listOfAdj, v.speedX + 1, v.speedY + 1,racetrack[row2][column2]);
                }}
                if (!(column3 < 0) && !(column3 >= columns )) {
                    if (racetrack[row2][column3] != 'O'){
                        listOfAdj = newVertex(v, listOfAdj, v.speedX + 1, v.speedY - 1,racetrack[row2][column3]);
                }}
                }
            if (!(row3 < 0) && !(row3 >= rows )) {
                if (!(column1 < 0) && !(column1 >= columns )) {
                    if (racetrack[row3][column1] != 'O'){
                        listOfAdj = newVertex(v, listOfAdj, v.speedX - 1, v.speedY,racetrack[row3][column1]);
                }}
                if (!(column2 < 0) && !(column2 >= columns )) {
                    if (racetrack[row3][column2] != 'O'){
                        listOfAdj = newVertex(v, listOfAdj, v.speedX - 1, v.speedY + 1,racetrack[row3][column2]);
                }
                }
                if (!(column3 < 0) && !(column3 >= columns )) {
                    if (racetrack[row3][column3] != 'O') {
                        listOfAdj = newVertex(v, listOfAdj, v.speedX - 1, v.speedY - 1, racetrack[row3][column3]);
                    }
                }
            }
            if (!adj.containsKey(v)) {
                adj.put(v, listOfAdj);
            }
        }
            vertices.clear();
            vertices.putAll(auxVertices);
            auxVertices.clear();
        }

    private void findStartPositions(){
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                if (racetrack[i][j] == 'S') {
                    Vertex start = new Vertex('S',i,j,0,0, count);
                    StartPos.add(start);
                    vertices.put(start,count++);
                }
            }
        }
    }
    private void loadMap(){
        rows = StdIn.readInt();
        columns = StdIn.readInt();
        racetrack = new char[rows][columns];

        //System.out.println("Racetrack:");
        //read in empty line that for some reason exists
        StdIn.readLine();
        for (int i = 0; i < rows; i++) {
            String line = StdIn.readLine();
          //  System.out.println();
            for (int j = 0; j < line.length(); j++) {
                racetrack[i][j] = line.charAt(j);
             //   System.out.print(racetrack[i][j]);
            }
        }

    }

    public static void main(String[] args) {
        Mario ma = new Mario();
        ma.loadMap();
        ma.findStartPositions();
       while(true) {
           if (!vertices.isEmpty())
               ma.findAndMapVertices();
           else break;
       }
       ma.createDigraph();
       System.out.println(ma.findShortestPath());
    }

    private class Vertex{

        private int x;
        private int y;
        private int speedX;
        private int speedY;
        private int count;
        private char trackIdentifier;

        public Vertex(char trackIdentifier,int x, int y, int speedX, int speedY, int count) {
            this.x = x;
            this.y = y;
            this.speedX = speedX;
            this.speedY = speedY;
            this.trackIdentifier = trackIdentifier;
            this.count = count;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            Vertex vertex = (Vertex) o;

            if (x != vertex.x) return false;
            if (y != vertex.y) return false;
            if (speedX != vertex.speedX) return false;
            if (speedY != vertex.speedY) return false;
            return trackIdentifier == vertex.trackIdentifier;
        }

        @Override
        public int hashCode() {
            int result = x;
            result = 31 * result + y;
            result = 31 * result + speedX;
            result = 31 * result + speedY;
            result = 31 * result + (int) trackIdentifier;
            return result;
        }
    }

}

