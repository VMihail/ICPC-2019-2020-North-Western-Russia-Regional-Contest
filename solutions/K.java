import java.io.*;
import java.util.*;

import static java.lang.Math.*;

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

    interface Solve {
        void solve();
    }

    static class Solver implements Solve {
        private final FastScanner in;
        private final FastPrintWriter out;

        public Solver(FastScanner in, FastPrintWriter out) {
            this.in = in;
            this.out = out;
        }

        @Override
        public void solve() {
            int n = scanInt(), m = scanInt();
            List<Pair<Integer, Integer>> children = new ArrayList<>();
            Pair<Integer, Integer> childA = null;
            char[][] map = new char[n][m];
            for (int i = 0; i < n; i++) {
                String line = next();
                for (int j = 0; j < line.length(); j++) {
                    line.getChars(0, m, map[i], 0);
                    char now = line.charAt(j);
                    if (now != '.') {
                        if (now == 'A') {
                            childA = new Pair<>(i, j);
                        } else {
                            children.add(new Pair<>(i, j));
                        }
                    }
                }
            }
            assert childA != null;
            int maxArea = 1;
            Pair<Integer, Integer> topLeft = childA, bottomRight = childA;
            for (int i = childA.first; i >= 0; i--) {
                for (int j = childA.first; j < n; j++) {
                    int left = 0, right = m - 1;
                    for (Pair<Integer, Integer> child : children) {
                        if (child.first >= i && child.first <= j) {
                            if (child.second <= childA.second) {
                                left = max(left, child.second + 1);
                            }
                            if (child.second >= childA.second) {
                                right = min(right, child.second - 1);
                            }
                        }
                    }
                    int area = (j - i + 1) * (right - left + 1);
                    if (area > maxArea) {
                        maxArea = area;
                        topLeft = new Pair<>(i, left);
                        bottomRight = new Pair<>(j, right);
                    }
                }
            }
            for (int i = topLeft.first; i <= bottomRight.first; i++) {
                for (int j = topLeft.second; j <= bottomRight.second; j++) {
                    if (map[i][j] != 'A') {
                        map[i][j] = 'a';
                    }
                }
            }
            List<Integer> ups = new ArrayList<>(children.size()), downs = new ArrayList<>(children.size());
            for (Pair<Integer, Integer> child : children) {
                char letter = Character.toLowerCase(map[child.first][child.second]);
                int up = child.first - 1;
                while (up >= 0 && map[up][child.second] == '.') {
                    map[up--][child.second] = letter;
                }
                ups.add(up + 1);
                int down = child.first + 1;
                while (down < n && map[down][child.second] == '.') {
                    map[down++][child.second] = letter;
                }
                downs.add(down - 1);
            }
            for (int i = 0; i < children.size(); i++) {
                final Pair<Integer, Integer> child = children.get(i);
                expand(map, child, ups.get(i), downs.get(i), -1);
                expand(map, child, ups.get(i), downs.get(i), +1);
            }
            final StringBuilder builder = new StringBuilder();
            for (int i = 0; i < n; i++) {
                builder.append(map[i]).append('\n');
            }
            out.print(builder);
        }

        private void expand(final char[][] map, final Pair<Integer, Integer> child, final int i, final int j, final int direction) {
            final char letter = map[child.first][child.second];
            int l = child.second + direction;
            while (l >= 0 && l < map[child.first].length) {
                for (int k = i; k <= j; k++) {
                    final char c = map[k][l];
                    if (c != '.' && c != letter) {
                        return;
                    }
                }
                for (int k = i; k <= j; k++) {
                    if (map[k][l] != letter) {
                        map[k][l] = Character.toLowerCase(letter);
                    }
                }
                l += direction;
            }
        }

        private static class Pair<TypeFirst, TypeSecond> {
            public final TypeFirst first;
            public final TypeSecond second;

            Pair(TypeFirst first, TypeSecond second) {
                this.first = first;
                this.second = second;
            }

            @Override
            public String toString() {
                return first + " " + second;
            }
        }

        static class ArraysMethods {
            public static void swap(int[] array, int i, int j) {
                int save = array[j];
                array[j] = array[i];
                array[i] = save;
            }
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

        private int[] nextIntArray(int len) {
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

    static class FastScanner implements Closeable {
        private final BufferedReader reader;
        private StringTokenizer st;

        public FastScanner(InputStream inputStream) {
            reader = new BufferedReader(new InputStreamReader(inputStream));
        }

        @Override
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

        public void print(Object ... objects) {
            for (int i = 0; i < objects.length - 1; i++) {
                print(objects[i] + " ");
            }
            print(objects[objects.length - 1]);
        }

        public void println(Object ... objects) {
            for (int i = 0; i < objects.length - 1; i++) {
                print(objects[i] + " ");
            }
            println(objects[objects.length - 1]);
        }

        public void printArray(int[] array) {
            for (int i = 0; i < array.length - 1; i++) {
                print(array[i] + " ");
            }
            println(array[array.length - 1]);
        }
    }
}
