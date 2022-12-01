import java.io.BufferedReader;
import java.io.InputStream;
import java.io.Closeable;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer;

public class Main {

    private static final FastScanner in = new FastScanner(System.in);

    public static void main(String[] args) {
        solve();
    }

    private static int scanInt() {
        return in.nextInt();
    }

    public static void solve() {
        int n = scanInt(), m = scanInt();
        FastList<FastList<Integer>> ribs = new FastList<>(n);
        fillRibs(ribs, n);
        int[] cities = new int[m], depth = new int[n], ints = new int[n];
        for (int i = 0; i < m; i++) {
            cities[i] = scanInt() - 1;
        }
        bfs(cities[0], cities[0], 0, ribs, depth, ints);
        int index = find(cities, depth, m), res = depth[index];
        if (res % 2 != 0) {
            System.out.println("NO");
            return;
        }
        int i1 = index, range = res / 2;
        for (int i = 0; i < range; i++) {
            i1 = ints[i1];
            //System.err.println(i1 + " " + i);
        }
        bfs(i1, i1, 0, ribs, depth, ints);
        for (int citi : cities) {
            if (depth[citi] != range) {
                System.out.println("NO");
                return;
            }
        }
        System.out.println("YES");
        System.out.println(i1 + 1);
    }

    private static int find(int[] cities, int[] depth, int m) {
        int max = Integer.MIN_VALUE, index = -1;
        for (int i = 0; i < m; i++) {
            if (max < depth[cities[i]]) {
                index = cities[i];
                max = depth[cities[i]];
            }
        }
        return index;
    }

    private static void fillRibs(FastList<FastList<Integer>> ribs, int n) {
        for (int i = 0; i < n; i++) {
            FastList<Integer> list = new FastList<>(n - 1);
            ribs.add(list);
        }
        for (int i = 0; i < n - 1; i++) {
            int u = scanInt() - 1, v = scanInt() - 1;
            ribs.get(u).add(v);
            ribs.get(v).add(u);
        }
    }

    private static void bfs(int command, int x, int deep, FastList<FastList<Integer>> ribs, int[] depth, int[] ints) {
        if (command == depth.length) {
            return;
        }
        depth[command] = deep;
        ints[command] = x;
        for (int i = 0; i < ribs.get(command).size(); i++) {
            if (ribs.get(command).get(i) != x) {
                bfs(ribs.get(command).get(i), command, deep + 1, ribs, depth, ints);
            }
        }
    }
}

 class FastScanner implements Closeable {
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
}

class FastList<Type> {
    private Type[] array;
    private int last;

    public FastList(int len) {
        array = (Type[]) new Object[len];
    }

    public void add(Type element) {
        if (isFill()) {
            expand();
        }
        array[last++] = element;
    }

    public Type get(int index) {
        if (index < 0 || index >= last) {
            throw new IndexOutOfBoundsException();
        }
        return array[index];
    }

    public int size() {
        return last;
    }

    private void expand() {
        Type[] newArray = (Type[]) new Object[array.length * 2];
        System.arraycopy(array, 0, newArray, 0, array.length);
        array = newArray;
    }

    private boolean isFill() {
        return last == array.length;
    }
}
