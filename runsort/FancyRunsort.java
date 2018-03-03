package edu.princeton.cs.algs4;

public class FancyRunsort {



public class FancyRunsort {

    public static void sort(Comparable[] a){
        if(a.length < 8){
            Insertion.sort(a);
        }else{
            Runsort.sort(a);
        }
    }

    // from Sedgewick and Wayne, Section 2.2
    private static void show(Comparable[] a) {
        for (int i = 0; i < a.length; i++) {
            StdOut.println(a[i]);
        }
        StdOut.println();
    }


4. Draw a visual trace of runsort for each round in the style of the
book, e.g., like the “Visual trace of shellsort” ﬁgure in Section 2.1.
(StdStats.plotBars does most the work for you.)
*/


//
}
