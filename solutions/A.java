import java.io.*;
import java.util.*;

public class Problem {

    public static void main(final String[] args) {
        final InputStream inputStream = System.in;
        final OutputStream outputStream = System.out;
        final FastScanner in = new FastScanner(inputStream);
        final FastPrintWriter out = new FastPrintWriter(outputStream);
        final boolean multiTests = false;
        if (multiTests) {
            int t = in.nextInt();
            while (t-- > 1) {
                new Solver(in, out).solve();
            }
        }
        new Solver(in, out).solve();
        out.close();
        in.close();
    }

    static class Solver {
        private final FastScanner in;
        private final FastPrintWriter out;

        public Solver(FastScanner in, FastPrintWriter out) {
            this.in = in;
            this.out = out;
        }

        public void solve() {
            int[] inp = nextArrayInt(3);
            out.print((int) Math.ceil((double) (inp[2] - inp[1]) / (inp[1] - inp[0])) * 2 + 1);
        }

        // InputRead
        private int scanInt() {
            return in.nextInt();
        }

        private long scanLong() {
            return in.nextLong();
        }

        private double scanDouble() {
            return in.nextDouble();
        }

        private String next() {
            return in.next();
        }

        private int[] nextArrayInt(int len) {
            int[] array = new int[len];
            for (int i = 0; i < len; i++) {
                array[i] = scanInt();
            }
            return array;
        }

        private long[] nextLongArray(int len) {
            long[] array = new long[len];
            for (int i = 0; i < len; i++) {
                array[i] = scanLong();
            }
            return array;
        }
    }

    static class FastScanner {
        private final BufferedReader reader;
        private StringTokenizer st;

        public FastScanner(InputStream inputStream) {
            reader = new BufferedReader(new InputStreamReader(inputStream));
        }

        public void close() {
            try {
                reader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        private void read() {
            while (st == null || !st.hasMoreTokens()) {
                try {
                    st = new StringTokenizer(reader.readLine());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        public String next() {
            read();
            return st.nextToken();
        }

        public int nextInt() {
            return Integer.parseInt(next());
        }

        public long nextLong() {
            return Long.parseLong(next());
        }

        public double nextDouble() {
            return Double.parseDouble(next());
        }
    }

    static class FastPrintWriter extends PrintWriter {
        public FastPrintWriter(OutputStream outputStream) {
            super(outputStream);
        }

        public void prints(Object... objects) {
            for (Object ob : objects) {
                print(ob + " ");
            }
        }

        public void printSln(Object... objects) {
            for (Object ob : objects) {
                print(ob + " ");
            }
            println();
        }

        public void printInts(int[] array) {
            for (int element : array) {
                print(element + " ");
            }
        }
    }
}
