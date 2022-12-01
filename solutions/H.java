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
            int n = scanInt();
            int[] a = new int[n];
            int max = Integer.MIN_VALUE;
            for (int i = 0; i < n; i++) {
                a[i] = scanInt();
                max = Math.max(max, a[i]);
            }
            int q = scanInt();
            int[] fib = new int[n];
            fib[0] = a[0];
            for (int i = 1; i < n; i++) {
                fib[i] = fib[i - 1] + a[i];
            }
            Map<Integer, Integer> map = new HashMap<>();
            for (int i = 0; i < q; i++) {
                int t = scanInt();
                if (t < max) {
                    out.println("Impossible");
                    continue;
                }
                if (map.containsKey(t)) {
                    out.println(map.get(t));
                    continue;
                }
                int result = binSearch(n, fib, t);
                map.put(t, result);
                out.println(result);
            }
        }

        private int binSearch(int n, int[] b, int t) {
            int result = 0, count = 0;
            while (count < n) {
                int left = count, right = n;
                while (right > left + 1) {
                    int middle = (right - left) / 2 + left;
                    if (good(count, b, middle, t)) {
                        left = middle;
                    } else {
                        right = middle;
                    }
                }
                count = right;
                ++result;
            }
            return result;
        }

        private boolean good(int count, int[] b, int middle, int t) {
            return b[middle] <= t || count > 0 && b[middle] - b[count - 1] <= t;
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
