import java.util.ArrayList;
import java.util.List;

/**
 * Created by Kristin K on 02/05/2017.
 */
public class Vertex {
    private int x;
    private int y;
    private int speedX;
    private int speedY;

    public Vertex(int x, int y, int speedX, int speedY) {
        this.x = x;
        this.y = y;
        this.speedX = speedX;
        this.speedY = speedY;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getSpeedX() {
        return speedX;
    }

    public int getSpeedY() {
        return speedY;
    }

    public List<Vertex> buildAdjVertices() {
        List<Vertex> adjVertices = new ArrayList<>();
        List<Vertex> validVertices = new ArrayList<>();

        Vertex vertex1 = new Vertex(x + speedX, y + speedY, speedX, speedY);
        adjVertices.add(vertex1);

        Vertex vertex2 = new Vertex(x + speedX + 1, y + speedY, speedX + 1, speedY);
        adjVertices.add(vertex2);

        Vertex vertex3 = new Vertex(x + speedX - 1, y + speedY, speedX - 1, speedY);
        adjVertices.add(vertex3);

        Vertex vertex4 = new Vertex(x + speedX + 1, y + speedY + 1, speedX + 1, speedY + 1);
        adjVertices.add(vertex4);
  
        Vertex vertex5 = new Vertex(x + speedX + 1, y + speedY - 1, speedX + 1, speedY - 1);
        adjVertices.add(vertex5);

        Vertex vertex6 = new Vertex(x + speedX - 1, y + speedY + 1, speedX - 1, speedY + 1);
        adjVertices.add(vertex6);

        Vertex vertex7 = new Vertex(x + speedX - 1, y + speedY - 1, speedX - 1, speedY - 1);
        adjVertices.add(vertex7);

        Vertex vertex8 = new Vertex(x + speedX, y + speedY + 1, speedX, speedY + 1);
        adjVertices.add(vertex8);

        Vertex vertex9 = new Vertex(x + speedX, y + speedY - 1, speedX, speedY - 1);
        adjVertices.add(vertex9);

        for (Vertex vertex : adjVertices) {
            if (x >= 0 && x < SuperMario.rows && y >= 0 && y < SuperMario.columns && SuperMario.racetrack[vertex.x][vertex.y] != 'O')
                validVertices.add(vertex);
        }
        return validVertices;
    }
}
