package timingtest;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.Stopwatch;

/**
 * Created by hug.
 */
public class TimeSLList {
    private static void printTimingTable(AList<Integer> Ns, AList<Double> times, AList<Integer> opCounts) {
        System.out.printf("%12s %12s %12s %12s\n", "N", "time (s)", "# ops", "microsec/op");
        System.out.printf("------------------------------------------------------------\n");
        for (int i = 0; i < Ns.size(); i += 1) {
            int N = Ns.get(i);
            double time = times.get(i);
            int opCount = opCounts.get(i);
            double timePerOp = time / opCount * 1e6;
            System.out.printf("%12d %12.2f %12d %12.2f\n", N, time, opCount, timePerOp);
        }
    }

    public static void main(String[] args) {
        timeGetLast();
    }

    public static void timeGetLast() {
        // TODO: YOUR CODE HERE
        AList <Integer> Ns = new AList<>();
        AList <Double> times = new AList<>();
        AList <Integer> opCount = new AList<>();

        for (int i = 1000; i <= 128000; i = i*2) {
            Ns.addLast(i);
            opCount.addLast(1000);
            Stopwatch stopwatch = new Stopwatch();
            SLList s = new SLList<>();
            for (int j = 1; j<=i; j++) {
                s.addLast(j);
            }
            for (int k = 1; k <= 10000; k++){
                s.getLast();
            }
            double t = stopwatch.elapsedTime();
            times.addLast(t);
        }
        printTimingTable(Ns, times, opCount);
    }

}
