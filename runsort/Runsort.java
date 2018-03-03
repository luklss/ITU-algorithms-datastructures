import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class Runsort {

public static void sort (Comparable [] a) {
<<<<<<< HEAD
  int n = a.length;
  Comparable[] aux = new Comparable[n];
  int lo = 0;
  int mid = 0;
  int hi = 0;

while (!isSorted(a)) {
  for (int i = 0; i < n; i++) {
    if (i == n-1) {
      continue;
    }

    else if(!less(a[i], a[i+1]) && mid == 0) {
      mid = i;

    }
    else if (!less(a[i], a[i+1])) {
      hi = i;
      merge (a, aux, lo, mid, hi);
      lo = hi + 1;
      mid = 0;
    }

  }
  }

=======
    int n = a.length;
    int numberOfRuns = 1;
    Comparable[] aux = new Comparable[n];
>>>>>>> 851ef6dfd42ba41e114cb214f11e8144121449f3

    // iterate through and count number runs or "sub arrays"
    for (int i = 0; i < n -1; i++) {
        if (!lessOrEqual(a[i], a[i+1]))
            numberOfRuns++;
    }

        // we use the number of runs and a calculation at the end of the while loop
        // instead of running isSorted() each time. increases speed alot!
        // i.e no need to do a linear run of isSorted() when you can do constant time calculations
        while(numberOfRuns > 1) {
        int min = 0;
        int mid = 0;
        int max = 0;
        boolean midNotSet = true;

        // the same as before, except we made a boolean value midNotSet.
            // boolean was needed for testfile tiny.txt where mid will be set to 0
            // in the first run and in our old version, this would make our else if chain break.
        for (int i = 0; i < n; i++) {
            if (i == n - 1 && midNotSet) break;
            else if (i == n - 1 && !midNotSet){
                merge(a, aux, min, mid, max);
                break;
            }
            if (lessOrEqual(a[i], a[i+1])) {
                max++;
            } else if (midNotSet) {
                mid = max;
                max++;
                midNotSet = false;
            } else {
                merge(a, aux, min, mid, max);
                min = ++max;
                midNotSet = true;
            }
        }
        numberOfRuns = ((numberOfRuns/2) + (numberOfRuns % 2));
    }
}

    // from Sedgewick and Wayne, Section 2.2
    private static boolean lessOrEqual(Comparable v, Comparable w) {
        return v.compareTo(w) <= 0;
    }

    // from Sedgewick and Wayne, Section 2.2
    private static boolean isSorted(Comparable[] a) {
        for (int i = 1; i < a.length; i++)
            if (lessOrEqual(a[i], a[i-1])) return false;
        return true;
    }

    // from Sedgewick and Wayne, Section 2.2
    private static void merge(Comparable[] a, Comparable[] aux, int lo, int mid, int hi) {

        // copy to aux[]
        for (int k = lo; k <= hi; k++) {
            aux[k] = a[k];
        }

        // merge back to a[]
        int i = lo, j = mid+1;
        for (int k = lo; k <= hi; k++) {
            if      (i > mid)              a[k] = aux[j++];  // this copying is unneccessary
            else if (j > hi)               a[k] = aux[i++];
            else if (lessOrEqual(aux[j], aux[i])) a[k] = aux[j++];
            else                           a[k] = aux[i++];
        }
    }

<<<<<<< HEAD
    private static void show(Comparable[] a) {
            for (int i = 0; i < a.length; i++) {
                StdOut.println(a[i]);
            }
    }

=======
    // from Sedgewick and Wayne, Section 2.2
    private static void show(Comparable[] a) {
        for (int i = 0; i < a.length; i++) {
            StdOut.println(a[i]);
        }
        StdOut.println();
    }
>>>>>>> 851ef6dfd42ba41e114cb214f11e8144121449f3

    /**
     *
     * @param args the command-line arguments
     */
    public static void main(String[] args) {
        String[] a = StdIn.readAllStrings();
        Runsort.sort(a);
        assert isSorted(a);
        show(a);
    }
}
