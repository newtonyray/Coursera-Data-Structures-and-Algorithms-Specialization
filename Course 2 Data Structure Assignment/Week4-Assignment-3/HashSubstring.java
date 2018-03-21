import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.*;
import java.util.StringTokenizer;

public class HashSubstring {

    private static FastScanner in;
    private static PrintWriter out;

    public static void main(String[] args) throws IOException {
        in = new FastScanner();
        out = new PrintWriter(new BufferedOutputStream(System.out));
        printOccurrences(getOccurrences(readInput()));
        out.close();
    }

    private static Data readInput() throws IOException {
        String pattern = in.next();
        String text = in.next();
        return new Data(pattern, text);
    }

    private static void printOccurrences(List<Integer> ans) throws IOException {
        for (Integer cur : ans) {
            out.print(cur);
            out.print(" ");
        }
    }

    // private static List<Integer> getOccurrences(Data input) {
        // String s = input.pattern, t = input.text;
        // int m = s.length(), n = t.length();
        // List<Integer> occurrences = new ArrayList<Integer>();
        // for (int i = 0; i + m <= n; ++i) {
	    // boolean equal = true;
	    // for (int j = 0; j < m; ++j) {
		// if (s.charAt(j) != t.charAt(i + j)) {
		     // equal = false;
 		    // break;
		// }
	    // }
            // if (equal)
                // occurrences.add(i);
	// }
        // return occurrences;
    // }
    
private static List<Integer> getOccurrences(Data input) {
        String s = input.pattern, t = input.text;
        int m = s.length(), n = t.length();
        List<Integer> occurrences = new ArrayList<Integer>();
        //preCompute hashFunc
        long p = 1000000007;
        
        int x = 13;
        //compute the hash for pattern
        long patternHash = 0;
        for (int i = m-1 ; i >= 0 ; i--){
            patternHash = (patternHash * x + s.charAt(i)) % p;
        }
        //System.out.println("pattern hash: " + patternHash);
        //compute and store all the hashes for text
        long[] hashes = new long[n-m+1];
        //compute the last string length hash in text, starting position is  n-m;
        long hash = 0;
        for (int i = n - 1 ; i >= n - m ; --i){
            hash = (hash * x + t.charAt(i)) % p;
        }
        hashes[n-m] = hash;
        //System.out.println("last hash: " + hashes[n-m]);
        long factor = 1;
        for (int i = 1; i <= m; i++) {
			factor = (factor * x) % p;
        }
        //System.out.println("factor: " + factor);
        for(int i = n-m-1 ; i >= 0 ; i--){
            long preHash = hashes[i+1] * x + t.charAt(i) - t.charAt(i + m) * factor;
            while( preHash < 0){ //this is the most important step!
                preHash += p;
            }
            hashes[i] = preHash % p;
        }
        //System.out.println(Arrays.toString(hashes));
        for(int i = 0 ; i <= n-m ; ++i){
            if(patternHash != hashes[i]){
                //when extracting substring, the second int para is exclusive, so +1
                continue;
            }
            if(t.substring(i,i+m).equals(s)){
                occurrences.add(i);
            }
            
        }
        return occurrences;
    }

    static class Data {
        String pattern;
        String text;
        public Data(String pattern, String text) {
            this.pattern = pattern;
            this.text = text;
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

