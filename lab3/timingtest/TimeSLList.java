package timingtest;
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
        AList<Integer> Ns = new AList<>();
        AList<Double> times = new AList<>();
        AList<Integer> opCounts = new AList<>();
        for (int N = 1000; N < 128000; N *=2){
            Ns.addLast(N);
            SLList<Integer> slList = new SLList<>();
            for (int i = 0; i < N; i++){
                slList.addLast(0);
            }
            Stopwatch sw = new Stopwatch();
            int last;
            for (int M = 0; M < 10000; M++){
                last = slList.getLast();
            }
            double time = sw.elapsedTime();
            times.addLast(time);
            opCounts.addLast(10000);
        }
        printTimingTable(Ns,times,Ns);
    }

}
