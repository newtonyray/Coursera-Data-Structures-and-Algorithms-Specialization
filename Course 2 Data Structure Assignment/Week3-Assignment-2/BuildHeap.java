import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.StringTokenizer;

public class BuildHeap {
    private int[] data;
    private List<Swap> swaps;

    private FastScanner in;
    private PrintWriter out;

    public static void main(String[] args) throws IOException {
        new BuildHeap().solve();
    }

    private void readData() throws IOException {
        int n = in.nextInt();
        data = new int[n];
        for (int i = 0; i < n; ++i) {
          data[i] = in.nextInt();
        }
    }

    private void writeResponse() {
        out.println(swaps.size());
        for (Swap swap : swaps) {
          out.println(swap.index1 + " " + swap.index2);
        }
    }
    
    private void siftDown(int i){
        int minIdx = i;
        if(2 * i + 1 <= data.length -1 && data[i] > data[2 * i + 1]){
            minIdx = 2 * i + 1;
        }
        if(2 * i + 2 <= data.length -1 && data[minIdx] > data[2 * i + 2]){
            minIdx = 2 * i + 2;
        }
        if (minIdx != i){
            swaps.add(new Swap(i, minIdx));
            int temp = data[i];
            data[i] = data[minIdx];
            data[minIdx] = temp;
            siftDown(minIdx);
        }
        
    }
    
    
    private void generateSwaps() {
      swaps = new ArrayList<Swap>();
      for (int i = data.length/2 ; i >= 0 ; i-- ){
          siftDown(i);
          
      }
    }

    public void solve() throws IOException {
        in = new FastScanner();
        out = new PrintWriter(new BufferedOutputStream(System.out));
        readData();
        generateSwaps();
        writeResponse();
        out.close();
    }

    static class Swap {
        int index1;
        int index2;

        public Swap(int index1, int index2) {
            this.index1 = index1;
            this.index2 = index2;
        }
    }

    static class FastScanner {
        private BufferedReader reader;
        private StringTokenizer tokenizer;

        public FastScanner() {
            reader = new BufferedReader(new InputStreamReader(System.in));
            tokenizer = null;
        }

        public String next() throws IOException {
            while (tokenizer == null || !tokenizer.hasMoreTokens()) {
                tokenizer = new StringTokenizer(reader.readLine());
            }
            return tokenizer.nextToken();
        }

        public int nextInt() throws IOException {
            return Integer.parseInt(next());
        }
    }
}
