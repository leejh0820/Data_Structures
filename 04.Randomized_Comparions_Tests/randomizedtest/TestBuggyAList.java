package randomizedtest;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdRandom;
import org.junit.Test;
import timingtest.AList;

import static org.junit.Assert.*;

/**
 * Created by hug.
 */
public class TestBuggyAList {
  // YOUR TESTS HERE
    @Test
    public void testThreeAddThreeRemove() {
        AListNoResizing<Integer> L = new AListNoResizing<>();
        BuggyAList<Integer> L2 = new BuggyAList<>();

        L.addLast(4);
        L.addLast(5);
        L.addLast(6);

        L2.addLast(4);
        L2.addLast(5);
        L2.addLast(6);

        assertEquals(L.size(), L2.size());
        assertEquals(L.removeLast(), L2.removeLast());
        assertEquals(L.removeLast(), L2.removeLast());
        assertEquals(L.removeLast(), L2.removeLast());
    }

    @Test
    public void randomizedTest() {
        AListNoResizing<Integer> L = new AListNoResizing<>();
        BuggyAList<Integer> L2 = new BuggyAList<>();

        int N = 500;
        for (int i = 0; i < N; i += 1) {
            int operationNumber = StdRandom.uniform(0, 2);
            if (operationNumber == 0) {
                // addLast
                int randVal = StdRandom.uniform(0, 100);
                L.addLast(randVal);
                L2.addLast(randVal);
                System.out.println("addLast(" + randVal + ")");
            } else if (operationNumber == 1) {
                // size
                int size = L.size();
                int size2 = L2.size();
                System.out.println("size: " + size);
                System.out.println("size2: " + size2);
            }else if (operationNumber == 2 && L.size() > 0 && L2.size()>0) {
                // size
                int size_last = L.getLast();
                int size2_last = L2.getLast();
                System.out.println("Last value of size: " + size_last);
                System.out.println("Last value of size2: " + size2_last);
            }else if (operationNumber == 3 && L.size()>0 && L2.size()>0) {
                // size
                L.removeLast();
                L2.removeLast();

            }
        }
    }

}
