import edu.princeton.cs.algs4.*;

public class GiantBookCodeJudge {
  public static void main(String[] args) {
    int size = StdIn.readInt();
    MyUnionFind UF = new MyUnionFind(size);

    int giantCounter = 0;
    int connectedCounter = 0;
    int nonIsolatedCounter = 0;

    while (!StdIn.isEmpty()) {
      int p = StdIn.readInt();
      int q = StdIn.readInt();
      if (UF.maxComponentSize() < size / 2.0) giantCounter ++;
      if (!UF.isAllConnected()) connectedCounter++;
      if (UF.hasIsolatedNode()) nonIsolatedCounter++;
      UF.union(p, q);
    }


    StdOut.print(size + " " + nonIsolatedCounter + " " + giantCounter + " " + connectedCounter);
  }
}
